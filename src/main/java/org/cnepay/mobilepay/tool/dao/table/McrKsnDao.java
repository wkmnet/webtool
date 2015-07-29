package org.cnepay.mobilepay.tool.dao.table;

import java.util.List;
import java.util.Map;

import org.cnepay.mobilepay.tool.dao.AbstractDao;
import org.springframework.stereotype.Component;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;

@Component
public class McrKsnDao extends AbstractDao {

	public List<Map<String,Object>> loadKsn(){
		return getJdbcTemplate().queryForList("select * from mcr_ksn");
	}
	
	public int resetKsnTmk(String ksnNo){
		Integer count = getJdbcTemplate().queryForObject("select count(*) num from key_store ks where ks.key_alias like '%" + ksnNo + "%'", Integer.class);
		if(count.intValue() != 1){
			return count;
		}
		return getJdbcTemplate().update("update key_store ks set ks.key_value='4B608D3C6280D5B64B608D3C6280D5B6',ks.check_value='0F2FCF4A'  where ks.key_alias like '%" + ksnNo + "%'");
	}
	
	public int updateICKeyForDownload(String ksnNo){
		return getJdbcTemplate().update("update merchant_terminal mt set mt.ic_status='11' where mt.id in (select mk.terminal_id from mcr_ksn mk where mk.ksn_no='" + ksnNo + "')");
	}
	
	public List<Map<String,Object>> findByKsn(String ksnNo){
		return getJdbcTemplate().queryForList("select mk.ksn_no ksnNo,mk.is_activated isActivated,mk.is_used isUsed from mcr_ksn mk where mk.ksn_no like '%" + ksnNo + "%'");
	}
	
	public boolean unbundKsn(String ksnNo){
		Boolean result = getTransactionTemplate().execute(new TransactionCallbackKsnImpl(ksnNo));
		return result.booleanValue();
	}
	
	public int findMobileCount(String mobileNo){
		Integer count = getJdbcTemplate().queryForObject("select count(*) count from ws_identify_code wic where wic.mobile_no = '" + mobileNo + "'", Integer.class);
		return count.intValue();
	}
	
	public int deleteMobileMessage(String mobileNo){
		return getJdbcTemplate().update("delete from ws_identify_code wic where wic.mobile_no = '"+ mobileNo +"'");
	}
	
	public int deleteKsnMessage(String ksnNo){
		return getJdbcTemplate().update("delete from ws_identify_code wic where wic.ksn_no= '"+ ksnNo +"'");
	}
	
	public boolean unbundMobileNo(String mobileNo){
		Boolean result = getTransactionTemplate().execute(new TransactionCallbackMobileImpl(mobileNo));
		return result.booleanValue();
	}
	
	private class TransactionCallbackKsnImpl implements TransactionCallback<Boolean>{
		
		private String ksnNo = null;
		
		public TransactionCallbackKsnImpl(String ksnNo){
			this.ksnNo = ksnNo;
		}
		
		public Boolean doInTransaction(TransactionStatus status) {
			// TODO Auto-generated method stub
			getJdbcTemplate().update("update mcr_ksn k set k.is_activated = 0,k.is_used = 0,k.terminal_id = '',k.mac_address=null where k.ksn_no = '" + ksnNo + "'");
			getJdbcTemplate().update("update mcr_serial_number s set s.is_used = 0,s.ksn_id=null,s.terminal_id=null where s.ksn_id = (select k.id from mcr_ksn k where k.ksn_no = '" + ksnNo + "')");
			logger.info("isCompleted:" + status.isCompleted());
			logger.info("isRollbackOnly:" + status.isRollbackOnly());
			logger.info("hasSavepoint:" + status.hasSavepoint());
			return Boolean.TRUE;
		}
		
	}
	
	
	private class TransactionCallbackMobileImpl implements TransactionCallback<Boolean>{
		
		private String mobileNo = null;
		
		public TransactionCallbackMobileImpl(String mobileNo){
			this.mobileNo = mobileNo;
		}
		
		public Boolean doInTransaction(TransactionStatus status) {
			// TODO Auto-generated method stub
			getJdbcTemplate().execute("delete from ws_identify_code wic where wic.mobile_no = '" + this.mobileNo + "'");
			getJdbcTemplate().execute("delete from cm_personal cp where cp.mobile_no = '" + this.mobileNo + "'");
			getJdbcTemplate().execute("delete from merchant_operator mo where mo.login_name like '%" + this.mobileNo + "'");
			
			if(status.isCompleted() || status.isRollbackOnly() || status.hasSavepoint()){
				return Boolean.FALSE;
			}
			
			return Boolean.TRUE;
		}
		
	}
	
//	public List<Map<String,Object>> findByKsn(String ksnNo){
//		return getJdbcTemplate().queryForList("select mk.ksn_no ksnNo,mk.is_activated isActivated,mk.is_used isUsed from mcr_ksn mk where mk.ksn_no like '%" + ksnNo + "%'");
//	}
}
