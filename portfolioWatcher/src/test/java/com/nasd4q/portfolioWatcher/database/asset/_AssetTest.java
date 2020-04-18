package com.nasd4q.portfolioWatcher.database.asset;

import com.nasd4q.portfolioWatcher.database.asset._Asset;
import org.junit.jupiter.api.Test;

public class _AssetTest {

    @Test
    public void seeConstraintStatements() {
        System.out.println(_Asset.ADD_CONSTRAINT_CHECK_IDENTIFICATION);
        System.out.println(_Asset.ADD_CONSTRAINT_UNIQUE_IDENTITY);
    }
}
