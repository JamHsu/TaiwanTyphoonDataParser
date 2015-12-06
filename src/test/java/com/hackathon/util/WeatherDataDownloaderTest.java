package com.hackathon.util;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.net.ConnectException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.hackathon.constant.WeatherDataInfo;
import com.hackathon.constant.WeatherDataSetting;

public class WeatherDataDownloaderTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void testDownloadFile() throws ConnectException, IOException {
		File file = WeatherDataDownloader.downloadWeatherData(WeatherDataInfo.TYPHOON_ALERT);
		assertNotNull(file);
		assertTrue(file.exists());
		assertTrue(file.getTotalSpace() > 0);
		assertTrue(file.delete());
	}
}

