package com.howbuy.uac.collection.bean;

import com.howbuy.hadoop.hbase.orm.annotation.HColumn;
import com.howbuy.hadoop.hbase.orm.annotation.HTable;

/**
 * <pre>
 *  todo:请添加注释描述
 * </pre>
 *
 * @author ji.ma
 * @create 13-1-24 下午4:47
 * @modify
 * @since JDK1.6
 */
@HTable(tableName = "user_action")
public class UserAction {
    @HColumn(id = true)
    private String rowKey;

    @HColumn(familyName = "action", qualifierName = "cookie")
    private String cookie;

    @HColumn(familyName = "action", qualifierName = "time")
    private long time;

    @HColumn(familyName = "action", qualifierName = "type")
    private String type;

    @HColumn(familyName = "action", qualifierName = "url")
    private String url;

    @HColumn(familyName = "action", qualifierName = "key")
    private String key;

    @HColumn(familyName = "action", qualifierName = "channel")
    private String channel;

    @HColumn(familyName = "action", qualifierName = "subchannel")
    private String subchannel;

    
    /**
     * 交易数据收集
     */
    //amount: 交易金额（元）（部分业务类型该值为空）
    @HColumn(familyName = "action", qualifierName = "amount")
    private String amount;
    
    //fundcode （部分业务类型该值为空）
    @HColumn(familyName = "action", qualifierName = "fundcode")
    private String fundCode;
    
    //toFundCode （转换后基金代码,只有转换业务，该字段才有值）
    @HColumn(familyName = "action", qualifierName = "tofundcode")
    private String toFundCode;
    
    //cardNo （部分业务类型该值为空）
    @HColumn(familyName = "action", qualifierName = "cardno")
    private String cardNo;
    
    //certCode （部分业务类型该值为空）
    @HColumn(familyName = "action", qualifierName = "certtype")
    private String certType;
    
    //certCode 证件号码
    @HColumn(familyName = "action", qualifierName = "certcode")
    private String certCode;
    
    //txRslt 1-成功   0-失败 -1-付款中（只针对交易业务才有值）
    @HColumn(familyName = "action", qualifierName = "txrslt")
    private String txRslt;
    
    //payType 1-代扣 2-网银转账 （只针对交易业务才有值）
    @HColumn(familyName = "action", qualifierName = "paytype")
    private String payType;
    
    
    //custNo （客户号，后续供活动查询联机接口使用，只有注册，且登入状态下，才会传该值)
    @HColumn(familyName = "action", qualifierName = "custNo")
    private String custNo; 
    
    
    //交易合同号
    @HColumn(familyName = "action", qualifierName = "contractno")
    private String contractNo; 
    
    //渠道号
    @HColumn(familyName = "action", qualifierName = "coopid")
    private String coopId;
    
    //活动号
    @HColumn(familyName = "action", qualifierName = "actid")
    private String actId; 
    
    //赎回
    @HColumn(familyName = "action", qualifierName = "appvol ")
    private String appVol; 

    /**
     * 预约用户信息
     */
    @HColumn(familyName = "action",qualifierName = "userid")
    private String userid;
    
    @HColumn(familyName = "action",qualifierName = "phone")
    private String phone;
    
    @HColumn(familyName = "action",qualifierName = "email")
    private String email;
    
    @HColumn(familyName = "action",qualifierName = "name")
    private String name;
    
    @HColumn(familyName = "action",qualifierName = "proid")
    private String proid;
    
    /**
     * 订单流信息
     * @return
     */
    @HColumn(familyName = "action",qualifierName = "otrack")
    private String otrack;
    
    
    /*
     * 搜索框关键则
     */
    private String searchkey;
    
    /*
     * 搜索命中数
     */
    private String hitcount;
    

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

	public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getChannel() {
        return channel;
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


	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getToFundCode() {
		return toFundCode;
	}

	public void setToFundCode(String toFundCode) {
		this.toFundCode = toFundCode;
	}

	public String getCardNo() {
		return cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

	public String getCertType() {
		return certType;
	}

	public void setCertType(String certType) {
		this.certType = certType;
	}

	public String getCertCode() {
		return certCode;
	}

	public void setCertCode(String certCode) {
		this.certCode = certCode;
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

	public String getCustNo() {
		return custNo;
	}

	public void setCustNo(String custNo) {
		this.custNo = custNo;
	}
	
	
	public String getContractNo() {
		return contractNo;
	}

	public void setContractNo(String contractNo) {
		this.contractNo = contractNo;
	}


	public String getCoopId() {
		return coopId;
	}

	public void setCoopId(String coopId) {
		this.coopId = coopId;
	}

	public String getActId() {
		return actId;
	}

	public void setActId(String actId) {
		this.actId = actId;
	}
	
	public String getFundCode() {
		return fundCode;
	}

	public void setFundCode(String fundCode) {
		this.fundCode = fundCode;
	}

	public String getAppVol() {
		return appVol;
	}

	public void setAppVol(String appVol) {
		this.appVol = appVol;
	}
	
	

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getOtrack() {
		return otrack;
	}

	public void setOtrack(String otrack) {
		this.otrack = otrack;
	}

	public String getProid() {
		return proid;
	}

	public void setProid(String proid) {
		this.proid = proid;
	}

	public String getSearchkey() {
		return searchkey;
	}

	public void setSearchkey(String searchkey) {
		this.searchkey = searchkey;
	}

	public String getHitcount() {
		return hitcount;
	}

	public void setHitcount(String hitcount) {
		this.hitcount = hitcount;
	}

	@Override
	public String toString() {
		return "UserAction [rowKey=" + rowKey + ", cookie=" + cookie
				+ ", time=" + time + ", type=" + type + ", url=" + url
				+ ", key=" + key + ", channel=" + channel + ", subchannel="
				+ subchannel + ", amount=" + amount + ", fundCode=" + fundCode
				+ ", toFundCode=" + toFundCode + ", cardNo=" + cardNo
				+ ", certType=" + certType + ", certCode=" + certCode
				+ ", txRslt=" + txRslt + ", payType=" + payType + ", custNo="
				+ custNo + ", contractNo=" + contractNo + ", coopId=" + coopId
				+ ", actId=" + actId + ", appVol=" + appVol + ", userid="
				+ userid + ", phone=" + phone + ", email=" + email + ", name="
				+ name + ", proid=" + proid + ", otrack=" + otrack
				+ ", searchkey=" + searchkey + ", hitcount=" + hitcount + "]";
	}

}
