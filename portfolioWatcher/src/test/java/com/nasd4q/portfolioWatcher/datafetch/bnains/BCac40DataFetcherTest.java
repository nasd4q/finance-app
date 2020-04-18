package com.nasd4q.portfolioWatcher.datafetch.bnains;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.LocalDate;

public class BCac40DataFetcherTest {
    private BCac40DataFetcher bCac40DataFetcher = new BCac40DataFetcher();

    @Test
    public void getList() throws IOException {
        bCac40DataFetcher.fetchMembers(LocalDate.of(1999,1,1));
    }
}
