package com.howbuy.uac.collection.bean;

/**
 * 移动端PV
 * @author yichao.song
 *
 */
public class MPageView extends MReport{

	/**
	 * pageid
	 */
	private String pageid;
	
	/**
	 * plevel
	 */
	private String plevel;
	
	/**
	 * tag
	 */
	private String tag;
	
	/**
	 * url
	 */
	private String url;
	
	/**
	 * referid
	 */
	private String referid;
	
	/**
	 * refer
	 */
	private String refer;
	
	/**
	 * pid
	 */
	private String pid;
	
	/**
	 * network
	 */
	private String network;

	public String getPageid() {
		return pageid;
	}

	public void setPageid(String pageid) {
		this.pageid = pageid;
	}

	public String getPlevel() {
		return plevel;
	}

	public void setPlevel(String plevel) {
		this.plevel = plevel;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getReferid() {
		return referid;
	}

	public void setReferid(String referid) {
		this.referid = referid;
	}

	public String getRefer() {
		return refer;
	}

	public void setRefer(String refer) {
		this.refer = refer;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public String getNetwork() {
		return network;
	}

	public void setNetwork(String network) {
		this.network = network;
	}

	@Override
	public String toString() {
		return "MPageView [pageid=" + pageid + ", plevel=" + plevel + ", tag="
				+ tag + ", url=" + url + ", referid=" + referid + ", refer="
				+ refer + ", pid=" + pid + ", network=" + network
				+ "," + super.toString() + "]";
	}

	
}
