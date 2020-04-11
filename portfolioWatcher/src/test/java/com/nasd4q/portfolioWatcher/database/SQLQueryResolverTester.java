package com.nasd4q.portfolioWatcher.database;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SQLQueryResolverTester {
    @Autowired
    SQLQueryResolver queryResolver;

    @Test
    public void printQueries() {
        System.out.println(queryResolver.getCreateSchema());
        System.out.println(queryResolver.getCreateTable());
        System.out.println(queryResolver.getListTables());
        System.out.println(queryResolver.getSetSchema());
    }
}
