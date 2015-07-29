/**
 * Project Name:webtool
 * File Name:MerchantDao.java
 * Package Name:org.cnepay.mobilepay.tool.dao.table
 * Date:2015年7月2日下午6:50:05
 * Copyright (c) 2015, wukm@cnepay.com.cn All Rights Reserved.
 **/

package org.cnepay.mobilepay.tool.dao.table;

import java.util.List;
import java.util.Map;

import org.cnepay.mobilepay.tool.dao.AbstractDao;
import org.springframework.stereotype.Component;

/**
 * ClassName:MerchantDao <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2015年7月2日 下午6:50:05 <br/>
 * @author   wkm
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
@Component
public class MerchantDao extends AbstractDao {

	public  List<Map<String,Object>> findMerchantById(String id){
		return getJdbcTemplate().queryForList("select id,review_status from cm_merchant cm where cm.id = " + id + "");
	}
	
	public  int validateMerchant(String id){
		return getJdbcTemplate().update("update cm_merchant cm set cm.review_status='accept' where cm.id = " + id + "");
	}
	
	public  int unvalidateMerchant(String id){
		return getJdbcTemplate().update("update cm_merchant cm set cm.review_status='init' where cm.id = " + id + "");
	}
}

