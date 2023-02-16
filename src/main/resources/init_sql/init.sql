DROP TABLE IF EXISTS vote;
DROP TABLE IF EXISTS quote_table;
DROP TABLE IF EXISTS users;

CREATE TABLE users (
    id  bigserial NOT NULL,
    name varchar(255) NOT NULL CONSTRAINT uk_username UNIQUE,
    email varchar(255) NOT NULL CONSTRAINT uk_email UNIQUE,
    password varchar(255) NOT NULL,
    creation_date timestamp NOT NULL,
    PRIMARY KEY (id)
);
-- ALTER TABLE users ADD CONSTRAINT uk_email UNIQUE (email);
-- ALTER TABLE users ADD CONSTRAINT uk_username UNIQUE (name);

CREATE TABLE quote_table (
    id  bigserial NOT NULL,
    content varchar(255),
    votes int4 NOT NULL,
    creation_date timestamp NOT NULL,
    update_date timestamp,
    created_user_id int8 NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (created_user_id) REFERENCES users(id)
);
-- ALTER TABLE quote_table ADD CONSTRAINT fk_created_user_id

CREATE TABLE vote (
    id  bigserial NOT NULL,
    is_upvote boolean NOT NULL,
    quote_id int8 NOT NULL,
    voted_user_id int8 NOT NULL,
    voted_time timestamp NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (quote_id) REFERENCES quote_table(id),
    FOREIGN KEY (voted_user_id) REFERENCES users(id)
);
-- ALTER TABLE vote ADD CONSTRAINT fk_quote_id
-- ALTER TABLE vote ADD CONSTRAINT fk_voted_user_id