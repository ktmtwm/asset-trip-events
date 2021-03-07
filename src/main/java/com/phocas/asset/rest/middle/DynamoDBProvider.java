package com.phocas.asset.rest.middle;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperConfig;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.model.*;

import com.phocas.asset.rest.model.AssetEvent;
import org.slf4j.Logger;

import javax.annotation.Resource;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

public class DynamoDBProvider {
    @Resource
    private AmazonDynamoDB dynamoDB;

    @Resource
    private Logger logger;

    private Method testedMethod;

    public DynamoDBMapper getDynamoDBMapper() {
        return new DynamoDBMapper(dynamoDB);
    }

    public QueryRequest createQueryRequestFromExpression(
            DynamoDBMapper mapper,
            Class<AssetEvent> clazz, DynamoDBQueryExpression<AssetEvent> queryExpression)
            throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {

            testedMethod = DynamoDBMapper.class.getDeclaredMethod("createQueryRequestFromExpression",
                    Class.class, DynamoDBQueryExpression.class, DynamoDBMapperConfig.class);
            testedMethod.setAccessible(true);

            QueryRequest request = (QueryRequest) testedMethod.invoke(mapper,
                    clazz, queryExpression, DynamoDBMapperConfig.DEFAULT);

            return request;

    }

}
