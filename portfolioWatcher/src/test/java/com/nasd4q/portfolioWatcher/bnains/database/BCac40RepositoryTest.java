package com.nasd4q.portfolioWatcher.bnains.database;

import com.nasd4q.portfolioWatcher.databundles.Cac40Member;
import com.nasd4q.portfolioWatcher.databundles.Stock;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

@SpringBootTest
public class BCac40RepositoryTest {

    @Autowired
    private BCac40Repository repository;

    private Stock s1 = Stock.from("FR 123",
            "123 S",
            "SOCIETE 1");
    private Stock s2 = Stock.from("FR 456",
            "456 S",
            "ALFRED");
    private Stock s3 = Stock.from("3189123",
            "12390",
            "GIVENCHY");
    private Cac40Member member1 = Cac40Member.from(s1,
            LocalDate.of(2000, 3, 28));
    private Cac40Member member2 = Cac40Member.from(s2,
            LocalDate.of(1988, 6, 30));
    private Cac40Member member3 = Cac40Member.from(s3,
            LocalDate.of(2012, 12, 2));

    @Test
    public void addToDB() {
        repository.add(member1);
        repository.add(member2);
        repository.add(member3);
    }

    @Test
    public void selectAll() {
        repository.findAll().forEach(System.out::println);
    }

    @Test void addAndRetrieve() {
        addToDB();
        selectAll();
    }
}
