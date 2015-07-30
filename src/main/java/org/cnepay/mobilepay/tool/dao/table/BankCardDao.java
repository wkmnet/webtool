/**
 * Project Name:webtool
 * File Name:BankCardDao.java
 * Package Name:org.cnepay.mobilepay.tool.dao.table
 * Date:2015年7月29日下午6:40:10
 * Copyright (c) 2015, wukm@cnepay.com.cn All Rights Reserved.
 **/

package org.cnepay.mobilepay.tool.dao.table;

import java.util.List;
import java.util.Map;

import org.cnepay.mobilepay.tool.dao.AbstractDao;
import org.springframework.stereotype.Component;

/**
 * ClassName:BankCardDao <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2015年7月29日 下午6:40:10 <br/>
 * @author   wkm
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
@Component
public class BankCardDao extends AbstractDao {

	public  List<Map<String,Object>> findBankCardInfo(String cardno){
		//查询卡号信息
		return getJdbcTemplate().queryForList("select * from dict_cardbin dc where dc.CARDBIN_CODE=substr('" + cardno + "',1,dc.CARDBIN_LENGTH) and dc.CARD_NO_LENGTH=length('" + cardno + "')");
	}
	
}

