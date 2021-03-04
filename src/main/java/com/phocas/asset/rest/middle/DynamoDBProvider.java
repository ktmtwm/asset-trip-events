package com.phocas.asset.rest.middle;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.model.AttributeDefinition;
import com.amazonaws.services.dynamodbv2.model.KeySchemaElement;
import com.amazonaws.services.dynamodbv2.model.KeyType;
import com.amazonaws.services.dynamodbv2.model.ProvisionedThroughput;
import com.amazonaws.services.dynamodbv2.model.ScalarAttributeType;

import org.slf4j.Logger;

import javax.annotation.Resource;
import java.util.Arrays;

public class DynamoDBProvider {
    @Resource
    private AmazonDynamoDB dynamoDB;

    @Resource
    private Logger logger;

//    public void creatTable() {
//        try {
//            logger.info("Attempting to create table; please wait...");
//            Table table = dynamoDB.createTable(tableName,
//                    Arrays.asList(new KeySchemaElement("year", KeyType.HASH), // Partition
//                            // key
//                            new KeySchemaElement("title", KeyType.RANGE)), // Sort key
//                    Arrays.asList(new AttributeDefinition("year", ScalarAttributeType.N),
//                            new AttributeDefinition("title", ScalarAttributeType.S)),
//                    new ProvisionedThroughput(10L, 10L));
//            table.waitForActive();
//            System.out.println("Success.  Table status: " + table.getDescription().getTableStatus());
//
//        }
//        catch (Exception e) {
//            System.err.println("Unable to create table: ");
//            System.err.println(e.getMessage());
//        }
//    }
//
//    public void query() {
//        AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard()
//                .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration("http://localhost:8000", "us-west-2"))
//                .build();
//
//        DynamoDB dynamoDB = new DynamoDB(client);
//
//        Table table = dynamoDB.getTable("Movies");
//
//        HashMap<String, String> nameMap = new HashMap<String, String>();
//        nameMap.put("#yr", "year");
//
//        HashMap<String, Object> valueMap = new HashMap<String, Object>();
//        valueMap.put(":yyyy", 1985);
//
//        QuerySpec querySpec = new QuerySpec().withKeyConditionExpression("#yr = :yyyy").withNameMap(nameMap)
//                .withValueMap(valueMap);
//
//        ItemCollection<QueryOutcome> items = null;
//        Iterator<Item> iterator = null;
//        Item item = null;
//
//        try {
//            System.out.println("Movies from 1985");
//            items = table.query(querySpec);
//
//            iterator = items.iterator();
//            while (iterator.hasNext()) {
//                item = iterator.next();
//                System.out.println(item.getNumber("year") + ": " + item.getString("title"));
//            }
//
//        }
//        catch (Exception e) {
//            System.err.println("Unable to query movies from 1985");
//            System.err.println(e.getMessage());
//        }
//
//        valueMap.put(":yyyy", 1992);
//        valueMap.put(":letter1", "A");
//        valueMap.put(":letter2", "L");
//
//        querySpec.withProjectionExpression("#yr, title, info.genres, info.actors[0]")
//                .withKeyConditionExpression("#yr = :yyyy and title between :letter1 and :letter2").withNameMap(nameMap)
//                .withValueMap(valueMap);
//
//        try {
//            System.out.println("Movies from 1992 - titles A-L, with genres and lead actor");
//            items = table.query(querySpec);
//
//            iterator = items.iterator();
//            while (iterator.hasNext()) {
//                item = iterator.next();
//                System.out.println(item.getNumber("year") + ": " + item.getString("title") + " " + item.getMap("info"));
//            }
//
//        }
//        catch (Exception e) {
//            System.err.println("Unable to query movies from 1992:");
//            System.err.println(e.getMessage());
//        }
//    }
}
