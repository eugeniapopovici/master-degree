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

CREATE TABLE cinemas (
  id integer
    NOT NULL
    PRIMARY KEY,
  cinema_name char(30),
  address_id integer
    NOT NULL
    REFERENCES address(id),
  cinema_phone char(20),
  cinema_seat_capacity integer
);

CREATE TABLE movies (
  id integer
    NOT NULL
    PRIMARY KEY,
  movie_rating integer,
  movie_name char(30),
  genre char(20),
  duration integer
);
