package se.kth.payment;

import se.kth.payment.implementation.BetalningserviceParser;
import se.kth.payment.implementation.InbetalningstjanstenParser;
import se.kth.payment.implementation.PrintReceiver;

import java.io.File;

public class PaymentParserService {

    private static final String INBETALNIG_TJANSTEN = "_inbetalningstjansten.txt";
    private static final String BETALNING_SERVICE = "_betalningsservice.txt";

    private PaymentParserInterface betalningserviceParser = new BetalningserviceParser();
    private PaymentParserInterface inbetalningstjanstenParser = new InbetalningstjanstenParser();
    private PaymentReceiver paymentReceiver = new PrintReceiver();

    public boolean readPaymentFile(File paymentFile) {
        String fileName = paymentFile.getName();

        if (fileName.endsWith(INBETALNIG_TJANSTEN)) {
            callPaymentReceiver(inbetalningstjanstenParser.readPaymentBundle(paymentFile));
            return true;

        } else if (fileName.endsWith(BETALNING_SERVICE)) {
            callPaymentReceiver(betalningserviceParser.readPaymentBundle(paymentFile));
            return true;
        }
        return false;
    }

    private void callPaymentReceiver(PaymentBundle paymentBundle) {
        paymentReceiver.startPaymentBundle(paymentBundle.getAccountNumber(),
                paymentBundle.getPaymentDate(), paymentBundle.getCurrency());
        paymentBundle.getPaymentList().stream().forEach(payment ->
                paymentReceiver.payment(payment.getAmount(), payment.getReference()));
        paymentReceiver.endPaymentBundle();
    }


}
