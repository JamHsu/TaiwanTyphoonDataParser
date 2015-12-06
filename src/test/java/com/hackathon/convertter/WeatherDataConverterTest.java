package com.hackathon.convertter;

import static org.junit.Assert.*;

import java.text.ParseException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.hackathon.constant.WeatherDataInfo;
import com.hackathon.converter.WeatherDataConverter;
import com.hackathon.exception.ParserFailureException;
import com.hackathon.model.typhoon.cap.TyphoonInfo;
import com.hackathon.model.typhoon.kml.KMLData;
import com.hackathon.processor.DataProcessor;
import com.hackathon.processor.impl.CapDataProcessor;
import com.hackathon.processor.impl.KMLDataProcessor;
import com.hacker.bean.request.TyphoonCWBBean;

public class WeatherDataConverterTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testConvertToBean() throws ParseException {
		KMLData kmlData = null;
		TyphoonInfo typhoonInfo = null;

		for(WeatherDataInfo dataInfo : WeatherDataInfo.values()) {
			try {
				DataProcessor processor = getDataProcessor(dataInfo);
				processor.process();
				switch(dataInfo) {
					case TYPHOON_ALERT:
						typhoonInfo = (TyphoonInfo) processor.getResult();
						break;
					case TYPHOON_HITS:
						break;
					case TYPHOON_INFO:
						kmlData = (KMLData) processor.getResult();
						break;
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		WeatherDataConverter converter = new WeatherDataConverter();
		TyphoonCWBBean cwbBean = converter.convertToBean(typhoonInfo, kmlData);
		System.out.print(cwbBean.getNews().toString());
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
