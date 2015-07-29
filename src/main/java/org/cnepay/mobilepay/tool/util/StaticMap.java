/**
 * Project Name:webtool
 * File Name:StaticMap.java
 * Package Name:org.cnepay.mobilepay.tool.util
 * Date:2015年7月2日下午8:16:13
 * Copyright (c) 2015, wukm@cnepay.com.cn All Rights Reserved.
 **/

package org.cnepay.mobilepay.tool.util;

import java.util.HashMap;
import java.util.Map;


/**
 * ClassName:StaticMap <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2015年7月2日 下午8:16:13 <br/>
 * @author   wkm
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class StaticMap {

	public static Map<String,String> STATUS_MAP = new HashMap<String,String>();
	
	public static Map<String,String> STATUS_MER_MAP = new HashMap<String,String>();
	
	static {
		STATUS_MAP.put("0", "未提交");
		STATUS_MAP.put("1", "已通过");
		STATUS_MAP.put("2", "未通过");
		STATUS_MAP.put("3", "已提交");
		
		STATUS_MER_MAP.put("init", "未提交");
		STATUS_MER_MAP.put("submit", "已提交");
		STATUS_MER_MAP.put("reject", "未通过");
		STATUS_MER_MAP.put("accept", "已通过");
	}
}

