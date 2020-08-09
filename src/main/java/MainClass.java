import se.kth.payment.PaymentParserService;

import java.io.File;

public class MainClass {
    public static void main(String[] args) {
        PaymentParserService paymentParserService = new PaymentParserService();

        for (int i = 0; i < args.length; i++) {
            paymentParserService.readPaymentFile(new File(args[i]));

        }

    }
}
