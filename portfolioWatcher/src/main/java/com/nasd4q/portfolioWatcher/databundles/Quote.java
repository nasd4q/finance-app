package com.nasd4q.portfolioWatcher.databundles;

import java.time.LocalDate;
import java.time.Period;

public interface Quote {
    Asset getAsset();
    float getValue();
    LocalDate getDate();
    Period getValidity();
}
