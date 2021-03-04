package com.phocas.asset.rest;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@SpringBootTest
@AutoConfigureMockMvc
public class AssetEventControllerTest {

	@Autowired
	private MockMvc mvc;

	@Test
	public void getEventAssetDuration() throws Exception {
		int asset = 0;
		// TODO pass with param
		mvc.perform(MockMvcRequestBuilders.get(String.format("/event/asset/duration"))
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				// TODO mock data with assert
				.andExpect(content().string(equalTo("")));
	}

	@Test
	public void getEventById() throws Exception {
		int id = 0;
		// TODO pass with param
		mvc.perform(MockMvcRequestBuilders.get(String.format("/event/id"))
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				// TODO mock data with assert
				.andExpect(content().string(equalTo("")));
	}

	@Test
	public void getLastEvent() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get(String.format("/event/last"))
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				// TODO mock data with assert
				.andExpect(content().string(equalTo("")));
	}

	@Test
	public void getEventAssetTrip() throws Exception {
		int asset = 0;
		int trip = 0;
		// TODO pass with param
		mvc.perform(MockMvcRequestBuilders.get(String.format("/event/asset/trip"))
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				// TODO mock data with assert
				.andExpect(content().string(equalTo("")));
	}
}
