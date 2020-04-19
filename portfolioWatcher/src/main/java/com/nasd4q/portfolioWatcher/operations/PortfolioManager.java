package com.nasd4q.portfolioWatcher.operations;

import com.nasd4q.portfolioWatcher.datatypes.Asset;
import com.nasd4q.portfolioWatcher.datatypes.Portfolio;
import com.nasd4q.portfolioWatcher.operations.dependencies.QuoteRepository;

import java.time.LocalDateTime;
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
     * @param datetime at which the porfolio is valued
     * @return the total value of the portfolio
     */
    public Double getValue(LocalDateTime datetime) {
        Collection<Asset> assets = portfolio.getAssets(datetime);
        return assets.stream()
                .map(a->portfolio.getAmount(a,datetime)*quoteRepository.getValue(a,datetime))
                .reduce(0.0, (f,g)->f+g);
    }
}
