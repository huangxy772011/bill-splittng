CREATE SEQUENCE friends_id_seq;

CREATE TABLE friends (
    id bigint NOT NULL DEFAULT NEXTVAL('friends_id_seq'),
    name varchar (50),
    email varchar (50),
    user_id bigint DEFAULT NULL,
    primary key (id),

    CONSTRAINT fk_friend_user
    FOREIGN KEY (user_id)
    REFERENCES users (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
);

ALTER SEQUENCE friends_id_seq OWNED BY friends.id;