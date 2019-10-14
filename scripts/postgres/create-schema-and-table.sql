-- SCHEMA: skillsurvey

DROP SCHEMA skillsurvey ;

CREATE SCHEMA skillsurvey
    AUTHORIZATION postgres;

-- Table: skillsurvey.user_details

DROP TABLE skillsurvey.user_details;

CREATE TABLE skillsurvey.user_details
(
    id serial NOT NULL ,
    name text COLLATE pg_catalog."default",
    email text COLLATE pg_catalog."default",
    last_login date,
    isactive boolean,
    CONSTRAINT user_details_pkey PRIMARY KEY (id)
);

ALTER TABLE skillsurvey.user_details
    OWNER to postgres;
	

-- Table: skillsurvey.skillsurvey_details

DROP TABLE skillsurvey.skillsurvey_details;

CREATE TABLE skillsurvey.skillsurvey_details
(
    id serial NOT NULL,
    name text COLLATE pg_catalog."default",
    description text COLLATE pg_catalog."default",
    start_date date,
    end_date date,
    created_date date,
    created_by integer,
    isactive boolean,
    CONSTRAINT skillsurvey_details_pkey PRIMARY KEY (id),
    CONSTRAINT fk_user_details_id FOREIGN KEY (created_by)
        REFERENCES skillsurvey.user_details (id) MATCH SIMPLE
        ON UPDATE RESTRICT
        ON DELETE NO ACTION
);

ALTER TABLE skillsurvey.skillsurvey_details
    OWNER to postgres;
	
-- Table: skillsurvey.skillsurvey_questions

DROP TABLE skillsurvey.skillsurvey_questions;

CREATE TABLE skillsurvey.skillsurvey_questions
(
    id serial ,
    question text COLLATE pg_catalog."default",
    input_type integer,
    answer_required boolean,
    skillsurvey_id integer,
    CONSTRAINT skillsurvey_questions_pkey PRIMARY KEY (id),
    CONSTRAINT fk_skillsurvey_details_id FOREIGN KEY (skillsurvey_id)
        REFERENCES skillsurvey.skillsurvey_details (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fk_skillsurvey_input_type_id FOREIGN KEY (input_type)
        REFERENCES skillsurvey.skillsurvey_input_type (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);

ALTER TABLE skillsurvey.skillsurvey_questions
    OWNER to postgres;
	
-- Table: skillsurvey.skillsurvey_question_options

DROP TABLE skillsurvey.skillsurvey_question_options;

CREATE TABLE skillsurvey.skillsurvey_question_options
(
    id serial,
    question_id integer,
    option_choice_name text COLLATE pg_catalog."default",
    CONSTRAINT skillsurvey_question_options_pkey PRIMARY KEY (id),
    CONSTRAINT fk_skillsurvey_questions_id FOREIGN KEY (question_id)
        REFERENCES skillsurvey.skillsurvey_questions (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);

ALTER TABLE skillsurvey.skillsurvey_question_options
    OWNER to postgres;
	
-- Table: skillsurvey.skillsurvey_input_type

DROP TABLE skillsurvey.skillsurvey_input_type;

CREATE TABLE skillsurvey.skillsurvey_input_type
(
    id serial,
    input_type_name text COLLATE pg_catalog."default",
    CONSTRAINT skillsurvey_input_type_pkey PRIMARY KEY (id)
);

ALTER TABLE skillsurvey.skillsurvey_input_type
    OWNER to postgres;
	
-- Table: skillsurvey.skillsurvey_answers

DROP TABLE skillsurvey.skillsurvey_answers;

CREATE TABLE skillsurvey.skillsurvey_answers
(
    id serial,
    answered_by integer,
    question_id integer,
    option_id integer,
    answer_text text COLLATE pg_catalog."default",
    isanswered boolean,
    CONSTRAINT skillsurvey_answers_pkey PRIMARY KEY (id),
    CONSTRAINT fk_skillsurvey_question_options_id FOREIGN KEY (option_id)
        REFERENCES skillsurvey.skillsurvey_question_options (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fk_skillsurvey_questions_id FOREIGN KEY (question_id)
        REFERENCES skillsurvey.skillsurvey_questions (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fk_user_details_id FOREIGN KEY (answered_by)
        REFERENCES skillsurvey.user_details (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);

ALTER TABLE skillsurvey.skillsurvey_answers
    OWNER to postgres;