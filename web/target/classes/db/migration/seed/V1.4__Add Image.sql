CREATE TABLE image (

                          id                BIGSERIAL NOT NULL,
                          file_name         VARCHAR(30) not null unique,
                          s3key             VARCHAR(64),
                          create_date       TIMESTAMP,
                          user_id           BIGINT,
                          primary key (id)
);


ALTER TABLE image ADD CONSTRAINT image_customer_fk FOREIGN KEY ( user_id ) REFERENCES customer ( id );