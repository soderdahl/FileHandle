package se.kth.payment.implementation;

import org.junit.jupiter.api.Test;
import se.kth.payment.PaymentBundle;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

class InbetalningstjanstenParserTest {
    InbetalningstjanstenParser inbetalningstjanstenParser = new InbetalningstjanstenParser();

    @Test
    void readPaymentBundle() {
        String filePath = this.getClass().getClassLoader().getResource("Exempelfil_inbetalningstjansten.txt").getFile();
        File paymentFile = new File(filePath);
        Double amount = 15300.00;
        PaymentBundle paymentBundle = inbetalningstjanstenParser.readPaymentBundle(paymentFile);
        assertNotNull(paymentBundle);
        assertEquals("1234 1234567897", paymentBundle.getAccountNumber());
        assertEquals("SEK", paymentBundle.getCurrency());
        assertEquals(amount, paymentBundle.getAmount().doubleValue());
        assertEquals(3, paymentBundle.getPaymentList().size());
    }
}