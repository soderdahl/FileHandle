package se.kth.payment.implementation;

import se.kth.payment.Payment;
import se.kth.payment.PaymentBundle;
import se.kth.payment.PaymentParserInterface;

import java.io.File;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static se.kth.payment.Util.convertStringToBigDecimal;
import static se.kth.payment.Util.convertStringToDate;

public class BetalningserviceParser implements PaymentParserInterface {


    public PaymentBundle readPaymentBundle(File paymentFile) {
        List<String> lines = ReadFile.readFile(paymentFile).orElseThrow(() -> new RuntimeException("File could not be read!"));
        try {
            PaymentBundle paymentBundle = handlePost(lines);
            boolean controlTotalAmount = paymentBundle.controlTotalAmount(paymentBundle.getPaymentList(), paymentBundle);
            if (!controlTotalAmount) {
                System.out.println("Please check the specified total amount!");
                //throw new RuntimeException("The total amount is not correct!");
            }
            return paymentBundle;

        } catch (ParseException e) {
            throw new RuntimeException("Amount could not convert to BigDecimal!");
        }
    }

    private PaymentBundle handlePost(List<String> lines) throws ParseException {
        PaymentBundle paymentBundle = new PaymentBundle();
        List<Payment> paymentList = new ArrayList<>();
        for (int i = 0; i < lines.size(); i++) {
            String line = lines.get(i);
            // Handle open post,the first line of post starts with value "O" which includes clearing and account number,
            // amount of the whole post, payment date and currency
            if (line.startsWith("O")) {
                handleOpenPost(line, paymentBundle);
            }
            // Handle payment post, the lines starts with value "B", these includes amounts and references
            else if (line.startsWith("B")) {
                paymentList.add(handlePaymentPost(line));
            }
        }
        paymentBundle.setPaymentList(paymentList);
        return paymentBundle;
    }

    private void handleOpenPost(String line, PaymentBundle paymentBundle) throws ParseException {
        String accountNr = line.substring(1, 16);
        accountNr.trim();
        paymentBundle.setAccountNumber(accountNr);

        String sum = line.substring(16, 30);
        sum = sum.replaceAll("\\s", "");
        BigDecimal bigDecimal = convertStringToBigDecimal(sum);
        paymentBundle.setAmount(bigDecimal);

        String date = line.substring(40, 48);
        date = date.replaceAll("\\s", "");
        Date date1 = convertStringToDate(date);
        paymentBundle.setPaymentDate(date1);

        String currency = line.substring(48, 51);
        currency = currency.replaceAll("\\s", "");
        paymentBundle.setCurrency(currency);
    }

    private Payment handlePaymentPost(String line) throws ParseException {
        Payment payment = new Payment();
        String amount = line.substring(2, 15);
        BigDecimal bigDecimal = convertStringToBigDecimal(amount.replaceAll("\\s", ""));
        payment.setAmount(bigDecimal);
        String reference = line.substring(15, 50);
        payment.setReference(reference.replaceAll("\\s", ""));
        return payment;
    }


}
