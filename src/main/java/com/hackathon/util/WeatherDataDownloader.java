package com.hackathon.util;

import java.io.File;

import org.apache.log4j.Logger;

import com.hackathon.constant.WeatherDataInfo;
import com.hackathon.constant.WeatherDataSetting;

public class WeatherDataDownloader {
	
	private static Logger logger = Logger.getLogger(WeatherDataDownloader.class);
	
	public static File downloadWeatherData(WeatherDataInfo data) {
		try {
			String url = genDataURL(data.getDataId());
			String destination = WeatherDataSetting.DOWNLOAD_FOLDER + File.separator + data.getDataId() + "." + data.getFileFormat();
			File downloadFile = HttpDownloader.downloadFile(url, destination);
			return downloadFile;
		} catch(Exception e) {
			logger.error("Download weather data failed.", e);
			return null;
		}
	}
	
	// template: "http://opendata.cwb.gov.tw/opendataapi?dataid={dataid}&authorizationkey={apikey}"
	private static String genDataURL(String dataId) {
		String WEATHER_DATA_TEMPLATE = "http://opendata.cwb.gov.tw/opendataapi?authorizationkey=" + WeatherDataSetting.API_KEY;
		String url = WEATHER_DATA_TEMPLATE + "&dataid=" + dataId;
		return url;
	}
 
}
