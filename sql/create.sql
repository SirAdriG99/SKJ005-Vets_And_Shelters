CREATE DATABASE IF NOT EXISTS skj005_vets_and_shelters;
\connect skj005_vets_and_shelters;
CREATE SCHEMA IF NOT EXISTS skj005_vets_and_shelters;
GRANT ALL PRIVILEGES ON DATABASE skj005_vets_and_shelters TO "user";

CREATE  TABLE skj005_vets_and_shelters.animal_status ( 
	id                   integer  NOT NULL GENERATED BY DEFAULT AS IDENTITY  ,
	name                 varchar(100)  NOT NULL  ,
	CONSTRAINT pk_animal_status PRIMARY KEY ( id )
 );

CREATE  TABLE skj005_vets_and_shelters.customer ( 
	id                   bigint  NOT NULL GENERATED BY DEFAULT AS IDENTITY  ,
	doc_type_id          integer  NOT NULL  ,
	doc_number           varchar(100)  NOT NULL  ,
	name                 varchar(100)  NOT NULL  ,
	surname              varchar(100)  NOT NULL  ,
	user_alias           varchar(100)  NOT NULL  ,
	date_birth           date  NOT NULL  ,
	banned               boolean DEFAULT FALSE NOT NULL  ,
	email                varchar(255)  NOT NULL  ,
	phone1               varchar(20)  NOT NULL  ,
	phone2               varchar(20)    ,
	address              bigint  NOT NULL  ,
	CONSTRAINT pk_customer PRIMARY KEY ( id ),
	CONSTRAINT unq_customer UNIQUE ( doc_number ) 
 );

CREATE  TABLE skj005_vets_and_shelters.customer_pwd ( 
	id                   bigint  NOT NULL GENERATED BY DEFAULT AS IDENTITY  ,
	customer_id          bigint  NOT NULL  ,
	pwd                  varchar(255)  NOT NULL  ,
	CONSTRAINT pk_customer_pwd PRIMARY KEY ( id )
 );

CREATE  TABLE skj005_vets_and_shelters.procedence_type ( 
	id                   integer  NOT NULL GENERATED BY DEFAULT AS IDENTITY  ,
	name                 varchar(100)  NOT NULL  ,
	CONSTRAINT pk_procedence_type PRIMARY KEY ( id )
 );

CREATE  TABLE skj005_vets_and_shelters.sex ( 
	id                   integer  NOT NULL GENERATED BY DEFAULT AS IDENTITY  ,
	name                 varchar(100)  NOT NULL  ,
	CONSTRAINT pk_sex PRIMARY KEY ( id )
 );

CREATE  TABLE skj005_vets_and_shelters.species ( 
	id                   bigint  NOT NULL GENERATED BY DEFAULT AS IDENTITY  ,
	name                 varchar(100)  NOT NULL  ,
	CONSTRAINT pk_species PRIMARY KEY ( id )
 );

CREATE  TABLE skj005_vets_and_shelters.tbl ( 
 );

CREATE  TABLE skj005_vets_and_shelters.race ( 
	id                   bigint  NOT NULL GENERATED BY DEFAULT AS IDENTITY  ,
	species_id           bigint  NOT NULL  ,
	is_exotic            boolean  NOT NULL  ,
	name                 varchar(100)  NOT NULL  ,
	santeria_related     boolean  NOT NULL  ,
	space_need           varchar(100)  NOT NULL  ,
	activity_need        varchar(100)  NOT NULL  ,
	alimentation_need    varchar(100)  NOT NULL  ,
	dangerous_race       boolean  NOT NULL  ,
	time_dedication_need varchar(100)  NOT NULL  ,
	CONSTRAINT pk_race PRIMARY KEY ( id )
 );

CREATE  TABLE skj005_vets_and_shelters.animal ( 
	id                   bigint  NOT NULL GENERATED BY DEFAULT AS IDENTITY  ,
	sex_id               integer  NOT NULL  ,
	race_id              bigint  NOT NULL  ,
	procedence_type_id   integer  NOT NULL  ,
	name                 varchar(100)    ,
	colour               varchar(100)  NOT NULL  ,
	CONSTRAINT pk_animal PRIMARY KEY ( id )
 );

CREATE  TABLE skj005_vets_and_shelters.animal_photos ( 
	id                   bigint  NOT NULL  ,
	animal_id            bigint  NOT NULL  ,
	url                  varchar(255)  NOT NULL  ,
	CONSTRAINT pk_animal_photis PRIMARY KEY ( id )
 );

CREATE  TABLE skj005_vets_and_shelters.animal_status_hist ( 
	animal_id            bigint  NOT NULL  ,
	status_id            integer  NOT NULL  ,
	date_from            date  NOT NULL  ,
	date_to              date    ,
	photo                bigint  NOT NULL  ,
	CONSTRAINT pk_animal_status_hist PRIMARY KEY ( animal_id, date_from )
 );

CREATE  TABLE skj005_vets_and_shelters.appointment ( 
	customer_id          bigint  NOT NULL  ,
	appointment_date     timestamp  NOT NULL  ,
	animal_id            bigint    ,
	CONSTRAINT pk_appointment PRIMARY KEY ( customer_id, appointment_date )
 );

ALTER TABLE skj005_vets_and_shelters.animal ADD CONSTRAINT fk_animal_sex FOREIGN KEY ( sex_id ) REFERENCES skj005_vets_and_shelters.sex( id );

ALTER TABLE skj005_vets_and_shelters.animal ADD CONSTRAINT fk_animal_race FOREIGN KEY ( race_id ) REFERENCES skj005_vets_and_shelters.race( id );

ALTER TABLE skj005_vets_and_shelters.animal ADD CONSTRAINT fk_animal_procedence_type FOREIGN KEY ( procedence_type_id ) REFERENCES skj005_vets_and_shelters.procedence_type( id );

ALTER TABLE skj005_vets_and_shelters.animal_photos ADD CONSTRAINT fk_animal_photis_animal FOREIGN KEY ( animal_id ) REFERENCES skj005_vets_and_shelters.animal( id );

ALTER TABLE skj005_vets_and_shelters.animal_status_hist ADD CONSTRAINT fk_animal_status_hist_animal FOREIGN KEY ( animal_id ) REFERENCES skj005_vets_and_shelters.animal( id );

ALTER TABLE skj005_vets_and_shelters.animal_status_hist ADD CONSTRAINT fk_animal_status_hist FOREIGN KEY ( status_id ) REFERENCES skj005_vets_and_shelters.animal_status( id );

ALTER TABLE skj005_vets_and_shelters.animal_status_hist ADD CONSTRAINT fk_animal_status_hist_photo FOREIGN KEY ( photo ) REFERENCES skj005_vets_and_shelters.animal_photos( id );

ALTER TABLE skj005_vets_and_shelters.appointment ADD CONSTRAINT fk_appointment_customer FOREIGN KEY ( customer_id ) REFERENCES skj005_vets_and_shelters.customer( id );

ALTER TABLE skj005_vets_and_shelters.appointment ADD CONSTRAINT fk_appointment_animal FOREIGN KEY ( animal_id ) REFERENCES skj005_vets_and_shelters.animal( id );

ALTER TABLE skj005_vets_and_shelters.customer_pwd ADD CONSTRAINT fk_customer_pwd_customer FOREIGN KEY ( customer_id ) REFERENCES skj005_vets_and_shelters.customer( id );

ALTER TABLE skj005_vets_and_shelters.race ADD CONSTRAINT fk_race_species FOREIGN KEY ( species_id ) REFERENCES skj005_vets_and_shelters.species( id );

COMMENT ON COLUMN skj005_vets_and_shelters.race.santeria_related IS 'these animals can''t be given when there can be involved in rituals during certain periods (example: black cats in october)';

COMMENT ON COLUMN skj005_vets_and_shelters.race.space_need IS 'define data type after seeing more data';

COMMENT ON COLUMN skj005_vets_and_shelters.race.activity_need IS 'define data type after seeing more data';

COMMENT ON COLUMN skj005_vets_and_shelters.race.alimentation_need IS 'define data type after seeing more data';

COMMENT ON COLUMN skj005_vets_and_shelters.race.dangerous_race IS 'defines if this race is catalogued as a dangerous race or not';

COMMENT ON COLUMN skj005_vets_and_shelters.race.time_dedication_need IS 'define data type when seeing more data';

