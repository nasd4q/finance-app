package com.nasd4q.portfolioWatcher.database.abcbourse.codes;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class AIdsRepositoryTest {

    @Autowired
    private AIdsRepository repo;

    @Test
    public void loadEverything() {
        repo.loadIntoDb();
    }
}
