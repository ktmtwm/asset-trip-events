package com.phocas.asset.rest;

import static org.assertj.core.api.Assertions.*;

import java.net.URL;

import com.phocas.asset.rest.model.AssetEvent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AssetEventControllerIT {

	@LocalServerPort
	private int port;

	private URL base;

	@Autowired
	private TestRestTemplate template;

    @BeforeEach
    public void setUp() throws Exception {
        this.base = new URL("http://localhost:" + port + "/event");
    }

    @Test
    public void TestGetEventAssetDuration() throws Exception {
        int asset = 0;
        ResponseEntity<AssetEvent> response = template.getForEntity(
                // TODO pass with param
                String.format("%s/asset/duration", base.toString()),
                AssetEvent.class);
        // TODO mock data with assert
        assertThat(response.getBody()).isEqualTo(null);
    }

    @Test
    public void TestGetEventById() throws Exception {
        String id = "e13c0bba-1357-4cff-8abc-22a249068dab";
        ResponseEntity<AssetEvent> response = template.getForEntity(
                String.format("%s/event/id?id=%s", base.toString(), id),
                AssetEvent.class);
        // TODO mock data insert and remove
        assertThat(response.getBody().getId()).isEqualTo(id);
    }

    @Test
    public void TestGetLastEvent() throws Exception {
        ResponseEntity<AssetEvent> response = template.getForEntity(
                String.format("%s/event/last", base.toString()),
                AssetEvent.class);
        // TODO mock data with assert
        assertThat(response.getBody()).isEqualTo(null);
    }

    @Test
    public void TestGetEventAssetTrip() throws Exception {
        int asset = 0;
        int trip = 0;
        ResponseEntity<AssetEvent> response = template.getForEntity(
                // TODO pass with param
                String.format("%s/event/asset/trip", base.toString()),
                AssetEvent.class);
        // TODO mock data with assert
        assertThat(response.getBody()).isEqualTo(null);
    }

}
