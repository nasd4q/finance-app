package com.nasd4q.portfolioWatcher.database.abcbourse.codes;

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
public class AIdsRepository {

    private static final File DATA_FILE =
            new File("src/main/resources/data/from www-abcbourse-com/libelles.csv");

    private final _AIdsRepository repository;

    @Autowired
    public AIdsRepository(_AIdsRepository repository) {
        this.repository = repository;
    }

    public void loadIntoDb() {
        repository.saveAll(dataAsList().stream().map(s-> _AIds.of(s)).collect(Collectors.toList()));
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
