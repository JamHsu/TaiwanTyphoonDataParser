package com.hackathon.processor.impl;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;

import com.hackathon.constant.WeatherDataInfo;
import com.hackathon.model.typhoon.kml.KMLData;
import com.hackathon.parser.impl.KMLDataParser;
import com.hackathon.processor.AbstractWeatherDataProcessor;
import com.hackathon.util.FileUtil;
import com.hackathon.util.WeatherDataDownloader;

public class KMLDataProcessor extends AbstractWeatherDataProcessor {

	private static final Logger logger = Logger.getLogger(KMLDataProcessor.class);
	private static final String zipFolderName = "zipTempFolder";
	private KMLData kmlData;
	
	public KMLDataProcessor(WeatherDataInfo dataInfo) {
		super(dataInfo);
	}

	@Override
	protected void init() {
		super.checkDownloadFolder();
	}

	@Override
	protected void processData() throws Exception {
		
		logger.debug("Start process KML data");
		
		logger.debug("1/3 Download KMl file:" + getDataInfo().toString() 
				+ ", id:" + getDataInfo().getDataId());
		processFile = WeatherDataDownloader.downloadWeatherData(getDataInfo());
		String parentPath = processFile.getParentFile().getAbsolutePath();
		File zipFolder = new File(parentPath + File.separator + zipFolderName);
		zipFolder.mkdir();
		
		logger.debug("2/3 unzip " + processFile.getAbsolutePath() 
				+ " to " + zipFolder.getAbsolutePath());
		FileUtil.unzipFile(processFile.getAbsolutePath(), zipFolder.getAbsolutePath());
		File[] kmlFiles = FileUtil.findFiles(zipFolder.getAbsolutePath(), "kml");
		if(kmlFiles.length > 0) {
			File kmlFile = kmlFiles[0];
			KMLDataParser parser = new KMLDataParser();
			
			logger.debug("3/3 Paring KML data.");
			parser.parse(kmlFile.getAbsolutePath());
			kmlData = parser.getKMLData();
		}
		
		logger.debug("End process KML data");
	}

	@Override
	protected void destory() {
		// delete file
		try {
			processFile.delete();
		} catch (Exception e) {
			logger.warn("Delete download file failed.", e);
		}
		
		// delete temp folder
		try {
			FileUtils.deleteDirectory(
					new File(processFile.getParentFile().getAbsolutePath() + File.separator + zipFolderName));
		} catch (IOException e) {
			logger.warn("Delete zip temp folder failed.", e);
		}
	}
	
	@Override
	public Object getResult() {
		return kmlData;
	}	

}
