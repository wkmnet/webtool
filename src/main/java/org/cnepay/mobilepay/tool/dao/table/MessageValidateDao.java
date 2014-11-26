package org.cnepay.mobilepay.tool.dao.table;

import java.util.List;
import java.util.Map;

import org.cnepay.mobilepay.tool.dao.AbstractDao;
import org.springframework.stereotype.Component;

@Component
public class MessageValidateDao extends AbstractDao {

	
	public List<Map<String,Object>> loadMessageValidateCode(String rownum){
		return getJdbcTemplate().queryForList("select * from (select wic.mobile_no mobileNo,wic.id_code validateCode,wic.date_created updateTime,wic.ksn_no ksnNo,wic.validate_status validateStatus,wic.get_count getCount,wic.validate_count validateCount,wic.status status from ws_identify_code wic order by wic.date_created desc) where rownum <= " + rownum);
	}
}
