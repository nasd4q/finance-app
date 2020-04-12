package com.nasd4q.portfolioWatcher.database;

import com.nasd4q.portfolioWatcher.model.BnainsMemberOfCac40;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

//TODO - add method to batch-add to db

@Repository
public class BnainsMembershipRepository {
    private static final String SCHEMA_NAME = "data_from_bnains";
    private static Boolean alreadyInitialized = false;

    @Autowired
    private _DBManager dbManager;

    @Autowired
    private _SQLQueryResolver queryResolver;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /* TODO - potential bug to fix...
        This method set current schema to "data_from_bnains" and
        create table (if not exists) membersOfCac40.
        Should be called only once (static variable alreadyInitialized) per application launch
        Will this not break this repository if schema somehow switches back to public...?
     */
    private void initializeTable(boolean lazy) {
        if (alreadyInitialized && lazy)
            return;
        dbManager.createAndSetCurrentSchema(SCHEMA_NAME);
        jdbcTemplate.update(queryResolver.getCreateTable());
    }

    public void addToTable(BnainsMemberOfCac40 member) {
        initializeTable(true);
        jdbcTemplate.update(queryResolver.getInsertMembersOfCac40(),
                member.getCodeIsin(),
                member.getCodeSicovam(),
                member.getNom(),
                member.getDate());
    }

    public List<BnainsMemberOfCac40> selectAllFromTable() {
        initializeTable(true);
        return jdbcTemplate.query(queryResolver.getSelectMembersOfCac40(),
                (resultSet, row) -> new BnainsMemberOfCac40(
                        resultSet.getInt("row_id"),
                        resultSet.getString("code_isin"),
                        resultSet.getString("code_sicovam"),
                        resultSet.getString("nom"),
                        LocalDate.parse(resultSet.getString("date"),
                                DateTimeFormatter.ISO_LOCAL_DATE)));
    }

}
