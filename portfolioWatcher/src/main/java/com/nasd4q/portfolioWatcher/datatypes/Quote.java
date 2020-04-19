package com.nasd4q.portfolioWatcher.datatypes;

import java.time.LocalDateTime;

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
