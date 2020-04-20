package com.nasd4q.portfolioWatcher.database.bnains.quote;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

/*
    WARNING - depends on table named data_from_bnains.quotes
 */

interface _BQuoteRepository extends CrudRepository<_BQuote, Long> {

    @Query("SELECT * FROM data_from_bnains.quotes WHERE ( code = :isin OR code = :sicovam ) AND " +
            "date = (:date ::date)")
    Iterable<_BQuote> findByIsinOrSicovamAndDate(
            @Param("isin") String isin,
            @Param("sicovam") String sicovam,
            @Param("date") String date);
}
