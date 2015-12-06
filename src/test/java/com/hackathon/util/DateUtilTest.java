package com.hackathon.util;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class DateUtilTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testChtDateToDate() throws ParseException {
		Date result = DateUtil.typhoonDateToDate("07月27日14時");
		assertEquals("1970-07-27 14:00:00", DateUtil.dateToString(result));
	}

}
