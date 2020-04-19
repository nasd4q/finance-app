package com.nasd4q.portfolioWatcher.database.bnains.cac40;

import com.nasd4q.portfolioWatcher.datatypes.Cac40Member;
import com.nasd4q.portfolioWatcher.datatypes.Stock;
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
public class BCac40Repository implements Cac40Repository {
    private static final String SCHEMA_NAME = "data_from_bnains";

    private static Boolean alreadyInitialized = false;

    private _DBManager dbManager;
    private _SQLQueryResolver bnainsQueries;
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public BCac40Repository(_DBManager dbManager, _SQLQueryResolver queryResolver, JdbcTemplate jdbcTemplate) {
        this.dbManager = dbManager;
        this.bnainsQueries = queryResolver;
        this.jdbcTemplate = jdbcTemplate;
        initializeTable(true);
    }

    private void initializeTable(boolean lazy) {
        if (alreadyInitialized && lazy)
            return;
        dbManager.createAndSetCurrentSchema(SCHEMA_NAME);
        jdbcTemplate.update(bnainsQueries.getCreateTable());
    }

    public void add(Cac40Member member) {
        jdbcTemplate.update(bnainsQueries.getInsertMembersOfCac40(),
                member.getStock().getCodeIsin(),
                member.getStock().getCodeSicovam(),
                member.getStock().getNom(),
                member.getDate());
    }

    @Override
    public Collection<Cac40Member> findAll() {
        return jdbcTemplate.query(bnainsQueries.getSelectMembersOfCac40(),
                (resultSet, row) -> Cac40Member.from(
                        Stock.from(resultSet.getString("code_isin"),
                                resultSet.getString("code_sicovam"),
                                resultSet.getString("nom")),
                        LocalDate.parse(resultSet.getString("date"),
                                DateTimeFormatter.ISO_LOCAL_DATE)));
    }

    @Override
    public void addAll(Collection<Cac40Member> cac40Members) {
        Iterator<Cac40Member> iterator = cac40Members.iterator();
        String Query = bnainsQueries.getInsertMembersOfCac40();

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
