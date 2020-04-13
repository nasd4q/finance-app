package com.nasd4q.portfolioWatcher.databundles;

import javax.management.timer.TimerMBean;
import java.time.LocalDate;

/**
 * represents the membership of a given Stock to the cac40 index at a certain date.
 * Methods : getStock(), getDate()
 */
public interface Cac40Member {
    Stock getStock();
    LocalDate getDate();

    /**
     * @return an object implementing Cac40Member, immutable IF passed Stock is immutable
     */
    static Cac40Member from(final Stock s, final LocalDate d) {
        return new Cac40Member() {
            @Override
            public Stock getStock() {
                return s;
            }

            @Override
            public LocalDate getDate() {
                return d;
            }
        };
    }
}
