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
}
