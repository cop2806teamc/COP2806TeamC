/*******************/
/****** TABLE ******/
/*******************/

/****** customer table ******/
CREATE TABLE customer (
    id int unsigned  NOT NULL,
    first_name varchar(255)  NOT NULL,
    last_name varchar(255)  NOT NULL,
	date_of_birth date  NOT NULL,
    address varchar(255),
    email varchar(255),
    phone varchar(32),
    CONSTRAINT customer_pk PRIMARY KEY (id)
);


/****** user table ******/
CREATE TABLE `user` (
    id int unsigned  NOT NULL,
    username varchar(255)  NOT NULL UNIQUE,
    password varchar(255)  NOT NULL,
    customer_id int unsigned  NOT NULL UNIQUE,
    CONSTRAINT user_pk PRIMARY KEY (id)
);

ALTER TABLE user ADD CONSTRAINT user_customer FOREIGN KEY user_customer (customer_id)
REFERENCES customer (id);


/****** bank account table ******/
CREATE TABLE bank_account (
    id int unsigned  NOT NULL,
	account_number varchar(255)  NOT NULL UNIQUE,
    account_name varchar(255)  NOT NULL,
    balance decimal(12,2)  NOT NULL,
    account_type varchar(255)  NOT NULL,
    CONSTRAINT bank_account_pk PRIMARY KEY (id)
);


/****** customer <-> bank account table ******/
CREATE TABLE customer_accounts (
    customer_id int unsigned NOT NULL,
	bank_account_id int unsigned NOT NULL,
    CONSTRAINT customer_accounts_pk PRIMARY KEY (customer_id, bank_account_id)
);

ALTER TABLE customer_accounts ADD CONSTRAINT account_customer FOREIGN KEY account_customer (customer_id)
REFERENCES customer (id);

ALTER TABLE customer_accounts ADD CONSTRAINT customer_account FOREIGN KEY customer_account (bank_account_id)
REFERENCES bank_account (id);


/****** transfer request table ******/
CREATE TABLE transfer_request (
    id int unsigned  NOT NULL,
	from_account_id int unsigned  NOT NULL,
	to_account_id int unsigned  NOT NULL,
    amount decimal(12,2)  NOT NULL,
	active_date date  NOT NULL,
	schedule_status varchar(255)  NOT NULL,
    transfer_status varchar(255)  NOT NULL,
    CONSTRAINT transfer_request_pk PRIMARY KEY (id)
);

ALTER TABLE transfer_request ADD CONSTRAINT from_bank_account FOREIGN KEY from_bank_account (from_account_id)
REFERENCES bank_account (id);

ALTER TABLE transfer_request ADD CONSTRAINT to_bank_account FOREIGN KEY to_bank_account (to_account_id)
REFERENCES bank_account (id);


/****** check table ******/
CREATE TABLE `check` (
    id int unsigned  NOT NULL,
	check_number varchar(255)  NOT NULL,
	bank_account_id int unsigned  NOT NULL,
	routing_number varchar(255)  NOT NULL,
	`date` date  NOT NULL,
	pay_to varchar(255)  NOT NULL,
	amount decimal(12,2)  NOT NULL,
	status varchar(255)  NOT NULL,
    CONSTRAINT check_pk PRIMARY KEY (id)
);

ALTER TABLE `check` ADD CONSTRAINT check_account FOREIGN KEY check_account (bank_account_id)
REFERENCES bank_account (id);


/****** check order table ******/
CREATE TABLE check_order (
    id int unsigned  NOT NULL,
	customer_id int unsigned  NOT NULL,
	bank_account_id int unsigned  NOT NULL,
    CONSTRAINT check_order_pk PRIMARY KEY (id)
);

ALTER TABLE check_order ADD CONSTRAINT check_order_customer FOREIGN KEY check_order_customer (customer_id)
REFERENCES customer (id);

ALTER TABLE check_order ADD CONSTRAINT check_order_account FOREIGN KEY check_order_account (bank_account_id)
REFERENCES bank_account (id);



/******************/
/****** DATA ******/
/******************/

/****** customer data ******/
INSERT INTO `titanbank`.`customer`
(`id`, `first_name`, `last_name`, `date_of_birth`, `address`, `email`, `phone`)
VALUES
(1,
"John",
"Doe",
"1980-01-01",
"St. Petersburg, FL",
"john@doe.com",
"(727)123-4567");

INSERT INTO `titanbank`.`customer`
(`id`, `first_name`, `last_name`, `date_of_birth`, `address`, `email`, `phone`)
VALUES
(2,
"Ji",
"Zhou",
"1982-04-26",
"St. Petersburg, FL",
"jizhou26@gmail.com",
"(727)223-4567");

INSERT INTO `titanbank`.`customer`
(`id`, `first_name`, `last_name`, `date_of_birth`, `address`, `email`, `phone`)
VALUES
(3,
"Wife",
"Zhou",
"1988-11-16",
"St. Petersburg, FL",
"jizhou26@gmail.com",
"(727)323-4567");


/****** user data ******/
INSERT INTO `titanbank`.`user`
(`id`, `username`, `password`, `customer_id`)
VALUES
(1,
"user",
"password",
1);

INSERT INTO `titanbank`.`user`
(`id`, `username`, `password`, `customer_id`)
VALUES
(2,
"jizhou",
"password",
2);


/****** bank account data ******/
INSERT INTO `titanbank`.`bank_account`
(`id`, `account_number`, `account_name`, `balance`, `account_type`)
VALUES
(1,
"000001",
"Checking Account",
1000,
"CHECKING");

INSERT INTO `titanbank`.`bank_account`
(`id`, `account_number`, `account_name`, `balance`, `account_type`)
VALUES
(2,
"000002",
"Saving Account",
2000,
"SAVING");

INSERT INTO `titanbank`.`bank_account`
(`id`, `account_number`, `account_name`, `balance`, `account_type`)
VALUES
(3,
"000003",
"Checking Account",
3000,
"CHECKING");

INSERT INTO `titanbank`.`bank_account`
(`id`, `account_number`, `account_name`, `balance`, `account_type`)
VALUES
(4,
"000004",
"Saving Account",
4000,
"SAVING");


/****** customer <-> bank account data ******/
INSERT INTO `titanbank`.`customer_accounts`
(`customer_id`, `bank_account_id`)
VALUES
(1, 1);

INSERT INTO `titanbank`.`customer_accounts`
(`customer_id`, `bank_account_id`)
VALUES
(1, 2);

INSERT INTO `titanbank`.`customer_accounts`
(`customer_id`, `bank_account_id`)
VALUES
(2, 3);

INSERT INTO `titanbank`.`customer_accounts`
(`customer_id`, `bank_account_id`)
VALUES
(2, 4);

INSERT INTO `titanbank`.`customer_accounts`
(`customer_id`, `bank_account_id`)
VALUES
(3, 3);


/****** transfer request data ******/
INSERT INTO `titanbank`.`transfer_request`
(`id`, `from_account_id`, `to_account_id`, `amount`, `active_date`, `schedule_status`, `transfer_status`)
VALUES
(1,
1,
2,
1000.00,
"2014-1-1",
"IMMEDIATE",
"COMPLETED");

INSERT INTO `titanbank`.`transfer_request`
(`id`, `from_account_id`, `to_account_id`, `amount`, `active_date`, `schedule_status`, `transfer_status`)
VALUES
(2,
3,
4,
2000.00,
"2014-5-1",
"IMMEDIATE",
"COMPLETED");


INSERT INTO `titanbank`.`transfer_request`
(`id`, `from_account_id`, `to_account_id`, `amount`, `active_date`, `schedule_status`, `transfer_status`)
VALUES
(3,
4,
3,
3000.00,
"2014-5-31",
"FUTURE",
"PENDING");


/****** check data ******/
INSERT INTO `titanbank`.`check`
(`id`, `check_number`, `bank_account_id`, `routing_number`, `date`, `pay_to`, `amount`, `status`)
VALUES
(1,
"001",
1,
"000000001",
"2014-5-1",
"Store A",
100,
"PAID");

INSERT INTO `titanbank`.`check`
(`id`, `check_number`, `bank_account_id`, `routing_number`, `date`, `pay_to`, `amount`, `status`)
VALUES
(2,
"001",
2,
"000000001",
"2014-5-2",
"Store B",
200,
"PENDING");

INSERT INTO `titanbank`.`check`
(`id`, `check_number`, `bank_account_id`, `routing_number`, `date`, `pay_to`, `amount`, `status`)
VALUES
(3,
"001",
3,
"000000002",
"2014-5-1",
"Store A",
100,
"PAID");

INSERT INTO `titanbank`.`check`
(`id`, `check_number`, `bank_account_id`, `routing_number`, `date`, `pay_to`, `amount`, `status`)
VALUES
(4,
"002",
3,
"000000002",
"2014-5-2",
"Store B",
200,
"PENDING");

INSERT INTO `titanbank`.`check`
(`id`, `check_number`, `bank_account_id`, `routing_number`, `date`, `pay_to`, `amount`, `status`)
VALUES
(5,
"003",
3,
"000000002",
"2014-5-3",
"Store C",
300,
"PENDING");

INSERT INTO `titanbank`.`check`
(`id`, `check_number`, `bank_account_id`, `routing_number`, `date`, `pay_to`, `amount`, `status`)
VALUES
(6,
"004",
3,
"000000002",
"2014-5-4",
"Store D",
400,
"PAID");

INSERT INTO `titanbank`.`check`
(`id`, `check_number`, `bank_account_id`, `routing_number`, `date`, `pay_to`, `amount`, `status`)
VALUES
(7,
"001",
4,
"000000002",
"2014-5-1",
"Store A",
100,
"PENDING");

INSERT INTO `titanbank`.`check`
(`id`, `check_number`, `bank_account_id`, `routing_number`, `date`, `pay_to`, `amount`, `status`)
VALUES
(8,
"002",
4,
"000000002",
"2014-5-2",
"Store B",
200,
"PENDING");

INSERT INTO `titanbank`.`check`
(`id`, `check_number`, `bank_account_id`, `routing_number`, `date`, `pay_to`, `amount`, `status`)
VALUES
(9,
"003",
4,
"000000002",
"2014-5-3",
"Store C",
300,
"STOPPED");

INSERT INTO `titanbank`.`check`
(`id`, `check_number`, `bank_account_id`, `routing_number`, `date`, `pay_to`, `amount`, `status`)
VALUES
(10,
"004",
4,
"000000002",
"2014-5-4",
"Store D",
400,
"RETURNED");
