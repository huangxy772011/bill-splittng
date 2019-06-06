CREATE SEQUENCE users_id_seq;

CREATE TABLE users(
    id bigint not null DEFAULT NEXTVAL('users_id_seq'),
    username varchar (50) NOT NULL,
    password varchar (50) NOT NULL,
    last_name varchar (255),
    first_name varchar (255),
    email varchar (255),
    PRIMARY KEY(id)
);

ALTER SEQUENCE users_id_seq OWNED BY users.id;