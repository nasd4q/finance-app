package com.nasd4q.portfolioWatcher.dtbs;

import com.nasd4q.portfolioWatcher.configuration.DbConfigResolver;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

@SpringBootTest
public class Manipulater {

    @Autowired
    DbConfigResolver dbConfigResolver;

    @Test
    public void addSchema() {

        String name = "data_from_bnains";

        int schemaCountBefore = -1;
        int schemaCountAfter = -1;

        try (Connection con = DriverManager.getConnection(
                dbConfigResolver.getUrl(),
                dbConfigResolver.getUser(),
                dbConfigResolver.getPassword());
             Statement st = con.createStatement()) {
            try (ResultSet rs = st.executeQuery(
                    "SELECT count(*) FROM information_schema.schemata;")) {
                if (rs.next()) {
                    schemaCountBefore = rs.getInt(1);
                }
            }

            st.executeUpdate("CREATE SCHEMA " + name + ";");

            try (ResultSet rs = st.executeQuery(
                    "SELECT count(*) FROM information_schema.schemata;")) {
                if (rs.next()) {
                    schemaCountAfter = rs.getInt(1);
                }
            }
        } catch (SQLException ex) {
            Logger lgr = java.util.logging.Logger.getLogger(Manipulater.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
        }

        System.out.println("Before : " + schemaCountBefore);
        System.out.println("After : " + schemaCountAfter);
    }
}
