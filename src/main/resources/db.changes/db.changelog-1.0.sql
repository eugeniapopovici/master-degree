--liquibase formatted sql

--changeset nbalan:1
CREATE TABLE addresses (
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
    REFERENCES addresses(id),
  cinema_phone char(20)
);

CREATE TABLE movies (
  id integer
    NOT NULL
    PRIMARY KEY,
  rating integer,
  name char(30),
  description char(30),
  genre char(20),
  stage_director char(50),
  duration integer,
  three_D boolean,
  release_date date
);

CREATE TABLE performances(
  id integer
    NOT NULL
    PRIMARY KEY,
  cinema_id integer
    REFERENCES cinemas(id),
  movie_id integer
    REFERENCES movies(id),
  performance_id integer
    REFERENCES performances(id),
  performance_start_time time,
  performance_end_time time
);









/*


CREATE TABLE customers (
  id integer
    NOT NULL
    PRIMARY KEY,
  customer_name char(50),
  customer_phone char(20)
);

CREATE TABLE categories (
  id integer
    NOT NULL
    PRIMARY KEY,
  category_name char(20)
);

CREATE TABLE cinema_category (
  cinema_id integer NOT NULL
    REFERENCES cinemas(id) ON DELETE CASCADE ,
  category_id integer NOT NULL
    REFERENCES categories(id),
  capacity integer,

  CONSTRAINT cinema_category_pk PRIMARY KEY (cinema_id, category_id)
);

CREATE TABLE performances(
  id integer
    NOT NULL
    PRIMARY KEY,
  performance_start_time time,
  performance_end_time time
);

CREATE TABLE cinema_movies(
  cinema_id integer
    REFERENCES cinemas(id),
  movie_id integer
    REFERENCES movies(id),
  performance_id integer
    REFERENCES performances(id),

  CONSTRAINT cinema_movies_pk PRIMARY KEY (cinema_id, movie_id, performance_id)
);

CREATE TABLE booking(
  id integer
    NOT NULL
    PRIMARY KEY,
  customer_id integer
    NOT NULL,
  cinema_id integer,
  movie_id integer,
  performance_id integer,
  booking_for_date date,
  booking_made_date date NOT NULL,
  category_id integer NOT NULL
    REFERENCES categories(id),

  FOREIGN KEY (customer_id)
    REFERENCES customers(id),
  FOREIGN KEY (cinema_id, movie_id, performance_id)
    REFERENCES cinema_movies (cinema_id, movie_id, performance_id)
);*/
