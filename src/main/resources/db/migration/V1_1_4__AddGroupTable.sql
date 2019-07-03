CREATE SEQUENCE groups_id_seq;

CREATE TABLE groups (
    id bigint NOT NULL DEFAULT NEXTVAL('groups_id_seq'),
    name varchar (50),
    type varchar (50),
    balances int,
    primary key (id)
);

ALTER SEQUENCE groups_id_seq OWNED BY groups.id;