package com.phocas.asset.rest;


import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.model.CreateTableRequest;
import com.amazonaws.services.dynamodbv2.model.ProvisionedThroughput;
import com.phocas.asset.rest.model.AssetEvent;
import com.phocas.asset.rest.repository.AssetRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.List;

import static org.assertj.core.internal.bytebuddy.matcher.ElementMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = AssetApplication.class)
@WebAppConfiguration
@ActiveProfiles("local")
@TestPropertySource(properties = {
        "amazon.dynamodb.endpoint=http://localhost:8000/",
        "amazon.aws.accesskey=AdaT",
        "amazon.aws.secretkey=AdaTKey" })
public class DynamoDBTest {

    private DynamoDBMapper dynamoDBMapper;

    @Autowired
    private AmazonDynamoDB amazonDynamoDB;

    @Autowired
    AssetRepository repository;

    private static final double EXPECTED_SPEED = 20.0;

    @Before
    public void setup() throws Exception {
        dynamoDBMapper = new DynamoDBMapper(amazonDynamoDB);

        CreateTableRequest tableRequest = dynamoDBMapper
                .generateCreateTableRequest(AssetEvent.class);
        tableRequest.setProvisionedThroughput(
                new ProvisionedThroughput(1L, 1L));
        amazonDynamoDB.createTable(tableRequest);

        //...

        dynamoDBMapper.batchDelete(
                (List<AssetEvent>)repository.findAll());
    }

    @Test
    public void givenItemWithExpectedSpeed_whenRunFindAll_thenItemIsFound() {
        AssetEvent eventInfo = new AssetEvent();
        repository.save(eventInfo);
        List<AssetEvent> result = (List<AssetEvent>) repository.findAll();

        // TODO mock data with assert
//        assertThat(result.size(), is(greaterThan(0)));
//        assertThat(result.get(0).getSpeed(), is(equalTo(EXPECTED_SPEED)));
    }
}