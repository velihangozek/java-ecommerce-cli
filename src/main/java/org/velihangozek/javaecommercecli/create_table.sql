CREATE TABLE category
(
    id          SERIAL PRIMARY KEY,
    name        VARCHAR(255) NOT NULL,
    createdDate DATE DEFAULT current_date,
    updatedDate DATE DEFAULT current_date
);

CREATE TABLE product
(
    id          SERIAL PRIMARY KEY,
    name        VARCHAR(255) NOT NULL,
    price       NUMERIC(10, 2),
    stock       INT,
    category_id INT REFERENCES category (id),
    createdDate DATE DEFAULT current_date,
    updatedDate DATE DEFAULT current_date
);

CREATE TABLE customer
(
    id          SERIAL PRIMARY KEY,
    name        VARCHAR(255) NOT NULL,
    email       VARCHAR(255) NOT NULL,
    password    VARCHAR(255) NOT NULL,
    createdDate DATE DEFAULT current_date,
    updatedDate DATE DEFAULT current_date
);

CREATE TABLE "order"
(
    id           SERIAL PRIMARY KEY,
    customer_id  INT REFERENCES customer (id),
    order_date   TIMESTAMP,
    total_amount NUMERIC(10, 2),
    createdDate  DATE DEFAULT current_date,
    updatedDate  DATE DEFAULT current_date
);

CREATE TABLE payment
(
    id             SERIAL PRIMARY KEY,
    order_id       INT REFERENCES "order" (id),
    payment_method VARCHAR(50),
    amount         NUMERIC(10, 2) NOT NULL,
    createdDate    DATE DEFAULT current_date,
    updatedDate    DATE DEFAULT current_date
);

CREATE TABLE users
(
    id       SERIAL PRIMARY KEY,
    username VARCHAR(100) UNIQUE NOT NULL,
    password VARCHAR(200)        NOT NULL,
    role     VARCHAR(100)        NOT NULL,
    isActive boolean default true
);

-- ALTER Command - Just in case
-- ALTER TABLE product RENAME COLUMN private TO price;

ALTER TABLE product
    ADD COLUMN created_by_user INT REFERENCES users (id);
ALTER TABLE product
    ADD COLUMN updated_by_user INT REFERENCES users (id);

ALTER TABLE category
    ADD COLUMN created_by_user INT REFERENCES users (id);
ALTER TABLE category
    ADD COLUMN updated_by_user INT REFERENCES users (id);