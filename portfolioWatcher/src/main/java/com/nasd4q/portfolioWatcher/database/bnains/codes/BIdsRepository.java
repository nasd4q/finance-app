package com.nasd4q.portfolioWatcher.database.bnains.codes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

@Repository
public class BIdsRepository {

    private static final File DATA_FILE =
            new File("src/main/resources/data from www-bnains-org/codes/conv_isin.csv");

    private final _BIdsRepository repository;

    @Autowired
    public BIdsRepository(_BIdsRepository repository) {
        this.repository = repository;
    }

    public void loadIntoDb() {
        repository.saveAll(dataAsList().stream().map(s-> _BIds.of(s)).collect(Collectors.toList()));
    }

    private List<String> dataAsList() {
        ArrayList<String> result = new ArrayList<>();

        try (Scanner s = new Scanner(new FileReader(DATA_FILE))) {
            if (s.hasNext())
                s.nextLine();
            while (s.hasNext()) {
                result.add(s.nextLine());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return result;
    }
}
