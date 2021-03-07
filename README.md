# asset-trip-events
|    API                | 	  Param  				                    |  	Desc  	                                              |
| :--------:   	        | 	  :-----  			  	                | 	:----   	                                            |
| /event/asset/duration |    	asset, startTime, endTime     		|   asset as GSI Partition key, createdAt as GSI Sort key	|
| /event/id      	      |    	Id                      	      	|   Id as primary key(Auto Generated Key) 	|
| /event/last     	    |    	-    		                         	|   asset as GSI Partition key, createdAt as GSI Sort key |
|                       |                                       |   select with ScanIndexForward(false) && MaxResultSize(1)|
| /event/asset/trip    	|    	asset, trip			                  |  	Scan with FilterExpression                             |
