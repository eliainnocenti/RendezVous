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

        User user = new User(3, "Giovanni", "Verdi", 25, "gio.verdi7", "gio.verdi7@gmail.com", "giovanni7", "PayPal", "1", "gio.verdi7@gmail.com", "gio7");
        Event event = new Event("Infiorata di Genzano", "Artistic event with flower carpets", "Genzano", "2024-06-12", "09:00", true, 5.00f, 5);

        System.out.println("PayPalTest: payTest()");

        String simulatedUserInput = "yes\ngio7\n";
        InputStream savedStandardInputStream = System.in;
        System.setIn(new ByteArrayInputStream(simulatedUserInput.getBytes()));

        user.getPaymentMethod().pay(event);

        System.out.println();

    }

    @Test
    void refundTest() {

        User user = new User(3, "Giovanni", "Verdi", 25, "gio.verdi7", "gio.verdi7@gmail.com", "giovanni7", "PayPal", "1", "gio.verdi7@gmail.com", "gio7");
        Event event = new Event("Infiorata di Genzano", "Artistic event with flower carpets", "Genzano", "2024-06-12", "09:00", true, 5.00f, 5);

        System.out.println("PayPalTest: refundTest()");

        user.getPaymentMethod().refund(event);

        System.out.println();

    }

    @Test
    void getPaymentMethodTest() {

        PayPal payPal = new PayPal("Giovanni", "Verdi", "1", "gio.verdi7@gmail.com", "gio7");

        assertEquals("PayPal", payPal.getPaymentMethod());

    }

    @Test
    void getPaymentDataTest() {

        PayPal payPal = new PayPal("Giovanni", "Verdi", "1", "gio.verdi7@gmail.com", "gio7");

        assertEquals("Owner: Giovanni Verdi\nUnique code: 1\nAccount email: gio.verdi7@gmail.com\nAccount password: gio7", payPal.getPaymentData());

    }

}