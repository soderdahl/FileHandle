package se.kth.payment.implementation;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class ReadFileTest {

    @Test
    void readFile() {
        String filePath = this.getClass().getClassLoader().getResource("Exempelfil_inbetalningstjansten.txt").getFile();
        File paymentFile = new File(filePath);
        Optional<List<String>> lines = ReadFile.readFile(paymentFile);
        assertNotNull(lines);
    }
}