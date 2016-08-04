package com.howbuy.uac.collection.bean;

import java.io.Serializable;


public class UrlChannel implements Serializable { 
	private static final long serialVersionUID = -3827954044321685952L;
	private String id;
	private String hostKey;   //一级域名
	private String pathKey;    //项目名
	private String channel;    //所属渠道 （一级渠道名称）
	private String subchannel; //二级渠道名称
	private String sedchannel; //三级级渠道名称
	private String urlPattern;  //匹配规则
	private String keyPattern;  //关键字匹配规则
	
	private String errorAllowed;
	private String notFoundAllowed;
	
	private long time;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getHostKey() {
		return hostKey==null?"":hostKey;
	}

	public void setHostKey(String hostKey) {
		this.hostKey = hostKey;
	}

	public String getPathKey() {
		return pathKey==null?"":pathKey;
	}

	public void setPathKey(String pathKey) {
		this.pathKey = pathKey;
	}

	public String getChannel() {
		return channel==null?"":channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public String getSubchannel() {
		return subchannel;
	}

	public void setSubchannel(String subchannel) {
		this.subchannel = subchannel;
	}

	public String getUrlPattern() {
		return urlPattern;
	}

	public void setUrlPattern(String urlPattern) {
		this.urlPattern = urlPattern;
	}

	public String getKeyPattern() {
		return keyPattern;
	}

	public void setKeyPattern(String keyPattern) {
		this.keyPattern = keyPattern;
	}

	public long getTime() {
		return time!=0l?System.currentTimeMillis():time;
	}

	public void setTime(long time) {
		this.time = time;
	}

	public String getErrorAllowed() {
		return errorAllowed;
	}

	public void setErrorAllowed(String errorAllowed) {
		this.errorAllowed = errorAllowed;
	}

	public String getNotFoundAllowed() {
		return notFoundAllowed;
	}

	public void setNotFoundAllowed(String notFoundAllowed) {
		this.notFoundAllowed = notFoundAllowed;
	}

	public String getSedchannel() {
		return sedchannel;
	}

	public void setSedchannel(String sedchannel) {
		this.sedchannel = sedchannel;
	}
	
	@Override
	public String toString() {
		return "UrlChannel [id=" + id + ", hostKey=" + hostKey + ", pathKey="
				+ pathKey + ", channel=" + channel + ", subchannel="
				+ subchannel + ", sedchannel=" + sedchannel + ", urlPattern="
				+ urlPattern + ", keyPattern=" + keyPattern + ", errorAllowed="
				+ errorAllowed + ", notFoundAllowed=" + notFoundAllowed
				+ ", time=" + time + "]";
	}
	
}
