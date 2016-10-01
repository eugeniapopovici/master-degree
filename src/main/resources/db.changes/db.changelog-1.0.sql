--liquibase formatted sql

--changeset nbalan:1

CREATE TABLE address (
  id integer
    NOT NULL
    PRIMARY KEY,
  country char(30),
  city char(30),
  address char(50)
);
