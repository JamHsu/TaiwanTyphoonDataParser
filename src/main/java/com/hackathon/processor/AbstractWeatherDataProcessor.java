package com.hackathon.processor;

import java.io.File;

import org.apache.log4j.Logger;

import com.hackathon.constant.WeatherDataInfo;
import com.hackathon.constant.WeatherDataSetting;

public abstract class AbstractWeatherDataProcessor implements DataProcessor {
	
	private static final Logger logger = Logger.getLogger(AbstractWeatherDataProcessor.class);
	
	abstract protected void init();
	abstract protected void processData() throws Exception;
	abstract protected void destory();
	
	protected File processFile;
	
	private WeatherDataInfo dataInfo;
	
	public AbstractWeatherDataProcessor(WeatherDataInfo dataInfo) {
		if(dataInfo == null) {
			throw new IllegalArgumentException("WeatherDataInfo can not null.");
		}
		this.dataInfo = dataInfo;
	}
	
	public WeatherDataInfo getDataInfo() {
		return dataInfo;
	}
	
	@Override
	public void process() throws Exception {
		try {
			logger.debug("1/3 initialize context.");
			init();
			
			logger.debug("2/3 start process data.");
			processData();
			
			logger.debug("3/3 destory.");
			destory();
		} catch (Exception e) {
			throw new RuntimeException("Process weather data failed.", e);
		}
	}
	
	protected void checkDownloadFolder() {
		try {
			String folderPath = WeatherDataSetting.DOWNLOAD_FOLDER;
			File folder = new File(folderPath);
			if(!folder.exists()) {
				folder.mkdir();
				logger.info("Create download folder:" + folderPath);
			}
		} catch (Exception e) {
			throw new RuntimeException("Create download folder failed.", e);
		}
	}

}
