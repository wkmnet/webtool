package org.cnepay.mobilepay.tool.interceptor;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.commons.lang3.StringUtils;

public class SystemEnvironmentListener implements ServletContextListener {

	public void contextInitialized(ServletContextEvent sce) {
		// TODO Auto-generated method stub
		if(!StringUtils.isBlank(System.getProperty("catalina.home"))){
			System.setProperty("LOG4j_PATH", System.getProperty("catalina.home") + System.getProperty("file.separator") + "logs");
		} else {
			System.setProperty("LOG4j_PATH", sce.getServletContext().getRealPath("/"));
		}
		System.out.println("LOG4j_PATH:" + System.getProperty("LOG4j_PATH"));
	}

	public void contextDestroyed(ServletContextEvent sce) {
		// TODO Auto-generated method stub

	}

}
