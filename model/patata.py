import torch
from transformers import BertTokenizer, BertForSequenceClassification, Trainer, TrainingArguments
from sklearn.model_selection import train_test_split
from datasets import Dataset
import pandas as pd
import json

# CONSTANTS: Model-related.
QUERY_GET_TRAINING_DATA = """
                          SELECT a.id, a.color, b.space_need, b.activity_need, b.dangerous_race, b.time_dedication_need
                          FROM animal AS a
                          INNER JOIN breed AS b ON a.breed_id = b.id                           
                          """
QUERY_GET_VALIDATION_DATA = ""

MODEL_NAME = "distilbert-base-uncased"
MODEL_PATH = "./model"
NUM_LABELS = 665  # Realmente e
BATCH_SIZE = 10
NUM_EPOCHS = 2
OUTPUT_DIR = "."
SUGGESTION_JSON_KEY = ["color", "space_need", "activity_need", "dangerous_race", "time_dedication_need"]


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


# Function to encapsulate the query to get the training data
def get_data(file_path="./test.txt"):
    with open(file_path, 'r') as file:
        df = pd.DataFrame(json.load(file))
        X_train, X_temp =train_test_split(df, test_size=0.4, random_state=42, stratify=df_train['id'])
        X_test, X_val = train_test_split(df, test_size=0.5, random_state=42, stratify=df_train['id'])

        X_train = X_train.to_csv('train_data.csv', index=False)
        X_test = X_test.to_csv('test_data.csv', index=False)
        X_val = X_val.to_csv('validation_data.csv', index=False)

        return X_train, X_test, X_val


def train_model(model, tokenizer, test, train, validation):
    def tokenize(batch):
        return tokenizer(batch["text"], padding=True, truncation=True, max_length=512)

    device = torch.device("cuda" if torch.cuda.is_available() else "cpu")
    model = model.to(device)

    test_encoded = Dataset.from_pandas(test).map(tokenize, batched=True, batch_size=None)
    train_encoded = Dataset.from_pandas(train).map(tokenize, batched=True, batch_size=None)
    valid_encoded = Dataset.from_pandas(validation).map(tokenize, batched=True, batch_size=None)

    test_encoded.set_format("torch", columns=["input_ids", "attention_mask", "label"])
    train_encoded.set_format("torch", columns=["input_ids", "attention_mask", "label"])
    valid_encoded.set_format("torch", columns=["input_ids", "attention_mask", "label"])

    if len(train) != 0 and len(validation) != 0:
        logging_steps = len(train) // BATCH_SIZE
        training_args = TrainingArguments(
            output_dir="OUTPUT_DIR",
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
        trainer = Trainer(
            model=model,
            args=training_args,
            train_dataset=train,
            eval_dataset=validation,
        )
        trainer.train()

        results = trainer.evaluate()
        message = f"Training results: {results}"
        test_results = trainer.evaluate(test_encoded)
        message += f"Test results: {test_results}"

        save_the_model(model, tokenizer)
    else:
        message = "There is no: "
        message += "\n\t-> training data" if len(train) == 0 else ""
        message += "\n\t-> validation data" if len(validation) == 0 else ""
    return message


# The main function
def main():
    print("starting\n")
    print("loading model and tokenizer")
    model, tokenizer = load_the_model()
    print("loading data")
    test, train, validation = get_data()
    print("trainig the model")
    result = train_model(model, tokenizer, test, train, validation)
    print(result)


# The place where the program starts.
if __name__ == '__main__':
    main()
