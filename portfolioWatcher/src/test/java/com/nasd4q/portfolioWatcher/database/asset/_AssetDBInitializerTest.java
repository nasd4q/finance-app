package com.nasd4q.portfolioWatcher.database.asset;

import com.nasd4q.portfolioWatcher.database.asset._AssetDBInitializer;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class _AssetDBInitializerTest {


    _AssetDBInitializer initializer;

    @Test
    public void seeIfTableIIndeedCreated() {

    }

    @Test
    public void seeConstraintStatements() {
        System.out.println(_AssetIdentification.ADD_CONSTRAINT_CHECK_TYPE);
        System.out.println(_AssetIdentification.ADD_CONSTRAINT_UNIQUE_IDENTITY);
    }


}
