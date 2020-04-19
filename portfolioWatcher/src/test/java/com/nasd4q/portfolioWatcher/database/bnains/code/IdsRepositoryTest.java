package com.nasd4q.portfolioWatcher.database.bnains.code;

import com.nasd4q.portfolioWatcher.database.bnains.codes.IdsRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class IdsRepositoryTest {

    @Autowired
    private IdsRepository repo;

    @Test
    public void loadEverything() {
        repo.loadIntoDb();
    }
}
