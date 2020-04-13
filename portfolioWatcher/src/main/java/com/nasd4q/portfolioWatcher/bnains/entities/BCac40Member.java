package com.nasd4q.portfolioWatcher.bnains.entities;

import com.nasd4q.portfolioWatcher.databundles.Cac40Member;

import java.time.LocalDate;

/**
 * class to represent rows of the table data_from_bnains.membersOfCac40
 */
public final class BCac40Member implements Cac40Member {
    private final Integer rowId;
    private final BStock stock;
    private final LocalDate date;

    public BCac40Member(Integer rowId, BStock stock, LocalDate date) {
        this.rowId = rowId;
        this.stock = stock;
        this.date = date;
    }

    //FOR LEGACY PURPOSES
    public BCac40Member(Integer rowId, String codeIsin, String codeSicovam, String nom, LocalDate date) {
        this.rowId = rowId;
        this.stock = new BStock(codeIsin,codeSicovam,nom);
        this.date = date;
    }

    public Integer getRowId() {
        return rowId;
    }

    public BStock getStock() {
        return stock;
    }

    //FOR LEGACY PURPOSES
    public String getCodeIsin() {
        return this.stock.getCodeIsin();
    }

    public String getCodeSicovam() {
        return this.stock.getCodeSicovam();
    }

    public String getNom() {
        return this.stock.getNom();
    }

    public LocalDate getDate() {
        return date;
    }

    @Override
    public String toString() {
        return "BCac40Member{" +
                "rowId=" + rowId +
                ", stock=" + stock +
                ", date=" + date +
                '}';
    }
}
