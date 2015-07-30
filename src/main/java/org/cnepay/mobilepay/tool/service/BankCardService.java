/**
 * Project Name:webtool
 * File Name:BankCardService.java
 * Package Name:org.cnepay.mobilepay.tool.service
 * Date:2015年7月30日下午1:20:41
 * Copyright (c) 2015, wukm@cnepay.com.cn All Rights Reserved.
 **/

package org.cnepay.mobilepay.tool.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.cnepay.mobilepay.tool.dao.table.BankCardDao;
import org.cnepay.mobilepay.tool.views.BandCardEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * ClassName:BankCardService <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2015年7月30日 下午1:20:41 <br/>
 * @author   wkm
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
@Service
public class BankCardService extends AbstractService {


	@Autowired
	private BankCardDao bankCard = null;
	
	public List<BandCardEntity> findBankCardInfo(String cardno){
		List<Map<String,Object>> bankCards = bankCard.findBankCardInfo(cardno);
		
		List<BandCardEntity> bankCard = new ArrayList<BandCardEntity>();
		if(bankCards != null){
			for(Map<String,Object> map : bankCards){
				
				BandCardEntity entity = new BandCardEntity();
				entity.setCardNo(cardno);
				entity.setCardCode(map.get("cardbin_code").toString());
				entity.setCardCodeLength(map.get("cardbin_length").toString());
				entity.setCardIssuer(map.get("issuer_name").toString());
				entity.setCardLength(map.get("card_no_length").toString());
				entity.setCardName(map.get("cardbin_name").toString());
				if("credit".equals(map.get("card_type").toString())){
					entity.setCardType("信用卡/" + map.get("card_type").toString());
				} else if("debit".equals(map.get("card_type").toString())){
					entity.setCardType("借记卡/" + map.get("card_type").toString());
				} else {
					entity.setCardType("其它类型卡/" + map.get("card_type").toString());
				}
				
				bankCard.add(entity);
			}
		}
		
		return bankCard;
	}
	
}

