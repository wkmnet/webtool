/**
 * Project Name:webtool
 * File Name:BandCardEntity.java
 * Package Name:org.cnepay.mobilepay.tool.views
 * Date:2015年7月30日下午1:25:23
 * Copyright (c) 2015, wukm@cnepay.com.cn All Rights Reserved.
 **/

package org.cnepay.mobilepay.tool.views;
/**
 * ClassName:BandCardEntity <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2015年7月30日 下午1:25:23 <br/>
 * @author   wkm
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class BandCardEntity {
	
	private String cardNo;
	
	private String cardLength;
	
	private String cardName;
	
	private String cardCode;
	
	private String cardCodeLength;
	
	private String cardIssuer;
	
	private String cardType;

	public String getCardNo() {
		return cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

	public String getCardLength() {
		return cardLength;
	}

	public void setCardLength(String cardLength) {
		this.cardLength = cardLength;
	}

	public String getCardName() {
		return cardName;
	}

	public void setCardName(String cardName) {
		this.cardName = cardName;
	}

	public String getCardCode() {
		return cardCode;
	}

	public void setCardCode(String cardCode) {
		this.cardCode = cardCode;
	}

	public String getCardCodeLength() {
		return cardCodeLength;
	}

	public void setCardCodeLength(String cardCodeLength) {
		this.cardCodeLength = cardCodeLength;
	}

	public String getCardIssuer() {
		return cardIssuer;
	}

	public void setCardIssuer(String cardIssuer) {
		this.cardIssuer = cardIssuer;
	}
	

	public String getCardType() {
		return cardType;
	}

	public void setCardType(String cardType) {
		this.cardType = cardType;
	}

	@Override
	public String toString() {
		return "BandCardEntity [cardNo=" + cardNo + ", cardLength="
				+ cardLength + ", cardName=" + cardName + ", cardCode="
				+ cardCode + ", cardCodeLength=" + cardCodeLength
				+ ", cardIssuer=" + cardIssuer + ", cardType=" + cardType + "]";
	}

	
}

