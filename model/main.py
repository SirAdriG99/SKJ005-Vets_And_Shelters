import socket
import psycopg2
import torch
from transformers import AutoTokenizer, AutoModelForSequenceClassification, Trainer, TrainingArguments

# CONSTANTS
CORE_PORT = 9876  # TODO: Set the correct one
DB_PORT = 1234  # TODO: Set the correct one
CORE_IP = "0.0.0.0"  # TODO: Pick the correct one.
DB_IP = "0.0.0.0"  # TODO: Pick the correct one.
SOCKET_DATA_SIZE = 1024
USER_DB = "user"
PASSWORD_DB = "patata"
NAME_DB = "skj005_vets_and_shelters"

# CONSTANTS Codes
MSG_STOP = "STOP"
MSG_QUERY_DB = "QUERY_DB"
MSG_PREDICTION = "PREDICTION"
MSG_TRAIN = "TRAIN"

# OTHER CONSTANTS
QUERY_GET_TRAINING_DATA = ""
DB_PARAMETERS = {
    "host": DB_IP,
    "port": DB_PORT,
    "user": USER_DB,
    "password": PASSWORD_DB,
    "database": NAME_DB
}
MODEL_NAME = ""
NUM_LABELS = 2
BATCH_SIZE = 10


def read_socket(socket_recv, data_size=SOCKET_DATA_SIZE):
    data = socket_recv.recv(data_size).decode('utf-8')
    print(f"Message received: {data}")
    return data


def write_socket(socket_send, message):
    socket_send.send(str(message).encode('utf-8'))


def create_sockets_connections():

    core_socket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    core_socket.bind((CORE_IP, CORE_PORT))
    core_socket.listen()

    db_socket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    db_socket.bind((DB_IP, DB_PORT))
    db_socket.listen()

    core_socket, addr = core_socket.accept()
    print(f"Connection from {addr} (core)")

    db = psycopg2.connect(**DB_PARAMETERS)
    db_cursor = db.cursor()

    return core_socket, db_cursor


def query_action(params):
    core_socket = params["core_socket"]
    db_cursor = params["db_cursor"]
    query = params["query"]

    try:
        db_cursor.execute(query)
        result = db_cursor.fetchall()
        write_socket(core_socket, result)
    except Exception as e:
        write_socket(core_socket, e)


def load_model():
    # TODO
    return None


def save_model(model):
    model.save_pretrained('./model')


def prediction_action(params):
    model = params["model"]
    core_socket = params["core_socket"]
    # TODO


def get_train_data():
    # TODO
    return list()


def get_valid_data():
    # TODO
    return list()


def train_model_action(params):
    # TODO: Check if this is correct.
    model = params["model"]
    core_socket = params["core_socket"]
    db_cursor = params["db_cursor"]

    device = torch.device("cuda" if torch.cuda.is_available() else "cpu")
    model = (model.to(device))
    train = get_train_data()
    valid = get_valid_data()
    
    logging_steps = len(train) // BATCH_SIZE
    training_args = TrainingArguments(output_dir="results",
                                      num_train_epochs=3,
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

    trainer = Trainer(model=model, args=training_args)
    trainer.train()

    results = trainer.evaluate()

    message = f"Training complete: {results}"
    write_socket(core_socket, message)


def hidden_switch(data_rec, params):
    core_socket = params["core_socket"]
    db_cursor = params["db_cursor"]
    model = params["model"]

    if data_rec == MSG_STOP:
        return None, None
    elif data_rec == MSG_QUERY_DB:
        return query_action, {"core_socket": core_socket, "db_cursor": db_cursor, "query": read_socket(core_socket)}
    elif data_rec == MSG_PREDICTION:
        return prediction_action, {"core_socket": core_socket, "model": model}
    elif data_rec == MSG_TRAIN:
        return train_model_action, {"core_socket": core_socket, "db_cursor": db_cursor, "model": model, }
    return None, None


def main():
    core_socket, db_cursor = create_sockets_connections()
    model = load_model()
    params = {
        "core_socket": core_socket,
        "db_cursor": db_cursor,
        "model": model
    }

    while True:
        data_rec = read_socket(core_socket)
        action, params = hidden_switch(data_rec, params)

        if action is None:
            break
        else:
            action(params)

    if core_socket:
        core_socket.close()
    if db_cursor:
        db_cursor.close()


if __name__ == '__main__':
    main()

