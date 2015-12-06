package com.hackathon.model.typhoon.kml;

public class Coordinates {
	private Double lat;
	private Double lng;
	private Double height;
	
	public Coordinates(Double lat, Double lng) {
		this.lat = lat;
		this.lng = lng;
		this.height = 0.0;
	}
	
	public Coordinates(Double lat, Double lng, Double height) {
		this.lat = lat;
		this.lng = lng;
		this.height = height;
	}

	public Double getLatitude() {
		return lat;
	}

	public void setLatitude(Double lat) {
		this.lat = lat;
	}

	public Double getLongitude() {
		return lng;
	}

	public void setLongitude(Double lng) {
		this.lng = lng;
	}

	public Double getHeight() {
		return height;
	}

	public void setHeight(Double height) {
		this.height = height;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(lat.toString()).append(",")
				.append(lng.toString()).append(",")
				.append(height.toString());
		return builder.toString();
	}
	
	// support format lng,lat,height
	// sample: 179.70,13.20,0
	public static Coordinates parseCoordinates(String text) {
		String[] dataAry = text.split(",");
		try {
			Double lng = Double.parseDouble(dataAry[0]);
			Double lat = Double.parseDouble(dataAry[1]);
			Double height = Double.parseDouble(dataAry[2]);
			return new Coordinates(lat, lng, height);
		} catch (Exception e) {
			throw new RuntimeException("parsing data failed:" + text + ", caused by " + e.getMessage());
		}
	}
}
