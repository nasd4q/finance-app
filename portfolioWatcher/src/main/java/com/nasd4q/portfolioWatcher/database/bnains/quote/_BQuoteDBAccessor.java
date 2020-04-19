package com.nasd4q.portfolioWatcher.database.bnains.quote;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

public class _BQuoteDBAccessor {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public _BQuoteDBAccessor(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
}
