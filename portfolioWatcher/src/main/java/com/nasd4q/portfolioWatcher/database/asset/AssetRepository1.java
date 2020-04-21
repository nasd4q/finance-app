package com.nasd4q.portfolioWatcher.database.asset;

import com.nasd4q.portfolioWatcher.datatypes.Asset;
import com.nasd4q.portfolioWatcher.datatypes.AssetIdentification;
import com.nasd4q.portfolioWatcher.datatypes.Stock;

import com.nasd4q.portfolioWatcher.operations.dependencies.AssetRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

/*
    ASSUMPTIONS FOR THIS CLASS TO WORK
        * asset_id of identifications table IS NOT NULL
            AND REFERENCES pk of assets table

 */

@Component
public class AssetRepository1 implements AssetRepository {

    private static final Logger logger = LoggerFactory.getLogger(AssetRepository.class);

    private _AssetRepository assetRepository;
    private _AssetIdentificationRepository identificationRepository;
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public AssetRepository1(_AssetRepository assetRepository, _AssetIdentificationRepository identificationRepository, JdbcTemplate jdbcTemplate) {
        this.assetRepository = assetRepository;
        this.identificationRepository = identificationRepository;
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    /**
     * Tries to find an asset matching the isin or sicovam of the stock.
     * If exactly one asset matching is found, then returns it.
     * If nothing found, then creates an asset with isin or sicovam provided if those are valid
     * and returns the asset
     */
    public Asset register(Stock s) {
        if (s==null)
            return null;
        List<Long> longs = assetIdsWithTypeAndValueIn(toIds(s));
        Set<Long> idsAsSet = longs.stream().filter(l -> l != null).collect(Collectors.toSet());

        if (idsAsSet.size() > 1)
            throw new IllegalStateException("Stock " + s + " matching multiple assets !");
        if (idsAsSet.size() == 1) {
            //logger.info("Asset found for stock " + s.getNom());
            return Asset.of(idsAsSet.iterator().next());
        }


        logger.info("Creating asset for stock " + s.getNom());
        _Asset save = assetRepository.save(_Asset.of("Stock named : " + s.getNom()));

        Long assetId = save.getIdentifier();

        String isin = s.getCodeIsin();
        if (AssetIdentification.validIsin(isin)) {
            logger.info("Asset " +s.getNom() + " registering isin " + isin);
            identificationRepository.save(
                    _AssetIdentification.ofIsin(assetId, isin));
        }

        String sicovam = AssetIdentification.trimLeadingZeroesOnSicovam(
                s.getCodeSicovam());
        if (AssetIdentification.validSicovam(sicovam)) {
            logger.info("Asset " +s.getNom() + " registering sicovam " + sicovam);
            identificationRepository.save(
                    _AssetIdentification.ofSicovam(assetId, sicovam));
        }

        return Asset.of(assetId);
    }

    @Override
    public Map<AssetIdentification, String> getIdsFor(Asset asset) {

        EnumMap<AssetIdentification, String> res =
                new EnumMap<AssetIdentification, String>(AssetIdentification.class);

        Long assetId = asset.getIdentifier();
        if (assetId !=null) {
            identificationRepository.findByAssetId(assetId)
                    .forEach(id -> res.put(id.getType(), id.getValue()));
        }

        return res;
    }

    @Override
    public List<Asset> findByNameLike(String name) {
        return assetRepository.findByNameLike(name).stream().map(a->(Asset)a).collect(Collectors.toList());
    }



    /* ******************************************************
     *******        private "helper" functions        *******
     ****************************************************** */

    /**
     *
     * @param ids a map of entries like : ISIN - "FR0000123456", SICOVAM - "9673" etc.
     * @return all asset_ids matching any of these entries from table for _AssetIdentification
     */
    private List<Long> assetIdsWithTypeAndValueIn(Map<AssetIdentification, String> ids) {
        // MAYBE NEED TO PREVENT SQL INJECTIONS
        if (ids == null)
            return Collections.EMPTY_LIST;

        Set<Map.Entry<AssetIdentification, String>> entries = ids.entrySet();

        if (entries.size()==0)
            return Collections.EMPTY_LIST;

        String whereClauseContent = entries.stream()
                .map(e -> String.format("(i.type = '%s' AND i.value = '%s')",
                        e.getKey().toString(), e.getValue()))
                .collect(Collectors.joining(" OR "));

        return jdbcTemplate.queryForList(
                String.format("SELECT asset_id FROM %s i WHERE %s",
                        _AssetIdentification.TABLE_NAME,
                        whereClauseContent), Long.class);
    }

    /**
     * Prepares an empty map. If getCodeIsin and/or getCodeSicovam returns a valid
     * ISIN / SICOVAM, then adds them to the returned map with the corresponding
     * AssetIdentification key.
     * Note - sicovam code get its leading zeroes trimmed before being inserted in map
     * @return A map with entries like ISIN - "FR0921784422", etc.
     */
    private Map<AssetIdentification, String> toIds(Stock s) {
        EnumMap<AssetIdentification, String> res =
                new EnumMap<AssetIdentification, String>(AssetIdentification.class);
        if (s==null)
            return res;
        String isin = s.getCodeIsin();
        if (AssetIdentification.validIsin(isin))
            res.put(AssetIdentification.ISIN, isin);
        String codeSicovam = AssetIdentification.trimLeadingZeroesOnSicovam(
                s.getCodeSicovam());
        if (codeSicovam != null)
            res.put(AssetIdentification.SICOVAM, codeSicovam);
        return res;
    }
}
