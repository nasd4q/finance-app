package com.nasd4q.portfolioWatcher.databundles;

import java.time.LocalDate;
import java.util.Collection;

public interface Portfolio {
    Collection<Asset> getAssets(LocalDate date);
    Float getAmount(Asset a, LocalDate date);
}
