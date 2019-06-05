CREATE SEQUENCE users_id_seq;

CREATE TABLE users(
    id bigint not null DEFAULT NEXTVAL("users_id_seq"),
    username varchar (50) NOT NULL,
    password varchar (50) NOT NULL,
)