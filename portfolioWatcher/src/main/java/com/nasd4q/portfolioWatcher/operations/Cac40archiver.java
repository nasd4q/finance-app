package com.nasd4q.portfolioWatcher.operations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import java.time.LocalDate;
import java.time.Period;

public class Cac40archiver {
    public void archiveCac40Membership (LocalDate from, LocalDate until, Period step) {
        for (LocalDate current = from;
             current.compareTo(until) < 0;
             current = current.plus(step)) {
            //TODO
        }

    }
}
