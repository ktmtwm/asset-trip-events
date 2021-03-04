package com.phocas.asset.rest.middle;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.phocas.asset.rest.model.AssetEvent;
import com.phocas.asset.rest.repository.AssetRepository;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;

import javax.annotation.Resource;
import java.util.List;

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
//        List<AssetEvent> result = (List<AssetEvent>) repository.fi;
        return null;
    }

    public AssetEvent getEventById(int id) {
        // TODO
        return null;
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
