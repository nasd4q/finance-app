package com.nasd4q.portfolioWatcher.datafetch;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.LocalDate;

public class Cac40ListerTest {
    private Cac40Lister cac40Lister = new Cac40Lister();

    @Test
    public void getList() throws IOException {
        cac40Lister.getList(LocalDate.of(1999,1,1));
    }
}
