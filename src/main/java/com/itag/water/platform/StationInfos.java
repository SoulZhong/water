/**
 * 
 */
package com.itag.water.platform;

import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.itag.water.platform.domain.DataFrame;
import com.itag.water.platform.domain.Station;

/**
 * @author Soul
 * @email soullleo@hotmail.com
 * @date 2014年5月24日
 */
public class StationInfos {

	public static StationInfos instance = new StationInfos();

	private StationInfos() {

		DataFrame dataFrame = new DataFrame();
		dataFrame.setTime(new Date());
		updateInfo(1, dataFrame);
		updateInfo(2, dataFrame);
		updateInfo(3, dataFrame);
		updateInfo(4, dataFrame);
		updateInfo(5, dataFrame);
	}

	private Map<Integer, Station> stations = new ConcurrentHashMap<Integer, Station>();

	public void updateInfo(int stationId, DataFrame dataFrame) {

		Station station = stations.get(stationId);
		if (station == null) {
			station = new Station(stationId);
			stations.put(stationId, station);
		}
		station.setLastDataFrame(dataFrame);

	}

	public Station getStationInfo(int stationId) {
		return stations.get(stationId);
	}

	public Map<Integer, Station> getStations() {
		return stations;
	}

}
