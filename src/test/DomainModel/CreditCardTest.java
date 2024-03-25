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

        User user = new User(1, "Test name", "Test surname", 20, "Test email", "Test username", "Test password", "Credit Card", "Test card number", "Test expiration date", "Test security code");
        Event event = new Event("Test event", "Test description", "Test location", "Test date", "Test time", false, 10.0f, 1);

        System.out.println("CreditCardTest: payTest()");

        String simulatedUserInput = "yes\nTest security code\n";
        InputStream savedStandardInputStream = System.in;
        System.setIn(new ByteArrayInputStream(simulatedUserInput.getBytes()));

        user.getPaymentMethod().pay(event);

        System.out.println();

    }

    @Test
    void refundTest() {

        User user = new User(1, "Test name", "Test surname", 20, "Test email", "Test username", "Test password", "Credit Card", "Test card number", "Test expiration date", "Test security code");
        Event event = new Event("Test event", "Test description", "Test location", "Test date", "Test time", false, 10.0f, 1);

        System.out.println("CreditCardTest: refundTest()");

        user.getPaymentMethod().refund(event);

        System.out.println();

    }

    @Test
    void getPaymentMethodTest() {

        CreditCard creditCard = new CreditCard("Test name", "Test surname", "Test card number", "Test expiration date", "Test security code");

        assertEquals("Credit Card", creditCard.getPaymentMethod());

    }

    @Test
    void getPaymentDataTest() {

        CreditCard creditCard = new CreditCard("Test name", "Test surname", "Test card number", "Test expiration date", "Test security code");

        assertEquals("Owner: Test name Test surname\nCard number: Test card number\nExpiration date: Test expiration date\nSecurity code: Test security code", creditCard.getPaymentData());

    }

}