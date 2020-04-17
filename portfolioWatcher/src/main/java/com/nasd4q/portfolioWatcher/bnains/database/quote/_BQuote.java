package com.nasd4q.portfolioWatcher.bnains.database.quote;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDate;


/* Spring data JDBC recommends, for generated - repository entites :
    - immutable objects
    - only one public all args contructor
    - withId method that allows to set Id from existing entity
 */
@Table(_BQuote.TABLE_NAME)
public class _BQuote {

    private static final Logger logger = LoggerFactory.getLogger(
            _BQuote.class);

    static final String TABLE_NAME = "data_from_bnains.quotes";
    static final String SCHEMA_SQL =
            "CREATE SCHEMA IF NOT EXISTS data_from_bnains;\n" +
                    "\n" +
                    "CREATE TABLE IF NOT EXISTS data_from_bnains.quotes(" +
                    "id serial PRIMARY KEY," +
                    "code VARCHAR(30)," +
                    "nom text," +
                    "open decimal," +
                    "high decimal," +
                    "low decimal," +
                    "close decimal," +
                    "volume bigint," +
                    "date date" +
                    ");";

    @Id
    private final Long id;
    private final String code, nom;
    private final Double open, high, low, close;
    private final Long volume;
    private final LocalDate date;

    /* public all args unique constructor */
    public _BQuote(Long id, String code, String nom, Double open, Double high, Double low, Double close, Long volume, LocalDate date) {
        this.id = id;
        this.code = code;
        this.nom = nom;
        this.open = open;
        this.high = high;
        this.low = low;
        this.close = close;
        this.volume = volume;
        this.date = date;
    }


    /**
     * @param s de la forme
     *          3180	DEVANLAY S.A.	1288.00	1288.00	1230.00	1231.00	397
     */
    public static _BQuote of(String s, LocalDate date) {
        String[] parts = s.split("\\s+");

        int nomPartEndIndex = 2 + parts.length - 7;

        StringBuilder nom = new StringBuilder(parts[1]);
        for (int i = 2; i < nomPartEndIndex; i++)
            nom.append(" " + parts[i]);

        return new _BQuote(null,
                parts[0],
                nom.toString(),
                Double.valueOf(parts[parts.length - 5].replace(',','.')),
                Double.valueOf(parts[parts.length - 4].replace(',','.')),
                Double.valueOf(parts[parts.length - 3].replace(',','.')),
                Double.valueOf(parts[parts.length - 2].replace(',','.')),
                Long.valueOf(parts[parts.length - 1]),
                date);
    }

    /* recomended for spring data JDBC repositories */
    public _BQuote withId(Long id) {
        //logger.info("withId() called");
        return new _BQuote(id, this.code, this.nom, this.open,
                this.high, this.low, this.close, this.volume, this.date);
    }

    @Override
    public String toString() {
        return "_BnainsQuote{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", nom='" + nom + '\'' +
                ", open=" + open +
                ", high=" + high +
                ", low=" + low +
                ", close=" + close +
                ", volume=" + volume +
                ", date=" + date +
                '}';
    }
}
