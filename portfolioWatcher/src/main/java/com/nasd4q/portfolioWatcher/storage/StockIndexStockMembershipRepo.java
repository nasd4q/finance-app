package com.nasd4q.portfolioWatcher.storage;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface StockIndexStockMembershipRepo extends JpaRepository<StockIndexStockMembershipDAO,Long> {
}
