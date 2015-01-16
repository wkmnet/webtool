package org.cnepay.mobilepay.tool.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CalendarUtil {

	private static Logger LOG = LoggerFactory.getLogger(CalendarUtil.class);
	
	private CalendarUtil(){
		
	}
	
	public static String DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";
	
	public static String DATE_PATTERN = "yyyyMMdd";
	
	public static String TIME_PATTERN = "HHmmss";
	
	public static long parse(String source,String pattern){
		SimpleDateFormat format = new SimpleDateFormat(pattern);
		try {
			return format.parse(source).getTime();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			LOG.error("转换日期" + source + "异常：" + e.getMessage(),e);
		}
		return -1;
	}
	
	public static long parse(String source){
		return parse(source,DATE_TIME_PATTERN);
	}
	
	public static Date parseDate(String source,String pattern){
		SimpleDateFormat format = new SimpleDateFormat(pattern);
		try {
			return format.parse(source);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			LOG.error("转换日期" + source + "异常：" + e.getMessage(),e);
		}
		return null;
	}
	
	public static Date parseDate(String source){
		return parseDate(source,DATE_PATTERN + TIME_PATTERN);
	}
	
	public static String formatDate(Date date){
		SimpleDateFormat format = new SimpleDateFormat(DATE_PATTERN);
		return format.format(date);
	}
	
	public static String formatTime(Date date){
		SimpleDateFormat format = new SimpleDateFormat(TIME_PATTERN);
		return format.format(date);
	}
}
