CREATE SCHEMA IF NOT EXISTS hotel;

CREATE TABLE IF NOT EXISTS hotel.customers (
	`customer_id`            int(11)  NOT NULL  AUTO_INCREMENT  PRIMARY KEY,
	`email`          varchar(60)  NOT NULL  UNIQUE  ,
	`name`          varchar(30)      ,
	`surname`          varchar(30)
 ) engine=InnoDB;

CREATE TABLE IF NOT EXISTS hotel.rooms (
	`room_id`   int(11)  NOT NULL  AUTO_INCREMENT  PRIMARY KEY,
	`number`   varchar(3)  NOT NULL    ,
	`price`   decimal(10,2)  NOT NULL    ,
    `size`   int(2)   NOT NULL
 ) engine=InnoDB;

CREATE TABLE IF NOT EXISTS hotel.reservation (
    `reservation_id` int(11) NOT NULL,
    `cust_id` int(11) NOT NULL,
    `check_in_dreservationroomsate` date NOT NULL,
    `check_out_date` date NOT NULL,
	CONSTRAINT fk_customer_id FOREIGN KEY ( cust_id ) REFERENCES hotel.customers( customer_id ) ON DELETE RESTRICT ON UPDATE RESTRICT
 ) engine=InnoDB;