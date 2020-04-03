package com.nasd4q.portfolioWatcher.model;

public final class Stock {
    final private String codeIsin;
    final private String codeSicovam;
    final private String nom;

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
