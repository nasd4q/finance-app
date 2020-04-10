package com.nasd4q.portfolioWatcher.storage;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
interface StockRepo extends CrudRepository<StockDAO,Long> {
}
