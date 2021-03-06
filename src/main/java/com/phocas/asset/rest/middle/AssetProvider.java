package com.phocas.asset.rest.middle;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBSaveExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.document.ItemCollection;
import com.amazonaws.services.dynamodbv2.document.QueryOutcome;
import com.amazonaws.services.dynamodbv2.document.spec.QuerySpec;
import com.amazonaws.services.dynamodbv2.document.utils.ValueMap;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.phocas.asset.rest.model.AssetEvent;
import com.phocas.asset.rest.repository.AssetRepository;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import java.util.*;

public class AssetProvider {
    @Resource
    private DynamoDBProvider dynamoDBProvider;

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
    public List<AssetEvent> getEventAssetDuration(int assetId, String start, String end) {
        DynamoDBMapper dynamoDBMapper = dynamoDBProvider.getDynamoDBMapper();

        String threadSubject = "DynamoDB Thread 1";
//        String partitionKey = String.format("%d#%s", assetId, threadSubject);
        String partitionKey = String.valueOf(assetId);

        Map<String, AttributeValue> eav = new HashMap<>();
        eav.put(":val1", new AttributeValue().withN(String.valueOf(assetId)));
        eav.put(":val2", new AttributeValue().withS(start));
        eav.put(":val3", new AttributeValue().withS(end));

        DynamoDBScanExpression scanExpression = new DynamoDBScanExpression()
                .withFilterExpression("asset = :val1 and created between :val2 and :val3")
                .withExpressionAttributeValues(eav);
//        DynamoDBQueryExpression<AssetEvent> queryExpression = new DynamoDBQueryExpression<AssetEvent>()
////                .withKeyConditionExpression("asset = :val1 and created between :val2 and :val3")
//                .withKeyConditionExpression("asset = :val1")
//                .withExpressionAttributeValues(eav);

//        List<AssetEvent> between = dynamoDBMapper.query(AssetEvent.class, queryExpression);
        List<AssetEvent> between = dynamoDBMapper.parallelScan(AssetEvent.class, scanExpression,
                Runtime.getRuntime().availableProcessors());
        return between;
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
