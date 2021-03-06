package com.phocas.asset.rest.middle;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
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

    public DynamoDBMapper getDynamoDBMapper() {
        return new DynamoDBMapper(dynamoDB);
    }


}
