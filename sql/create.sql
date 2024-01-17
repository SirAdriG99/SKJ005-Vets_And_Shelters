CREATE SCHEMA IF NOT EXISTS skj005_vets_and_shelters;

CREATE  TABLE animal_status ( 
	id                   integer  NOT NULL  ,
	name                 varchar(100)  NOT NULL  ,
	CONSTRAINT pk_animal_status PRIMARY KEY ( id )
 );

CREATE  TABLE breed ( 
	id                   bigserial  NOT NULL  ,
	is_exotic            boolean  NOT NULL  ,
	name                 varchar(100)  NOT NULL  ,
	space_need           varchar(100)  NOT NULL  ,
	activity_need        varchar(100)  NOT NULL  ,
	alimentation_need    varchar(100)  NOT NULL  ,
	dangerous_race       boolean  NOT NULL  ,
	time_dedication_need varchar(100)  NOT NULL  ,
	CONSTRAINT pk_race PRIMARY KEY ( id )
 );

CREATE  TABLE customer ( 
	id                   bigserial  NOT NULL  ,
	doc_number           varchar(100)  NOT NULL  ,
	name                 varchar(100)  NOT NULL  ,
	surname              varchar(100)  NOT NULL  ,
	user_alias           varchar(100)  NOT NULL  ,
	date_birth           date  NOT NULL  ,
	banned               boolean DEFAULT FALSE NOT NULL  ,
	email                varchar(255)  NOT NULL  ,
	phone1               varchar(20)  NOT NULL  ,
	phone2               varchar(20)    ,
	address              varchar(100)  NOT NULL  ,
	CONSTRAINT pk_customer PRIMARY KEY ( id ),
	CONSTRAINT unq_customer UNIQUE ( doc_number ) 
 );

CREATE  TABLE customer_pwd ( 
	id                   bigserial  NOT NULL  ,
	customer_id          bigint  NOT NULL  ,
	pwd                  varchar(255)  NOT NULL  ,
	CONSTRAINT pk_customer_pwd PRIMARY KEY ( id )
 );

CREATE  TABLE procedence_type ( 
	id                   integer  NOT NULL  ,
	name                 varchar(100)  NOT NULL  ,
	CONSTRAINT pk_procedence_type PRIMARY KEY ( id )
 );

CREATE  TABLE sex ( 
	id                   integer  NOT NULL  ,
	name                 varchar(100)  NOT NULL  ,
	CONSTRAINT pk_sex PRIMARY KEY ( id )
 );

CREATE  TABLE animal ( 
	id                   bigserial  NOT NULL  ,
	sex_id               integer  NOT NULL  ,
	breed_id             bigint  NOT NULL  ,
	procedence_type_id   integer  NOT NULL  ,
	name                 varchar(100)    ,
	color                varchar(100)  NOT NULL  ,
	CONSTRAINT pk_animal PRIMARY KEY ( id )
 );

CREATE  TABLE animal_photos ( 
	id                   bigserial  NOT NULL  ,
	animal_id            bigint  NOT NULL  ,
	url                  varchar(255)  NOT NULL  ,
	CONSTRAINT pk_animal_photis PRIMARY KEY ( id )
 );

CREATE  TABLE animal_status_hist ( 
	animal_id            bigint  NOT NULL  ,
	status_id            integer  NOT NULL  ,
	date_from            date  NOT NULL  ,
	date_to              date    ,
	CONSTRAINT pk_animal_status_hist PRIMARY KEY ( animal_id, date_from )
 );

CREATE  TABLE appointment ( 
	customer_id          bigint  NOT NULL  ,
	appointment_date     timestamp  NOT NULL  ,
	animal_id            bigint    ,
	CONSTRAINT pk_appointment PRIMARY KEY ( customer_id, appointment_date )
 );

ALTER TABLE animal ADD CONSTRAINT fk_animal_sex FOREIGN KEY ( sex_id ) REFERENCES sex( id );

ALTER TABLE animal ADD CONSTRAINT fk_animal_breed FOREIGN KEY ( breed_id ) REFERENCES breed( id );

ALTER TABLE animal ADD CONSTRAINT fk_animal_procedence_type FOREIGN KEY ( procedence_type_id ) REFERENCES procedence_type( id );

ALTER TABLE animal_photos ADD CONSTRAINT fk_animal_photis_animal FOREIGN KEY ( animal_id ) REFERENCES animal( id );

ALTER TABLE animal_status_hist ADD CONSTRAINT fk_animal_status_hist_animal FOREIGN KEY ( animal_id ) REFERENCES animal( id );

ALTER TABLE animal_status_hist ADD CONSTRAINT fk_animal_status_hist FOREIGN KEY ( status_id ) REFERENCES animal_status( id );

ALTER TABLE appointment ADD CONSTRAINT fk_appointment_customer FOREIGN KEY ( customer_id ) REFERENCES customer( id );

ALTER TABLE appointment ADD CONSTRAINT fk_appointment_animal FOREIGN KEY ( animal_id ) REFERENCES animal( id );

ALTER TABLE customer_pwd ADD CONSTRAINT fk_customer_pwd_customer FOREIGN KEY ( customer_id ) REFERENCES customer( id );

COMMENT ON COLUMN breed.space_need IS 'define data type after seeing more data';

COMMENT ON COLUMN breed.activity_need IS 'define data type after seeing more data';

COMMENT ON COLUMN breed.alimentation_need IS 'define data type after seeing more data';

COMMENT ON COLUMN breed.dangerous_race IS 'defines if this race is catalogued as a dangerous race or not';

COMMENT ON COLUMN breed.time_dedication_need IS 'define data type when seeing more data';

