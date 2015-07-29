/**
 * Project Name:webtool
 * File Name:PersonalDao.java
 * Package Name:org.cnepay.mobilepay.tool.dao.table
 * Date:2015年7月2日下午6:11:12
 * Copyright (c) 2015, wukm@cnepay.com.cn All Rights Reserved.
 **/

package org.cnepay.mobilepay.tool.dao.table;

import java.util.List;
import java.util.Map;

import org.cnepay.mobilepay.tool.dao.AbstractDao;
import org.springframework.stereotype.Component;

/**
 * ClassName:PersonalDao <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2015年7月2日 下午6:11:12 <br/>
 * @author   wkm
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
@Component
public class PersonalDao extends AbstractDao {

	public  List<Map<String,Object>> findPersonalByMobile(String mobile){
		return getJdbcTemplate().queryForList("select id,mobile_no,is_certification,is_signature from cm_personal cp where cp.mobile_no like '%" + mobile + "%'");
	}
	
	
	public int validatePersonal(String id){
		return getJdbcTemplate().update("update cm_personal cp set cp.is_certification='1',cp.is_signature='1' where cp.id='" + id + "'");
	}
	
	public int unvalidatePersonal(String id){
		return getJdbcTemplate().update("update cm_personal cp set cp.is_certification='0',cp.is_signature='0' where cp.id='" + id + "'");
	}
}

