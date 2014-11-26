package org.cnepay.mobilepay.tool.interceptor;

import java.util.Enumeration;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class RequestInterceptor extends HandlerInterceptorAdapter {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)throws Exception {
			
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("有来自：[");
		stringBuilder.append(request.getRemoteAddr());
		stringBuilder.append(";");
		stringBuilder.append(request.getRemoteHost());
		stringBuilder.append("]的请求，user=");
		stringBuilder.append(request.getRemoteUser());
		stringBuilder.append(";port=");
		stringBuilder.append(request.getRemotePort());
		stringBuilder.append(System.getProperty("line.separator"));
		stringBuilder.append("请求参数：[");
		Enumeration<String> paramNames = request.getParameterNames();
		while(paramNames.hasMoreElements()){
			String key = paramNames.nextElement();
			stringBuilder.append("{" + key);
			stringBuilder.append("=");
			String[] values = request.getParameterValues(key);
			if( null == values){
					stringBuilder.append("null");
			} else {
				for(String v:values){
					stringBuilder.append(v);
					stringBuilder.append(",");
				}
				stringBuilder.deleteCharAt(stringBuilder.length() - 1);
			}
			stringBuilder.append("}");
		}
		stringBuilder.append("]");
		logger.info(stringBuilder.toString());

		return true;
	}
	
	public void postHandle(
			HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView)
			throws Exception {
		if(modelAndView == null){
			logger.info("没有返回实体信息...");
			return;
		}
		
		logger.info("响应jsp信息：" + modelAndView.getViewName());
		logger.info("响应url信息：" + modelAndView.getView());
		Map<String,Object> map = modelAndView.getModel();
		logger.info("响应data信息：" + map);
	}
}
