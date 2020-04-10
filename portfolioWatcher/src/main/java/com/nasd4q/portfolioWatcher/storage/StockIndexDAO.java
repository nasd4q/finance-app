package com.nasd4q.portfolioWatcher.storage;

import javax.persistence.*;

/**
 * immutable class that represents a stock index like CAC 40
 */
@Entity
class StockIndexDAO implements StockIndex {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @Column(unique = true, nullable = true)
    private String nom;

    protected StockIndexDAO() {}

    public StockIndexDAO(String nom) {
        this.nom = nom;
    }

    @Override
    public String getNom() {
        return nom;
    }
}
