-- table generation

-- TODO: put some comments

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
	id SERIAL PRIMARY KEY,
	name VARCHAR(50),
	description VARCHAR(500),
	location VARCHAR(100),
	date VARCHAR(20),
	time VARCHAR(20),
	refundable BOOLEAN,
	fee FLOAT,
	created_by INTEGER NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS "Participation" (
	user_id INTEGER,
	event_id INTEGER,
	paymentMethod VARCHAR(50),
	PRIMARY KEY (user_id, event_id)
);

CREATE TABLE "Request" (
	user_id INTEGER,
	description VARCHAR(1000),
	created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
	PRIMARY KEY (user_id, created_at)
);