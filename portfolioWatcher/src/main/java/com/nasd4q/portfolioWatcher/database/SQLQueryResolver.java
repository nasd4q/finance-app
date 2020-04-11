package com.nasd4q.portfolioWatcher.database;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:sql.properties")
public class SQLQueryResolver {
    @Value("${query.list.tables}")
    private String listTables;
    @Value("${query.create.schema}")
    private String createSchema;
    @Value("${query.set.schema}")
    private String setSchema;
    @Value("${query.create.table}")
    private String createTable;

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
}
