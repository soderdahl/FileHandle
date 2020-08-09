package se.kth.payment.implementation;

import se.kth.payment.Payment;
import se.kth.payment.PaymentBundle;
import se.kth.payment.PaymentParserInterface;
import se.kth.payment.Util;

import java.io.File;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class InbetalningstjanstenParser implements PaymentParserInterface {

    @Override
    public PaymentBundle readPaymentBundle(File paymentFile) {
        List<String> lines = ReadFile.readFile(paymentFile).orElseThrow(() -> new RuntimeException("File could not be read!"));
        try {
            PaymentBundle paymentBundle = handlePost(lines);
            boolean controlTotalAmount = paymentBundle.controlTotalAmount(paymentBundle.getPaymentList(), paymentBundle);
            if (!controlTotalAmount) {
                System.out.println("Please check the specified total amount!");
                //throw new RuntimeException("The total amount is not corrected!");
            }
            return paymentBundle;
        } catch (ParseException e) {
            throw new RuntimeException("Amount could not convert to BigDecimal!");
        }
    }

    private PaymentBundle handlePost(List<String> lines) throws ParseException {
        PaymentBundle paymentBundle = new PaymentBundle();
        List<Payment> paymentList = new ArrayList<>();
        paymentBundle.setPaymentDate(new Date()); // could not find paymentDate from the file?
        paymentBundle.setCurrency("SEK");
        for (int i = 0; i < lines.size(); i++) {
            String line = lines.get(i);
            // Read open post,the first line of post starts with value "00" which includes clearing and account number
            if (line.startsWith("00")) {
                paymentBundle.setAccountNumber(handleOpenPost(line));
            }
            // Read payment post, the lines starts with value "30", these includes amounts and references
            else if (line.startsWith("30")) {
                paymentList.add(handlePaymentPost(line));
            }
            //  Read end post, the line start with value "99" which included amount of the whole post
            else if (line.startsWith("99")) {
                paymentBundle.setAmount(handleEndPost(line));
            }
        }
        paymentBundle.setPaymentList(paymentList);
        return paymentBundle;
    }

    private String handleOpenPost(String line) {
        String clearingNr = line.substring(10, 14).replaceFirst("^0+", "");
        String accountNr = line.substring(14, 24).replaceFirst("^0+", "");
        accountNr = clearingNr + " " + accountNr;
        return accountNr;
    }

    private Payment handlePaymentPost(String line) throws ParseException {
        Payment payment = new Payment();
        String amount = line.substring(3, 22).replaceFirst("^0+", "");
        String substringInteger = amount.substring(0, amount.length() - 2);
        String substringDecimal = amount.substring(amount.length() - 2);
        amount = substringInteger + "," + substringDecimal;
        BigDecimal bigDecimal = Util.convertStringToBigDecimal(amount);
        payment.setAmount(bigDecimal);
        String reference = line.substring(41, 65).replaceFirst("^0+", "");
        payment.setReference(reference);
        return payment;
    }

    private BigDecimal handleEndPost(String line) throws ParseException {
        String amount = line.substring(3, 22).replaceFirst("^0+", "");
        String substringInteger = amount.substring(0, amount.length() - 2);
        String substringDecimal = amount.substring(amount.length() - 2);
        String substring = substringInteger + "," + substringDecimal;
        BigDecimal totalAmount = Util.convertStringToBigDecimal(substring);
        return totalAmount;
    }
}
