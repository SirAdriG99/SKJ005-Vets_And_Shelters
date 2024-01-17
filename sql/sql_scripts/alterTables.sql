ALTER TABLE animal ADD CONSTRAINT fk_animal_sex FOREIGN KEY ( sex_id ) REFERENCES sex( id );

ALTER TABLE animal ADD CONSTRAINT fk_animal_breed FOREIGN KEY ( breed_id ) REFERENCES breed( id );

ALTER TABLE animal ADD CONSTRAINT fk_animal_procedence_type FOREIGN KEY ( procedence_type_id ) REFERENCES procedence_type( id );

ALTER TABLE animal ADD CONSTRAINT fk_animal_animal_status FOREIGN KEY ( animal_status_id ) REFERENCES animal_status( id );

ALTER TABLE animal_photos ADD CONSTRAINT fk_animal_photis_animal FOREIGN KEY ( animal_id ) REFERENCES animal( id );

ALTER TABLE animal_status_hist ADD CONSTRAINT fk_animal_status_hist_animal FOREIGN KEY ( animal_id ) REFERENCES animal( id );

ALTER TABLE animal_status_hist ADD CONSTRAINT fk_animal_status_hist FOREIGN KEY ( status_id ) REFERENCES animal_status( id );

ALTER TABLE appointment ADD CONSTRAINT fk_appointment_customer FOREIGN KEY ( customer_id ) REFERENCES customer( id );

ALTER TABLE appointment ADD CONSTRAINT fk_appointment_animal FOREIGN KEY ( animal_id ) REFERENCES animal( id );

ALTER TABLE customer_pwd ADD CONSTRAINT fk_customer_pwd_customer FOREIGN KEY ( customer_id ) REFERENCES customer( id );