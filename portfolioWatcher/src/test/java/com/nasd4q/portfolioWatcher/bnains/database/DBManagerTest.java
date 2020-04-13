package com.nasd4q.portfolioWatcher.bnains.database;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class DBManagerTest {

    @Autowired
    _DBManager dbManager;

    @Test
    public void printBasicDBInfos() {
        System.out.println("Current Schema : ");
        System.out.println(dbManager.getCurrentSchema());
        System.out.println();

        System.out.println("Schemas :");
        dbManager.listSchemas().forEach(System.out::println);
        System.out.println();

        System.out.println("Tables in current schema");
        dbManager.listTablesInCurrentSchema().forEach(System.out::println);
    }

    @Test
    public void createAndSetCurrentSchema() {
        printBasicDBInfos();
        dbManager.createAndSetCurrentSchema("data_from_bnains");
        printBasicDBInfos();
    }
}
