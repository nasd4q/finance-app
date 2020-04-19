package com.nasd4q.portfolioWatcher.database.asset;

import java.util.Arrays;

public enum AssetIdentification {
    ISIN;

    public static boolean isValid(String identification) {
        return Arrays.stream(AssetIdentification.values()).anyMatch(i->i.toString().equals(identification));
    }
}
