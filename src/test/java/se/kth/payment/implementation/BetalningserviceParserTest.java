package se.kth.payment.implementation;

import se.kth.payment.PaymentBundle;

import java.io.File;
import java.text.ParseException;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class BetalningserviceParserTest {
BetalningserviceParser betalningserviceParser = new BetalningserviceParser();

    @org.junit.jupiter.api.Test
    void readPaymentBundle() {
        String filePath = this.getClass().getClassLoader().getResource("Exempelfil_betalningsservice.txt").getFile();
        File paymentFile = new File(filePath);
        Double num = 4711.17;
        PaymentBundle paymentBundle = betalningserviceParser.readPaymentBundle(paymentFile);
        assertNotNull(paymentBundle);
        assertEquals(4, paymentBundle.getPaymentList().size());
        assertEquals("5555 5555555555", paymentBundle.getAccountNumber());
        assertEquals("SEK", paymentBundle.getCurrency());
        assertEquals(num, paymentBundle.getAmount().doubleValue());

    }


}