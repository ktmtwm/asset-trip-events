package com.phocas.asset.rest.service;

import com.amazonaws.services.dynamodbv2.model.AmazonDynamoDBException;
import com.phocas.asset.rest.middle.AssetProvider;
import com.phocas.asset.rest.model.AssetEvent;
import io.swagger.annotations.ApiOperation;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.slf4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/event")
public class EventController {

	@Resource
	AssetProvider assetProvider;

	@Resource
	private Logger logger;

	@GetMapping("/asset/duration")
	// TODO add swagger
	@ApiOperation("Query events with Asset and duration")
	public List<AssetEvent> getEventAssetDuration(@RequestParam("asset") int asset,
												  @RequestParam(required=true) String startT,
												  @RequestParam(required=true) String endT) {
		List<AssetEvent> result = null;
		try {
			result = assetProvider.getEventAssetDuration(asset, startT, endT);
		} catch (AmazonDynamoDBException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
		return result;
	}

	@GetMapping("/id")
	public Optional<AssetEvent> getEventById(@RequestParam("id") String id) {
		try {
			return assetProvider.getEventById(id);
		} catch (AmazonDynamoDBException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	@GetMapping("/last")
	public List<AssetEvent> getLastEvent() {
		try {
			assetProvider.getLastEvent();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@GetMapping("/asset/trip")
	public List<AssetEvent> getEventAssetTrip(@RequestParam("asset") int asset,
											  @RequestParam("trip") int trip) {
		List<AssetEvent> result = null;
		try {
			result = assetProvider.getEventAssetTrip(asset, trip);
		} catch (AmazonDynamoDBException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
		return result;
	}

}
