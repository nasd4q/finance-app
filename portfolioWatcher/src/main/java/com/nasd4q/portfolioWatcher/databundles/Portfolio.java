package com.nasd4q.portfolioWatcher.databundles;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;

public interface Portfolio {
    Collection<Asset> getAssets(LocalDateTime date);
    Double getAmount(Asset a, LocalDateTime date);
}
