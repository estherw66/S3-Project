# create employee, address and room table
CREATE TABLE employee (
    id int NOT NULL AUTO_INCREMENT,
    first_name varchar(25) NOT NULL,
    last_name varchar(35) NOT NULL,
    email varchar(50) NOT NULL,
    phone_number varchar(12) NOT NULL,
    date_of_birth date NOT NULL,
    PRIMARY KEY (id),
    UNIQUE (email),
    UNIQUE (phone_number)
);

CREATE TABLE address (
    id int NOT NULL AUTO_INCREMENT,
    street_name varchar(50) NOT NULL,
    zip_code varchar(6) NOT NULL,
    city varchar(50) NOT NULL,
    employee_id int,
    FOREIGN KEY (employee_id) REFERENCES employee (id),
    PRIMARY KEY (id)
);

CREATE TABLE room (
    id int NOT NULL AUTO_INCREMENT,
    capacity int NOT NULL,
    price_per_night double NOT NULL,
    image_url varchar(100),
    room_type varchar(25) NOT NULL,
    is_featured boolean NOT NULL,
    total_amount_in_hotel int NOT NULL,
    PRIMARY KEY (id)
);