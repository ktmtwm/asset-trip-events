package com.phocas.asset.rest.middle;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBSaveExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.document.*;
import com.amazonaws.services.dynamodbv2.document.spec.QuerySpec;
import com.amazonaws.services.dynamodbv2.document.utils.ValueMap;
import com.amazonaws.services.dynamodbv2.model.*;
import com.phocas.asset.rest.model.AssetEvent;
import com.phocas.asset.rest.repository.AssetRepository;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

public class AssetProvider {
    @Resource
    private DynamoDBProvider dynamoDBProvider;

    @Resource
    private AmazonDynamoDB dynamoDBClient;

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

    public List<AssetEvent> getLastEvent() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        DynamoDBMapper dynamoDBMapper = dynamoDBProvider.getDynamoDBMapper();

        //--------distinct asset---------

        //----------get MAX-------------
        QuerySpec querySpec = new QuerySpec();
        querySpec.withKeyConditionExpression("asset = :key")
                .withValueMap(new ValueMap()
                        .withInt(":key", 0))
//                 .withRangeKeyCondition(new RangeKeyCondition("created").ge("2019-01-15T18:57:10Z"))
                ;
        querySpec.withScanIndexForward(false);
        querySpec.withMaxResultSize(1);

        Table table = new DynamoDB(dynamoDBClient).getTable("AssetEvent");
        ItemCollection<QueryOutcome> items = table.query(querySpec);

        Iterator<Item> iterator = items.iterator();
        Item item = null;
        while (iterator.hasNext()) {
            item = iterator.next();
            System.out.println(item.toJSONPretty());
        }
        return null;
    }

    public List<AssetEvent> getEventAssetTrip(int asset, int trip) {
        DynamoDBMapper dynamoDBMapper = dynamoDBProvider.getDynamoDBMapper();

        String partitionKey = String.valueOf(asset);

        Map<String, AttributeValue> eav = new HashMap<>();
        eav.put(":val1", new AttributeValue().withN(String.valueOf(asset)));
        eav.put(":val2", new AttributeValue().withN(String.valueOf(trip)));

        DynamoDBScanExpression scanExpression = new DynamoDBScanExpression()
                .withFilterExpression("asset = :val1 and trip = :val2")
                .withExpressionAttributeValues(eav);
        List<AssetEvent> between = dynamoDBMapper.parallelScan(AssetEvent.class, scanExpression,
                Runtime.getRuntime().availableProcessors());
        return between;
    }
}
