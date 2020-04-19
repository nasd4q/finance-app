package com.nasd4q.portfolioWatcher.database.bnains.codes;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table(_Ids.TABLE_NAME)
class _Ids {
    private static final Logger logger = LoggerFactory.getLogger(
            _Ids.class);

    static final String TABLE_NAME = "data_from_bnains.codes";
    static final String SCHEMA_SQL =
            "CREATE SCHEMA IF NOT EXISTS data_from_bnains;\n" +
                    "\n" +
                    "CREATE TABLE IF NOT EXISTS data_from_bnains.codes(" +
                    "id serial PRIMARY KEY," +
                    "sicovam text," +
                    "mnemo text," +
                    "isin text," +
                    "libelle text," +
                    "code_euronext text," +
                    "mep text," +
                    "asset_id integer REFERENCES assets.assets (id)" +
                    ");";

    @Id
    private final Long id;
    private final String sicovam, mnemo, isin, libelle;
    @Column("code_euronext")
    private final String codeEuronext;
    private final String mep;
    @Column("asset_id")
    private final Long assetId;

    public _Ids(Long id, String sicovam, String mnemo, String isin, String libelle, String codeEuronext,
                String mep, Long assetId) {
        this.id = id;
        this.sicovam = sicovam;
        this.mnemo = mnemo;
        this.isin = isin;
        this.libelle = libelle;
        this.codeEuronext = codeEuronext;
        this.mep = mep;
        this.assetId = assetId;
    }

    public _Ids withId(Long id) {
        //logger.info("withId() called");
        return new _Ids(id, this.sicovam, this.mnemo, this.isin, this.libelle, this.codeEuronext,
                this.mep, this.assetId);
    }

    public static _Ids of(String line) {
        final String[] splits = line.split(";");
        return new _Ids(null,
                splits[0],
                splits[1],
                splits[2],
                splits[3],
                splits[4],
                splits[5],
                null);
    }

    @Override
    public String toString() {
        return "_Ids{" +
                "id=" + id +
                ", sicovam='" + sicovam + '\'' +
                ", mnemo='" + mnemo + '\'' +
                ", isin='" + isin + '\'' +
                ", libelle='" + libelle + '\'' +
                ", codeEuronext='" + codeEuronext + '\'' +
                ", mep='" + mep + '\'' +
                ", assetId=" + assetId +
                '}';
    }
}
