package com.nasd4q.portfolioWatcher.database;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

@SpringBootTest
public class BasicSetupTest {

    @Autowired
    _DbConfigResolver dbConfigResolver;

    @Test
    public void printPostgresVersion() {

        try (Connection con = DriverManager.getConnection(
                dbConfigResolver.getUrl(),
                dbConfigResolver.getUser(),
                dbConfigResolver.getPassword());
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery("SELECT VERSION()")) {

            if (rs.next()) {
                System.out.println(rs.getString(1));
            }

        } catch (SQLException ex) {

            Logger lgr = java.util.logging.Logger.getLogger(BasicSetupTest.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
        }
    }
}