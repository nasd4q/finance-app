package com.nasd4q.portfolioWatcher.datatypes;

import java.time.LocalDateTime;
import java.util.Collection;

public interface Portfolio {
    Collection<Asset> getAssets(LocalDateTime date);
    Double getAmount(Asset a, LocalDateTime date);
}
