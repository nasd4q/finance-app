package com.nasd4q.portfolioWatcher.bnains.database.cac40;

import com.nasd4q.portfolioWatcher.bnains.database.cac40._SQLQueryResolver;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SQLQueryResolverTest {
    @Autowired
    _SQLQueryResolver queryResolver;

    @Test
    public void printQueries() {
        System.out.println(queryResolver.getCreateSchema());
        System.out.println(queryResolver.getCreateTable());
        System.out.println(queryResolver.getListTables());
        System.out.println(queryResolver.getSetSchema());
    }
}
