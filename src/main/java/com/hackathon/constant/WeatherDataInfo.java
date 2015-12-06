package com.hackathon.constant;

public enum WeatherDataInfo {
	TYPHOON_ALERT("W-C0034-001", "cap"), 
	TYPHOON_INFO("W-C0034-002", "kmz"),
	TYPHOON_HITS("W-C0034-003", "kmz");
	
	private String dataId;
	private String format;
	
	WeatherDataInfo(String dataId, String fileFormat) {
		this.dataId = dataId;
		this.format = fileFormat;
	}
	
	public String getDataId() {
		return dataId;
	}
	
	public String getFileFormat() {
		return format;
	}
	
}
