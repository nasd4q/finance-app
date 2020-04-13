package com.nasd4q.portfolioWatcher.databundles;

public interface Stock extends Asset {
    String getCodeIsin();
    String getCodeSicovam();
    String getNom();
}
