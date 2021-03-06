package com.phocas.asset.rest.service;

import com.phocas.asset.rest.middle.AssetProvider;
import com.phocas.asset.rest.model.AssetEvent;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/event")
public class EventController {

	@Resource
	AssetProvider assetProvider;

	@GetMapping("/asset/duration")
	// TODO add swagger
	@ApiOperation("Query events with Asset and duration")
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
	public Optional<AssetEvent> getEventById(@RequestParam("id") String id) {
		return assetProvider.getEventById(id);
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
