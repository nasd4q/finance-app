package com.nasd4q.portfolioWatcher.operations;

import com.nasd4q.portfolioWatcher.datatypes.Asset;
import com.nasd4q.portfolioWatcher.datatypes.Portfolio;
import com.nasd4q.portfolioWatcher.operations.dependencies.QuoteRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Period;
import java.util.*;

@SpringBootTest
public class PortfolioManagerTest {

    @Autowired
    private QuoteRepository quoteRepository;

    private Portfolio basicPortfolio = new Portfolio() {
        List<Asset> assets = List.of();//TODO - put just one asset : stock for TOTAL)
        Map<Long, Double> amounts = Map.of(Long.valueOf(1), Double.valueOf(1.0));

        @Override
        public Collection<Asset> getAssets(LocalDateTime date) {
            return assets;
        }

        @Override
        public Double getAmount(Asset a, LocalDateTime date) {
            return amounts.get(a.getIdentifier());
        }
    };

    @Test
    public void printValueForStockTotal() {
        PortfolioManager manager = new PortfolioManager(basicPortfolio, quoteRepository);


        LocalDateTime current = null;
        Period aFortnight = Period.of(0, 0, 14);

        LocalDate start = LocalDate.of(1990, 01, 01);
        LocalDate until = LocalDate.of(2016, 01, 01);

        for (LocalDate d = start; d.compareTo(until)<0; d=d.plus(aFortnight)) {
            current = LocalDateTime.of(d, LocalTime.of(12, 00));
            manager.getValue(current);
        }

    }
}
