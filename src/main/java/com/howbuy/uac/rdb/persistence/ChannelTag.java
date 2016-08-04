package com.howbuy.uac.rdb.persistence;

import com.howbuy.rdb.database.dto.IBaseDto;

/**
 * 外部渠道tag
 * @author yichao.song
 *
 */
public class ChannelTag implements IBaseDto{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

	/**
	 * 0级tag名称
	 * 
	 * google.com  3800000
	 * baidu.com    2800000
	 * 
	 */
	private String tagName;
	
	private String tagCode;

	public String getTagName() {
		return tagName;
	}

	public void setTagName(String tagName) {
		this.tagName = tagName;
	}

	public String getTagCode() {
		return tagCode;
	}

	public void setTagCode(String tagCode) {
		this.tagCode = tagCode;
	}

	@Override
	public String toString() {
		return "ChannelTag [tagName=" + tagName + ", tagCode=" + tagCode + "]";
	}
	
	
	
}
