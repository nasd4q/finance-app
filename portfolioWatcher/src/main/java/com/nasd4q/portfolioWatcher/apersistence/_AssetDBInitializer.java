package com.nasd4q.portfolioWatcher.apersistence;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
class _AssetDBInitializer implements InitializingBean {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public void afterPropertiesSet() throws Exception {
        jdbcTemplate.update(_Asset.SCHEMA_SQL);
        logger.info("_Asset.SCHEMA_SQL executed");
        jdbcTemplate.update(_Asset.ADD_CONSTRAINT_CHECK_IDENTIFICATION);
        logger.info("_Asset.ADD_CONSTRAINT_IDENTIFICATION executed");
        jdbcTemplate.update(_Asset.ADD_CONSTRAINT_UNIQUE_IDENTITY);
        logger.info("_Asset.ADD_CONSTRAINT_UNIQUE_IDENTITY executed");
    }
}
