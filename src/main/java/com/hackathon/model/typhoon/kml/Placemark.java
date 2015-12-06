package com.hackathon.model.typhoon.kml;

import java.util.ArrayList;
import java.util.List;

public class Placemark extends AbstractKMLEntity {
	
	private CanvasType canvasType;
	private List<Coordinates> coordinatesList;
	
	public Placemark() {
		coordinatesList = new ArrayList<Coordinates>();
	}
	
	@Override
	public Boolean isPlacemark() {
		return Boolean.TRUE;
	}
	
	@Override
	public Placemark asPlacemark() {
		return this;	
	}


	public CanvasType getCanvasType() {
		return canvasType;
	}

	public void setCanvasType(CanvasType canvasType) {
		this.canvasType = canvasType;
	}
	
	public void addCoordinates(List<Coordinates> coordinates) {
		coordinatesList.addAll(coordinates);
	}
	
	public void addCoordinates(Coordinates coordinates) {
		coordinatesList.add(coordinates);
	}
	
	public List<Coordinates> getCoordinates() {
		return new ArrayList<Coordinates>(coordinatesList);
	}
	
	@Override
	public void dump() {
		System.out.println("---------- Placemark -----------");
		System.out.println("name:" + super.getName());
		System.out.println("description:" + super.getDescription());
		System.out.println("canvas:" + getCanvasType().toString());
		System.out.println("coordinates:" + generateCoordinatesString());
	}
	
	public String generateCoordinatesString() {
		StringBuilder builder = new StringBuilder();
		for(Coordinates coord : coordinatesList) {
			if(builder.length() > 0) {
				builder.append(" ");
			}
			builder.append(coord.toString());
			ForecastType type = ForecastType.getForecastTypeByChtName(getName());
			String sql = "INSERT INTO `typhoon_coordination` "
					+ "(`typhoone_id`,`forecast_type`,`loc_x`,`loc_y`) "
					+ "VALUES "
					+ "(5, " + type.getId() + "," + coord.getLongitude() + "," + coord.getLatitude() + ");";
			System.out.println(sql);
		}
		return builder.toString();
	}
	
	
	
	
		
}
