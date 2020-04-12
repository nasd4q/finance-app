package com.nasd4q.portfolioWatcher.database;

import com.nasd4q.portfolioWatcher.model.BnainsMemberOfCac40;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

@SpringBootTest
public class BnainsMembershipRepositoryTest {

    @Autowired
    private BnainsMembershipRepository repository;

    private BnainsMemberOfCac40 member1 = new BnainsMemberOfCac40(
            null,
            "FR 123",
            "123 S",
            "SOCIETE 1",
            LocalDate.of(2000, 3, 28));
    private BnainsMemberOfCac40 member2 = new BnainsMemberOfCac40(
            null,
            "FR 456",
            "456 S",
            "ALFRED",
            LocalDate.of(1988, 6, 30));
    private BnainsMemberOfCac40 member3 = new BnainsMemberOfCac40(
            null,
            "3189123",
            "12390",
            "GIVENCHY",
            LocalDate.of(2012, 12, 2));

    @Test
    public void addToDB() {
        repository.addToTable(member1);
        repository.addToTable(member2);
        repository.addToTable(member3);
    }

    @Test
    public void selectAll() {
        repository.selectAllFromTable().forEach(System.out::println);
    }

    @Test void addAndRetrieve() {
        addToDB();
        selectAll();
    }
}
