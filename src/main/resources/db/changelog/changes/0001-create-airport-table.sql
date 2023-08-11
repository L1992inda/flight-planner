--liquibase formatted sql

--changeset linda:1

CREATE TABLE airport
(

    airport_id SERIAL       NOT NULL,
    country    VARCHAR(255) NOT NULL,
    city       VARCHAR(255) NOT NULL,
    airport    VARCHAR(255) NOT NULL,
    PRIMARY KEY (airport_id)
);
CREATE TABLE flight
(
    flight_id       SERIAL       NOT NULL,
    from_airport_id INTEGER      NOT NULL REFERENCES airport (airport_id),
    to_airport_id   INTEGER      NOT NULL REFERENCES airport (airport_id),
    carrier         VARCHAR(255) NOT NULL,
    departure_time  TIMESTAMP    NOT NULL,
    arrival_time    TIMESTAMP    NOT NULL,
    PRIMARY KEY (flight_id)

);