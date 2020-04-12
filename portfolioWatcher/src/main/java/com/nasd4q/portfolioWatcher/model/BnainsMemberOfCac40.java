package com.nasd4q.portfolioWatcher.model;

import java.time.LocalDate;

/**
 * final class to represent rows of the table data_from_bnains.membersOfCac40
 */
/* Class is immutable :     (1) No mutators
                            (2) Can't be extended
                            (3) fields final
                            (4) fields private
                            (5) exclusive access to any mutable components (here none)
   (See Effective Java, Joshua Bloch, 2nd Edition, page 73)
 */
public final class BnainsMemberOfCac40 {
    private final Integer rowId;
    private final String codeIsin;
    private final String codeSicovam;
    private final String nom;
    private final LocalDate date;

    public BnainsMemberOfCac40(Integer rowId, String codeIsin, String codeSicovam, String nom, LocalDate date) {
        this.rowId = rowId;
        this.codeIsin = codeIsin;
        this.codeSicovam = codeSicovam;
        this.nom = nom;
        this.date = date;
    }

    public Integer getRowId() {
        return rowId;
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

    public LocalDate getDate() {
        return date;
    }

    @Override
    public String toString() {
        return "BnainsMemberOfCac40{" +
                "rowId=" + rowId +
                ", codeIsin='" + codeIsin + '\'' +
                ", codeSicovam='" + codeSicovam + '\'' +
                ", nom='" + nom + '\'' +
                ", date=" + date +
                '}';
    }
}
