--liquibase formatted sql

--changeset epopovici:1
CREATE TABLE addresses (
  id INTEGER
    NOT NULL
    PRIMARY KEY,
  country CHAR(30),
  city CHAR(30),
  address_line CHAR(50)
);

CREATE TABLE cinemas (
  id INTEGER
    NOT NULL
    PRIMARY KEY,
  cinema_name CHAR(30),
  address_id INTEGER
    NOT NULL
    REFERENCES addresses(id),
  cinema_phone CHAR(20)
);

CREATE TABLE categories (
  id INTEGER
    NOT NULL
    PRIMARY KEY,
  category_name CHAR(1)
);

CREATE TABLE cinema_categories (
  cinema_id INTEGER
    NOT NULL
    REFERENCES cinemas(id)
    ON DELETE CASCADE,
  category_id INTEGER
    NOT NULL
    REFERENCES categories(id)
    ON DELETE CASCADE,
  capacity INTEGER,

  CONSTRAINT cinema_category_pk
    PRIMARY KEY (cinema_id, category_id)
);

--changeset srotari:2
CREATE TABLE movies (
  id INTEGER
    NOT NULL
    PRIMARY KEY,
  rating INTEGER,
  name CHAR(30),
  description CHAR(30),
  genre CHAR(20),
  stage_director CHAR(50),
  duration INTEGER,
  three_D BOOLEAN,
  release_date DATE
);

CREATE TABLE performances(
  id INTEGER
    NOT NULL
    PRIMARY KEY,
  performance_start_time TIME,
  performance_end_time TIME
);

CREATE TABLE showings (
  id INTEGER
    NOT NULL
    PRIMARY KEY,
  cinema_id INTEGER
    NOT NULL
    REFERENCES cinemas(id)
    ON DELETE CASCADE,
  movie_id INTEGER
    NOT NULL
    REFERENCES movies(id)
    ON DELETE CASCADE,
  performance_id INTEGER
    NOT NULL
    REFERENCES performances(id)
    ON DELETE CASCADE
);

CREATE TABLE customers (
  id INTEGER
    NOT NULL
    PRIMARY KEY,
  customer_name CHAR(50),
  customer_phone CHAR(20)
);

CREATE TABLE bookings(
  id INTEGER
    NOT NULL
    PRIMARY KEY,
  customer_id INTEGER
    NOT NULL
    REFERENCES customers(id)
    ON DELETE CASCADE,
  showing_id INTEGER
    NOT NULL
    REFERENCES showings(id)
    ON DELETE CASCADE,
  category_id INTEGER
    NOT NULL
    REFERENCES categories(id)
    ON DELETE CASCADE ,
  booking_for_date DATE,
  booking_made_date DATE NOT NULL
);