package com.hackathon;

import java.text.ParseException;

import org.apache.log4j.Logger;

import com.common.object.exception.DocException;
import com.hackathon.client.service.TyphoonNewService;
import com.hackathon.constant.WeatherDataInfo;
import com.hackathon.converter.WeatherDataConverter;
import com.hackathon.env.CertificationEnv;
import com.hackathon.exception.ParserFailureException;
import com.hackathon.model.typhoon.cap.TyphoonInfo;
import com.hackathon.model.typhoon.kml.KMLData;
import com.hackathon.processor.DataProcessor;
import com.hackathon.processor.impl.CapDataProcessor;
import com.hackathon.processor.impl.KMLDataProcessor;
import com.hacker.bean.request.TyphoonCWBBean;

public class AppController {
	
	private final static String TYPHOON_LINK = "http://www.pigtower.com:3521/hackerapi-web/HackerSoap";
//	private final static String TYPHOON_LINK = "http://127.0.0.1:8080/hackerapi-web/HackerSoap";
	private final static Logger logger = Logger.getLogger(AppController.class);
	
	public void run() throws ParseException {
		
		KMLData kmlData = null;
		TyphoonInfo typhoonInfo = null;

		for(WeatherDataInfo dataInfo : WeatherDataInfo.values()) {
			try {
				logger.debug("Start process " + dataInfo.toString());
				DataProcessor processor = getDataProcessor(dataInfo);
				processor.process();
				switch(dataInfo) {
					case TYPHOON_ALERT:
						typhoonInfo = (TyphoonInfo) processor.getResult();
						break;
					case TYPHOON_HITS:
						logger.warn("Skip Typhoon Hit result");
						break;
					case TYPHOON_INFO:
						kmlData = (KMLData) processor.getResult();
						break;
				}
				
			} catch (Exception e) {
				logger.error("Process " + dataInfo.toString() + "failed.", e);
			}
		}
		
		
		try {
			logger.debug("Convert data to bean");
			WeatherDataConverter converter = new WeatherDataConverter();
			TyphoonCWBBean cwbBean = converter.convertToBean(typhoonInfo, kmlData);
			
			logger.debug("Call API to save data");
			TyphoonNewService service = new TyphoonNewService(TYPHOON_LINK, CertificationEnv.authEnity, cwbBean);
		
			logger.info(service.execute().toString());
		} catch (DocException e) {
			logger.error("Error code:" + e.getErrorCode() + ", msg:" + e.getMessage(),e);
			
		}
	}
	
	private DataProcessor getDataProcessor(WeatherDataInfo dataInfo) throws ParserFailureException {
		DataProcessor processor;
		switch(dataInfo) {
			case TYPHOON_ALERT:
				processor = new CapDataProcessor(dataInfo);
				break;
			case TYPHOON_HITS:
			case TYPHOON_INFO:
				processor = new KMLDataProcessor(dataInfo);
				break;
			default :
				throw new ParserFailureException("Unknown WeatherDataInfo.");
		}
		return processor;
	}
}
