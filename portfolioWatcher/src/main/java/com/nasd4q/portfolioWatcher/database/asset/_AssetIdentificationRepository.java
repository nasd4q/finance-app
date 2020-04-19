package com.nasd4q.portfolioWatcher.database.asset;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
interface  _AssetIdentificationRepository extends CrudRepository<_AssetIdentification, Long> {
}
