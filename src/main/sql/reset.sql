-- reset

DROP TABLE IF EXISTS "User" CASCADE;
DROP TABLE IF EXISTS "Event" CASCADE;
DROP TABLE IF EXISTS "Participation" CASCADE;
DROP TABLE IF EXISTS "CreditCard" CASCADE;
DROP TABLE IF EXISTS "PayPal" CASCADE;

CREATE TABLE "CreditCard" (
	ownerName VARCHAR(50),
	ownerSurname VARCHAR(50),
	cardNumber VARCHAR(50) PRIMARY KEY,
	cardType VARCHAR(50),
	cardExpirationDate VARCHAR(50),
	cardSecurityCode VARCHAR(50)
);


CREATE TABLE "PayPal" (
	ownerName VARCHAR(50),
	ownerSurname VARCHAR(50),
	uniqueCode SERIAL PRIMARY KEY,
	accountEmail VARCHAR(100),
	accountPassword VARCHAR(100)
);

CREATE TABLE "User" (
	id SERIAL PRIMARY KEY,
	name VARCHAR(50),
	surname VARCHAR(50),
	age INTEGER CONSTRAINT age_positive CHECK (age > 0 AND age < 150),
	username VARCHAR(50) NOT NULL UNIQUE,
	email VARCHAR(100) NOT NULL,
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
	maxParticipants INTEGER,
	refundable BOOLEAN,
	fee FLOAT
);

CREATE TABLE "Participation" (
	user_id INTEGER,
	event_id INTEGER,
	paymentMethod VARCHAR(50),
	PRIMARY KEY (user_id, event_id)
);