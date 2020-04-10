package com.nasd4q.portfolioWatcher.storage;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public interface Stock {
    String getCodeIsin();
    String getCodeSicovam();
    String getNom();
    String toString();
}
