/**
 * 
 */
package com.itag.water.platform.domain;

import java.util.Date;

/**
 * @author Soul
 * @date May 22, 2014
 */
public class DataFrame {

	private String ip;
	private int port;
	private int id;

	private int state;

	private long time;

	private double voltage;
	private double electricity;
	private double waterGage;
	private double waterLevel;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	@Override
	public String toString() {
		return "发送者：" + ip + ":" + port + ", 时间:" + new Date(time) + ", 电压:" + voltage + ", 电流:" + electricity
				+ ", 水压:" + waterGage + ", 水位:" + waterLevel;

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

	public long getTime() {
		return time;
	}

	public void setTime(long time) {
		this.time = time;
	}

	public double getVoltage() {
		return voltage;
	}

	public void setVoltage(double voltage) {
		this.voltage = voltage;
	}

	public double getElectricity() {
		return electricity;
	}

	public void setElectricity(double electricity) {
		this.electricity = electricity;
	}

	public double getWaterGage() {
		return waterGage;
	}

	public void setWaterGage(double waterGage) {
		this.waterGage = waterGage;
	}

	public double getWaterLevel() {
		return waterLevel;
	}

	public void setWaterLevel(double waterLevel) {
		this.waterLevel = waterLevel;
	}

}
