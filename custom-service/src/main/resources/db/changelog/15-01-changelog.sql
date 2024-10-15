-- liquibase formatted sql

-- changeset ClementOwireku-Bogya:1728980913401-1
CREATE SEQUENCE IF NOT EXISTS customers_seq START WITH 1 INCREMENT BY 50;

-- changeset ClementOwireku-Bogya:1728980913401-2
CREATE TABLE customers
(
    id         INTEGER      NOT NULL,
    first_name VARCHAR(255) NOT NULL,
    last_name  VARCHAR(255) NOT NULL,
    email      VARCHAR(255) NOT NULL,
    phone      VARCHAR(255) NOT NULL,
    address    VARCHAR(255),
    CONSTRAINT pk_customers PRIMARY KEY (id)
);

