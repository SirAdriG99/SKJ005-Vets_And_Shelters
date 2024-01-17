CREATE DATABASE skj005_vets_and_shelters;
\connect skj005_vets_and_shelters
-- CREATE SCHEMA skj005_vets_and_shelters;


CREATE  TABLE animal_status ( 
	id                   integer  NOT NULL  ,
	name                 varchar(100)  NOT NULL  ,
	CONSTRAINT pk_animal_status PRIMARY KEY ( id )
 );

CREATE  TABLE breed ( 
	id                   serial  NOT NULL  ,
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
	id                   serial  NOT NULL  ,
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
	id                   serial  NOT NULL  ,
	customer_id          integer  NOT NULL  ,
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
	id                   serial  NOT NULL  ,
	sex_id               integer  NOT NULL  ,
	breed_id             integer  NOT NULL  ,
	procedence_type_id   integer  NOT NULL  ,
	name                 varchar(100)    ,
	color                varchar(100)  NOT NULL  ,
	CONSTRAINT pk_animal PRIMARY KEY ( id )
 );

CREATE  TABLE animal_photos ( 
	id                   serial  NOT NULL  ,
	animal_id            integer  NOT NULL  ,
	url                  varchar(255)  NOT NULL  ,
	CONSTRAINT pk_animal_photis PRIMARY KEY ( id )
 );

CREATE  TABLE animal_status_hist ( 
	animal_id            serial  NOT NULL  ,
	status_id            integer  NOT NULL  ,
	date_from            date  NOT NULL  ,
	date_to              date    ,
	CONSTRAINT pk_animal_status_hist PRIMARY KEY ( animal_id, date_from )
 );

CREATE  TABLE appointment ( 
	customer_id          integer  NOT NULL  ,
	appointment_date     timestamp  NOT NULL  ,
	animal_id            integer    ,
	CONSTRAINT pk_appointment PRIMARY KEY ( customer_id, appointment_date )
 );