package se.kth.payment;

import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

class PaymentParserServiceTest {
    PaymentParserService paymentParserService = new PaymentParserService();

    @Test
    void readPaymentFileBetalningsservice() {
        String filePathBetalningsservice = this.getClass().getClassLoader().getResource("Exempelfil_betalningsservice.txt").getFile();
        File paymentFile = new File(filePathBetalningsservice);
        boolean b = paymentParserService.readPaymentFile(paymentFile);
        assertTrue(b);

    }

    @Test
    void readPaymentFileInbetalningstjansten() {
        String filePathInbetalningstjansten = this.getClass().getClassLoader().getResource("Exempelfil_inbetalningstjansten.txt").getFile();
        File paymentFile = new File(filePathInbetalningstjansten);
        boolean b = paymentParserService.readPaymentFile(paymentFile);
        assertTrue(b);
    }
}