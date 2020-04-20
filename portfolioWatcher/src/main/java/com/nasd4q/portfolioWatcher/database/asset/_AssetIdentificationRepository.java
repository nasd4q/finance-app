package com.nasd4q.portfolioWatcher.database.asset;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/*
    WARNING - depends on a table named asset.identifications in DB
 */

@Repository
interface  _AssetIdentificationRepository extends CrudRepository<_AssetIdentification, Long> {
    //TODO - test if we need to write that query
    @Query("SELECT * FROM assets.identifications WHERE asset_id = :assetId;")
    List<_AssetIdentification> findByAssetId(@Param("assetId") Long assetId);
}
