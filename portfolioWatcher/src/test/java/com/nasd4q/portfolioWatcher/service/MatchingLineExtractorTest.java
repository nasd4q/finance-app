package com.nasd4q.portfolioWatcher.service;

import org.junit.jupiter.api.Test;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

public class MatchingLineExtractorTest {
    private MatchingLineExtractor lineExtractor = new MatchingLineExtractor();

    @Test
    void getAllLines() {
        Map<String, String> concurrentMap = lineExtractor.getLines(List.of("12050", "bouygues"), bnainFileSelector.getRelevantFiles());

        LoggerFactory.getLogger(this.getClass()).info("READY");
        concurrentMap
                .keySet()
                .stream()
                .sorted()
                .forEach(i-> System.out.println(concurrentMap.get(i)+" * " +
                    LocalDate.parse(i.substring(0,8), DateTimeFormatter.ofPattern("yyyyMMdd"))));
    }
}
