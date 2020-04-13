package com.nasd4q.portfolioWatcher.bnains.entities;

import com.nasd4q.portfolioWatcher.databundles.Stock;

public final class BStock implements Stock {
    private final String codeIsin;
    private final String codeSicovam;
    private final String nom;

    public BStock(String codeIsin, String codeSicovam, String nom) {
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
