package com.nasd4q.portfolioWatcher.database.asset;

import com.nasd4q.portfolioWatcher.datatypes.Asset;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.util.Arrays;
import java.util.stream.Collectors;

@Table(_AssetIdentification.TABLE_NAME)
class _AssetIdentification {

    private static final Logger logger = LoggerFactory.getLogger(_AssetIdentification.class);

    static final String TABLE_NAME = "assets.identifications";
    static final String SCHEMA_SQL =
            "CREATE SCHEMA IF NOT EXISTS assets;\n" +
                    "\n" +
                    "CREATE TABLE IF NOT EXISTS assets.identifications(" +
                    "id serial PRIMARY KEY," +
                    "asset_id integer NOT NULL REFERENCES assets.assets (id)," +
                    "type text NOT NULL," +
                    "value text NOT NULL" +
                    ");";
    static final String ADD_CONSTRAINT_CHECK_TYPE =
            "ALTER TABLE assets.identifications ADD CONSTRAINT check_type CHECK(" +
                    "type in (" +
                    Arrays.stream(AssetIdentification.values())
                            .map(s -> "'" + s.toString() + "'")
                            .collect(Collectors.joining(", ")) +
                    "));";
    static final String ADD_CONSTRAINT_UNIQUE_IDENTITY =
            "ALTER TABLE assets.identifications ADD CONSTRAINT unique_identity UNIQUE(" +
                    "type, value);";
    static final String DROP_CONSTRAINT_CHECK_TYPE =
            "ALTER TABLE assets.identifications DROP CONSTRAINT check_type;";


    @Id
    private final Long id;
    @Column("asset_id")
    private final Long assetId;
    private final String type, value;

    public _AssetIdentification(Long id, Long assetId, String type, String value) {
        if (assetId == null || type == null || value == null) {
            throw new IllegalArgumentException(
                    "No _AssetIdentification with any null significant field");
        }
        if (!Arrays.stream(AssetIdentification.values()).anyMatch(t -> t.toString().equals(type))) {
            throw new IllegalArgumentException(
                    "_AssetIdentification type not recognized");
        }
        this.id = id;
        this.assetId = assetId;
        this.type = type;
        this.value = value;
    }

    public _AssetIdentification withId(Long id) {
        //logger.info("withId() called");
        return new _AssetIdentification(id, this.assetId, this.type, this.value);
    }

    @Override
    public String toString() {
        return "_AssetIdentification{" +
                "id=" + id +
                ", assetId=" + assetId +
                ", type='" + type + '\'' +
                ", value='" + value + '\'' +
                '}';
    }

    public static _AssetIdentification ofIsin(Long assetId, String isin) {
        return new _AssetIdentification(null, assetId,
                AssetIdentification.ISIN.toString(), isin);
    }

    public static _AssetIdentification ofSicovam(Long assetId, String sicovam) {
        return new _AssetIdentification(null, assetId,
                AssetIdentification.SICOVAM.toString(), sicovam);
    }

    public AssetIdentification getType() {
        return AssetIdentification.valueOf(this.type);
    }

    public String getValue() {
        return value;
    }
}
