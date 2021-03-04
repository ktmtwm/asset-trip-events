package com.phocas.asset.rest.service;

import com.phocas.asset.rest.model.AssetEvent;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/event")
public class EventController {

	@GetMapping("/asset/duration")
	// TODO add swagger
//	@ApiOperation("Get list of available event sets")
	public List<AssetEvent> getEventAssetDuration(@RequestParam("asset") int asset,
												  @RequestParam(required=true) long startT,
												  @RequestParam(required=true) long endT) {

		// TODO
		return null;
	}

	@GetMapping("/id")
	public AssetEvent getEventById(@RequestParam("id") int id) {
		// TODO
		return null;
	}

	@GetMapping("/last")
	public List<AssetEvent> getLastEvent() {
		// TODO
		return null;
	}

	@GetMapping("/asset/trip")
	public AssetEvent getEventAssetTrip(@RequestParam("asset") int asset,
										@RequestParam("trip") int trip) {
		// TODO
		return null;
	}

}
