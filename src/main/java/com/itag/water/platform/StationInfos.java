/**
 * 
 */
package com.itag.water.platform;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.itag.water.platform.domain.Station;

/**
 * @author Soul
 * @email soullleo@hotmail.com
 * @date 2014年5月24日
 */
public class StationInfos {

	public static StationInfos instance = new StationInfos();

	private StationInfos() {
	}

	private Map<Integer, Station> stations = new ConcurrentHashMap<Integer, Station>();

	public void updateInfo(int stationId, String ip, int port) {

		Station station = stations.get(stationId);
		if (station == null) {
			station = new Station(stationId, ip, port);
			stations.put(stationId, station);
		}

		station.setIp(ip);
		station.setPort(port);

	}

	public Station getStationInfo(int stationId) {
		return stations.get(stationId);
	}

}
