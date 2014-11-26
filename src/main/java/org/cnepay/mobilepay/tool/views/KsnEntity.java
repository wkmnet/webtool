package org.cnepay.mobilepay.tool.views;

import java.util.HashMap;
import java.util.Map;

public class KsnEntity extends AbstractEntity{

	public static Map<String,String> ACTIVATED_ITEMS = new HashMap<String, String>();

	public static Map<String,String> USED_ITEMS = new HashMap<String, String>();
	
	static {
		USED_ITEMS.put("1", "已使用");
		USED_ITEMS.put("0", "未使用");
		
		ACTIVATED_ITEMS.put("0", "未激活");
		ACTIVATED_ITEMS.put("1", "已激活");
	}
	
	private String ksnNo = null;
	
	private String isActivated = null;
	
	private String isUsed = null;

	public String getKsnNo() {
		return ksnNo;
	}

	public void setKsnNo(String ksnNo) {
		this.ksnNo = ksnNo;
	}

	public String getIsActivated() {
		return ACTIVATED_ITEMS.get(isActivated);
	}

	public void setIsActivated(String isActivated) {
		this.isActivated = isActivated;
	}

	public String getIsUsed() {
		return USED_ITEMS.get(isUsed);
	}

	public void setIsUsed(String isUsed) {
		this.isUsed = isUsed;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return entityValues(this);
	}
}
