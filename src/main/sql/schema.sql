-- table generation

CREATE TABLE IF NOT EXISTS "CreditCard" (
	cardNumber VARCHAR(50) PRIMARY KEY,
	cardType VARCHAR(50),
	cardExpirationDate VARCHAR(50),
	cardSecurityCode VARCHAR(50)
);


CREATE TABLE IF NOT EXISTS "PayPal" (
	uniqueCode SERIAL PRIMARY KEY,
	accountEmail VARCHAR(100) UNIQUE,
	accountPassword VARCHAR(100)
);

CREATE TABLE IF NOT EXISTS "User" (
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

CREATE TABLE IF NOT EXISTS "Event" (
	code SERIAL PRIMARY KEY,
	name VARCHAR(50) NOT NULL,
	description VARCHAR(500),
	location VARCHAR(100) NOT NULL,
	date VARCHAR(20) NOT NULL,
	time VARCHAR(20) NOT NULL,
	refundable BOOLEAN NOT NULL,
	fee FLOAT NOT NULL,
	created_by INTEGER NOT NULL
);

CREATE TABLE IF NOT EXISTS "Participation" (
	user_id INTEGER,
	event_id INTEGER,
	paymentMethod VARCHAR(50),
	PRIMARY KEY (user_id, event_id)
);

CREATE TABLE "Request" (
	code SERIAL PRIMARY KEY,
	user_id INTEGER NOT NULL,
	description VARCHAR(1000) NOT NULL,
	created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
	--PRIMARY KEY (user_id, created_at)
);