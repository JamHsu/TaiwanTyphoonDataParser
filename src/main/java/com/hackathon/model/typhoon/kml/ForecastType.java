package com.hackathon.model.typhoon.kml;

public enum ForecastType {
	Unknown(0, ""),
	Past_path(1, "過去路徑"),
	Path_forecast(2, "路徑潛勢預測"),
	Typhoon_path_forecast(3, "颱風路徑預測"),
	Current_path(4, "目前路徑"),
	New_status(5, "最新狀態"),
	Typhoon_boundary(6, "暴風圈範圍"),
	Typhoon_location_forecast(7, "預測颱風位置");
	
	private int id;
	private String chtName;
	
	ForecastType(int id, String chtName) {
		this.id = id;
		this.chtName = chtName;
	}
	
	public int getId() {
		return id;
	}
	
	public String getChtName() {
		return chtName;
	}
	
	public static ForecastType getForecastTypeByChtName(String name) {
		for(ForecastType type : ForecastType.values()) {
			if(type.getChtName().equals(name)) {
				return type;
			}
		}
		return ForecastType.Unknown;
	}
}
