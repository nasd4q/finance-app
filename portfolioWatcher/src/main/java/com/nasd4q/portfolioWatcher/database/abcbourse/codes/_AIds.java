package com.nasd4q.portfolioWatcher.database.abcbourse.codes;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table(_AIds.TABLE_NAME)
class _AIds {
    private static final Logger logger = LoggerFactory.getLogger(_AIds.class);

    static final String TABLE_NAME = "abcbourse.codes";
    static final String SCHEMA_SQL =
            "CREATE SCHEMA IF NOT EXISTS abcbourse;\n" +
                    "\n" +
                    "CREATE TABLE IF NOT EXISTS abcbourse.codes(" +
                    "id serial PRIMARY KEY," +
                    "isin text," +
                    "nom text," +
                    "ticker text" +
                    ");";

    @Id
    private final Long id;
    private final String isin, nom, ticker;

    public _AIds(Long id, String isin, String nom, String ticker) {
        this.id = id;
        this.isin = isin;
        this.nom = nom;
        this.ticker = ticker;
    }

    public _AIds withId(Long id) {
        //logger.info("withId() called");
        return new _AIds(id, this.isin, this.nom, this.ticker);
    }

    public static _AIds of(String line) {
        final String[] splits = line.split(";");

        if (splits.length==2) {
            return new _AIds(null,
                    splits[0],
                    splits[1],
                    null);
        }

        return new _AIds(null,
                splits[0],
                splits[1],
                splits[2]);
    }

    @Override
    public String toString() {
        return "_Ids{" +
                "id=" + id +
                ", isin='" + isin + '\'' +
                ", nom='" + nom + '\'' +
                ", ticker='" + ticker + '\'' +
                '}';
    }
}
