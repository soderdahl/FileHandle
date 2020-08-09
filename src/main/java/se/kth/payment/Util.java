package se.kth.payment;

import java.math.BigDecimal;
import java.text.*;
import java.util.Date;

public class Util {

    public static BigDecimal convertStringToBigDecimal(String str) throws ParseException {
        DecimalFormatSymbols symbols = new DecimalFormatSymbols();
        symbols.setDecimalSeparator(',');
        String pattern = "#0.0#";
        DecimalFormat decimalFormat = new DecimalFormat(pattern, symbols);
        decimalFormat.setParseBigDecimal(true);
        BigDecimal bigDecimal = (BigDecimal) decimalFormat.parse(str);

        return bigDecimal;
    }


    public static Date convertStringToDate(String str) throws ParseException {
        DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        Date date = dateFormat.parse(str);

        return date;
    }
}
