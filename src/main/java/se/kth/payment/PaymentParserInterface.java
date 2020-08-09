package se.kth.payment;

import java.io.File;

public interface PaymentParserInterface {

    PaymentBundle readPaymentBundle(File paymentFile);


}
