package org.cnepay.mobilepay.tool.action;

import java.util.List;

import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.cnepay.mobilepay.tool.service.AccountManageService;
import org.cnepay.mobilepay.tool.service.KsnManageService;
import org.cnepay.mobilepay.tool.views.KsnEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/app")
public class UnbundAction extends AbstractAction {

	private KsnManageService ksnManageService = null;
	
	private AccountManageService accountManageService = null;
	
	public AccountManageService getAccountManageService() {
		return accountManageService;
	}

	@Autowired
	public void setAccountManageService(AccountManageService accountManageService) {
		this.accountManageService = accountManageService;
	}

	public KsnManageService getKsnManageService() {
		return ksnManageService;
	}

	@Autowired
	public void setKsnManageService(KsnManageService ksnManageService) {
		this.ksnManageService = ksnManageService;
	}

	@RequestMapping("/scanKsn.action")
//	@RequestMapping(value="/{abc}",method=RequestMethod.GET)
	public ModelAndView scanKsn(@RequestParam(value="ksnNo",required=false,defaultValue="") String ksnNo){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("unbundksn");
		logger.info("ksnNo : " + ksnNo);
		if(StringUtils.isBlank(ksnNo)){
			return modelAndView;
		}
		
		List<KsnEntity> list = this.ksnManageService.loadKsnByKsnNo(ksnNo);
		
		modelAndView.addObject("ksns", list);
		modelAndView.addObject("ksnNo", ksnNo);
		return modelAndView;
	}
	
	@RequestMapping("/unbundKsnNo.action")
	public ModelAndView unbundKsn(@RequestParam(value="ksnNo",required=false,defaultValue="") String ksnNo,
												@RequestParam(value="unbundKsnNo",required=false,defaultValue="") String unbundKsnNo){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("unbundksn");
		logger.info("ksnNo : " + ksnNo);
		logger.info("unbundKsnNo : " + unbundKsnNo);
		if(StringUtils.isBlank(unbundKsnNo)){
			modelAndView.addObject("message", "不支持的请求!");
			return modelAndView;
		}
		
		if(request.getParameter("unbundButton") != null) {
			logger.info("解绑设备 ：" + unbundKsnNo);
			if(this.ksnManageService.unbundKsn(unbundKsnNo)){
				modelAndView.addObject("message", "解绑成功!");
			} else {
				modelAndView.addObject("message", "解绑失败!");
			}
		} else if(request.getParameter("clearButton") != null){
			logger.info("清除设备短信 ： " + unbundKsnNo);
			if(this.ksnManageService.clearKsnMessage(unbundKsnNo) >= 0){
				modelAndView.addObject("message", "清除短信成功!");
			} else {
				modelAndView.addObject("message", "清除短信失败!");
			}
		} else if(request.getParameter("sendIcKey") != null){
			logger.info("设置公钥开关为true ： " + unbundKsnNo);
			if(this.ksnManageService.sendIcKey(unbundKsnNo) >= 0){
				modelAndView.addObject("message", "现在登陆，可以收到IC公钥了!");
			} else {
				modelAndView.addObject("message", "更新数据库失败,请检测数据库!");
			}
		} else if(request.getParameter("resetTMK") != null){
			logger.info("设置公钥开关为true ： " + unbundKsnNo);
			if(this.ksnManageService.resetKsnTmk(unbundKsnNo) == 1){
				modelAndView.addObject("message", "设备[" + unbundKsnNo + "]的主密钥已重置为：32个9");
			} else if(this.ksnManageService.resetKsnTmk(unbundKsnNo) > 1){
				modelAndView.addObject("message", "检测到多个重复的ksn:" + unbundKsnNo);
			} else {
				modelAndView.addObject("message", "没有找到ksn:" + unbundKsnNo);
			}
		} else {
			modelAndView.addObject("message", "不支持的请求!");
			return modelAndView;
		}
		
		List<KsnEntity> list = this.ksnManageService.loadKsnByKsnNo(ksnNo);
		modelAndView.addObject("ksns", list);
		modelAndView.addObject("ksnNo", ksnNo);
		return modelAndView;
	}
	
	@RequestMapping("/unbundMobileNo.action")
	public ModelAndView unbundMobileNo(@RequestParam(value="mobileNo",required=false,defaultValue="") String mobileNo){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("unbundksn");
		logger.info("mobileNo : " + mobileNo);
		if(StringUtils.isBlank(mobileNo)){
			modelAndView.addObject("message", "无效手机号!");
			return modelAndView;
		}
		
		if(this.ksnManageService.unbundMobileNo(mobileNo)){
			modelAndView.addObject("message", "解绑成功!");
		} else {
			modelAndView.addObject("message", "解绑失败!");
		}
		
		modelAndView.addObject("mobileNo", mobileNo);
		return modelAndView;
	}
	
	@RequestMapping("/scanAccount.action")
	public ModelAndView scanAccount(@RequestParam(value="accountNo",required=false,defaultValue="") String accountNo){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("unbundksn");
		logger.info("accountNo : " + accountNo);
		if(StringUtils.isBlank(accountNo)){
			modelAndView.addObject("message", "无效卡号");
			return modelAndView;
		}
		if(!this.accountManageService.existBankCard(accountNo)){
			modelAndView.addObject("message", "不支持的卡，请不要乱输！");
			return modelAndView;
		}
		int count = this.accountManageService.unbundAccountCount(accountNo);
		if(NumberUtils.INTEGER_ZERO.intValue() == count){
			modelAndView.addObject("message", "检测不到你输入的卡号,请你确认输入的卡号是否正确或者绑定过此卡！");
			return modelAndView;
		}
		modelAndView.addObject("accountList", this.accountManageService.loadAccountNo(accountNo));
		modelAndView.addObject("accountNo", accountNo);
		return modelAndView;
	}
	
	@RequestMapping("/unbundAccount.action")
	public ModelAndView unbundAccount(@RequestParam(value="accountNo",required=false,defaultValue="") String accountNo,
												@RequestParam(value="forceDeleteAccount",required=false,defaultValue="false") String forceDeleteAccount){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("unbundksn");
		logger.info("accountNo : " + accountNo);
		if(StringUtils.isBlank(accountNo)){
			modelAndView.addObject("message", "无效卡号");
			return modelAndView;
		}
		
		if(!this.accountManageService.existBankCard(accountNo)){
			modelAndView.addObject("message", "不支持的卡，请不要乱输！");
			return modelAndView;
		}
		
		int count = this.accountManageService.unbundAccountCount(accountNo);
		if(NumberUtils.INTEGER_ZERO.intValue() == count){
			modelAndView.addObject("message", "检测不到你输入的卡号,请你确认输入的卡号是否正确或者绑定过此卡！");
			return modelAndView;
		}
		
		boolean forceDeleteAccountFlag = BooleanUtils.toBoolean(forceDeleteAccount);
		
		if(count > 1 && !forceDeleteAccountFlag){
			modelAndView.addObject("message", "检测到你输入的卡号绑定商户的数量超过1个，不知道该解绑哪一个！");
			modelAndView.addObject("accountList", this.accountManageService.loadAccountNo(accountNo));
			modelAndView.addObject("accountNo", accountNo);
			modelAndView.addObject("forceDeleteAccount", "true");
			return modelAndView;
		}
		
		if(this.accountManageService.unbundAccountNo(accountNo)){
			modelAndView.addObject("message", "解绑成功!");
		} else {
			modelAndView.addObject("message", "解绑失败!");
		}
		modelAndView.addObject("accountList", this.accountManageService.loadAccountNo(accountNo));
		modelAndView.addObject("accountNo", accountNo);
		return modelAndView;
	}
	
	@RequestMapping("/deleteMobileMessage.action")
	public ModelAndView deleteMobileMessage(@RequestParam(value="mobileMessage",required=false,defaultValue="") String mobileNo){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("unbundksn");
		logger.info("mobileNo : " + mobileNo);
		if(StringUtils.isBlank(mobileNo)){
			modelAndView.addObject("message", "无效手机号");
			return modelAndView;
		}
		
		if(this.ksnManageService.getMobileNoCount(mobileNo) == 0){
			modelAndView.addObject("message", "手机号不存在");
			return modelAndView;
		}
		
		if(this.ksnManageService.deleteMobileMessage(mobileNo) > 0){
			modelAndView.addObject("message", "短信次数已清零");
		} else {
			modelAndView.addObject("message", "短信清零失败");
		}
		
		modelAndView.addObject("mobileMessage", mobileNo);
		return modelAndView;
	}
	
	@RequestMapping("/indexKsn.action")
	public String indexKsn(){
		return "unbundksn";
	}
}
