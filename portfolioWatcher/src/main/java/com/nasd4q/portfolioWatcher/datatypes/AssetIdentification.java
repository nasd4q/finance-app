package com.nasd4q.portfolioWatcher.datatypes;

import java.util.Arrays;
import java.util.regex.Pattern;

public enum AssetIdentification {
    ISIN, SICOVAM;

    public static boolean isValid(String identification) {
        return Arrays.stream(AssetIdentification.values()).anyMatch(i->i.toString().equals(identification));
    }

    private static Pattern ISIN_PATTERN = Pattern.compile("[A-Z]{2}[A_Z0-9]{10}");

    public static boolean validIsin(String isin) {
        return ISIN_PATTERN.matcher(isin).matches();
    }

    private static Pattern SICOVAM_PATTERN = Pattern.compile("[1-9][0-9]*");

    public static boolean validSicovam(String sicovam) {
        return SICOVAM_PATTERN.matcher(sicovam).matches();
    }

    public static String trimLeadingZeroesOnSicovam(String sicovam) {
        if (sicovam==null)
            return null;
        while (sicovam.length()>1 && sicovam.charAt(0)=='0')
                sicovam = sicovam.substring(1);
        return sicovam;
    }
}
