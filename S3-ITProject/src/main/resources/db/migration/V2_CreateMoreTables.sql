# CREATE TABLE employee (
#     id int NOT NULL AUTO_INCREMENT,
#     first_name char(30) NOT NULL,
#     last_name char(40) NOT NULL,
#     email char(50) NOT NULL,
#     username char(25) NOT NULL,
#     password char(25) NOT NULL,
#     phoneNumber char(12) NOT NULL,
#     date_of_birth date NOT NULL,
#     PRIMARY KEY (id),
#     UNIQUE (email)
# );
#
# CREATE TABLE user (
#     id int NOT NULL AUTO_INCREMENT,
#     first_name char(30) NOT NULL,
#     last_name char(40) NOT NULL,
#     email char(50) NOT NULL,
#     username char(25) NOT NULL,
#     password char(25) NOT NULL,
#     PRIMARY KEY (id),
#     UNIQUE (email)
# );
#
# CREATE TABLE address (
#     id int NOT NULL AUTO_INCREMENT,
#     id_employee int NOT NULL,
#     street_name char(50) NOT NULL,
#     zip_code char(6) NOT NULL,
#     city char(30) NOT NULL,
#     PRIMARY KEY(id),
#     FOREIGN KEY (id_employee) REFERENCES employee (id)
# );
#
# CREATE TABLE specific_room (
#     id int NOT NULL AUTO_INCREMENT,
#     room_number int NOT NULL,
#     price_per_night double NOT NULL,
#     is_available boolean NOT NULL,
#     room_id int NOT NULL,
#     PRIMARY KEY (id),
#     FOREIGN KEY (room_id) REFERENCES rooms (id)
# );
#
# CREATE TABLE reservation (
#     id int NOT NULL AUTO_INCREMENT,
#     reservation_date date NOT NULL,
#     check_in date NOT NULL,
#     check_out date NOT NULL,
#     user_id int NOT NULL,
#     amount_of_guests int NOT NULL,
#     total_price double NOT NULL,
#     reservation_status char(15) NOT NULL,
#     PRIMARY KEY (id),
#     FOREIGN KEY (user_id) REFERENCES user (id)
# );
#
# CREATE TABLE reservation_room (
#     id int NOT NULL AUTO_INCREMENT,
#     reservation_id int NOT NULL,
#     specific_room_id int NOT NULL,
#     PRIMARY KEY (id),
#     FOREIGN KEY (reservation_id) REFERENCES reservation (id),
#     FOREIGN KEY (specific_room_id) REFERENCES specific_room (id)
# );