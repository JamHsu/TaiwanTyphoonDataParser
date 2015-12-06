package com.hackathon.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
	
	private final static SimpleDateFormat typhoonChtFormat =  new SimpleDateFormat("MM月dd日HH時");
	private final static SimpleDateFormat dateFormat =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	public static Date typhoonDateToDate(String chtDate) throws ParseException {
		return typhoonChtFormat.parse(chtDate);
	}
	
	public static String dateToString(Date date) {
		return dateFormat.format(date);
	}
	
}
