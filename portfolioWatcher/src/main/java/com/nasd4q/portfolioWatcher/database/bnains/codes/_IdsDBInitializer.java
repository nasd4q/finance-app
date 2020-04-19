package com.nasd4q.portfolioWatcher.database.bnains.codes;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
class _IdsDBInitializer implements InitializingBean {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public void afterPropertiesSet() throws Exception {
        jdbcTemplate.update(_Ids.SCHEMA_SQL);
        logger.info("_Ids.SCHEMA_SQL executed");
    }
}
