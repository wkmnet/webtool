package org.cnepay.mobilepay.tool.dao.table;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.BooleanUtils;
import org.cnepay.mobilepay.tool.dao.AbstractDao;
import org.springframework.stereotype.Component;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.transaction.support.TransactionCallback;

@Component
public class AccountDao extends AbstractDao {

	public List<Map<String,Object>> loadAccount(String accountNo){
		return getJdbcTemplate().queryForList("select ba.account_no accountNo,ba.account_name accountName,ba.issuer_bank_name bankName,ba.is_verified isVerified from bank_account ba where ba.account_no like '%"+ accountNo +"%'");
	}
	
	public List<Map<String,Object>> findAccountByMerchant(String merchantId){
		return getJdbcTemplate().queryForList("select ba.id id,ba.account_no accountNo,ba.account_name accountName,ba.issuer_bank_name bankName,ba.is_verified isVerified from bank_account ba left join merchant_bank_account mba on ba.id=mba.bank_account_id where mba.merchant_id = "+ merchantId);
	}
	
	public int validateMerchant(String id){
		return getJdbcTemplate().update("update bank_account ba set ba.is_verified='1' where ba.id = " + id + "");
	}
	
	public int unvalidateMerchant(String id){
		return getJdbcTemplate().update("update bank_account ba set ba.is_verified='0' where ba.id = " + id + "");
	}
	
	public boolean existBankCard(String accountNo){
		int num = getJdbcTemplate().queryForObject("select count(*) num from dict_cardbin dc where substr('" + accountNo + "',0,dc.cardbin_length) = dc.cardbin_code and dc.card_no_length=" + String.valueOf(accountNo.length()) , Integer.class);
		return BooleanUtils.toBoolean(num);
	}
	
	public int unbundAccountCount(String accountNo){
		return getJdbcTemplate().queryForObject("select count(*) num from bank_account ba where ba.account_no='" + accountNo + "'" , Integer.class);
	}
	
	public boolean unbundAccount(String accountNo){
		Boolean result = false;
		try {
			result = getTransactionTemplate().execute(new TransactionCallbackAccountImpl(accountNo));
		} catch (Exception e){
			logger.error("执行数据库操作异常：" + e.getMessage(),e);
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
		return result.booleanValue();
	}
	
	
	private class TransactionCallbackAccountImpl implements TransactionCallback<Boolean>{
		
		private String accountNo = null;
		
		public TransactionCallbackAccountImpl(String accountNo){
			this.accountNo = accountNo;
		}
		
		public Boolean doInTransaction(TransactionStatus status) {
			// TODO Auto-generated method stub
			getJdbcTemplate().execute("delete from merchant_bank_account mba where mba.bank_account_id in (select id from bank_account ba where ba.account_no='" + this.accountNo + "')");
			getJdbcTemplate().execute("delete from bank_account ba where ba.account_no= '" + this.accountNo + "'");
			
			if(status.isCompleted() || status.isRollbackOnly() || status.hasSavepoint()){
				return Boolean.FALSE;
			}
			return Boolean.TRUE;
		}
		
	}
}
