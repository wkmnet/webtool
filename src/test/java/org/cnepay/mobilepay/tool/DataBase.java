package org.cnepay.mobilepay.tool;

import java.util.List;
import java.util.Map;

import org.cnepay.mobilepay.tool.dao.table.McrKsnDao;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;

public class DataBase extends AbstractInitParams {

	@Test
	public void test(){
		JdbcTemplate jdbcTemplate = (JdbcTemplate)getApplicationContext().getBean("jdbcTemplate");
		List<Map<String,Object>> list = jdbcTemplate.queryForList("select * from mcr_ksn");
//		for(Map<String,Object> map : list){
//			System.out.println(map);
//		}
		
		McrKsnDao dao = (McrKsnDao)getApplicationContext().getBean(McrKsnDao.class.getName());
		for(Map<String,Object> map : dao.loadKsn()){
			System.out.println(map);
		}
	}
}
