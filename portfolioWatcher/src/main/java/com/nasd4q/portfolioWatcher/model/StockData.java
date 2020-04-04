package com.nasd4q.portfolioWatcher.model;

import java.time.Duration;
import java.time.LocalDateTime;

/**
 * immutable class that stores a single piece of stock historical data
 */
public final class StockData {
    private final Stock stock; //Stock is immutable. So are LocalDateTime and Duration.
    private final LocalDateTime date;
    private final Duration duration; //high, low, open, close....relevant to when ?
    private final String unit;
    private final float open;
    private final float high;
    private final float low;
    private final float close;
    private final int volume;

    public StockData(Stock stock, LocalDateTime date, Duration duration, String unit, float open, float high, float low, float close, int volume) {
        this.stock = stock;
        this.date = date;
        this.duration = duration;
        this.unit = unit;
        this.open = open;
        this.high = high;
        this.low = low;
        this.close = close;
        this.volume = volume;
    }

    public Stock getStock() {
        return stock;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public Duration getDuration() {
        return duration;
    }

    public String getUnit() {
        return unit;
    }

    public float getOpen() {
        return open;
    }

    public float getHigh() {
        return high;
    }

    public float getLow() {
        return low;
    }

    public float getClose() {
        return close;
    }

    public int getVolume() {
        return volume;
    }
}
