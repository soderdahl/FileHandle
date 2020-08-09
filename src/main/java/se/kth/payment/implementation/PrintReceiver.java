package se.kth.payment.implementation;

import se.kth.payment.PaymentReceiver;

import java.math.BigDecimal;
import java.util.Date;

public class PrintReceiver implements PaymentReceiver {
    @Override
    public void startPaymentBundle(String accountNumber, Date paymentDate, String currency) {
        System.out.println("Start a new PaymentBundle with" + "\n" + "Account number: " +
                accountNumber + "\n" + "Payment date: " + paymentDate + "\n" + "Currency: " + currency);
    }

    @Override
    public void payment(BigDecimal amount, String reference) {
        System.out.println("The payment is bundled with amount: " + amount + " reference: " + reference);
    }

    @Override
    public void endPaymentBundle() {
        System.out.println("The payment bundle is ended");
    }
}
