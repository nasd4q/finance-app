package com.nasd4q.portfolioWatcher.dtbs;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class StateCheckerTest {

    @Autowired
    StateChecker dtbsChecker;

    @Test void printBasicDBInfos() {
        System.out.println("Current Schema : ");
        System.out.println(dtbsChecker.getCurrentSchema());
        System.out.println();

        System.out.println("Schemas :");
        dtbsChecker.listSchemas().forEach(System.out::println);
        System.out.println();

        System.out.println("Tables in current schema");
        dtbsChecker.listTablesInCurrentSchema().forEach(System.out::println);
    }
}
