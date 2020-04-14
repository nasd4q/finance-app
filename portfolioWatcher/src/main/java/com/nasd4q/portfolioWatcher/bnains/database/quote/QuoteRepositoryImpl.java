package com.nasd4q.portfolioWatcher.bnains.database.quote;

import com.nasd4q.portfolioWatcher.databundles.Asset;
import com.nasd4q.portfolioWatcher.operations.dependencies.QuoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Component
public class QuoteRepositoryImpl implements QuoteRepository {

    private _BnainsQuoteRepository repository;

    @Autowired
    public QuoteRepositoryImpl(_BnainsQuoteRepository repository) {
        this.repository = repository;
    }

    public void loadDataFromBnains(File file){


    }

    @Override
    public double getValue(Asset asset, LocalDateTime datetime) {
        //TODO
        return 0;
    }

    private List<_BnainsQuote> fileToQuotes(String filename) {

    }

    private List<String> fileToList(String filename) {
        ArrayList<String> result = new ArrayList<>();

        try (Scanner s = new Scanner(new FileReader(filename))) {
            while (s.hasNext()) {
                result.add(s.nextLine());
            }
            return result;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return result;
    }
}
