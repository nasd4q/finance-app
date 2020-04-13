package com.nasd4q.portfolioWatcher.bnains.database;

import com.nasd4q.portfolioWatcher.bnains.entities.BCac40Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

@SpringBootTest
public class BnainsMembershipRepositoryTest {

    @Autowired
    private BnainsMembershipRepository repository;

    private BCac40Member member1 = new BCac40Member(
            null,
            "FR 123",
            "123 S",
            "SOCIETE 1",
            LocalDate.of(2000, 3, 28));
    private BCac40Member member2 = new BCac40Member(
            null,
            "FR 456",
            "456 S",
            "ALFRED",
            LocalDate.of(1988, 6, 30));
    private BCac40Member member3 = new BCac40Member(
            null,
            "3189123",
            "12390",
            "GIVENCHY",
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
