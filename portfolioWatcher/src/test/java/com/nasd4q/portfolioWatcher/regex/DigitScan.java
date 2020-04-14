package com.nasd4q.portfolioWatcher.regex;

import org.junit.jupiter.api.Test;

import java.util.Scanner;

public class DigitScan {
    @Test
    public void containsDigit() {
        String lineOne = "20160105.txt";
        if (lineOne.matches("^[0-9]{8}[\\s\\S]*"))
            System.out.println("matches");
    }
}
