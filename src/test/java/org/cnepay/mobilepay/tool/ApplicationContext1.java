package org.cnepay.mobilepay.tool;

import java.util.Date;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContextAware;

public class ApplicationContext1 implements ApplicationContextAware{

	private static org.springframework.context.ApplicationContext applicationContext = null;
	
	public void setApplicationContext(
			org.springframework.context.ApplicationContext applicationContext)
			throws BeansException {
		// TODO Auto-generated method stub
		System.out.println("-------创建:" + new Date());
		ApplicationContext1.applicationContext = applicationContext;
		
	}

//	public org.springframework.context.ApplicationContext getApplicationContext() {
//		return applicationContext;
//	}

	public static org.springframework.context.ApplicationContext getApplicationContext(){
		
		return ApplicationContext1.applicationContext;
	}
}
