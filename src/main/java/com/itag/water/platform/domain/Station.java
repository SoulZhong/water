/**
 * 
 */
package com.itag.water.platform.domain;

/**
 * @author Soul
 * @email soullleo@hotmail.com
 * @date 2014年5月24日
 */
public class Station {

	private long id;

	private final int stationId;
	private volatile String name;
	private volatile String description;

	private volatile boolean enable;

	private volatile DataFrame lastDataFrame;

	public Station(int stationId) {
		this.stationId = stationId;

		this.enable = true;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getStationId() {
		return stationId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isEnable() {
		return enable;
	}

	public void setEnable(boolean enable) {
		this.enable = enable;
	}

	public DataFrame getLastDataFrame() {
		return lastDataFrame;
	}

	public void setLastDataFrame(DataFrame lastDataFrame) {
		this.lastDataFrame = lastDataFrame;
	}

}
