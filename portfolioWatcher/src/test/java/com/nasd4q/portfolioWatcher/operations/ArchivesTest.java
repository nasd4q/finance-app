package com.nasd4q.portfolioWatcher.operations;

import com.nasd4q.portfolioWatcher.datatypes.Cac40Member;
import com.nasd4q.portfolioWatcher.operations.dependencies.Cac40Repository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.Period;
import java.util.Collection;

@SpringBootTest
public class ArchivesTest {

    @Autowired
    private Cac40Archiver cac40Archiver;

    @Autowired
    private Cac40Repository cac40Repository;

    @Test
    public void testArchiving() {
        System.out.println("-------Initial content of autowired cac40repository-------");
        cac40Repository.findAll().stream().forEach(System.out::println);
        System.out.println("-------Archiving...-------");
        cac40Archiver.archiveComposition(
                LocalDate.of(2000,1,1),
                LocalDate.of(2017,1,1),
                Period.of(0,1,0)
                );
        System.out.println("-------Subsequent content of autowired cac40repository-------");
        Collection<Cac40Member> repoContentAfter = cac40Repository.findAll();
        repoContentAfter.stream().forEach(System.out::println);
        System.out.println(repoContentAfter.size());
    }

    @Test
    public void testRegisteringAssets() {
        testArchiving();
        cac40Archiver.registerAllCac40MembersAsAssets();
    }
}
