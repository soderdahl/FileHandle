package se.kth.payment.implementation;

import java.io.*;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ReadFile {

    public static Optional<List<String>> readFile(File paymentFile) {
        try {
            FileInputStream fileInputStream = new FileInputStream(paymentFile);
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, Charset.forName("ISO-8859-1"));
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            List<String> collect = bufferedReader.lines().collect(Collectors.toList());
            bufferedReader.close();
            return Optional.of(collect);
        } catch (Exception e) {
            System.out.println("The file could not fund!");
        }
        return Optional.empty();
    }

}

