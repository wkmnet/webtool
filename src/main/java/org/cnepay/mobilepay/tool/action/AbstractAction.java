package org.cnepay.mobilepay.tool.action;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractAction {

	protected HttpServletRequest request = null;
	
	protected HttpServletResponse response = null;
	
	protected Logger logger = LoggerFactory.getLogger(this.getClass());

	@Resource
	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	@Resource
	public void setResponse(HttpServletResponse response) {
		this.response = response;
	}
	
}
