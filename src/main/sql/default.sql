-- default database file


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

-- fourth user
INSERT INTO "User" (name, surname, age, username, email, password, creditCard, payPal)
            VALUES ('Paolo', 'Neri', 35, 'paolo.neri', 'paolone@gmail.com', 'neri123', null, null);
INSERT INTO "CreditCard" (cardNumber, cardExpirationDate, cardSecurityCode)
            VALUES ('371074589', '2026-05-20', '456');
UPDATE "User" SET creditCard = '371074589' WHERE username = 'paolo.neri';

-- fifth user
INSERT INTO "User" (name, surname, age, username, email, password, creditCard, payPal)
            VALUES ('Giuseppe', 'Gialli', 41, 'giusgialli', 'giallo.giuseppe46@alice.it', 'giallo46', null, null);
INSERT INTO "PayPal" (accountEmail, accountPassword)
            VALUES ('giallo.giuseppe46@alice.it', 'giallo46');
DO $$
    DECLARE PayPalUniqueCode INTEGER;
    BEGIN
        SELECT uniqueCode INTO PayPalUniqueCode FROM "PayPal" WHERE accountEmail = 'giallo.giuseppe46@alice.it';
        UPDATE "User" SET payPal = PayPalUniqueCode WHERE username = 'giusgialli';
END $$;

-- sixth user
INSERT INTO "User" (name, surname, age, username, email, password, creditCard, payPal)
            VALUES ('Antonio', 'Verdi', 37, 'greentonio', 'tonio.verdi@hotmail.it', 'antonio912', null, null);
INSERT INTO "CreditCard" (cardNumber, cardExpirationDate, cardSecurityCode)
            VALUES ('462810474', '2028-07-01', '789');
UPDATE "User" SET creditCard = '462810474' WHERE username = 'greentonio';

-- seventh user
INSERT INTO "User" (name, surname, age, username, email, password, creditCard, payPal)
            VALUES ('Francesco', 'Bianchi', 28, 'f_bianchi', 'francesco.b@outlook.it', 'cesco4', null, null);
INSERT INTO "PayPal" (accountEmail, accountPassword)
            VALUES ('francesco.b@outlook.it', 'cesco4');
DO $$
    DECLARE PayPalUniqueCode INTEGER;
    BEGIN
        SELECT uniqueCode INTO PayPalUniqueCode FROM "PayPal" WHERE accountEmail = 'francesco.b@outlook.it';
        UPDATE "User" SET payPal = PayPalUniqueCode WHERE username = 'f_bianchi';
END $$;

-- eighth user
INSERT INTO "User" (name, surname, age, username, email, password, creditCard, payPal)
            VALUES ('Roberto', 'Rossi', 33, 'robyrossi', 'rossiroberto30@yahoo.com', 'rossi30', null, null);
INSERT INTO "CreditCard" (cardNumber, cardExpirationDate, cardSecurityCode)
            VALUES ('261383572', '2029-03-05', '324');
UPDATE "User" SET creditCard = '261383572' WHERE username = 'robyrossi';

-- ninth user
INSERT INTO "User" (name, surname, age, username, email, password, creditCard, payPal)
            VALUES ('Marco', 'Neri', 29, 'n_marco', 'marco.neri@gmail.com', '29marco_neri', null, null);
INSERT INTO "PayPal" (accountEmail, accountPassword)
            VALUES ('marco.neri@gmail.com', '29marco_neri');
DO $$
    DECLARE PayPalUniqueCode INTEGER;
    BEGIN
        SELECT uniqueCode INTO PayPalUniqueCode FROM "PayPal" WHERE accountEmail = 'marco.neri@gmail.com';
        UPDATE "User" SET payPal = PayPalUniqueCode WHERE username = 'n_marco';
END $$;

-- tenth user
INSERT INTO "User" (name, surname, age, username, email, password, creditCard, payPal)
            VALUES ('Alessandro', 'Gialli', 31, 'alegialli', 'aleyellow@gmail.com', 'ale31', null, null);
INSERT INTO "CreditCard" (cardNumber, cardExpirationDate, cardSecurityCode)
            VALUES ('193738428', '2025-12-31', '473');
UPDATE "User" SET creditCard = '193738428' WHERE username = 'alegialli';


-- EVENTS

INSERT INTO "Event" (name, description, location, date, time, refundable, fee, created_by)
             VALUES ('Rificolona', 'Festa della Rificolona', 'Ellera', '2024-09-10', '21:30', FALSE, 5.00, 1);

INSERT INTO "Event" (name, description, location, date, time, refundable, fee, created_by)
             VALUES ('Carnevale di Viareggio', 'Famous carnival with floats and masks', 'Viareggio', '2024-02-10', '14:00', FALSE, 10.00, 2);

INSERT INTO "Event" (name, description, location, date, time, refundable, fee, created_by)
             VALUES ('Palio di Siena', 'Historic horse race in the city center', 'Siena', '2024-07-02', '19:00', FALSE, 15.00, 3);

INSERT INTO "Event" (name, description, location, date, time, refundable, fee, created_by)
             VALUES ('Festa dei Ceri', 'Traditional event with a race of "Ceri"', 'Gubbio', '2024-05-15', '16:00', FALSE, 8.00, 4);

INSERT INTO "Event" (name, description, location, date, time, refundable, fee, created_by)
             VALUES ('Infiorata di Genzano', 'Artistic event with flower carpets', 'Genzano', '2024-06-12', '09:00', TRUE, 5.00, 5);

INSERT INTO "Event" (name, description, location, date, time, refundable, fee, created_by)
             VALUES ('Luminara di San Ranieri', 'Light festival with candles on the river', 'Pisa', '2024-06-16', '21:00', FALSE, 7.00, 6);

INSERT INTO "Event" (name, description, location, date, time, refundable, fee, created_by)
             VALUES ('Festa della Madonna Bruna', 'Religious procession with a final firework show', 'Matera', '2024-07-02', '20:00', TRUE, 10.00, 7);

INSERT INTO "Event" (name, description, location, date, time, refundable, fee, created_by)
             VALUES ('Ferragosto', 'Celebration of the Assumption of Mary', 'Rome', '2024-08-15', '12:00', FALSE, 0.00, 8);

INSERT INTO "Event" (name, description, location, date, time, refundable, fee, created_by)
             VALUES ('Festa di Sant''Agata', 'One of the most important religious festivals', 'Catania', '2024-02-05', '08:00', TRUE, 0.00, 9);

INSERT INTO "Event" (name, description, location, date, time, refundable, fee, created_by)
             VALUES ('Notte Bianca', 'Night of culture, art, and entertainment', 'Florence', '2024-04-30', '18:00', FALSE, 0.00, 10);


-- PARTICIPATIONS

INSERT INTO "Participation" (user_id, event_id, paymentMethod)
                     VALUES (1, 2, 'CreditCard');

INSERT INTO "Participation" (user_id, event_id, paymentMethod)
                     VALUES (1, 4, 'CreditCard');

INSERT INTO "Participation" (user_id, event_id, paymentMethod)
                     VALUES (1, 8, 'CreditCard');

INSERT INTO "Participation" (user_id, event_id, paymentMethod)
                     VALUES (2, 1, 'CreditCard');

INSERT INTO "Participation" (user_id, event_id, paymentMethod)
                     VALUES (2, 10, 'CreditCard');

INSERT INTO "Participation" (user_id, event_id, paymentMethod)
                     VALUES (3, 6, 'PayPal');

INSERT INTO "Participation" (user_id, event_id, paymentMethod)
                     VALUES (3, 8, 'PayPal');

INSERT INTO "Participation" (user_id, event_id, paymentMethod)
                     VALUES (3, 19, 'PayPal');

INSERT INTO "Participation" (user_id, event_id, paymentMethod)
                     VALUES (3, 10, 'PayPal');

INSERT INTO "Participation" (user_id, event_id, paymentMethod)
                     VALUES (4, 3, 'CreditCard');

INSERT INTO "Participation" (user_id, event_id, paymentMethod)
                     VALUES (4, 5, 'CreditCard');

INSERT INTO "Participation" (user_id, event_id, paymentMethod)
                     VALUES (5, 1, 'PayPal');

INSERT INTO "Participation" (user_id, event_id, paymentMethod)
                     VALUES (6, 1, 'CreditCard');

INSERT INTO "Participation" (user_id, event_id, paymentMethod)
                     VALUES (6, 2, 'CreditCard');

INSERT INTO "Participation" (user_id, event_id, paymentMethod)
                     VALUES (6, 3, 'CreditCard');

INSERT INTO "Participation" (user_id, event_id, paymentMethod)
                     VALUES (7, 1, 'PayPal');

INSERT INTO "Participation" (user_id, event_id, paymentMethod)
                     VALUES (7, 2, 'PayPal');

INSERT INTO "Participation" (user_id, event_id, paymentMethod)
                     VALUES (7, 3, 'PayPal');

INSERT INTO "Participation" (user_id, event_id, paymentMethod)
                     VALUES (7, 6, 'PayPal');

INSERT INTO "Participation" (user_id, event_id, paymentMethod)
                     VALUES (7, 8, 'PayPal');

INSERT INTO "Participation" (user_id, event_id, paymentMethod)
                     VALUES (8, 7, 'CreditCard');

INSERT INTO "Participation" (user_id, event_id, paymentMethod)
                     VALUES (8, 9, 'CreditCard');

INSERT INTO "Participation" (user_id, event_id, paymentMethod)
                     VALUES (8, 10, 'CreditCard');

INSERT INTO "Participation" (user_id, event_id, paymentMethod)
                     VALUES (9, 1, 'PayPal');

INSERT INTO "Participation" (user_id, event_id, paymentMethod)
                     VALUES (9, 5, 'PayPal');

INSERT INTO "Participation" (user_id, event_id, paymentMethod)
                     VALUES (10, 2, 'CreditCard');

INSERT INTO "Participation" (user_id, event_id, paymentMethod)
                     VALUES (10, 6, 'CreditCard');


-- REQUESTS

INSERT INTO "Request" (user_id, description)
                VALUES (1, '| UPDATE | Event code: 1 | New time: 20:00 |');

INSERT INTO "Request" (user_id, description)
                VALUES (4, '| UPDATE | Event code: 4 | New date: 2024-05-15 |');

INSERT INTO "Request" (user_id, description)
                VALUES (7, '| CANCEL | Event code: 7 | New Fee: 0.00 |');

INSERT INTO "Request" (user_id, description)
                VALUES (9, '| UPDATE | Event code: 9 | Refundable: No |');