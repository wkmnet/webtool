package org.cnepay.mobilepay.tool.service;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractService {

	protected Logger logger = LoggerFactory.getLogger(this.getClass());

	protected void copyValueToBean(Object bean,Map<String,Object> value){
		try {
			BeanUtils.populate(bean, value);
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			logger.error("复制数据异常:" + e.getMessage() ,e);
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			logger.error("复制数据异常:" + e.getMessage() ,e);
		}
	}
	
	protected void renameForKey(Class<?> clzz,Map<String,Object> value){
		if(value == null){
			return ;
		}
		Field[] fields = clzz.getDeclaredFields();
		String[] keys = value.keySet().toArray(new String[0]);
		for(String key:keys){
			for(Field f:fields){
				if(f.getName().equalsIgnoreCase(key)){
					Object temp = value.get(key);
					value.remove(key);
					value.put(f.getName(), temp);
				}
			}
		}
	}
}
