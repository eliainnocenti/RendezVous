-- default

-- TODO: put some comments

-- USERS

-- first user
INSERT INTO "User" (name, surname, age, username, email, password, creditCard, payPal)
            VALUES ('Mario', 'Rossi', 30, 'mario.rossi', 'mario.rossi@gmail.com', 'Password1', null, null);
INSERT INTO "CreditCard" (cardNumber, cardExpirationDate, cardSecurityCode)
            VALUES ('135895322', '2025-12-31', '473');
UPDATE "User" SET creditCard = '135895322' WHERE username = 'mario.rossi';

-- second user
INSERT INTO "User" (name, surname, age, username, email, password, creditCard, payPal)
            VALUES ('Luigi', 'Bianchi', 45, 'luigibianchi', 'luigibianchi1@hotmail.it', '12345', null, null);
INSERT INTO "CreditCard" (cardNumber, cardExpirationDate, cardSecurityCode)
            VALUES ('976542341', '2027-10-15', '963');
UPDATE "User" SET creditCard = '976542341' WHERE username = 'luigibianchi';

-- third user
INSERT INTO "User" (name, surname, age, username, email, password, creditCard, payPal)
            VALUES ('Giovanni', 'Verdi', 25, 'gio.verdi7', 'gio.verdi7@gmail.com', 'giovanni7', null, null);
INSERT INTO "PayPal" (accountEmail, accountPassword)
            VALUES ('gio.verdi@gmail.com', 'gio7');
DO $$
DECLARE PayPalUniqueCode INTEGER;
BEGIN
    SELECT uniqueCode INTO PayPalUniqueCode FROM "PayPal" WHERE accountEmail = 'gio.verdi@gmail.com';
    UPDATE "User" SET payPal = PayPalUniqueCode WHERE username = 'gio.verdi7';
END $$;

-- EVENTS

INSERT INTO "Event" (name, description, location, date, time, refundable, fee, created_by)
             VALUES ('Rificolona', 'Festa della Rificolona', 'Ellera', '2024-09-10', '21:30', FALSE, 5.00, 1);