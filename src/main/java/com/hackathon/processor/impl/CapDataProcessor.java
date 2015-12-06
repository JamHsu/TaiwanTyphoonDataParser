package com.hackathon.processor.impl;

import org.apache.log4j.Logger;

import com.hackathon.constant.WeatherDataInfo;
import com.hackathon.model.typhoon.cap.TyphoonInfo;
import com.hackathon.parser.impl.CapDataParser;
import com.hackathon.processor.AbstractWeatherDataProcessor;
import com.hackathon.util.WeatherDataDownloader;

public class CapDataProcessor extends AbstractWeatherDataProcessor {

	private static final Logger logger = Logger.getLogger(CapDataProcessor.class);
	private TyphoonInfo typhoonInfo;
	
	public CapDataProcessor(WeatherDataInfo dataInfo) {
		super(dataInfo);
	}

	@Override
	protected void init() {
		super.checkDownloadFolder();
	}

	@Override
	protected void processData() throws Exception {
		logger.debug("Start process CAP data");
		
		logger.debug("1/2 Download CAP file:" + getDataInfo().toString()
					+ ", id:" + getDataInfo().getDataId());
		processFile = WeatherDataDownloader.downloadWeatherData(getDataInfo());
		String path = processFile.getAbsolutePath();
		
		logger.debug("2/2 Paring CAP data.");
		CapDataParser parser = new CapDataParser();
		parser.parse(path);
		typhoonInfo = parser.getTyphoonInfo();
		
		logger.debug("End process CAP data");
	}

	@Override
	protected void destory() {
		// delete file
		try {
			processFile.delete();
		} catch (Exception e) {
			logger.warn("Delete download file failed.", e);
		}
	}
	
	@Override
	public Object getResult() {
		return typhoonInfo;
	}	

}
