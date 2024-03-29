-- reset file

DROP TABLE IF EXISTS "User" CASCADE;
DROP TABLE IF EXISTS "Event" CASCADE;
DROP TABLE IF EXISTS "Participation" CASCADE;
DROP TABLE IF EXISTS "CreditCard" CASCADE;
DROP TABLE IF EXISTS "PayPal" CASCADE;
DROP TABLE IF EXISTS "Request" CASCADE;

CREATE TABLE "CreditCard" (
	cardNumber VARCHAR(50) PRIMARY KEY,
	cardType VARCHAR(50),
	cardExpirationDate VARCHAR(50) NOT NULL,
	cardSecurityCode VARCHAR(50) NOT NULL
);

CREATE TABLE "PayPal" (
	uniqueCode SERIAL PRIMARY KEY,
	accountEmail VARCHAR(100) NOT NULL UNIQUE,
	accountPassword VARCHAR(100) NOT NULL
);

CREATE TABLE "User" (
	id SERIAL PRIMARY KEY,
	name VARCHAR(50),
	surname VARCHAR(50),
	age INTEGER CONSTRAINT age_positive CHECK (age >= 0 AND age < 150),
	username VARCHAR(50) NOT NULL UNIQUE,
	email VARCHAR(100) NOT NULL UNIQUE,
	password VARCHAR(100) NOT NULL,
	creditCard VARCHAR(50) UNIQUE,
    payPal INTEGER UNIQUE,
	FOREIGN KEY (creditCard) REFERENCES "CreditCard"(cardNumber),
	FOREIGN KEY (payPal) REFERENCES "PayPal"(uniqueCode),
	created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE "Event" (
	code SERIAL PRIMARY KEY,
	name VARCHAR(50) NOT NULL UNIQUE,
	description VARCHAR(500),
	location VARCHAR(100) NOT NULL,
	date VARCHAR(20) NOT NULL,
	time VARCHAR(20) NOT NULL,
	refundable BOOLEAN NOT NULL,
	fee FLOAT NOT NULL,
	created_by INTEGER NOT NULL,
    FOREIGN KEY (created_by) REFERENCES "User"(id)
);

CREATE TABLE "Participation" (
	user_id INTEGER NOT NULL,
	event_id INTEGER NOT NULL,
	paymentMethod VARCHAR(50) NOT NULL,
	PRIMARY KEY (user_id, event_id),
    FOREIGN KEY (user_id) REFERENCES "User"(id),
    FOREIGN KEY (event_id) REFERENCES "Event"(code)
);

CREATE TABLE "Request" (
	code SERIAL PRIMARY KEY,
	user_id INTEGER NOT NULL,
	description VARCHAR(1000) NOT NULL,
	created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES "User"(id)
	--PRIMARY KEY (user_id, created_at)
);

INSERT INTO "User" (id, username, email, password) VALUES (0, 'ADMIN', '', 'admin');