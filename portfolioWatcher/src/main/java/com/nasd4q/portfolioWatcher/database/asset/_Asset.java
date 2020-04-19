package com.nasd4q.portfolioWatcher.database.asset;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

/* Spring data JDBC recommends, for generated - repository entites :
    - immutable objects
    - only one public all args contructor
    - withId method that allows to set Id from existing entity
 */
@Table(_Asset.TABLE_NAME)
class _Asset {

    private static final Logger logger = LoggerFactory.getLogger(_Asset.class);

    static final String TABLE_NAME = "assets.assets";
    static final String SCHEMA_SQL =
            "CREATE SCHEMA IF NOT EXISTS assets;\n" +
                    "\n" +
                    "CREATE TABLE IF NOT EXISTS assets.assets(" +
                    "id serial PRIMARY KEY," +
                    "description text" +
                    ");";


    @Id
    private final Long id;
    private final String description;

    public _Asset(Long id, String description) {
        this.id = id;
        this.description = description;
    }

    public _Asset withId(Long id) {
        logger.info("withId() called");
        return new _Asset(id, this.description);
    }

    @Override
    public String toString() {
        return "_Asset{" +
                "id=" + id +
                ", description='" + description + '\'' +
                '}';
    }
}
