--liquibase formatted sql

--changeset epopovici:1
CREATE TABLE addresses (
  id SERIAL
    NOT NULL
    PRIMARY KEY,
  country VARCHAR(30),
  city VARCHAR(30),
  address_line VARCHAR(50)
);

CREATE TABLE cinemas (
  id SERIAL
    NOT NULL
    PRIMARY KEY,
  cinema_name VARCHAR(30),
  address_id INTEGER
    NOT NULL
    REFERENCES addresses(id),
  cinema_phone VARCHAR(20)
);

CREATE TABLE categories (
  id SERIAL
    NOT NULL
    PRIMARY KEY,
  category_name VARCHAR(1)
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
  id SERIAL
    NOT NULL
    PRIMARY KEY,
  rating INTEGER,
  name VARCHAR(100),
  description VARCHAR(500),
  genre VARCHAR(20),
  stage_director VARCHAR(50),
  duration INTEGER,
  three_D BOOLEAN,
  release_date DATE
);

CREATE TABLE performances(
  id SERIAL
    NOT NULL
    PRIMARY KEY,
  performance_start_time TIME,
  performance_end_time TIME
);

CREATE TABLE showings (
  id SERIAL
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
  id SERIAL
    NOT NULL
    PRIMARY KEY,
  customer_name VARCHAR(50),
  customer_phone VARCHAR(20)
);

CREATE TABLE bookings(
  id SERIAL
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
--changeset srotari:3
INSERT INTO addresses (country, city, address_line) VALUES
    ('Republic of Moldova', 'Balty', 'str.Stefan cel Mare'),
    ('Republic of Moldova', 'Chisinau', 'str.Ion Costin'),
    ('Republic of Moldova', 'Chisinau', 'str.Miron Costin'),
    ('Russian Fediration', 'Moscow', 'Tverskaya street'),
    ('Russian Fediration', 'Moscow', 'Naberejnaea street');

INSERT INTO cinemas (cinema_name, address_id, cinema_phone) VALUES
    ('Multipex', 1, '+37378945612'),
    ('Speranta', 2, '+37378945634'),
    ('Riscani', 3, '+37378945645'),
    ('Hollywood', 4, '+789564257812'),
    ('Russia', 5, '+789564257834');


INSERT INTO movies (name, description, genre, stage_director, duration, three_D, release_date) VALUES
('COPIII DOMNIŞOAREI PEREGRINE: ÎNTRE DOUĂ LUMI',
'Jacob, în vârstă de 16 ani, urmărește niște indicii care îi duc pe o insulă misterioasă, unde descoperă ruinele dărăpanate ale Școlii Domnișoarei Peregrine pentru Copii Ciudați. Pe măsură ce Jacob explorează dormitoarele și holurile abandonate, el descoperă că foștii ei ocupanți erau mult mai mult decât ciudați; aveau puteri incredibile. Și ar putea fi încă în viață.!',
 'Fantasy', 'G.Bruman', 127, true, '2016-06-06'),
('Hotii de geniu', 'Short film description should go here', 'Comedy', 'R. Albert', 130, false, '2016-10-10');


INSERT INTO categories(category_name) VALUES
('A'), ('B'), ('C'), ('D');

INSERT INTO cinema_categories (cinema_id, category_id, capacity) VALUES
(1, 1, 10),
(1, 2, 10),
(1, 3, 10),
(1, 4, 10),
(2, 1, 10),
(2, 2, 10),
(2, 3, 10),
(2, 4, 10),
(3, 1, 10),
(3, 2, 10),
(3, 3, 10),
(3, 4, 10),
(4, 1, 10),
(4, 2, 10),
(4, 3, 10),
(4, 4, 10);

INSERT INTO performances(performance_start_time, performance_end_time) VALUES
('09:08:42.686', '12:08:42.686'),
('13:08:42.686', '16:08:42.686'),
('17:08:42.686', '21:08:42.686');

INSERT INTO customers(customer_name, customer_phone) VALUES
  ('Pavel Mihailov', '23-23-23'),
  ('Natalia Balan', '12-12-12'),
  ('Diana Samson', '12-23-12'),
  ('Igor Curdvanovschi', '12-23-12');

INSERT INTO showings(cinema_id, movie_id, performance_id) VALUES
  (1, 1, 1),
  (1, 2, 2),
  (3, 2, 3),
  (3, 1, 3);

INSERT INTO bookings(customer_id, showing_id, category_id, booking_for_date, booking_made_date) VALUES
(2, 1, 1, '2016-01-01', '2016-01-01'),
(1, 2, 1, '2016-02-01', '2016-01-01'),
(2, 1, 3, '2016-01-11', '2016-01-01'),
(1, 2, 1, '2016-01-08', '2016-01-01');
