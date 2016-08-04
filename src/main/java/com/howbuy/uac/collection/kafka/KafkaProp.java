
package com.howbuy.uac.collection.kafka;
/**
 * kafka 相关属性配置
 * @author yichao.song
 *
 */
public class KafkaProp {
	
	/*
	 * broker
	 */
	private String broker;
	
	/*
	 *  topics
	 */
	
	private String webpvtopic;
	
	private String webeventtopic;

	private String webclicktopic;
	
	private String apppvtopic;
	
	private String appeventtopic;
	
	private String appclicktopic;
	
	private String appactivatetopic;
	
	private String zookeeperlist;
	
	/***
	 * 后端推送app行为数据topic
	 */
	private String dataappeventtopic;
	
	private String dataappeventgroup;
	
	private String h5pv;
	
	public String getBroker() {
		return broker;
	}

	public void setBroker(String broker) {
		this.broker = broker;
	}

	public String getWebpvtopic() {
		return webpvtopic;
	}

	public void setWebpvtopic(String webpvtopic) {
		this.webpvtopic = webpvtopic;
	}

	public String getWebeventtopic() {
		return webeventtopic;
	}

	public void setWebeventtopic(String webeventtopic) {
		this.webeventtopic = webeventtopic;
	}

	public String getWebclicktopic() {
		return webclicktopic;
	}

	public void setWebclicktopic(String webclicktopic) {
		this.webclicktopic = webclicktopic;
	}

	public String getApppvtopic() {
		return apppvtopic;
	}

	public void setApppvtopic(String apppvtopic) {
		this.apppvtopic = apppvtopic;
	}

	public String getAppeventtopic() {
		return appeventtopic;
	}

	public void setAppeventtopic(String appeventtopic) {
		this.appeventtopic = appeventtopic;
	}

	public String getAppclicktopic() {
		return appclicktopic;
	}

	public void setAppclicktopic(String appclicktopic) {
		this.appclicktopic = appclicktopic;
	}

	public String getAppactivatetopic() {
		return appactivatetopic;
	}

	public void setAppactivatetopic(String appactivatetopic) {
		this.appactivatetopic = appactivatetopic;
	}

	public String getDataappeventtopic() {
		return dataappeventtopic;
	}

	public void setDataappeventtopic(String dataappeventtopic) {
		this.dataappeventtopic = dataappeventtopic;
	}

	public String getZookeeperlist() {
		return zookeeperlist;
	}

	public void setZookeeperlist(String zookeeperlist) {
		this.zookeeperlist = zookeeperlist;
	}

	public String getDataappeventgroup() {
		return dataappeventgroup;
	}

	public void setDataappeventgroup(String dataappeventgroup) {
		this.dataappeventgroup = dataappeventgroup;
	}

	public String getH5pv() {
		return h5pv;
	}

	public void setH5pv(String h5pv) {
		this.h5pv = h5pv;
	}

}
