package com.nasd4q.portfolioWatcher.apersistence;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.util.Arrays;
import java.util.stream.Collectors;

/* Spring data JDBC recommends, for generated - repository entites :
    - immutable objects
    - only one public all args contructor
    - withId method that allows to set Id from existing entity
 */
@Table(_Asset.TABLE_NAME)
class _Asset {

    private static final Logger logger = LoggerFactory.getLogger(_Asset.class);

    static final String[] identifications = {"ISIN"};
    static final String TABLE_NAME = "finance.assets";
    static final String SCHEMA_SQL =
            "CREATE SCHEMA IF NOT EXISTS finance;\n" +
                    "\n" +
                    "CREATE TABLE IF NOT EXISTS finance.assets(" +
                    "id serial PRIMARY KEY," +
                    "description text," +
                    "identification text," +
                    "identifier text" +
                    ");";
    static final String ADD_CONSTRAINT_CHECK_IDENTIFICATION =
            "ALTER TABLE finance.assets ADD CONSTRAINT check_identification CHECK(" +
                    "identification in (" +
                    Arrays.stream(identifications)
                            .map(s->"'" + s + "'")
                            .collect(Collectors.joining(", ")) +
                    "));";
    static final String ADD_CONSTRAINT_UNIQUE_IDENTITY =
            "ALTER TABLE finance.assets ADD CONSTRAINT unique_identity UNIQUE(" +
                    "identification, identifier);";


    @Id
    private final Long id;
    private final String description, identification, identifier;

    public _Asset(Long id, String description, String identification, String identifier) {
        this.id = id;
        this.description = description;
        this.identification = identification;
        this.identifier = identifier;
    }

    public _Asset withId(Long id) {
        logger.info("withId() called");
        return new _Asset(id, this.description, this.identification, this.identifier);
    }

    @Override
    public String toString() {
        return "_Asset{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", identification='" + identification + '\'' +
                ", identifier='" + identifier + '\'' +
                '}';
    }

    public static _Asset ofIsin(String isin, String description) {
        return new _Asset(null, description, "ISIN", isin);
    }
}
