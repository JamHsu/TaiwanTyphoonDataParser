package com.hackathon.processor.impl;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.hackathon.constant.WeatherDataInfo;
import com.hackathon.model.typhoon.cap.TyphoonInfo;

public class CapDataProcessorTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testProcessData() {
		CapDataProcessor processor = new CapDataProcessor(WeatherDataInfo.TYPHOON_ALERT);
		try {
			processor.process();
			TyphoonInfo info = (TyphoonInfo) processor.getResult();
			System.out.println(info.getChtName());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
