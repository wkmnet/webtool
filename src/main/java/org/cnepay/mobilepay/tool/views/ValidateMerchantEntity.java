/**
 * Project Name:webtool
 * File Name:ValidateMerchantEntity.java
 * Package Name:org.cnepay.mobilepay.tool.views
 * Date:2015年7月2日下午7:32:12
 * Copyright (c) 2015, wukm@cnepay.com.cn All Rights Reserved.
 **/

package org.cnepay.mobilepay.tool.views;

import org.cnepay.mobilepay.tool.util.StaticMap;

/**
 * ClassName:ValidateMerchantEntity <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2015年7月2日 下午7:32:12 <br/>
 * @author   wkm
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class ValidateMerchantEntity extends AbstractEntity {
	
	private String mobile;
	
	private String merchant;
	
	private String idCard;
	
	private String account;
	
	private String signature;

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getMerchant() {
		return merchant;
	}

	public void setMerchant(String merchant) {
		this.merchant = StaticMap.STATUS_MER_MAP.get(merchant);
	}

	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = StaticMap.STATUS_MAP.get(idCard);
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = StaticMap.STATUS_MAP.get(account);
	}

	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = StaticMap.STATUS_MAP.get(signature);
	}

	@Override
	public String toString() {
		return "ValidateMerchantEntity [mobile=" + mobile + ", merchant="
				+ merchant + ", idCard=" + idCard + ", account=" + account
				+ ", signature=" + signature + "]";
	}
	
	

}

