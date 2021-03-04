package com.phocas.asset.rest.service;

import com.phocas.asset.rest.middle.AssetProvider;
import com.phocas.asset.rest.model.AssetEvent;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeoutException;


@RestController
@RequestMapping("/event")
public class EventController {

	@Resource
	AssetProvider assetProvider;

	@GetMapping("/asset/duration")
	// TODO add swagger
//	@ApiOperation("Get list of available event sets")
	public List<AssetEvent> getEventAssetDuration(@RequestParam("asset") int asset,
												  @RequestParam(required=true) Date startT,
												  @RequestParam(required=true) Date endT) {
//		try {
			assetProvider.getEventAssetDuration(asset, startT.getTime(), endT.getTime());
//		} catch (TimeoutException e) {
//			throw new ResponseStatusException(HttpStatus.REQUEST_TIMEOUT, e.getMessage());
//		}
		return null;
	}

	@GetMapping("/id")
	public AssetEvent getEventById(@RequestParam("id") int id) {
		assetProvider.getEventById(id);
		return null;
	}

	@GetMapping("/last")
	public List<AssetEvent> getLastEvent() {
		assetProvider.getLastEvent();
		return null;
	}

	@GetMapping("/asset/trip")
	public AssetEvent getEventAssetTrip(@RequestParam("asset") int asset,
										@RequestParam("trip") int trip) {
		assetProvider.getEventAssetTrip(asset, trip);
		return null;
	}

}
