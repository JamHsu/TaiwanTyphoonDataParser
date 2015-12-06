package com.hackathon.parser;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

import com.hackathon.exception.WetherDataParseException;
import com.hackathon.model.typhoon.cap.TyphoonInfo;
import com.hackathon.parser.impl.CapDataParser;

public class CapDataParserTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testParse() throws WetherDataParseException {
		CapDataParser parser = new CapDataParser();
		String filePath = "testData/typhoon_alert.cap";
		parser.parse(filePath);
		TyphoonInfo typhoonInfo = parser.getTyphoonInfo();
		
		assertEquals("昌鴻", typhoonInfo.getChtName());
		assertEquals("CHAN-HOM", typhoonInfo.getEngName());
		assertEquals(2, typhoonInfo.getStrength().getId());
		assertEquals(Double.valueOf(29.1), typhoonInfo.getCenterLocation().getLatitude());
		assertEquals(Double.valueOf(122.7), typhoonInfo.getCenterLocation().getLongitude());
		assertEquals(Double.valueOf(31.8), typhoonInfo.getForecastLocation().getLatitude());
		assertEquals(Double.valueOf(122.0), typhoonInfo.getForecastLocation().getLongitude());
		assertEquals(Integer.valueOf(280), typhoonInfo.getTyphoonRange());
		assertEquals(Integer.valueOf(950), typhoonInfo.getAirpressure());
		assertEquals(Integer.valueOf(40), typhoonInfo.getWindVelocity());
		assertEquals(Integer.valueOf(50), typhoonInfo.getMaxWindVelocity());
		assertEquals(2, typhoonInfo.getStrength().getId());
		assertEquals(19, typhoonInfo.getAlertNumber().intValue());
		assertEquals(9, typhoonInfo.getTyphoonNumber().intValue());
		assertEquals(260, typhoonInfo.getTyphoonCenterDistance().intValue());
		assertEquals(100, typhoonInfo.getForecastTyphoonCenterDistance().intValue());
		assertEquals(13, typhoonInfo.getTyphoonSpeed().intValue());
		assertEquals(294, typhoonInfo.getTyphoonRotation().intValue());
	}

}
