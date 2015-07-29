/**
 * Project Name:webtool
 * File Name:UserValidateService.java
 * Package Name:org.cnepay.mobilepay.tool.service
 * Date:2015年7月2日下午7:15:19
 * Copyright (c) 2015, wukm@cnepay.com.cn All Rights Reserved.
 **/

package org.cnepay.mobilepay.tool.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;

import org.cnepay.mobilepay.tool.dao.table.AccountDao;
import org.cnepay.mobilepay.tool.dao.table.MerchantDao;
import org.cnepay.mobilepay.tool.dao.table.PersonalDao;
import org.cnepay.mobilepay.tool.views.ValidateMerchantEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * ClassName:UserValidateService <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2015年7月2日 下午7:15:19 <br/>
 * @author   wkm
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
@Service
public class UserValidateService extends AbstractService {

	@Autowired
	private PersonalDao personal;
	
	@Autowired
	private MerchantDao merchant;
	
	@Autowired
	private AccountDao account;
	
	
	public List<ValidateMerchantEntity> findMerchantInfo(String mobile){
		
		List<Map<String,Object>> persons = personal.findPersonalByMobile(mobile);
		logger.info("find:" + JSONArray.fromObject(persons).toString(4));
		List<ValidateMerchantEntity> results = new ArrayList<ValidateMerchantEntity>();
		
		for(Map<String,Object> map:persons){
			ValidateMerchantEntity temp = new ValidateMerchantEntity();
			temp.setMobile(map.get("mobile_no").toString());
			temp.setIdCard(map.get("is_certification").toString());
			temp.setSignature(map.get("is_signature").toString());
			List<Map<String,Object>> merchants = merchant.findMerchantById(map.get("id").toString());
			if(merchants.size() > 0){
				temp.setMerchant(merchants.get(0).get("review_status").toString());
			}
			
			List<Map<String,Object>> accounts = account.findAccountByMerchant(merchants.get(0).get("id").toString());
			if(accounts.size() > 0){
				temp.setAccount(accounts.get(0).get("isVerified").toString());
			}
			results.add(temp);
		}
		logger.info("return:" + JSONArray.fromObject(results).toString(4));
		return results;
	}
	
	
	public boolean passAll(String mobile){
		
		List<Map<String,Object>> persons = personal.findPersonalByMobile(mobile);
		logger.info("find:" + JSONArray.fromObject(persons).toString(4));
		
		for(Map<String,Object> map:persons){
			personal.validatePersonal(map.get("id").toString());
			merchant.validateMerchant(map.get("id").toString());
			
			
			List<Map<String,Object>> accounts = account.findAccountByMerchant(map.get("id").toString());
			if(accounts.size() > 0)
				account.validateMerchant(accounts.get(0).get("id").toString());
		}
		return true;
	}
	
	
	public boolean unpassAll(String mobile){
		
		List<Map<String,Object>> persons = personal.findPersonalByMobile(mobile);
		logger.info("find:" + JSONArray.fromObject(persons).toString(4));
		
		for(Map<String,Object> map:persons){
			personal.unvalidatePersonal(map.get("id").toString());
			merchant.unvalidateMerchant(map.get("id").toString());
			
			
			List<Map<String,Object>> accounts = account.findAccountByMerchant(map.get("id").toString());
			if(accounts.size() > 0)
				account.unvalidateMerchant(accounts.get(0).get("id").toString());
		}
		return true;
	}
}

