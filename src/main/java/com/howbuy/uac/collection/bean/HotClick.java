package com.howbuy.uac.collection.bean;

import com.howbuy.hadoop.hbase.orm.annotation.HColumn;
import com.howbuy.hadoop.hbase.orm.annotation.HTable;

/**
 * <pre>
 *  todo:请添加注释描述
 * </pre>
 *
 * @author ji.ma
 * @create 13-1-22 下午2:24
 * @modify
 * @since JDK1.6
 */
@HTable(tableName = "hot_click")
public class HotClick {
    @HColumn(id = true)
    private String rowKey;

    @HColumn(familyName = "click", qualifierName = "cookie")
    private String cookie;

    @HColumn(familyName = "click", qualifierName = "time")
    private long time;
    
    @HColumn(familyName = "click",qualifierName = "srcurl")
    private String srcUrl;
    
    @HColumn(familyName = "click",qualifierName = "os")
    private String os;
    
    //上一个页面所属站点
    @HColumn(familyName = "click",qualifierName = "prevsite")
    private String prevSite;

    @HColumn(familyName = "click",qualifierName = "site")
    private String site;
    
    @HColumn(familyName = "click",qualifierName = "key")
    private String key;

    @HColumn(familyName = "click",qualifierName = "browser")
    private String browser;

    @HColumn(familyName = "click",qualifierName = "ip")
    private String ip;
    
    @HColumn(familyName = "click",qualifierName = "desturl")
    private String destUrl;
    
    @HColumn(familyName = "click",qualifierName = "ehbno")
    private String ehbno;
    
    @HColumn(familyName = "click",qualifierName = "hbno")
    private String hbno;
    
    @HColumn(familyName = "click",qualifierName = "pageid")
    private String pageid;
    
    @HColumn(familyName = "click",qualifierName = "pagelevel")
    private String pagelevel;
    
    @HColumn(familyName = "click",qualifierName = "tag")
    private String tag;

    @HColumn(familyName = "click", qualifierName = "htag")
    private String htag;

    @HColumn(familyName = "click", qualifierName = "proid")
    private String proid;
    
    private String hutmcc;
    
    private String referrer;
    

	public String getRowKey() {
		return rowKey;
	}

	public void setRowKey(String rowKey) {
		this.rowKey = rowKey;
	}

	public String getCookie() {
		return cookie;
	}

	public void setCookie(String cookie) {
		this.cookie = cookie;
	}

	public long getTime() {
		return time;
	}

	public void setTime(long time) {
		this.time = time;
	}

	public String getSrcUrl() {
		return srcUrl;
	}

	public void setSrcUrl(String srcUrl) {
		this.srcUrl = srcUrl;
	}

	public String getOs() {
		return os;
	}

	public void setOs(String os) {
		this.os = os;
	}

	public String getSite() {
		return site;
	}

	public void setSite(String site) {
		this.site = site;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getBrowser() {
		return browser;
	}

	public void setBrowser(String browser) {
		this.browser = browser;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getDestUrl() {
		return destUrl;
	}

	public void setDestUrl(String destUrl) {
		this.destUrl = destUrl;
	}

	public String getEhbno() {
		return ehbno;
	}

	public void setEhbno(String ehbno) {
		this.ehbno = ehbno;
	}

	public String getHbno() {
		return hbno;
	}

	public void setHbno(String hbno) {
		this.hbno = hbno;
	}

	public String getPageid() {
		return pageid;
	}

	public void setPageid(String pageid) {
		this.pageid = pageid;
	}

	public String getPagelevel() {
		return pagelevel;
	}

	public void setPagelevel(String pagelevel) {
		this.pagelevel = pagelevel;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public String getHtag() {
		return htag;
	}

	public void setHtag(String htag) {
		this.htag = htag;
	}

	public String getProid() {
		return proid;
	}

	public void setProid(String proid) {
		this.proid = proid;
	}

	public String getPrevSite() {
		return prevSite;
	}

	public void setPrevSite(String prevSite) {
		this.prevSite = prevSite;
	}

	public String getHutmcc() {
		return hutmcc;
	}

	public void setHutmcc(String hutmcc) {
		this.hutmcc = hutmcc;
	}

	public String getReferrer() {
		return referrer;
	}

	public void setReferrer(String referrer) {
		this.referrer = referrer;
	}

}
