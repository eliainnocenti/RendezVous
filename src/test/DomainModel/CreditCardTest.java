package test.DomainModel;

import main.java.DomainModel.CreditCard;
import main.java.DomainModel.User;
import main.java.DomainModel.Event;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

public class CreditCardTest {

    @Test
    void payTest() {

        User user = new User(1, "Mario", "Rossi", 30, "mario.rossi", "mario.rossi@gmail.com", "Password1", "Credit Card", "135895322", "2025-12-31", "473");
        Event event = new Event("Festa della Madonna Bruna", "Religious procession with a final firework show", "Matera", "2024-07-02", "20:00", true, 10.0f, 7);

        System.out.println("CreditCardTest: payTest()");

        String simulatedUserInput = "yes\n473\n";
        InputStream savedStandardInputStream = System.in;
        System.setIn(new ByteArrayInputStream(simulatedUserInput.getBytes()));

        user.getPaymentMethod().pay(event);

        System.out.println();

    }

    @Test
    void refundTest() {

        User user = new User(1, "Mario", "Rossi", 30, "mario.rossi", "mario.rossi@gmail.com", "Password1", "Credit Card", "135895322", "2025-12-31", "473");
        Event event = new Event("Festa della Madonna Bruna", "Religious procession with a final firework show", "Matera", "2024-07-02", "20:00", true, 10.0f, 7);

        System.out.println("CreditCardTest: refundTest()");

        user.getPaymentMethod().refund(event);

        System.out.println();

    }

    @Test
    void getPaymentMethodTest() {

        CreditCard creditCard = new CreditCard("Mario", "Rossi", "135895322", "2025-12-31", "473");

        assertEquals("Credit Card", creditCard.getPaymentMethod());

    }

    @Test
    void getPaymentDataTest() {

        CreditCard creditCard = new CreditCard("Mario", "Rossi", "135895322", "2025-12-31", "473");

        assertEquals("Owner: Mario Rossi\nCard number: 135895322\nExpiration date: 2025-12-31\nSecurity code: 473", creditCard.getPaymentData());

    }

}