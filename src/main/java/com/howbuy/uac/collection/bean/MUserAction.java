package com.howbuy.uac.collection.bean;

/**
 * 移动端行为上报
 * @author yichao.song
 *
 */
public class MUserAction extends MReport {

	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getFundCode() {
		return fundCode;
	}
	public void setFundCode(String fundCode) {
		this.fundCode = fundCode;
	}
	public String getToFundCode() {
		return toFundCode;
	}
	public void setToFundCode(String toFundCode) {
		this.toFundCode = toFundCode;
	}
	public String getBankAcct() {
		return bankAcct;
	}
	public void setBankAcct(String bankAcct) {
		this.bankAcct = bankAcct;
	}
	public String getIdType() {
		return idType;
	}
	public void setIdType(String idType) {
		this.idType = idType;
	}
	public String getIdNo() {
		return idNo;
	}
	public void setIdNo(String idNo) {
		this.idNo = idNo;
	}
	public String getTxRslt() {
		return txRslt;
	}
	public void setTxRslt(String txRslt) {
		this.txRslt = txRslt;
	}
	public String getPayType() {
		return payType;
	}
	public void setPayType(String payType) {
		this.payType = payType;
	}
	public String getContractNo() {
		return contractNo;
	}
	public void setContractNo(String contractNo) {
		this.contractNo = contractNo;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getCoopId() {
		return coopId;
	}
	public void setCoopId(String coopId) {
		this.coopId = coopId;
	}
	public String getActionId() {
		return actionId;
	}
	public void setActionId(String actionId) {
		this.actionId = actionId;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getAppVol() {
		return appVol;
	}
	public void setAppVol(String appVol) {
		this.appVol = appVol;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getOtag() {
		return otag;
	}
	public void setOtag(String otag) {
		this.otag = otag;
	}
	/**
	 * 事件类型（4-开户、5-绑卡、6-买基金、7-赎回、8-储蓄罐存入、9-储蓄罐取现、10-定投、
	 * 11-转换、12-撤单、13-储蓄罐活期转定期、14-储蓄罐直接购买定期、15-交易账号登录、16-搜索）
	 */
	private String type;
	/**
	 * 交易金额
	 */
	private String amount;
	
	/**
	 * 基金代码
	 */
	private String fundCode;
	
	/**
	 *  转换后基金代码
	 */
	private String toFundCode;
	
	/**
	 * 银行卡号
	 */
	private String bankAcct;
	
	/**
	 * 证件类型
	 */
	private String idType;
	/**
	 * 证件号码
	 */
	private String idNo;
	
	/**
	 *  返回结果（1-成功 、0-失败、-1-付款中，只针对交易业务才有值。开户、绑卡成功后会有该值1）
	 */
	private String txRslt;
	
	/**
	 * 1-代扣 2-网银转账 （只针对交易业务才有值）
	 */
	private String payType;
	
	/**
	 * 申购的合同号
	 */
	private String contractNo;
	
	private String mobile;
	
	private String coopId;
	
	private String actionId;
	
	/**
	 * （APP报当前页面事件ID，H5报当前页面url）
	 */
	private String url;
	
	private String appVol;
	/**
	 * 电子邮箱（预约时传）
	 */
	private String email;
	/**
	 * 访问路径跟踪
	 */
	private String otag;
	
	
}
