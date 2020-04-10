package com.nasd4q.portfolioWatcher.storage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collection;

@Repository
public class StorageAccessor {
    ArrayList<StockDAO> stocks = new ArrayList<>();

    @Autowired
    StockRepo stockRepo;

    public Collection<Stock> findAllStocks() {
        return null;
    }
    public void addStock(Stock stock) {
    }
    public void deleteStock(Stock stock) {
    }

    public Stock createAStock() {
        StockDAO stockOne = new StockDAO("CODEISIN","CODESICOV","NOM INITIAL");
        stocks.add(stockOne);
        stockRepo.save(stockOne);
        return stockOne.immutable();
    }

    public void mutateStockOne() {

    }
}
