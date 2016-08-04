package com.howbuy.uac.collection.bean;

import com.howbuy.hadoop.hbase.orm.annotation.HColumn;
import com.howbuy.hadoop.hbase.orm.annotation.HTable;

/**
 * <pre>
 *  用户PV对象bean
 * </pre>
 *
 * @author ji.ma
 * @create 13-1-22 下午7:35
 * @modify
 * @since JDK1.6
 */


@HTable(tableName = "page_view")
public class PageView {

    @HColumn(id = true)
    private String rowKey;

    //----------------------------src---------------------------------
    @HColumn(familyName = "src",qualifierName = "cookie")
    private String srcCookie;

    @HColumn(familyName = "src",qualifierName = "time")
    private long srcTime;

    @HColumn(familyName = "src",qualifierName = "channel")
    private String srcChannel;

    @HColumn(familyName = "src",qualifierName = "subchannel")
    private String srcSubchannel;

    @HColumn(familyName = "src",qualifierName = "url")
    private String srcUrl;

    @HColumn(familyName = "src",qualifierName = "os")
    private String srcOs;

    //流量来源站点
    @HColumn(familyName = "src",qualifierName = "site")
    private String srcSite;
    
    @HColumn(familyName = "src",qualifierName = "key")
    private String srcKey;
    
    //上一个页面所属站点
    @HColumn(familyName = "src",qualifierName = "prevsite")
    private String prevSite;

    @HColumn(familyName = "src",qualifierName = "browser")
    private String srcBrowser;

    @HColumn(familyName = "src",qualifierName = "ip")
    private String srcIp;
   
    
    @HColumn(familyName = "src",qualifierName = "htag")
    private String htag;
    
    @HColumn(familyName = "src",qualifierName = "pageid")
    private String refpageid;
    
    
    //----------------------------dest---------------------------------

    @HColumn(familyName = "dest",qualifierName = "url")
    private String destUrl;

    @HColumn(familyName = "dest",qualifierName = "channel")
    private String destChannel;

    @HColumn(familyName = "dest",qualifierName = "subchannel")
    private String destSubchannel;

    @HColumn(familyName = "dest",qualifierName = "key")
    private String destKey;

    @HColumn(familyName = "dest",qualifierName = "duration")
    private String destDuration;
    
    @HColumn(familyName = "dest",qualifierName = "land")
    private String destLand;
    
    @HColumn(familyName = "dest",qualifierName = "proid")
    private String proid;
    
    @HColumn(familyName = "dest",qualifierName = "ehbno")
    private String ehbno;
    
    @HColumn(familyName = "dest",qualifierName = "hbno")
    private String hbno;
    
    @HColumn(familyName = "dest",qualifierName = "pageid")
    private String pageid;
    
    @HColumn(familyName = "dest",qualifierName = "pagelevel")
    private String pageLevel;
    
    private String hutmcc;
    
    
    private String referrer;
    
    private String reserved1;
    
    private String reserved2;
    
    private String reserved3;
    
    private String reserved4;
    
    private String reserved5;
    
    
    public String getPrevSite() {
		return prevSite;
	}

	public void setPrevSite(String prevSite) {
		this.prevSite = prevSite;
	}

    
    public String getRowKey() {
        return rowKey;
    }

    public void setRowKey(String rowKey) {
        this.rowKey = rowKey;
    }

    public String getSrcCookie() {
        return srcCookie;
    }

    public void setSrcCookie(String srcCookie) {
        this.srcCookie = srcCookie;
    }

    public long getSrcTime() {
		return srcTime;
	}

	public void setSrcTime(long srcTime) {
		this.srcTime = srcTime;
	}

	public String getSrcChannel() {
        return srcChannel;
    }

    public void setSrcChannel(String srcChannel) {
        this.srcChannel = srcChannel;
    }

    public String getSrcSubchannel() {
        return srcSubchannel;
    }

    public void setSrcSubchannel(String srcSubchannel) {
        this.srcSubchannel = srcSubchannel;
    }

    public String getSrcUrl() {
        return srcUrl;
    }

    public void setSrcUrl(String srcUrl) {
        this.srcUrl = srcUrl;
    }

    public String getSrcOs() {
        return srcOs;
    }

    public void setSrcOs(String srcOs) {
        this.srcOs = srcOs;
    }

    public String getSrcSite() {
        return srcSite;
    }

    public void setSrcSite(String srcSite) {
        this.srcSite = srcSite;
    }

    public String getSrcBrowser() {
        return srcBrowser;
    }

    public void setSrcBrowser(String srcBrowser) {
        this.srcBrowser = srcBrowser;
    }

    public String getSrcIp() {
        return srcIp;
    }

    public void setSrcIp(String srcIp) {
        this.srcIp = srcIp;
    }

    public String getSrcKey() {
        return srcKey;
    }

    public void setSrcKey(String srcKey) {
        this.srcKey = srcKey;
    }

    public String getDestUrl() {
        return destUrl;
    }

    public void setDestUrl(String destUrl) {
        this.destUrl = destUrl;
    }

    public String getDestChannel() {
        return destChannel;
    }

    public void setDestChannel(String destChannel) {
        this.destChannel = destChannel;
    }

    public String getDestSubchannel() {
        return destSubchannel;
    }

    public void setDestSubchannel(String destSubchannel) {
        this.destSubchannel = destSubchannel;
    }

    public String getDestKey() {
        return destKey;
    }

    public void setDestKey(String destKey) {
        this.destKey = destKey;
    }

    public String getDestDuration() {
        return destDuration;
    }

    public void setDestDuration(String destDuration) {
        this.destDuration = destDuration;
    }
    
    

    public String getHtag() {
		return htag;
	}

	public void setHtag(String htag) {
		this.htag = htag;
	}

	public String getDestLand() {
		return destLand;
	}

	public void setDestLand(String destLand) {
		this.destLand = destLand;
	}

	public String getProid() {
		return proid;
	}

	public void setProid(String proid) {
		this.proid = proid;
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

	public String getPageLevel() {
		return pageLevel;
	}

	public void setPageLevel(String pageLevel) {
		this.pageLevel = pageLevel;
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

	public String getRefpageid() {
		return refpageid;
	}

	public void setRefpageid(String refpageid) {
		this.refpageid = refpageid;
	}

	public String getReserved1() {
		return reserved1;
	}

	public void setReserved1(String reserved1) {
		this.reserved1 = reserved1;
	}

	public String getReserved2() {
		return reserved2;
	}

	public void setReserved2(String reserved2) {
		this.reserved2 = reserved2;
	}

	public String getReserved3() {
		return reserved3;
	}

	public void setReserved3(String reserved3) {
		this.reserved3 = reserved3;
	}

	public String getReserved4() {
		return reserved4;
	}

	public void setReserved4(String reserved4) {
		this.reserved4 = reserved4;
	}

	public String getReserved5() {
		return reserved5;
	}

	public void setReserved5(String reserved5) {
		this.reserved5 = reserved5;
	}

	@Override
	public String toString() {
		return "PageView [rowKey=" + rowKey + ", srcCookie=" + srcCookie
				+ ", srcTime=" + srcTime + ", srcChannel=" + srcChannel
				+ ", srcSubchannel=" + srcSubchannel + ", srcUrl=" + srcUrl
				+ ", srcOs=" + srcOs + ", srcSite=" + srcSite + ", srcKey="
				+ srcKey + ", prevSite=" + prevSite + ", srcBrowser="
				+ srcBrowser + ", srcIp=" + srcIp + ", htag=" + htag
				+ ", refpageid=" + refpageid + ", destUrl=" + destUrl
				+ ", destChannel=" + destChannel + ", destSubchannel="
				+ destSubchannel + ", destKey=" + destKey + ", destDuration="
				+ destDuration + ", destLand=" + destLand + ", proid=" + proid
				+ ", ehbno=" + ehbno + ", hbno=" + hbno + ", pageid=" + pageid
				+ ", pageLevel=" + pageLevel + ", hutmcc=" + hutmcc
				+ ", referrer=" + referrer + ", reserved1=" + reserved1
				+ ", reserved2=" + reserved2 + ", reserved3=" + reserved3
				+ ", reserved4=" + reserved4 + ", reserved5=" + reserved5 + "]";
	}
	
}
