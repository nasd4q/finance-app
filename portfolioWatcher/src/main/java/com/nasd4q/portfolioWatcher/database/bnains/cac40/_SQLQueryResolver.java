package com.nasd4q.portfolioWatcher.database.bnains.cac40;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:bnainsSQL/sql.properties")
class _SQLQueryResolver {
    @Value("${select.userCreatedTables}")
    private String listTables;
    @Value("${create.schema}")
    private String createSchema;
    @Value("${set.schema}")
    private String setSchema;
    @Value("${create.table}")
    private String createTable;
    @Value("${select.currentSchema}")
    private String selectCurrentSchema;
    @Value("${insert.membersOfCac40}")
    private String insertMembersOfCac40;
    @Value("${select.membersOfCac40}")
    private String selectMembersOfCac40;


    public String getListTables() {
        return listTables;
    }

    public String getCreateSchema() {
        return createSchema;
    }

    public String getSetSchema() {
        return setSchema;
    }

    public String getCreateTable() {
        return createTable;
    }

    public String getSelectCurrentSchema() {
        return selectCurrentSchema;
    }

    public String getInsertMembersOfCac40() {
        return insertMembersOfCac40;
    }

    public String getSelectMembersOfCac40() {
        return selectMembersOfCac40;
    }
}
