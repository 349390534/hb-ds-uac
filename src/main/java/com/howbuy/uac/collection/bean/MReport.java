package com.howbuy.uac.collection.bean;

/**
 * 移动端上报数据
 * @author yichao.song
 *
 */
public abstract class MReport {
	
	private String actionTime;


	/*
	 * proid
	 */
	private String productId;
	
	/**
	 * guid
	 */
	private String tokenId;
	
	/**
	 * uid
	 */
	private String custNo;
	
	/**
	 * ext
	 */
	private String ext;
	
	

	public String getProductId() {
		return productId;
	}




	public void setProductId(String productId) {
		this.productId = productId;
	}




	public String getTokenId() {
		return tokenId;
	}




	public void setTokenId(String tokenId) {
		this.tokenId = tokenId;
	}




	public String getCustNo() {
		return custNo;
	}




	public void setCustNo(String custNo) {
		this.custNo = custNo;
	}




	public String getExt() {
		return ext;
	}




	public void setExt(String ext) {
		this.ext = ext;
	}




	public String getActionTime() {
		return actionTime;
	}




	public void setActionTime(String actionTime) {
		this.actionTime = actionTime;
	}

	
	
}
