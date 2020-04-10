package com.nasd4q.portfolioWatcher.storage;

import javax.persistence.*;

/**
 * immutable class that represents a stock
 */
@Entity
class StockDAO implements Stock {

    @Transient
    private final StockDAO that = this;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE) //One global sequence is fine
    private Long id;
    @Column(unique = true, nullable = true)
    private String codeIsin;
    @Column(unique = true, nullable = true)
    private String codeSicovam;
    @Column(unique = true, nullable = true)
    private String nom;

    protected StockDAO() {}

    public StockDAO(String codeIsin, String codeSicovam, String nom) {
        this.codeIsin = codeIsin;
        this.codeSicovam = codeSicovam;
        this.nom = nom;
    }

    @Override
    public String getCodeIsin() {
        return codeIsin;
    }

    @Override
    public String getCodeSicovam() {
        return codeSicovam;
    }

    @Override
    public String getNom() {
        return nom;
    }

    @Override
    public String toString() {
        return "StockDAO{" +
                ", id=" + id +
                ", codeIsin='" + codeIsin + '\'' +
                ", codeSicovam='" + codeSicovam + '\'' +
                ", nom='" + nom + '\'' +
                '}';
    }

    @Transient
    public Stock immutable = new Stock() {
            @Override
            public String getCodeIsin() {
                return that.getCodeIsin();
            }

            @Override
            public String getCodeSicovam() {
                return that.codeSicovam;
            }

            @Override
            public String getNom() {
                return that.getNom();
            }

            @Override
            public String toString() {
                return that.toString();
            }
    };

    public Stock immutable() { return immutable;}

    public void setNom(String nom) {
        this.nom = nom;
    }
}
