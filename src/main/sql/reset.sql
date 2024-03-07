-- reset

DROP TABLE IF EXISTS "User" CASCADE;
DROP TABLE IF EXISTS "Event" CASCADE;
DROP TABLE IF EXISTS "Participation" CASCADE;
DROP TABLE IF EXISTS "CreditCard" CASCADE;
DROP TABLE IF EXISTS "PayPal" CASCADE;

CREATE TABLE "CreditCard" (
	cardNumber VARCHAR(50) PRIMARY KEY,
	cardType VARCHAR(50),
	cardExpirationDate VARCHAR(50),
	cardSecurityCode VARCHAR(50)
);

CREATE TABLE "PayPal" (
	uniqueCode SERIAL PRIMARY KEY,
	accountEmail VARCHAR(100) UNIQUE,
	accountPassword VARCHAR(100)
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
	id SERIAL PRIMARY KEY,
	name VARCHAR(50),
	description VARCHAR(500),
	location VARCHAR(100),
	date VARCHAR(20),
	time VARCHAR(20),
	refundable BOOLEAN,
	fee FLOAT,
	created_by VARCHAR(50)
);

CREATE TABLE "Participation" (
	user_id INTEGER,
	event_id INTEGER,
	paymentMethod VARCHAR(50),
	PRIMARY KEY (user_id, event_id)
);

INSERT INTO "User" (id, username, email, password) VALUES (0, 'ADMIN', '', 'admin');