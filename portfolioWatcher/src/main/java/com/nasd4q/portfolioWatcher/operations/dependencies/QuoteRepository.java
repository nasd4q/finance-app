package com.nasd4q.portfolioWatcher.operations.dependencies;

import com.nasd4q.portfolioWatcher.datatypes.Asset;

import java.time.LocalDateTime;

public interface QuoteRepository {
    Double getValue(Asset asset, LocalDateTime datetime);
}
