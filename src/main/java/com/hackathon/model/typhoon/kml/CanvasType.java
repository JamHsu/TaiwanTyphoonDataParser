package com.hackathon.model.typhoon.kml;

public enum CanvasType {
	Point,
	LinearRing,
	LineString,
	Polygon;
	
	public static Boolean isCanvasType(String type) {
		for(CanvasType canvasType : CanvasType.values()) {
			if(canvasType.toString().equals(type)) {
				return Boolean.TRUE;
			}
		}
		return Boolean.FALSE;
	}
}
