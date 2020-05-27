CREATE TABLE role (
                       id                   BIGSERIAL NOT NULL,
                       name                 VARCHAR(30) not null unique,
                       allowed_resource     VARCHAR(200),
                       allowed_read         BOOLEAN not null default FALSE ,
                       allowed_create       BOOLEAN not null default FALSE,
                       allowed_update       BOOLEAN not null default FALSE,
                       allowed_delete       BOOLEAN not null default FALSE
);
ALTER TABLE role ADD CONSTRAINT role_pk PRIMARY KEY ( id );

CREATE TABLE customer_role (
                            customer_id    BIGINT NOT NULL,
                            role_id    BIGINT NOT NULL
);

ALTER TABLE customer_role
    ADD CONSTRAINT customer_fk FOREIGN KEY ( customer_id )
        REFERENCES customer ( id );

ALTER TABLE customer_role
    ADD CONSTRAINT role_fk FOREIGN KEY ( role_id )
        REFERENCES role ( id );