package com.nasd4q.portfolioWatcher.storage;

import java.sql.Date;

public interface StockIndexStockMembership {
    StockDAO getStock();

    StockIndexDAO getStockIndex();

    Date getDate();
}
