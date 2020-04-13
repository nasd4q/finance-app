package com.nasd4q.portfolioWatcher.operations.dependencies;

import com.nasd4q.portfolioWatcher.databundles.Asset;

import java.time.LocalDateTime;

public interface QuoteRepository {
    float getValue(Asset asset, LocalDateTime datetime);
}
