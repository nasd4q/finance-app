package com.nasd4q.portfolioWatcher.abcbourse.datafetch;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

@SpringBootTest
public class _QuoteFetcherTest {

    @Autowired
    _QuoteFetcher fetcher;

    @Test
    public void seeIfJsLoaded() {
        System.out.println(fetcher.getJsSetDateAndClickFor(
                LocalDate.of(2002, 1, 23)
        ));
    }

    private void fetchMissing(int year, int trials) {
        int initialIntervalMillis = 300;
        int millisToAdd = 0;
        while (trials-->0) {
            try {
                Collection<LocalDate> dates = fetcher.getMissingDates(year);
                fetcher.downloadQuotes(dates, initialIntervalMillis, false);
                fetcher.getMissingDates(year).stream().sorted().forEach(mD->
                        System.out.println(mD.toString()+ " - " + mD.getDayOfWeek())
                );
            } catch (Exception e) {
                e.printStackTrace();
            }
            initialIntervalMillis = initialIntervalMillis + millisToAdd;
            millisToAdd = millisToAdd * 2 + 100;
        }
    }

    @Test
    public void seeWhatsMissing() {
        fetcher.getMissingDates(2001).stream().sorted().forEach(mD->
                System.out.println(mD.toString()+ " - " + mD.getDayOfWeek()));
    }

    @Test
    public void go() throws InterruptedException {
        fetchMissing(1989, 5);
    }

    @Test
    public void goAll() {
        for (int year = 1994; year>1985; year--) {
            fetchMissing(year, 6);
        }
    }

    @Test
    public void seeDownloadPath() throws IOException {
        fetcher.ensureDriverIsInitialized();
    }

    @Test
    public void downloadQuotesForADay() throws IOException, InterruptedException {
        seeWhatsMissing();
        fetcher.downloadQuotes(
                List.of(LocalDate.of(2001,9,28)),
                1000,
                true);
        seeWhatsMissing();
    }
}
