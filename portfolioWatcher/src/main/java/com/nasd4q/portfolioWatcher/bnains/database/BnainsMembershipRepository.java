package com.nasd4q.portfolioWatcher.bnains.database;

import com.nasd4q.portfolioWatcher.bnains.entities.BCac40Member;
import com.nasd4q.portfolioWatcher.bnains.entities.BStock;
import com.nasd4q.portfolioWatcher.databundles.Cac40Member;
import com.nasd4q.portfolioWatcher.operations.dependencies.Cac40Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.Iterator;


@Repository
public class BnainsMembershipRepository implements Cac40Repository {
    private static final String SCHEMA_NAME = "data_from_bnains";

    private static Boolean alreadyInitialized = false;

    private _DBManager dbManager;
    private _SQLQueryResolver queryResolver;
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public BnainsMembershipRepository(_DBManager dbManager, _SQLQueryResolver queryResolver, JdbcTemplate jdbcTemplate) {
        this.dbManager = dbManager;
        this.queryResolver = queryResolver;
        this.jdbcTemplate = jdbcTemplate;
        initializeTable(true);
    }

    private void initializeTable(boolean lazy) {
        if (alreadyInitialized && lazy)
            return;
        dbManager.createAndSetCurrentSchema(SCHEMA_NAME);
        jdbcTemplate.update(queryResolver.getCreateTable());
    }

    public void add(Cac40Member member) {
        jdbcTemplate.update(queryResolver.getInsertMembersOfCac40(),
                member.getStock().getCodeIsin(),
                member.getStock().getCodeSicovam(),
                member.getStock().getNom(),
                member.getDate());
    }

    @Override
    public Collection<Cac40Member> findAll() {
        return jdbcTemplate.query(queryResolver.getSelectMembersOfCac40(),
                (resultSet, row) -> new BCac40Member(
                        resultSet.getInt("row_id"),
                        new BStock(resultSet.getString("code_isin"),
                                resultSet.getString("code_sicovam"),
                                resultSet.getString("nom")),
                        LocalDate.parse(resultSet.getString("date"),
                                DateTimeFormatter.ISO_LOCAL_DATE)));
    }

    @Override
    public void addAll(Collection<Cac40Member> cac40Members) {
        Iterator<Cac40Member> iterator = cac40Members.iterator();
        String Query = queryResolver.getInsertMembersOfCac40();

        jdbcTemplate.batchUpdate(Query, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                Cac40Member next = iterator.next();
                ps.setString(1, next.getStock().getCodeIsin());
                ps.setString(2, next.getStock().getCodeSicovam());
                ps.setString(3, next.getStock().getNom());
                ps.setObject(4, next.getDate());
            }
            @Override
            public int getBatchSize() {
                return cac40Members.size();
            }
        });
        return;
    }
}
