package com.nasd4q.portfolioWatcher.databundles;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;

public interface Quote {
    Asset getAsset();
    Double getValue();
    LocalDateTime getOpenDateTime();
    LocalDateTime getCloseDateTime();
    Double getOpenValue();
    Double getHighValue();
    Double getLowValue();
    Double getCloseValue();
    Long getVolume();

}
