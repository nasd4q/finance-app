package com.nasd4q.portfolioWatcher.databundles;

import javax.management.timer.TimerMBean;
import java.time.LocalDate;

/**
 * represents the membership of a given Stock to the cac40 index at a certain date.
 * Methods : getStock(), getDate()
 */
//All methods implicitly public in an interface, so you can omit the public modifier
//(https://docs.oracle.com/javase/tutorial/java/IandI/interfaceDef.html § The Interface Body)
public interface Cac40Member {
    Stock getStock();
    LocalDate getDate();

    /**
     * @return an immutable implementation of Cac40Member
     */
    static Cac40Member from(final Stock s, final LocalDate d) {
        //TODO choose an immutable implementation and return it
        // instead of creating this nested class...
        // because problem here is what if Stock s not immutable ??
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
