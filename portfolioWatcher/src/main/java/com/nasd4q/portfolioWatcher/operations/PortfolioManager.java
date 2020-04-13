package com.nasd4q.portfolioWatcher.operations;

import com.nasd4q.portfolioWatcher.databundles.Asset;
import com.nasd4q.portfolioWatcher.databundles.Portfolio;
import com.nasd4q.portfolioWatcher.operations.dependencies.QuoteRepository;

import java.time.LocalDate;
import java.util.Collection;

public class PortfolioManager {

    private final Portfolio portfolio;
    private final QuoteRepository quoteRepository;

    public PortfolioManager(Portfolio portfolio, QuoteRepository quoteRepository) {
        this.portfolio = portfolio;
        this.quoteRepository = quoteRepository;
    }

    /**
     * QUESTION : unit ?
     * @param date at which the porfolio is valued
     * @return the total value of the portfolio
     */
    public float getValue(LocalDate date) {
        Collection<Asset> assets = portfolio.getAssets(date);
        return assets.stream()
                .map(a->portfolio.getAmount(a,date)*quoteRepository.getValue(a,date))
                .reduce(0f, (f,g)->f+g);
    }
}
