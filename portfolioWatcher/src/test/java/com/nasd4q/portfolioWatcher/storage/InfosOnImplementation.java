package com.nasd4q.portfolioWatcher.storage;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.lang.reflect.Method;

@RunWith(SpringRunner.class)
@SpringBootTest
public class InfosOnImplementation {

    @Autowired
    StockRepo stockRepo;

    @Test
    public void printStockRepoClass() throws ClassNotFoundException {
        System.out.println(stockRepo.getClass().toString());
    }
}
