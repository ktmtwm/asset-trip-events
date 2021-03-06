package com.phocas.asset.rest.middle;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.phocas.asset.rest.model.AssetEvent;
import com.phocas.asset.rest.repository.AssetRepository;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

public class AssetProvider {
    @Resource
    private AmazonDynamoDB dynamoDB;

    @Resource
    private Logger logger;

    @Autowired
    AssetRepository repository;

    /**
     *
     * @param assetId
     * @param start
     * @param end
     * @return
     */
    public List<AssetEvent> getEventAssetDuration(int assetId, long start, long end) {

        return null;
    }

    public Optional<AssetEvent> getEventById(String id) {
        return repository.findById(id);
    }

    public List<AssetEvent> getLastEvent() {
        // TODO
        return null;
    }

    public AssetEvent getEventAssetTrip(int asset, int trip) {
        // TODO
        return null;
    }
}
