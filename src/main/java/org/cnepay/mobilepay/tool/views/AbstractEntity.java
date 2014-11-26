package org.cnepay.mobilepay.tool.views;

import java.lang.reflect.Method;
import java.text.SimpleDateFormat;

import org.apache.commons.lang3.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public abstract class AbstractEntity {
	
	protected Logger logger = LoggerFactory.getLogger(this.getClass());
	
	protected String entityValues(AbstractEntity entity){
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Method[] ms = entity.getClass().getDeclaredMethods();
		if(ArrayUtils.isEmpty(ms)){
			return "{}";
		}
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("{");
		try {
			for(Method m:ms){
				if(m.getName().startsWith("get")){
					Object o = m.invoke(entity, new Object[0]);
					String name = m.getName();
					String fieldName = name.replace("get", "");
					stringBuilder.append(fieldName.substring(0, 1).toLowerCase());
					stringBuilder.append(fieldName.substring(1, fieldName.length()));
					stringBuilder.append("=");
					if(o == null) {
						
					} else if (o instanceof String || o instanceof Integer || o instanceof Long || o instanceof Double){
						
						stringBuilder.append(o);
						
					} else if (o instanceof java.sql.Date){
						java.sql.Date date = (java.sql.Date)o;
						stringBuilder.append(format.format(new java.util.Date(date.getTime())));
					} else if (o instanceof java.util.Date) {
						stringBuilder.append(format.format((java.util.Date)o));
					} else if (o instanceof java.sql.Timestamp){
						java.sql.Timestamp time = (java.sql.Timestamp)o;
						stringBuilder.append(format.format(new java.util.Date(time.getTime())));
					} else {
						stringBuilder.append(o.toString());
					}
					
					stringBuilder.append(",");
				}
			}
			stringBuilder.deleteCharAt(stringBuilder.length() - 1);
		} catch (Exception e){
			logger.error("获取实体值出现异常：" + e.getMessage(),e); 
		} finally {
			stringBuilder.append("}");
		}
		return stringBuilder.toString();
	}

}
