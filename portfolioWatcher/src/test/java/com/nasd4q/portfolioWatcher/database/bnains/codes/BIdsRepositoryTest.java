package com.nasd4q.portfolioWatcher.database.bnains.codes;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class BIdsRepositoryTest {

    @Autowired
    private BIdsRepository repo;

    @Test
    public void loadEverything() {
        repo.loadIntoDb();
    }
}
