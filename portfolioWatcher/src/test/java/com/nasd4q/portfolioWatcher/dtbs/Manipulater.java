package com.nasd4q.portfolioWatcher.dtbs;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;


@SpringBootTest
public class Manipulater {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Test
    public void addSchema() {

        String name = "data_from_bnains";

        jdbcTemplate
                .query(
                "SELECT count(*) FROM information_schema.schemata;",
                (resultSet,rowNum)->rowNum + "" + resultSet.getInt(1))
                .forEach(System.out::println);

        System.out.println("creating schema");
        jdbcTemplate.update("CREATE SCHEMA " + name + ";");

        jdbcTemplate
                .query(
                        "SELECT count(*) FROM information_schema.schemata;",
                        (resultSet,rowNum)->rowNum + "" + resultSet.getInt(1))
                .forEach(System.out::println);
    }

    @Test
    public void listSchemas() {
        jdbcTemplate
                .query(
                        "SELECT schema_name FROM information_schema.schemata;",
                        (resultSet,rowNum)->rowNum + " " + resultSet.getString(1))
                .forEach(System.out::println);
    }

    @Test
    public void listTablesInCurrentSchema() {
        jdbcTemplate
                .query(
                        "SELECT table_name FROM information_schema.tables " +
                                "WHERE table_schema = current_schema();",
                        (resultSet,rowNum)->rowNum + " " + resultSet.getString(1))
                .forEach(System.out::println);
    }

    @Test
    public void showContentTableMembers() {
        jdbcTemplate
                .query(
                        "SELECT * FROM members;",
                        (resultSet,rowNum)->rowNum + " " + resultSet.getString(1))
                .forEach(System.out::println);
    }
}
