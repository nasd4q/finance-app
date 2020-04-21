package com.nasd4q.portfolioWatcher.database.asset;

import com.nasd4q.portfolioWatcher.datatypes.AssetIdentification;
import org.junit.jupiter.api.Test;

public class AssetIdentificationEnumTest {

    @Test
    public void isValidTest() {
        System.out.println(AssetIdentification.isValid("ISIN"));
        System.out.println(AssetIdentification.isValid("isin"));
        System.out.println(AssetIdentification.ISIN);
    }
}
