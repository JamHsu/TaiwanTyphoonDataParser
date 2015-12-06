package com.hackathon.model.typhoon.cap;

public enum CapDataParseTerm {
	Cht_name("中文譯名", "譯名：", "。"),
	Eng_name("國際命名", "國際命名：", "，"),
	Center_loc_lat("[中心位置]", "緯 ", " 度"),
	Center_loc_lng("[中心位置]", "經 ", " 度"),
	Forecast_loc_lat("[預測位置]", "緯 ", " 度"),
	Forecast_loc_lng("[預測位置]", "經 ", " 度"),
	Typhoon_range("[暴風半徑]", "半徑 ", " 公里"),
	Airpressure("中心氣壓", "[中心氣壓] ", "百帕"),
	Speed("近中心最大風速", "每秒 ", " 公尺"),
	Max_speed("瞬間之最大陣風", "每秒 ", " 公尺"),
	Strength("度颱風", "", "度颱風，"),
	AlertCnt("[警報報數]", "] ", ""),
	TyphoonNumber("[颱風編號]", "] ", ""),
	TyphoonCenterDistance("[中心位置]", "約 ", " 公里"),
	ForecastTyphoonCenterDistance("[預測位置]", "約 ", " 公里"),
	TyphoonSpeed("[預測速度及方向]", "小時", "公里");
	
	private String checkTerm;
	private String startKey;
	private String endKey;
	
	CapDataParseTerm(String name, String startKey, String endKey) {
		this.checkTerm = name;
		this.startKey = startKey;
		this.endKey = endKey;
	}
	
	public String getCheckTerm() {
		return checkTerm;
	}
	
	public String getStartKey() {
		return startKey;
	}
	
	public String getEndKey() {
		return endKey;
	}
	
}
