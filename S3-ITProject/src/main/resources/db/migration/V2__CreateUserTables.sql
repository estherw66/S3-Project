CREATE TABLE guest (
                       id int NOT NULL AUTO_INCREMENT,
                       first_name varchar(25) NOT NULL,
                       last_name varchar(35) NOT NULL,
                       email varchar(50) NOT NULL,
                       PRIMARY KEY (id),
                       UNIQUE KEY (email)
);

CREATE TABLE user
(
    id int NOT NULL AUTO_INCREMENT,
    username varchar(20) NOT NULL,
    password varchar(100) NOT NULL,
    employee_id int DEFAULT NULL,
    guest_id int DEFAULT NULL,
    PRIMARY KEY (id),
    UNIQUE KEY (username),
    FOREIGN KEY (employee_id) REFERENCES employee (id),
    FOREIGN KEY (guest_id) references guest (id)
);



CREATE TABLE user_role
(
    id int NOT NULL AUTO_INCREMENT,
    user_id int NOT NULL,
    role_name varchar(50) NOT NULL,
    PRIMARY KEY (id),
    UNIQUE KEY (user_id, role_name),
    FOREIGN KEY (user_id) REFERENCES user (id)
);
