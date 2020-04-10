package com.nasd4q.portfolioWatcher.storage;

import javax.persistence.*;
import java.sql.Date;

/**
 * class that represents the membership of a stock to a stock index at a given date
 */
@Entity
//TODO unique constraint
//TODO index on stockindex
class StockIndexStockMembershipDAO implements StockIndexStockMembership {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @ManyToOne
    @JoinColumn(nullable = false, updatable = false)
    private StockDAO stock;

    @ManyToOne
    @JoinColumn(nullable = false, updatable = false)
    private StockIndexDAO stockIndex;

    @Column(nullable = false, updatable = false)
    private Date date;

    protected StockIndexStockMembershipDAO() {}

    public StockIndexStockMembershipDAO(StockDAO stock, StockIndexDAO stockIndex, Date date) {
        this.stock = stock;
        this.stockIndex = stockIndex;
        this.date = date;
    }

    @Override
    public StockDAO getStock() {
        return stock;
    }

    @Override
    public StockIndexDAO getStockIndex() {
        return stockIndex;
    }

    @Override
    public Date getDate() {
        return date;
    }
}
