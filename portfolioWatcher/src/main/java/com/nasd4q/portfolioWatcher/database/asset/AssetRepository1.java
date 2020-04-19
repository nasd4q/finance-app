package com.nasd4q.portfolioWatcher.database.asset;

import com.nasd4q.portfolioWatcher.database.bnains.quote.dependencies.AssetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AssetRepository1 implements AssetRepository{

    private _AssetRepository assetRepository;
    private _AssetIdentificationRepository identificationRepository;

    @Autowired
    public AssetRepository1(_AssetRepository assetRepository, _AssetIdentificationRepository identificationRepository) {
        this.assetRepository = assetRepository;
        this.identificationRepository = identificationRepository;
    }

    public _Asset registerAsset(String description, AssetIdentification type, String value) {
        //TODO
        return null;
    }
}
