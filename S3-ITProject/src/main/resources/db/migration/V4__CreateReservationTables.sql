CREATE TABLE reservation (
    id int NOT NULL AUTO_INCREMENT,
    reservation_date date NOT NULL,
    check_in date NOT NULL,
    check_out date NOT NULL,
    amount_of_guests int NOT NULL,
    total_price double NOT NULL,
    is_checked_in boolean NOT NULL,
    guest_id int NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (guest_id) REFERENCES guest (id)
);

CREATE TABLE reservation_room (
    id int NOT NULL AUTO_INCREMENT,
    reservation_id int NOT NULL,
    room_id int NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (reservation_id) REFERENCES reservation (id),
    FOREIGN KEY (room_id) REFERENCES room (id)
);

