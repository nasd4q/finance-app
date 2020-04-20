package com.nasd4q.portfolioWatcher.database.asset;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/*
    WARNING - depends on table name being "assets.assets"
 */

@Repository
interface _AssetRepository extends CrudRepository<_Asset, Long> {

    @Query("SELECT * FROM assets.assets WHERE UPPER(description) like " +
            "CONCAT('STOCK NAMED :%',UPPER(:name),'%');")
    List<_Asset> findByNameLike(@Param("name") String name);
}
