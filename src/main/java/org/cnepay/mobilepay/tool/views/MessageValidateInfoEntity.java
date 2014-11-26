package org.cnepay.mobilepay.tool.views;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

public class MessageValidateInfoEntity extends AbstractEntity{
	
	public static Map<String,String> VALIDATE_STATUS = new HashMap<String,String>();
	
	public static Map<String,String> STATUS = new HashMap<String,String>();
	
	static {
		VALIDATE_STATUS.put("0","未验证");
		VALIDATE_STATUS.put("1","已验证");
		
		STATUS.put("0","未发送");
		STATUS.put("1","已发送");
	}

	private String mobileNo = null;
	
	private String validateCode = null;
	
	private Timestamp updateTime = null;
	
	private String ksnNo = null;
	
	private String validateStatus = null;
	
	private String getCount = null;
	
	private String validateCount = null;
	
	private String status = null;
	
	private String lastTime = null;

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getValidateCode() {
		return validateCode;
	}

	public void setValidateCode(String validateCode) {
		this.validateCode = validateCode;
	}

	public String getLastTime() {
		if(this.updateTime == null){
			this.lastTime = "--";
		} else {
			java.util.Date date = new java.util.Date(this.updateTime.getTime());
			this.lastTime =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
		}
		return this.lastTime;
	}
	
	public Timestamp getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
	}

	public String getKsnNo() {
		return ksnNo;
	}

	public void setKsnNo(String ksnNo) {
		this.ksnNo = ksnNo;
	}

	public String getValidateStatus() {
		String result = VALIDATE_STATUS.get(validateStatus);
		return result == null ? "未知" : result;
	}

	public void setValidateStatus(String validateStatus) {
		this.validateStatus = validateStatus;
	}

	public String getGetCount() {
		return getCount;
	}

	public void setGetCount(String getCount) {
		this.getCount = getCount;
	}

	public String getValidateCount() {
		return validateCount;
	}

	public void setValidateCount(String validateCount) {
		this.validateCount = validateCount;
	}

	public String getStatus() {
		String result = STATUS.get(status);
		return result == null ? "未知" : result;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	public int getStats() {
		return 1;
	}
	
	public static void main(String[] args) {
		MessageValidateInfoEntity e = new MessageValidateInfoEntity();
		System.out.println(e.entityValues(e));
		
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return entityValues(this);
	}
}
