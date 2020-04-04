package com.nasd4q.portfolioWatcher.model;

import java.io.Serializable;

/**
 * immutable class that identifies a stock by way of codeIsin and/or codeSicovam and a name
 */
public final class Stock implements Serializable {
    private final String codeIsin;
    private final String codeSicovam;
    private final String nom;

    public Stock(String codeIsin, String codeSicovam, String nom) {
        this.codeIsin = codeIsin;
        this.codeSicovam = codeSicovam;
        this.nom = nom;
    }

    public String getCodeIsin() {
        return codeIsin;
    }

    public String getCodeSicovam() {
        return codeSicovam;
    }

    public String getNom() {
        return nom;
    }

    @Override
    public String toString() {
        return "Stock{" +
                "codeIsin='" + codeIsin + '\'' +
                ", codeSicovam='" + codeSicovam + '\'' +
                ", nom='" + nom + '\'' +
                '}';
    }
}
