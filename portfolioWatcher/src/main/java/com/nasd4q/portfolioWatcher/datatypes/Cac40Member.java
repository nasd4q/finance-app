package com.nasd4q.portfolioWatcher.datatypes;

import java.time.LocalDate;

/**
 * represents the membership of a given Stock to the cac40 index at a certain date.
 * Methods : getStock(), getDate().
 */
public interface Cac40Member {
    Stock getStock();
    LocalDate getDate();

    // ******* static ******* //

    /**
     * @return an immutable version.
     */
    static Cac40Member.Immutable from(Cac40Member cac40Member) {
        if (cac40Member==null)
            return null;
        if (cac40Member.getClass()==Immutable.class)
            return (Cac40Member.Immutable) cac40Member;
        return new Immutable(cac40Member.getStock(), cac40Member.getDate());
    }

    /**
     * @return a new immutable Cac40Member.
     */
    static Cac40Member.Immutable from(final Stock s, final LocalDate d) {
        return new Immutable(s,d);
    }

    /**
     * Immutable basic implementation. Overrides toString().
     */
    static final class Immutable implements Cac40Member {
        private final Stock.Immutable stock;
        private final LocalDate date;

        private Immutable(Stock stock, LocalDate date) {
            this.stock = Stock.from(stock);
            this.date = date;
        }

        @Override
        public Stock getStock() {
            return stock;
        }

        @Override
        public LocalDate getDate() {
            return date;
        }

        @Override
        public String toString() {
            return "Cac40Member.Immutable{" +
                    "stock=" + stock +
                    ", date=" + date +
                    '}';
        }
    }
}
