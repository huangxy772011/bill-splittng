CREATE SEQUENCE bills_id_sequence;

CREATE TABLE bills(
    id bigint NOT NULL DEFAULT NEXTVAL('bills_id_sequence'),
    type varchar (50),
    amount integer,
    user_id bigint DEFAULT NULL,
    primary key (id),

    CONSTRAINT fk_bill_user
    FOREIGN KEY (user_id)
    REFERENCES users (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
);

ALTER SEQUENCE bills_id_sequence OWNED BY bills.id;