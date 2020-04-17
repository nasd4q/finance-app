package com.nasd4q.portfolioWatcher.bnains.database.quote;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class QuoteRepositoryTest {

    @Autowired
    BQuoteRepository repository;

    @Test
    public void loadEverything() {
        repository.loadDataIntoDB();
    }



}
