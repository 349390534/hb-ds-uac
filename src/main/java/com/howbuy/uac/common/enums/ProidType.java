/**
 * 
 */
package com.howbuy.uac.common.enums;

/**
 * @author qiankun.li 
 * 采集数据源
 */
public enum ProidType {
	/**
	 * howbuy网站
	 */
	PROID_1001(1001,"howbuy网站"),
	/**
	 * 储蓄罐iPhone APP
	 */
	PROID_2001(2001,"储蓄罐iPhone APP"),
	/**
	 * 储蓄罐安卓APP
	 */
	PROID_2002(2002,"储蓄罐安卓APP"),
	/**
	 * 储蓄罐H5
	 */
	PROID_2003(2003,"储蓄罐H5"),
	/**
	 * 掌上基金iPhone APP
	 */
	PROID_3001(3001,"掌上基金iPhone APP"),
	/**
	 * 掌上基金安卓APP
	 */
	PROID_3002(3002,"掌上基金安卓APP"),
	/**
	 * 信托大全iPhone App
	 */
	PROID_4001(4001,"信托大全iPhone App"),
	/**
	 * 信托大全安卓APP
	 */
	PROID_4002(4002,"信托大全安卓APP"),
	/**
	 * WAP
	 */
	PROID_5001(5001,"WAP"),
	/**
	 * 无线活动页
	 */
	PROID_5002(5002,"无线活动页"),
	/**
	 * 腾讯网站
	 */
	PROID_6001(6001,"腾讯网站"),
	/**
	 * 腾讯自选股
	 */
	PROID_6002(6002,"腾讯自选股"),
	/**
	 * 凤凰网站
	 */
	PROID_6101(6101,"凤凰网站"),
	/**
	 * 百度网站
	 */
	PROID_6201(6201,"百度网站"),
	/**
	 * 百度APP
	 */
	PROID_6202(6202,"百度APP"),
	/**
	 * AON网站
	 */
	PROID_6301(6301,"AON网站"),
	;
	private int type;
	private String name;
	private ProidType(int type,String name) {
		this.name=name;
		this.type=type;
		
	}
	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
