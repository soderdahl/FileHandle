package se.kth.payment;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class UtilTest {

    @Test
    void convertStringToBigDecimal() throws ParseException {
        String num = "1234,567";
        BigDecimal bigDecimal = Util.convertStringToBigDecimal(num);
        assertEquals(1234.567, bigDecimal.doubleValue());
    }

    @Test
    void convertStringToDate() throws ParseException {
        String date = "20110315";
        Date date1 = Util.convertStringToDate(date);
        assertEquals("Tue Mar 15 00:00:00 CET 2011", date1.toString());

    }
}