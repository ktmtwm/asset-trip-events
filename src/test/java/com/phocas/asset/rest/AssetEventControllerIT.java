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
    public void getEventAssetDuration() throws Exception {
        int asset = 0;
        ResponseEntity<AssetEvent> response = template.getForEntity(
                // TODO pass with param
                String.format("%s/asset/duration", base.toString()),
                AssetEvent.class);
        // TODO mock data with assert
        assertThat(response.getBody()).isEqualTo(null);
    }

    @Test
    public void getEventById() throws Exception {
        int id = 0;
        ResponseEntity<AssetEvent> response = template.getForEntity(
                // TODO pass with param
                String.format("%s/event/id", base.toString()),
                AssetEvent.class);
        // TODO mock data with assert
        assertThat(response.getBody()).isEqualTo(null);
    }

    @Test
    public void getLastEvent() throws Exception {
        ResponseEntity<AssetEvent> response = template.getForEntity(
                String.format("%s/event/last", base.toString()),
                AssetEvent.class);
        // TODO mock data with assert
        assertThat(response.getBody()).isEqualTo(null);
    }

    @Test
    public void getEventAssetTrip() throws Exception {
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
