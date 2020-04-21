package com.nasd4q.portfolioWatcher.operations.dependencies;

import com.nasd4q.portfolioWatcher.datatypes.AssetIdentification;
import com.nasd4q.portfolioWatcher.datatypes.Asset;
import com.nasd4q.portfolioWatcher.datatypes.Stock;

import java.util.List;
import java.util.Map;

public interface AssetRepository {
    //TODO getAssetFor also updates asset ids if necessary
    /**
     * Tries to find an asset matching the isin or sicovam of the stock.
     * If exactly one asset matching is found, then returns it.
     * If nothing found, then creates an asset with isin or sicovam provided if those are valid
     * and returns the asset
     */
    Asset register(Stock s);

    /**
     * @return A map containing all known ids for the given asset
     */
    Map<AssetIdentification, String> getIdsFor(Asset asset);

    /**
     * @param name case insensitive name.
     * @return a list of assets mentioning name in description.
     */
    List<Asset> findByNameLike(String name);
}
