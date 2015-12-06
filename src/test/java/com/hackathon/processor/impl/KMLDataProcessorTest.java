package com.hackathon.processor.impl;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.hackathon.constant.WeatherDataInfo;

public class KMLDataProcessorTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testProcessData() {
		KMLDataProcessor processor = new KMLDataProcessor(WeatherDataInfo.TYPHOON_INFO);
		try {
			processor.process();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
