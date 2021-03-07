# asset-trip-events
|    API                | 	  Param  				                    |  	Desc  	                                              |
| :--------:   	        | 	  :-----  			  	                | 	:----   	                                            |
| /event/asset/duration |    	asset, startTime, endTime     		|   asset as GSI Partition key, createdAt as GSI Sort key	|
| /event/id      	      |    	Id                      	      	|   Id as primary key(Auto Generated Key) 	|
| /event/last     	    |    	-    		                         	|   asset as GSI Partition key, createdAt as GSI Sort key |
|                       |                                       |   select with ScanIndexForward(false) && MaxResultSize(1)|
| /event/asset/trip    	|    	asset, trip			                  |  	Scan with FilterExpression                             |

### View Api server with:
##### * Get events with asset and duration:
		http://<ip>/event/asset/duration
		
<img src="https://github.com/ktmtwm/asset-trip-events/blob/main/result/asset_duration.png" width=800>

##### * Get events with asset and duration (wrong input, bad path):

<img src="https://github.com/ktmtwm/asset-trip-events/blob/main/result/asset_duration_bad_path.png" width=800>

##### * Get single event with an id:
		http://<ip>/event/id
		
<img src="https://github.com/ktmtwm/asset-trip-events/blob/main/result/id.png" width=800>


##### * Get single event with an id (wrong input, null result):
		
<img src="https://github.com/ktmtwm/asset-trip-events/blob/main/result/id_null.png" width=800>


##### * Get single event with asset and trip:
		http://<ip>/event/asset/trip
		
<img src="https://github.com/ktmtwm/asset-trip-events/blob/main/result/asset_trip.png" width=800>


##### * Local DyanmoDB logs:
		
<img src="https://github.com/ktmtwm/asset-trip-events/blob/main/result/dynamo_log.png" width=800>

