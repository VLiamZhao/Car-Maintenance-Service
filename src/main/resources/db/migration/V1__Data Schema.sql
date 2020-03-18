CREATE TABLE customer (

                        id                BIGSERIAL NOT NULL,
                        name              VARCHAR(30) not null unique,
                        description       VARCHAR(150),
                        primary key (id)
);

CREATE TABLE car (

                        id              BIGSERIAL NOT NULL,
                        type            VARCHAR(30) not null unique,
                        price           NUMERIC(10,2),
                        regi_date       date default CURRENT_DATE,
                        owner_id        BIGINT,
                        primary key (id)
);

ALTER TABLE car ADD CONSTRAINT car_customer_fk FOREIGN KEY ( owner_id ) REFERENCES customer ( id );