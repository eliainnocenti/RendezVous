package test.DomainModel;

import main.java.DomainModel.PayPal;
import main.java.DomainModel.User;
import main.java.DomainModel.Event;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

public class PayPalTest {

    @Test
    void payTest() {

        User user = new User(1, "Test name", "Test surname", 20, "Test email", "Test username", "Test password", "PayPal", "1", "Test account email", "Test account password");
        Event event = new Event("Test event", "Test description", "Test location", "Test date", "Test time", false, 10.0f, 1);

        System.out.println("PayPalTest: payTest()");

        String simulatedUserInput = "yes";
        InputStream savedStandardInputStream = System.in;
        System.setIn(new ByteArrayInputStream(simulatedUserInput.getBytes()));

        user.getPaymentMethod().pay(event);

        System.out.println();

    }

    @Test
    void refundTest() {

        User user = new User(1, "Test name", "Test surname", 20, "Test email", "Test username", "Test password", "PayPal", "1", "Test account email", "Test account password");
        Event event = new Event("Test event", "Test description", "Test location", "Test date", "Test time", false, 10.0f, 1);

        System.out.println("PayPalTest: refundTest()");

        user.getPaymentMethod().refund(event);

        System.out.println();

    }

    @Test
    void getPaymentMethodTest() {

        PayPal payPal = new PayPal("Test name", "Test surname", "1", "Test account email", "Test account password");

        assertEquals("PayPal", payPal.getPaymentMethod());

    }

    @Test
    void getPaymentDataTest() {

        PayPal payPal = new PayPal("Test name", "Test surname", "1", "Test account email", "Test account password");

        assertEquals("Owner: Test name Test surname\nUnique code: 1\nAccount email: Test account email\nAccount password: Test account password", payPal.getPaymentData());

    }

}