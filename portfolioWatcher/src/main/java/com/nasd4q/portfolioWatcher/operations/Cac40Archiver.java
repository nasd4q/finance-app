package com.nasd4q.portfolioWatcher.operations;

import com.nasd4q.portfolioWatcher.datatypes.Cac40Member;
import com.nasd4q.portfolioWatcher.datatypes.Stock;
import com.nasd4q.portfolioWatcher.operations.dependencies.Cac40DataFetcher;
import com.nasd4q.portfolioWatcher.operations.dependencies.Cac40Repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.Period;
import java.util.Collection;
import java.util.stream.Collectors;

@Component
public class Cac40Archiver {

    private final Cac40DataFetcher cac40DataFetcher;
    private final Cac40Repository cac40Repository;

    @Autowired
    public Cac40Archiver(Cac40DataFetcher cac40DataFetcher, Cac40Repository cac40Repository) {
        this.cac40DataFetcher = cac40DataFetcher;
        this.cac40Repository = cac40Repository;
    }

    /**
     * Persists the composition of the Cac 40 index at a number of evenly spaced dates
     * @param from the first date for which to archive
     * @param until the date (excluded) until which to archive
     * @param step the amount of time between two archival dates
     */
    public void archiveComposition (LocalDate from, LocalDate until, Period step) {

        for (LocalDate current = from;
             current.compareTo(until) < 0;
             current = current.plus(step)) {

            final LocalDate dateToArchive = current;

            Collection<Stock> members = cac40DataFetcher.fetchMembers(dateToArchive);

            Collection<Cac40Member> cac40Members = members.stream()
                    .map(s -> Cac40Member.from(s, dateToArchive))
                    .collect(Collectors.toList());

            cac40Repository.addAll(cac40Members);
        }
    }
}

