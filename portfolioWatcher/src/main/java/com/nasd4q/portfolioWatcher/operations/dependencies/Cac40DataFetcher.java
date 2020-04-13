package com.nasd4q.portfolioWatcher.operations.dependencies;

import com.nasd4q.portfolioWatcher.databundles.Stock;

import java.time.LocalDate;
import java.util.Collection;

public interface Cac40DataFetcher {
    /**
     * @param date the date for which to return the composition
     * @return the composition of the Cac 40 index at a given date
     */
    Collection<Stock> fetchMembers(LocalDate date);
}
