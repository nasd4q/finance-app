package com.nasd4q.portfolioWatcher.database;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component //in order to make it Autowired-able
public class DBManager {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public String getCurrentSchema() {
        return jdbcTemplate
                .query("SELECT current_schema();",
                        (resultSet,rowNum)-> resultSet.getString(1))
                .get(0);
    }

    public List<String> listSchemas() {
        return jdbcTemplate
                .query(
                        "SELECT schema_name FROM information_schema.schemata;",
                        (resultSet,rowNum)->resultSet.getString(1));
    }

    public List<String> listTablesInCurrentSchema() {
        return jdbcTemplate
                .query(
                        "SELECT table_name FROM information_schema.tables " +
                                "WHERE table_schema = current_schema();",
                        (resultSet,rowNum)->resultSet.getString(1));
    }

    public void createAndSetCurrentSchema(String name) {
        jdbcTemplate
                .update("CREATE SCHEMA IF NOT EXISTS " + name + ";\n" +
                                "SET search_path= \"$user\", " + name + ";");
    }
}
