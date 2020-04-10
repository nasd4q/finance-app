package com.nasd4q.portfolioWatcher.storage;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface StockIndexRepo extends JpaRepository<StockIndexDAO,Long> {
}
