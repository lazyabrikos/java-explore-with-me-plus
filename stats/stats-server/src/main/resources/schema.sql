DROP TABLE IF EXISTS hits;

 CREATE TABLE IF NOT EXISTS hits  (
     id       BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
     app      VARCHAR(256) NOT NULL,
     uri      VARCHAR(256) NOT NULL,
     ip       VARCHAR(15) NOT NULL,
     moment TIMESTAMP WITHOUT TIME ZONE NOT NULL,
     CONSTRAINT pk_hits PRIMARY KEY (id)
     );