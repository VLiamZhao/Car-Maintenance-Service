CREATE TABLE customer (

                        id              SERIAL NOT NULL,
                        name            VARCHAR(30) not null unique,
                        password        VARCHAR(64),
                        secret_key      varchar(512),
                        email           VARCHAR(50) not null unique,
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

CREATE TABLE maintenance (
                        id               BIGSERIAL NOT NULL,
                        component        VARCHAR(30) not null,
                        cost             NUMERIC(10,2),
                        date             date default CURRENT_DATE,
                        description      VARCHAR(150),
                        car_id           BIGINT,
                        primary key (id)
);

ALTER TABLE car ADD CONSTRAINT car_customer_fk FOREIGN KEY ( owner_id ) REFERENCES customer ( id );
ALTER TABLE maintenance ADD CONSTRAINT maintenance_car_fk FOREIGN KEY ( car_id ) REFERENCES car ( id );