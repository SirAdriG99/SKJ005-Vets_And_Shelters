import socket
import psycopg2
from transformers import AutoTokenizer, BertForSequenceClassification, Trainer, TrainingArguments
from http.server import BaseHTTPRequestHandler, HTTPServer
import torch
from sklearn.model_selection import train_test_split
import pandas as pd
import json

# CONSTANTS
BACK_PORT = 8080        # TODO: Set the correct one
FRONT_PORT = 8090
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

DB_PARAMETERS = {
    "host": DB_IP,
    "port": DB_PORT,
    "user": USER_DB,
    "password": PASSWORD_DB,
    "database": NAME_DB
}
MODEL_NAME = "distilbert-base-uncased"
MODEL_PATH = "./model-volume"
NUM_LABELS = 665  # It's the number of animals in the database.
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
        tokenizer = AutoTokenizer.from_pretrained(MODEL_PATH)
    except OSError:
        tokenizer = AutoTokenizer.from_pretrained(MODEL_NAME)
        model = BertForSequenceClassification.from_pretrained(MODEL_NAME, num_labels=NUM_LABELS)

    return model, tokenizer


# Function to encapsulate the model saving process
def save_the_model(model, tokenizer):
    model.save_pretrained(MODEL_PATH)
    tokenizer.save_pretrained(MODEL_PATH)


def get_suggestion(model, tokenizer, input_suggestion):
    device = torch.device("cuda" if torch.cuda.is_available() else "cpu")

    def tokenize_data(data, max_length=512):
        input_ids = []
        attention_masks = []

        for index, row in data.iterrows():
            text = f"Color: {row['color']}. Space Need: {row['space_need']}. Activity Need: {row['activity_need']}. Time Dedication Need: {row['time_dedication_need']}"
            encoded_text = tokenizer(text, padding='max_length', truncation=True, return_tensors='pt',
                                     max_length=max_length)

            input_ids.append(encoded_text['input_ids'].to(device))
            attention_masks.append(encoded_text['attention_mask'].to(device))

        return torch.cat(input_ids, dim=0), torch.cat(attention_masks, dim=0)

    input_ids, attention_masks = tokenize_data(input_suggestion)
    input_ids = input_ids.to(device)
    attention_masks = attention_masks.to(device)

    model = model.to(device)
    model.eval()

    with torch.no_grad():
        outputs = model(input_ids, attention_mask=attention_masks)

    logits = outputs.logits
    predictions = torch.argmax(logits, dim=1).cpu().numpy()
    return predictions


# Function where the suggestion is done
def suggestion_action(socket_rw, model, tokenizer):
    input_suggestion = read_socket(socket_rw)
    message = get_suggestion(model, tokenizer, input_suggestion)
    write_socket(socket_rw, message)


# Function to encapsulate the query to get the training data
def get_train_data(db_cursor):
    db_cursor.execute(QUERY_GET_TRAINING_DATA)

    # Fetch all rows and convert to a list of dictionaries
    rows = db_cursor.fetchall()
    result = []
    for row in rows:
        d = {}
        for i, col in enumerate(db_cursor.description):
            d[col[0]] = row[i]
            result.append(d)

    # Convert the list of dictionaries to JSON and print it
    json_result = json.dumps(result)

    df = pd.DataFrame(json_result)
    X_test, X_temp = train_test_split(df, test_size=0.4, random_state=42)
    X_train, X_val = train_test_split(X_temp, test_size=0.5, random_state=42)

    return X_train, X_test, X_val

def train_model(model, tokenizer, test, train, validation):
    device = torch.device("cuda" if torch.cuda.is_available() else "cpu")

    def tokenize_data(data, max_length=512):
        input_ids = []
        attention_masks = []

        for index, row in data.iterrows():
            text = f"Color: {row['color']}. Space Need: {row['space_need']}. Activity Need: {row['activity_need']}. Time Dedication Need: {row['time_dedication_need']}"
            encoded_text = tokenizer(text, padding='max_length', truncation=True, return_tensors='pt',
                                     max_length=max_length)
            input_ids.append(encoded_text['input_ids'])
            attention_masks.append(encoded_text['attention_mask'])

        return torch.cat(input_ids, dim=0), torch.cat(attention_masks, dim=0)

    if len(train) != 0 and len(validation) != 0:

        train_input_ids, train_attention_masks = tokenize_data(train)
        test_input_ids, test_attention_masks = tokenize_data(test)

        train_input_ids = train_input_ids.to(device)
        train_attention_masks = train_attention_masks.to(device)
        test_input_ids = test_input_ids.to(device)
        test_attention_masks = test_attention_masks.to(device)

        train_labels = torch.tensor(train['dangerous_race'].astype(int).values)
        test_labels = torch.tensor(test['dangerous_race'].astype(int).values)

        train_dataset = TensorDataset(train_input_ids, train_attention_masks, train_labels)
        test_dataset = TensorDataset(test_input_ids, test_attention_masks, test_labels)

        train_dataloader = DataLoader(train_dataset, batch_size=BATCH_SIZE, shuffle=True)
        test_dataloader = DataLoader(test_dataset, batch_size=BATCH_SIZE, shuffle=False)

        model = model.to(device)
        optimizer = torch.optim.AdamW(model.parameters(), lr=2e-5)

        for epoch in range(NUM_EPOCHS):
            model.train()
            total_loss = 0
            for batch in tqdm(train_dataloader, desc=f'Epoch {epoch + 1}/{NUM_EPOCHS}', unit='batch'):
                input_ids, attention_mask, labels = batch
                input_ids, attention_mask, labels = input_ids.to(device), attention_mask.to(device), labels.to(device)
                optimizer.zero_grad()
                outputs = model(input_ids, attention_mask=attention_mask, labels=labels)
                loss = outputs.loss
                loss.backward()
                optimizer.step()
                total_loss += loss.item()

        predictions = []
        true_labels = []

        for batch in test_dataloader:
            input_ids, attention_mask, labels = batch
            with torch.no_grad():
                outputs = model(input_ids, attention_mask=attention_mask)
            logits = outputs.logits
            predictions.extend(logits.argmax(dim=1).cpu().numpy())
            true_labels.extend(labels.cpu().numpy())

        accuracy = accuracy_score(true_labels, predictions)

        message = f"Training results: {accuracy}"
        save_the_model(model, tokenizer)
        # Liberamos memoria de la gpu
        if device == "cuda":
            torch.cuda.empty_cache()

    else:
        message = "There is no: "
        message += "\n\t-> training data" if len(train) == 0 else ""
        message += "\n\t-> validation data" if len(validation) == 0 else ""
    return message

# Function where the training is done
def train_model_action(back_socket, db_cursor, model, tokenizer):
    test, train, val = get_train_data(db_cursor)
    # validation = get_validation_data(db_cursor)
    training_result = train_model(model, tokenizer, test, train, val)
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
