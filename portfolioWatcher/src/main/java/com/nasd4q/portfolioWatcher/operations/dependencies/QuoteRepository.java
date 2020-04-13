package com.nasd4q.portfolioWatcher.operations.dependencies;

import com.nasd4q.portfolioWatcher.databundles.Asset;
import com.nasd4q.portfolioWatcher.databundles.Quote;

import java.time.LocalDate;

public interface QuoteRepository {
    float getValue(Asset asset, LocalDate date);
    void add(Quote quote);
}
