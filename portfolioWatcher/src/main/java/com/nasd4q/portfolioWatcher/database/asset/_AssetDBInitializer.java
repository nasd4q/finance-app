package com.nasd4q.portfolioWatcher.database.asset;

import org.postgresql.util.PSQLException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
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
        jdbcTemplate.update(_AssetIdentification.SCHEMA_SQL);
        logger.info("_AssetIdentification.SCHEMA_SQL executed");
        try {
            jdbcTemplate.update(_AssetIdentification.ADD_CONSTRAINT_CHECK_TYPE);
            logger.info("_AssetIdentification.ADD_CONSTRAINT_CHECK_TYPE executed");
        } catch (DataAccessException e) {
            e.printStackTrace();
        }

        try {
            jdbcTemplate.update(_AssetIdentification.ADD_CONSTRAINT_UNIQUE_IDENTITY);
            logger.info("_AssetIdentification.ADD_CONSTRAINT_UNIQUE_IDENTITY executed");
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
    }
}
