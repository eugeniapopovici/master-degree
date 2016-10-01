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
  director char(50),
  duration integer,
  three_D boolean,
  release_date date
);

--changeset srotari: 2
CREATE TABLE movie_showings (
  id integer
    NOT NULL
    PRIMARY KEY,
  cinema_id integer
    REFERENCES cinemas(id)
    NOT NULL,
  movie_id integer
    REFERENCES movies(id)
    NOT NULL,
  showing_from_date date,
  showing_to_date date
);

CREATE TABLE row_seats (
  cinema_id integer
    NOT NULL
    REFERENCES cinemas(id),
  row_number integer NOT NULL,
  seat_count integer,

  CONSTRAINT row_seats_pk
  PRIMARY KEY (cinema_id, row_number)
);

CREATE TABLE customers (
  id integer
    NOT NULL
    PRIMARY KEY,
  customer_name char(50),
  customer_phone char(20)
);

CREATE TABLE booking(
  id integer
    NOT NULL
    PRIMARY KEY,
  customer_id integer
    NOT NULL
    REFERENCES customers(id),
  booking_for_date date,
  booking_made_date date NOT NULL,
  booking_seat_count integer NOT NULL
);

CREATE TABLE seat_status(
  id integer
    NOT NULL
    PRIMARY KEY,
  seat_status boolean
);

CREATE TABLE performance_numbers(
  id integer
    NOT NULL
    PRIMARY KEY,
  performance_start_time time,
  performance_end_time time
);

--changeset epopovici: 2
CREATE TABLE all_performance_seats (
  cinema_id integer
    NOT NULL,
  row_number integer
    NOT NULL,
  seat_number integer
    NOT NULL,
  performance_date date
    NOT NULL,
  performance_number integer
    REFERENCES performance_numbers(id),
  seat_status_code integer
    REFERENCES seat_status(id)
    NOT NULL,
  booking_id integer
    REFERENCES booking(id)
    NOT NULL,

  FOREIGN KEY (cinema_id, row_number)
  REFERENCES row_seats (cinema_id, row_number),
  CONSTRAINT all_performance_seats_pk
  PRIMARY KEY (cinema_id, row_number, seat_number, performance_date)
);

CREATE TABLE booked_performance_seats (
  cinema_id integer
    NOT NULL,
  row_number integer
    NOT NULL,
  seat_number integer
    NOT NULL,
  performance_date date
    NOT NULL,
  performance_number integer
    REFERENCES performance_numbers(id),
  booking_id integer
    REFERENCES booking(id)
    NOT NULL,

  FOREIGN KEY (cinema_id, row_number)
  REFERENCES row_seats (cinema_id, row_number),
  CONSTRAINT booked_performance_seats_pk
  PRIMARY KEY (cinema_id, row_number, seat_number, performance_date, performance_number)
);
