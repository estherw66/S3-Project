CREATE TABLE guest (
    id int NOT NULL AUTO_INCREMENT,
    first_name varchar(25) NOT NULL,
    last_name varchar(35) NOT NULL,
    email varchar(50) NOT NULL,
    PRIMARY KEY (id),
    UNIQUE (email)
);