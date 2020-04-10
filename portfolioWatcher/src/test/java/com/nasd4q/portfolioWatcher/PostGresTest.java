package com.nasd4q.portfolioWatcher;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PostGresTest {


    @Autowired
    private JdbcTemplate jdbcTemplate;



    @Test
    public void shouldTestResourceFile_overridePropertyValues() throws SQLException {

        PreparedStatement preparedStatement = jdbcTemplate.getDataSource().getConnection().prepareStatement("SELECT current_database();");
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            System.out.println(resultSet.getString("current_database"));
        }
    }
}