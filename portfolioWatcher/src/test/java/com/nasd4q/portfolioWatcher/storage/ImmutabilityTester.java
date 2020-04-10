package com.nasd4q.portfolioWatcher.storage;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.lang.instrument.Instrumentation;
import java.util.ArrayList;

@SpringBootTest
public class ImmutabilityTester {

    @Autowired
    private StorageAccessor storageAccessor;

    @Test
    void seeWhatHappens() throws InterruptedException {
        System.out.println("creation");

        ArrayList<Stock> localVersionOfStocks = new ArrayList<>();
        localVersionOfStocks.add(new Stock() {
            @Override
            public String getCodeIsin() {
                return "thist";
            }

            @Override
            public String getCodeSicovam() {
                return "lzekfjhlkf";
            }

            @Override
            public String getNom() {
                return "zlehvjlrh";
            }
        });

        localVersionOfStocks.add(StockMaker.from("a","b","aze"));

        int j=0;

        while (true) {
            System.out.println("i is" + j++);
            System.out.println("waiting 5");
            Thread.sleep(5000);
            System.out.println("generating");
            for (int i = 0; i < 1; i++) {
                localVersionOfStocks.add(this.storageAccessor.createAStock());
            }
            System.out.println("done generating");
            System.out.println(localVersionOfStocks.get(0).getClass().toString());
            System.out.println(localVersionOfStocks.get(1).getClass().toString());
            System.out.println(localVersionOfStocks.get(2).getClass().toString());


        }


    }

    static class StockMaker{
        static Stock from(String a, String b, String c) {
            return new Stock() {
                @Override
                public String getCodeIsin() {
                    return a;
                }

                @Override
                public String getCodeSicovam() {
                    return b;
                }

                @Override
                public String getNom() {
                    return c;
                }
            };
        }
    }
}
