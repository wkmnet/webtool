/**
 * Project Name:webtool
 * File Name:UserValidateAction.java
 * Package Name:org.cnepay.mobilepay.tool.action
 * Date:2015年7月2日上午11:46:07
 * Copyright (c) 2015, wukm@cnepay.com.cn All Rights Reserved.
 **/

package org.cnepay.mobilepay.tool.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.cnepay.mobilepay.tool.service.UserValidateService;
import org.cnepay.mobilepay.tool.views.ValidateMerchantEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * ClassName:UserValidateAction <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2015年7月2日 上午11:46:07 <br/>
 * @author   wkm
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */

@Controller
@RequestMapping("/validate")
public class UserValidateAction extends AbstractAction {
	
	@Autowired
	private UserValidateService userValidateService;

	@RequestMapping("index.action")
	public ModelAndView validate(HttpServletRequest request){
		logger.info("request:" + request.getRequestURI());
		return new ModelAndView("validate");
	}
	
	@RequestMapping("findByMobile.action")
	public ModelAndView findByMobile(@RequestParam String mobile){
		logger.info("接收到的手机号：" + mobile);
		ModelAndView view = new ModelAndView("validate");
		view.addObject("mobile", mobile);
		List<ValidateMerchantEntity> data = userValidateService.findMerchantInfo(mobile);
		view.addObject("merchants", data);
		view.addObject("message", "查询成功:" + data.size() + "条");
		return view;
	}
	
	@RequestMapping("pass.action")
	public ModelAndView pass(@RequestParam String mobile,HttpServletRequest request){
		logger.info("接收到的手机号：" + mobile);
		ModelAndView view = new ModelAndView("validate");
		view.addObject("mobile", mobile);
		if(request.getParameter("passAll") != null){
			if(userValidateService.passAll(mobile)) {
				view.addObject("merchants", userValidateService.findMerchantInfo(mobile));
				view.addObject("message", "操作成功！");
			}
		}
		if(request.getParameter("unpassAll") != null){
			if(userValidateService.unpassAll(mobile)) {
				view.addObject("merchants", userValidateService.findMerchantInfo(mobile));
				view.addObject("message", "操作成功！");
			}
		}
		return view;
	}
	
	public ModelAndView unpass(@RequestParam String mobile){
		logger.info("接收到的手机号：" + mobile);
		ModelAndView view = new ModelAndView("validate");
		view.addObject("mobile", mobile);
		if(userValidateService.passAll(mobile)) {
			view.addObject("merchants", userValidateService.findMerchantInfo(mobile));
			view.addObject("message", "操作成功！");
		}
		return view;
	}
	
}

