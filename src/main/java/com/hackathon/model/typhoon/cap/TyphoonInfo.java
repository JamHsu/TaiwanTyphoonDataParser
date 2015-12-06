package com.hackathon.model.typhoon.cap;

import com.hackathon.model.typhoon.kml.Coordinates;

public class TyphoonInfo {
	
	private String chtName;
	private String engName;
	private TyphoonStrength strength;
	private String description;
	private Integer airpressure;
	private Coordinates centerLoc;
	private Coordinates forecastLoc;
	private Integer range; //unit km
	private Integer windVelocity; // unit m/s
	private Integer maxWindVelocity;
	private Integer alertNumber;
	private Integer typhoonNumber;
	private Integer typhoonCenterDistance;
	private Integer forecastTyphoonCenterDistance;
	private Integer typhoonSpeed;
	private Double typhoonRotation;
	
	public String getChtName() {
		return chtName;
	}
	
	public void setChtName(String chtName) {
		this.chtName = chtName;
	}
	
	public String getEngName() {
		return engName;
	}
	
	public void setEngName(String engName) {
		this.engName = engName;
	}

	public TyphoonStrength getStrength() {
		return strength;
	}

	public void setStrength(TyphoonStrength strength) {
		this.strength = strength;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getAirpressure() {
		return airpressure;
	}

	public void setAirpressure(Integer airpressure) {
		this.airpressure = airpressure;
	}

	public Coordinates getCenterLocation() {
		return centerLoc;
	}

	public void setCenterLocation(Coordinates centerLoc) {
		this.centerLoc = centerLoc;
	}

	public Coordinates getForecastLocation() {
		return forecastLoc;
	}

	public void setForecastLocation(Coordinates forecastLoc) {
		this.forecastLoc = forecastLoc;
	}

	public Integer getTyphoonRange() {
		return range;
	}

	public void setTyphoonRange(Integer range) {
		this.range = range;
	}

	public Integer getWindVelocity() {
		return windVelocity;
	}

	public void setWindVelocity(Integer windVelocity) {
		this.windVelocity = windVelocity;
	}

	public Integer getMaxWindVelocity() {
		return maxWindVelocity;
	}

	public void setMaxWindVelocity(Integer maxWindVelocity) {
		this.maxWindVelocity = maxWindVelocity;
	}

	public Integer getAlertNumber() {
		return alertNumber;
	}

	public void setAlertNumber(Integer alertNumber) {
		this.alertNumber = alertNumber;
	}

	public Integer getTyphoonNumber() {
		return typhoonNumber;
	}

	public void setTyphoonNumber(Integer typhoonNumber) {
		this.typhoonNumber = typhoonNumber;
	}

	public Integer getTyphoonCenterDistance() {
		return typhoonCenterDistance;
	}

	public void setTyphoonCenterDistance(Integer typhoonCenterDistance) {
		this.typhoonCenterDistance = typhoonCenterDistance;
	}

	public Integer getForecastTyphoonCenterDistance() {
		return forecastTyphoonCenterDistance;
	}

	public void setForecastTyphoonCenterDistance(
			Integer forecastTyphoonCenterDistance) {
		this.forecastTyphoonCenterDistance = forecastTyphoonCenterDistance;
	}

	public Integer getTyphoonSpeed() {
		return typhoonSpeed;
	}

	public void setTyphoonSpeed(Integer typhoonSpeed) {
		this.typhoonSpeed = typhoonSpeed;
	}

	public Double getTyphoonRotation() {
		return typhoonRotation;
	}

	public void setTyphoonRotation(Double typhoonRotation) {
		this.typhoonRotation = typhoonRotation;
	}
	
}
