package com.howbuy.uac.collection.bean;

/**
 * 移动应用激活
 * @author yichao.song
 *
 */
public class MActivation extends MReport{

	
	/**
	 * resolution
	 */
	private String resolution;
	
	/**
	 * aggent
	 */
	private String agent;
	
	/**
	 * version
	 */
	private String version;
	
	/**
	 * network
	 */
	private String network;
	
	
	public String getResolution() {
		return resolution;
	}



	public void setResolution(String resolution) {
		this.resolution = resolution;
	}




	public String getAgent() {
		return agent;
	}




	public void setAgent(String agent) {
		this.agent = agent;
	}




	public String getVersion() {
		return version;
	}




	public void setVersion(String version) {
		this.version = version;
	}




	public String getNetwork() {
		return network;
	}




	public void setNetwork(String network) {
		this.network = network;
	}



	@Override
	public String toString() {
		return "MActivation [resolution=" + resolution + ", agent=" + agent
				+ ", version=" + version + ", network=" + network
				+ ", toString()=" + super.toString() + "]";
	}


}
