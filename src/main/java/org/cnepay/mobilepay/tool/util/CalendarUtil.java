package org.cnepay.mobilepay.tool.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CalendarUtil {

	private static Logger LOG = LoggerFactory.getLogger(CalendarUtil.class);
	
	private CalendarUtil(){
		
	}
	
	public static String DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";
	
	public static long parse(String source){
		SimpleDateFormat format = new SimpleDateFormat(DATE_TIME_PATTERN);
		try {
			return format.parse(source).getTime();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			LOG.error("转换日期" + source + "异常：" + e.getMessage(),e);
		}
		return -1;
	}
}
