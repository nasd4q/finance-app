package com.nasd4q.portfolioWatcher.operations;

import com.nasd4q.portfolioWatcher.datatypes.Asset;
import com.nasd4q.portfolioWatcher.datatypes.Portfolio;
import com.nasd4q.portfolioWatcher.operations.dependencies.QuoteRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class PortfolioManager {
    private static final Logger logger = LoggerFactory.getLogger(PortfolioManager.class);

    private final Portfolio portfolio;
    private final QuoteRepository quoteRepository;

    public PortfolioManager(Portfolio portfolio, QuoteRepository quoteRepository) {
        this.portfolio = portfolio;
        if (quoteRepository==null) {
            throw new IllegalStateException("PortfolioManager must have a non null" +
                    "quoteRepository");
        }
        this.quoteRepository = quoteRepository;
    }

    /**
     * QUESTION : unit ?
     * @param datetime at which the porfolio is valued
     * @return the total value of the portfolio
     */
    public Double getValue(LocalDateTime datetime) {
        Collection<Asset> assets = portfolio.getAssets(datetime);

        Map<Long, Double> values = new HashMap<>();
        for (Asset a : assets) {
            Double value = quoteRepository.getValue(a, datetime);
            if (value==null) {
                logger.info("Was unable to obtain value for asset with id "
                        + a.getIdentifier() + " at time " + datetime);
                return null;
            }
            values.put(a.getIdentifier(), value);
        }

        return assets.stream()
                .map(a->portfolio.getAmount(a,datetime)*values.get(a.getIdentifier()))
                .reduce(0.0, (f,g)->f+g);
    }
}
