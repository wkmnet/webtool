/**
 * Project Name:webtool
 * File Name:BankCardAction.java
 * Package Name:org.cnepay.mobilepay.tool.action
 * Date:2015年7月29日下午6:38:08
 * Copyright (c) 2015, wukm@cnepay.com.cn All Rights Reserved.
 **/

package org.cnepay.mobilepay.tool.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.cnepay.mobilepay.tool.service.BankCardService;
import org.cnepay.mobilepay.tool.views.BandCardEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * ClassName:BankCardAction <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2015年7月29日 下午6:38:08 <br/>
 * @author   wkm
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
@Controller
@RequestMapping("/bankcard")
public class BankCardAction extends AbstractAction {
	
	@Autowired
	private BankCardService bankCardService;
	

	@RequestMapping("index.action")
	public ModelAndView validate(HttpServletRequest request){
		logger.info("request:" + request.getRequestURI());
		return new ModelAndView("bankcard");
	}
	
	
	@RequestMapping("scanBankCard.action")
	public ModelAndView scanBankCard(@RequestParam String cardno){
		logger.info("接收到的卡号：" + cardno);
		ModelAndView view = new ModelAndView("bankcard");
		view.addObject("cardno", cardno);
		List<BandCardEntity> data = bankCardService.findBankCardInfo(cardno);
		view.addObject("bankCards", data);
		view.addObject("message", "查询成功:" + data.size() + "条");
		return view;
	}
}

