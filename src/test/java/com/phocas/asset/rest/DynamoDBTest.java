package com.phocas.asset.rest;


import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.model.CreateTableRequest;
import com.amazonaws.services.dynamodbv2.model.ProvisionedThroughput;
import com.phocas.asset.rest.model.AssetEvent;
import com.phocas.asset.rest.repository.AssetRepository;
import org.assertj.core.api.Assertions;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
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
    }

    @Ignore
    @Test
    public void TestCreateTable() {
        CreateTableRequest tableRequest = dynamoDBMapper
                .generateCreateTableRequest(AssetEvent.class);
        tableRequest.setProvisionedThroughput(
                new ProvisionedThroughput(1L, 1L));
        amazonDynamoDB.createTable(tableRequest);
    }

    @Ignore
    @Test
    public void TestBatchLoadData() throws IOException {
        dynamoDBMapper.batchSave(loadDataFile("/Users/tanwenmin/code/github/phocas/data.json"));
        List<AssetEvent> result = (List<AssetEvent>) repository.findAll();
        Assertions.assertThat(result.size()).isEqualTo(64735);
    }

    @Test
    public void givenItemWithExpectedSpeed_whenRunFindAll_thenItemIsFound() {
        DateTimeFormatter dtf = DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ss'Z'");
        DateTime dt = dtf.parseDateTime("2019-01-01T21:47:10Z");
        Date date = dt.toDate();
        AssetEvent eventInfo = new AssetEvent(null, 0, 0,
                                        172.58148129875363, -43.533591542067846,
                                        1.3606179935446998, date.getTime());
        repository.save(eventInfo);

        List<AssetEvent> result = (List<AssetEvent>) repository.findAll();
//         TODO mock data with assert
//        assertThat(result.size(), is(greaterThan(0)));
//        assertThat(result.get(0).getSpeed(), is(equalTo(EXPECTED_SPEED)));
    }

    @Ignore
    @Test
    public void TestCleanTableData() {
        // clean all data in table
        dynamoDBMapper.batchDelete(repository.findAll());
        List<AssetEvent> result = (List<AssetEvent>) repository.findAll();
        Assertions.assertThat(result.size()).isEqualTo(0);
    }

    @After
    public void after(){
    }

    /**
     *  Load all data in file
     * @param fname
     * @return
     * @throws IOException
     */
    private List<AssetEvent> loadDataFile(String fname) throws IOException {
        File file = new File(fname);
        BufferedReader br = new BufferedReader(new FileReader(file));
        String st;

        List<AssetEvent> result = new ArrayList<>();
        while ((st = br.readLine()) != null) {
            AssetEvent one = conToAssetEvent(st);
            if (one != null) {
                result.add(one);
            }
        }
        return result;
    }

    /**
     *  Convert from String to AssetEvent Obj
     * @param st
     * @return
     */
    private AssetEvent conToAssetEvent(String st) {
        JSONObject obj;
        try {
            obj = new JSONObject(st);

            DateTimeFormatter dtf = DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ss'Z'");
            DateTime dt = dtf.parseDateTime(obj.getString("createdAt"));
            Date date = dt.toDate();

            AssetEvent eventInfo = new AssetEvent(null, obj.getInt("asset"), obj.getInt("trip"),
                    obj.getDouble("x"), obj.getDouble("y"),
                    obj.getDouble("speed"), date.getTime());
            return eventInfo;

        } catch(JSONException ex) {
            return null;
        }
    }

}