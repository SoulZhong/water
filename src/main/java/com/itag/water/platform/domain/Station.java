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
	private volatile String ip;
	private volatile int port;

	private volatile String name;
	private volatile String description;

	private volatile boolean enable;

	public Station(int stationId, String ip, int port) {
		this.stationId = stationId;
		this.ip = ip;
		this.port = port;
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

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public boolean isEnable() {
		return enable;
	}

	public void setEnable(boolean enable) {
		this.enable = enable;
	}

}
