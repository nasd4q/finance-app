package com.nasd4q.portfolioWatcher.databundles;

public interface Stock extends Asset {
    String getCodeIsin();
    String getCodeSicovam();
    String getNom();

    // ******* static ******* //

    /**
     * @return an immutable version.
     */
    static Stock.Immutable from(Stock s) {
        if (s==null)
            return null;
        if (s.getClass()==Immutable.class)
            return (Stock.Immutable) s;
        return new Immutable(s.getCodeIsin(),s.getCodeSicovam(),s.getNom());
    }

    /**
     * @return a new immutable Stock.
     */
    static Stock.Immutable from(String codeIsin, String codeSicovam, String nom) {
        return new Immutable(codeIsin, codeSicovam, nom);
    }

    /**
     * Immutable basic implementation. Overrides toString().
     */
    static final class Immutable implements Stock{
        private final String codeIsin;
        private final String codeSicovam;
        private final String nom;

        private Immutable(String codeIsin, String codeSicovam, String nom) {
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
            return "Stock.Immutable{" +
                    "codeIsin='" + codeIsin + '\'' +
                    ", codeSicovam='" + codeSicovam + '\'' +
                    ", nom='" + nom + '\'' +
                    '}';
        }
    }
}
