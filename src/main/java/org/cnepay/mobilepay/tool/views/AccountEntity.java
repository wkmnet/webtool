package org.cnepay.mobilepay.tool.views;

import java.util.HashMap;
import java.util.Map;

public class AccountEntity extends AbstractEntity{
	
	public static Map<String,String> ACCOUNT_VERIFIED = new HashMap<String,String>();
	
	static {
		ACCOUNT_VERIFIED.put("0", "未认证通过");
		ACCOUNT_VERIFIED.put("1", "已认证通过");
	}

	private String accountNo = null;
	
	private String accountName = null;
	
	private String bankName = null;
	
	private String isVerified = null;

	public String getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getIsVerified() {
		return ACCOUNT_VERIFIED.get(isVerified);
	}

	public void setIsVerified(String isVerified) {
		this.isVerified = isVerified;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return entityValues(this);
	}
}