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

		updateInfo(1, "1.1.1.1", 2020, 444, 222, 12312.01, 234.91);
		updateInfo(2, "2.2.2.2", 2020, 333, 111, 111.1, 111.2);
		updateInfo(3, "3.3.3.3", 2020, 333, 111, 111.1, 111.2);
		updateInfo(4, "4.4.4.4", 2020, 333, 111, 111.1, 111.2);
		updateInfo(5, "5.5.5.5", 2020, 333, 111, 111.1, 111.2);
	}

	private Map<Integer, Station> stations = new ConcurrentHashMap<Integer, Station>();

	public void updateInfo(int stationId, String ip, int port, double voltage,
			double electricity, double waterGage, double waterLevel) {

		Station station = stations.get(stationId);
		if (station == null) {
			station = new Station(stationId, ip, port, voltage, electricity,
					waterGage, waterLevel);
			stations.put(stationId, station);
		}

		station.setIp(ip);
		station.setPort(port);
		station.setVoltage(voltage);
		station.setElectricity(electricity);
		station.setWaterGage(waterGage);
		station.setWaterLevel(waterLevel);

	}

	public Station getStationInfo(int stationId) {
		return stations.get(stationId);
	}

	public Map<Integer, Station> getStations() {
		return stations;
	}

}
