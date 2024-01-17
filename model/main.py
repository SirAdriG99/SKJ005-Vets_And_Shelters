import socket
import psycopg2
import torch
from transformers import BertTokenizer, BertForSequenceClassification, Trainer, TrainingArguments
from datasets import Dataset

# CONSTANTS
CORE_PORT = 9876        # TODO: Set the correct one
DB_PORT = 5432          # TODO: Set the correct one
CORE_IP = "0.0.0.0"
DB_IP = "0.0.0.0"
SOCKET_DATA_SIZE = 1024
USER_DB = "user"
PASSWORD_DB = "patata"
NAME_DB = "skj005_vets_and_shelters"

# CONSTANTS: Codes messages.
MSG_STOP = "STOP"
MSG_QUERY_DB = "QUERY_DB"
MSG_PREDICTION = "PREDICTION"
MSG_TRAIN = "TRAIN"

# CONSTANTS: Model-related.
QUERY_GET_TRAINING_DATA = ""    # TODO: Pick the correct one.
QUERY_GET_VALIDATION_DATA = ""  # TODO: Pick the correct one.

DB_PARAMETERS = {
    "host": DB_IP,
    "port": DB_PORT,
    "user": USER_DB,
    "password": PASSWORD_DB,
    "database": NAME_DB
}
MODEL_NAME = "nlptown/bert-base-multilingual-uncased-sentiment"
MODEL_PATH = "./model"
NUM_LABELS = 2
BATCH_SIZE = 10
NUM_EPOCHS = 2
OUTPUT_DIR = "."

# Function to encapsulate the reception of messages over a socket.
def read_socket(socket_recv, data_size=SOCKET_DATA_SIZE):
    data = socket_recv.recv(data_size).decode('utf-8')
    return data


# Function to encapsulate the sending of messages over a socket.
def write_socket(socket_send, message):
    socket_send.send(str(message).encode('utf-8'))


# Function to create the socket and the database connection
def create_socket_and_db_connections():

    core_socket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    core_socket.bind((CORE_IP, CORE_PORT))
    core_socket.listen()
    core_socket, addr = core_socket.accept()
    print(f"Connection from {addr} (core)")

    # db_socket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    # db_socket.bind((DB_IP, DB_PORT))
    # db_socket.listen()

    db = psycopg2.connect(**DB_PARAMETERS)
    db_cursor = db.cursor()

    return core_socket, db_cursor


# Function to encapsulate the sending of a query and getting the result to the database.
def do_query(db_cursor, query):
    db_cursor.execute(query)
    result = db_cursor.fetchall()
    return result


# Function to receiving a query from a socket, making the query to the database and sending the result to that socket.
def query_action(core_socket, db_cursor, query):
    try:
        result = do_query(db_cursor, query)
        write_socket(core_socket, result)
    except Exception as e:
        write_socket(core_socket, e)


# Function to encapsulate the model loading process
def load_the_model():

    # If there is no model saved, it load the original one.
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


# Function where the classification is done
def classification_action(core_socket, model, tokenizer):
    
    input_classification = read_socket(core_socket)    
    if input_classification is None:
        result = "Error: No data received"
    else:
        tokens = tokenizer(input_classification)
        result = str(model.predict(input_classification))

        with torch.no_grad():
            logits = model(**tokens).logits
        # Get the predicted class (assuming binary classification)
        result = str(torch.argmax(logits, dim=1).item())
        
    message = f"Input received: ->'{input_classification}'<-.\nResult: {result}"
    write_socket(core_socket, message)


# Function to encapsulate the query to get the training data
def get_train_data(db_cursor):
    train_data = do_query(db_cursor, QUERY_GET_TRAINING_DATA)

    return train_data


# Function to encapsulate the query to get the validation data
def get_validation_data(db_cursor):
    validation_data = do_query(db_cursor, QUERY_GET_VALIDATION_DATA)
    return validation_data


# Function where the training is done
def train_model_action(core_socket, db_cursor, model, tokenizer):
    def tokenize(batch):
        return tokenizer(batch["text"], padding=True, truncation=True, max_length=512)

    device = torch.device("cuda" if torch.cuda.is_available() else "cpu")
    model = model.to(device)
    train = get_train_data(db_cursor)
    validation = get_validation_data(db_cursor)

    train_encoded = Dataset.from_pandas(train).map(tokenize, batched=True, batch_size=None)
    valid_encoded = Dataset.from_pandas(validation).map(tokenize, batched=True, batch_size=None)

    train_encoded.set_format("torch", columns=["input_ids", "attention_mask", "label"])
    valid_encoded.set_format("torch", columns=["input_ids", "attention_mask", "label"])

    if len(train) != 0 and len(validation) != 0:
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
            eval_dataset=validation,
        )
        trainer.train()

        results = trainer.evaluate()
        message = f"Training results: {results}"

        save_the_model(model, tokenizer)
    else:
        message = "There is no: "
        message += "\n\t-> training data" if len(train) == 0 else ""
        message += "\n\t-> validation data" if len(validation) == 0 else ""
    write_socket(core_socket, message)


# Function to hide the if-else statements. The parameters for the action are prepared here.
def hidden_switch(data_rec, params):
    core_socket = params["core_socket"]
    db_cursor = params["db_cursor"]
    model = params["model"]
    tokenizer = params["tokenizer"]

    pseudo_switch = {
        MSG_STOP:           True,
        MSG_QUERY_DB:       query_action(core_socket, db_cursor, read_socket(core_socket)),
        MSG_PREDICTION:     classification_action(core_socket, model, tokenizer),
        MSG_TRAIN:          train_model_action(core_socket, db_cursor, model, tokenizer)
    }
    # not True = not None = False
    return not pseudo_switch.get(data_rec)


# The main function
def main():
    core_socket, db_cursor = create_socket_and_db_connections()
    model, tokenizer = load_the_model()
    params = {
        "core_socket": core_socket,
        "db_cursor": db_cursor,
        "model": model,
        "tokenizer": tokenizer
    }

    not_stop = True
    while not_stop:
        data_rec = read_socket(core_socket)
        not_stop = hidden_switch(data_rec, params)

    if core_socket:
        core_socket.close()
    if db_cursor:
        db_cursor.close()


# The place where the program starts.
if __name__ == '__main__':
    main()
