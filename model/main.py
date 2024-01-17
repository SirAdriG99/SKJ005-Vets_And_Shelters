import socket
import psycopg2
import torch
from transformers import BertTokenizer, BertForSequenceClassification, Trainer, TrainingArguments
from datasets import Dataset
from http.server import BaseHTTPRequestHandler, HTTPServer
import json

# CONSTANTS
BACK_PORT = 9876        # TODO: Set the correct one
FRONT_PORT = 1425
DB_PORT = 5432
BACK_IP = "0.0.0.0"
FRONT_IP = "0.0.0.0"
DB_IP = "0.0.0.0"
SOCKET_DATA_SIZE = 1024

USER_DB = "user"
PASSWORD_DB = "patata"
NAME_DB = "skj005_vets_and_shelters"

# CONSTANTS: Codes messages.
MSG_STOP = "STOP"
MSG_QUERY_DB = "QUERY_DB"
MSG_SUGGESTION = "SUGGESTION"
MSG_TRAIN = "TRAIN"

# CONSTANTS: Model-related.
QUERY_GET_TRAINING_DATA = """
                          SELECT a.id, a.color, b.space_need, b.activity_need, b.dangerous_race, b.time_dedication_need
                          FROM animal AS a
                          INNER JOIN breed AS b ON a.breed_id = b.id                           
                          """
QUERY_GET_VALIDATION_DATA = ""

DB_PARAMETERS = {
    "host": DB_IP,
    "port": DB_PORT,
    "user": USER_DB,
    "password": PASSWORD_DB,
    "database": NAME_DB
}
MODEL_NAME = "distilbert-base-uncased"
MODEL_PATH = "./model"
NUM_LABELS = 2
BATCH_SIZE = 10
NUM_EPOCHS = 2
OUTPUT_DIR = "."
SUGGESTION_JSON_KEY = ["color", "space_need", "activity_need", "dangerous_race", "time_dedication_need"]


# Function to encapsulate the reception of messages over a socket.
def read_socket(socket_recv, data_size=SOCKET_DATA_SIZE):
    data = socket_recv.recv(data_size).decode('utf-8')
    return data


# Function to encapsulate the sending of messages over a socket.
def write_socket(socket_send, message):
    socket_send.send(str(message).encode('utf-8'))


# Function to create the socket and the database connection
def create_socket_and_db_connections():

    back_socket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    back_socket.bind((BACK_IP, BACK_PORT))
    back_socket.listen()
    back_socket, addr = back_socket.accept()
    print(f"Connection from {addr} (back)")

    db = psycopg2.connect(**DB_PARAMETERS)
    db_cursor = db.cursor()

    return back_socket, db_cursor


# Function to encapsulate the sending of a query and getting the result to the database.
def do_query(db_cursor, query):
    db_cursor.execute(query)
    result = db_cursor.fetchall()
    return result


# Function to receiving a query from a socket, making the query to the database and sending the result to that socket.
def query_action(back_socket, db_cursor, query):
    try:
        result = do_query(db_cursor, query)
        write_socket(back_socket, result)
    except Exception as e:
        write_socket(back_socket, e)


# Function to encapsulate the model loading process
def load_the_model():

    # If there is no model saved, it loads the original one.
    try:
        model = BertForSequenceClassification.from_pretrained(MODEL_PATH)
        tokenizer = BertTokenizer.from_pretrained(MODEL_PATH)
    except OSError:
        tokenizer = BertTokenizer.from_pretrained(MODEL_NAME)
        model = BertForSequenceClassification.from_pretrained(MODEL_NAME, num_labels=NUM_LABELS)

    return model, tokenizer


# Function to encapsulate the model saving process
def save_the_model(model, tokenizer):
    model.save_pretrained(MODEL_PATH)
    tokenizer.save_pretrained(MODEL_PATH)


def get_suggestion(model, tokenizer, input_suggestion):
    # input_suggestion = read_socket(back_socket)
    if input_suggestion is None:
        result = "Error: No data received"
    else:
        tokens = tokenizer(input_suggestion)
        with torch.no_grad():
            logits = model(**tokens).logits
        result = str(torch.argmax(logits, dim=1).item())

    message = f"Input received: ->'{input_suggestion}'<-.\nResult: {result}"
    return message


# Function where the suggestion is done
def suggestion_action(socket_rw, model, tokenizer):
    input_suggestion = read_socket(socket_rw)
    message = get_suggestion(model, tokenizer, input_suggestion)
    write_socket(socket_rw, message)


# Function to encapsulate the query to get the training data
def get_train_data(db_cursor):
    train_data = do_query(db_cursor, QUERY_GET_TRAINING_DATA)

    return train_data


# Function to encapsulate the query to get the validation data
def get_validation_data(db_cursor):
    validation_data = do_query(db_cursor, QUERY_GET_VALIDATION_DATA)
    return validation_data

def train_model(model, tokenizer, train, validation):
    def tokenize(batch):
        return tokenizer(batch["text"], padding=True, truncation=True, max_length=512)

    device = torch.device("cuda" if torch.cuda.is_available() else "cpu")
    model = model.to(device)

    train_encoded = Dataset.from_pandas(train).map(tokenize, batched=True, batch_size=None)
    # valid_encoded = Dataset.from_pandas(validation).map(tokenize, batched=True, batch_size=None)

    train_encoded.set_format("torch", columns=["input_ids", "attention_mask", "label"])
    # valid_encoded.set_format("torch", columns=["input_ids", "attention_mask", "label"])

    if len(train) != 0:  # and len(validation) != 0:
        logging_steps = len(train) // BATCH_SIZE
        training_args = TrainingArguments(
            output_dir="OUTPUT_DIR",
            num_train_epochs=3,  # No pongo 10 porque a la hora o así se me desconecta.
            learning_rate=2e-5,
            per_device_train_batch_size=BATCH_SIZE,
            per_device_eval_batch_size=BATCH_SIZE,
            load_best_model_at_end=True,
            metric_for_best_model="f1",
            weight_decay=0.01,
            evaluation_strategy="epoch",
            logging_steps=logging_steps,
            fp16=True,
            save_strategy="epoch",
            disable_tqdm=False)
        trainer = Trainer(
            model=model,
            args=training_args,
            train_dataset=train,
            # eval_dataset=validation,
        )
        trainer.train()

        results = trainer.evaluate()
        message = f"Training results: {results}"

        save_the_model(model, tokenizer)
    else:
        message = "There is no: "
        message += "\n\t-> training data" if len(train) == 0 else ""
        # message += "\n\t-> validation data" if len(validation) == 0 else ""
    return message

# Function where the training is done
def train_model_action(back_socket, db_cursor, model, tokenizer):
    train = get_train_data(db_cursor)
    # validation = get_validation_data(db_cursor)
    validation = list()
    training_result = train_model(model, tokenizer, train, validation)
    write_socket(back_socket, training_result)


def prepare_data_to_model(json_received):
    json_dict = json.loads(json_received)
    data_prepared = [str(json_dict[key]) for key in SUGGESTION_JSON_KEY]

    return data_prepared


# Function to hide the if-else statements. The parameters for the action are prepared here.
def hidden_switch(data_rec, params):
    back_socket = params["back_socket"]
    db_cursor = params["db_cursor"]
    model = params["model"]
    tokenizer = params["tokenizer"]

    pseudo_switch = {
        MSG_STOP:           True,
        MSG_QUERY_DB:       query_action(back_socket, db_cursor, read_socket(back_socket)),
        MSG_SUGGESTION:     suggestion_action(back_socket, model, tokenizer),
        MSG_TRAIN:          train_model_action(back_socket, db_cursor, model, tokenizer)
    }
    # not True = not None = False
    return not pseudo_switch.get(data_rec)

def backed_thread(params):
    back_socket = params["back_socket"]

    not_stop = True
    while not_stop:
        data_rec = read_socket(back_socket)
        not_stop = hidden_switch(data_rec, params)


def makeHandlerClass(model, tokenizer):
    class RequestHandler(BaseHTTPRequestHandler):

        model = None
        tokenizer = None

        def __init__(self, *args, **kwargs):
            self.model = model
            self.tokenizer = tokenizer

            super(RequestHandler, self).__init__(*args, **kwargs)

        def _send_response(self, status, message):
            self.send_response(status)
            self.send_header('Content-type', 'application/json')
            self.end_headers()
            self.wfile.write(json.dumps(message).encode('utf-8'))

        def do_POST(self):
            try:
                # Get the length of the incoming data
                content_length = int(self.headers['Content-Length'])
                # Read and decode the JSON data
                raw_data = self.rfile.read(content_length)
                json_data = json.loads(raw_data.decode('utf-8'))

                data_prepared = prepare_data_to_model(json_data)
                result = get_suggestion(self.model, self.tokenizer, data_prepared)

                # Send a JSON response
                self._send_response(200, result)
            except Exception as e:
                # Handle exceptions, if any
                error = {'error': str(e)}
                self._send_response(500, error)
    return RequestHandler


def frontend_thread(params):
    model = params["model"]
    tokenizer = params["tokenizer"]

    front_end_address = (FRONT_IP, FRONT_PORT)
    handler = makeHandlerClass(model, tokenizer)
    httpd = HTTPServer(front_end_address, handler)

    httpd.serve_forever()


# Por temas de tiempo, solo hacemos un hilo.
def start_threads(params):
    frontend_thread(params)


# The main function
def main():
    back_socket, db_cursor = create_socket_and_db_connections()
    model, tokenizer = load_the_model()
    params = {
        "back_socket": back_socket,
        "db_cursor": db_cursor,
        "model": model,
        "tokenizer": tokenizer
    }

    start_threads(params)

    if back_socket:
        back_socket.close()
    if db_cursor:
        db_cursor.close()


# The place where the program starts.
if __name__ == '__main__':
    main()
