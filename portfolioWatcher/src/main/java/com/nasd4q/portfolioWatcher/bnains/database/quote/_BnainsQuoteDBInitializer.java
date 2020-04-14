package com.nasd4q.portfolioWatcher.bnains.database.quote;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;


class _BnainsQuoteDBInitializer implements InitializingBean {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public void afterPropertiesSet() throws Exception {
        jdbcTemplate.update(_BnainsQuote.SCHEMA_SQL);
        logger.info("_BnainsQuote.SCHEMA_SQL executed");
    }
}
