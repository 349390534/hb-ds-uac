package com.howbuy.uac.hbase;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.hadoop.hbase.client.Put;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.howbuy.hadoop.mapping.MappingServer;
import com.howbuy.hadoop.mapping.dto.UrlInfo;
import com.howbuy.uac.collection.bean.PageView;
import com.howbuy.uac.collection.bean.UserAction;
import com.howbuy.uac.collection.dao.UserActionDao;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:context/spring/applicationContext-beans.xml"})
public class HBaseTest {
	
	@Autowired
	@Qualifier("userActionDao")
	private UserActionDao uadao;
	

//	public Configuration configuration;
	
//	@BeforeClass
//	public void init(){
//		configuration = HBaseConfiguration.create();
//	}
	
//	@Test
//	public void insertData() throws IOException {  
//        System.out.println("start insert data ......"); 
//        configuration = HBaseConfiguration.create();
//        HTablePool pool = new HTablePool(configuration, 1000);  
//        HTable table = (HTable) pool.getTable("page_view_1");  
//        Put put = new Put("112233bbbcccb".getBytes());// 一个PUT代表一行数据，再NEW一个PUT表示第二行数据,每行一个唯一的ROWKEY，此处rowkey为put构造方法中传入的值  
//        put.add("src".getBytes(), "site".getBytes(), "ali".getBytes());// 本行数据的第一列  
//        put.add("dest".getBytes(), "subchannel".getBytes(), "bbb".getBytes());// 本行数据的第二列  
//        put.add("dest".getBytes(), "tag".getBytes(), "tag23r".getBytes());// 本行数据的第三列
//        try {  
//            table.put(put);  
//        } catch (IOException e) {  
//            e.printStackTrace();  
//        } 
//        pool.close();
//        System.out.println("end insert data ......");  
//    } 
	
	@Test
	public void insertPageViewDto(){
		List<Put> pageViewPuts = new ArrayList<Put>();
		Put put = new Put("112233bbbcccb".getBytes());
		PageView dto = new PageView();
//		dto.setCustNo("custno");
		dto.setDestChannel("destchannel");
//		dto.setDestDomain("destdomain");
		dto.setDestDuration("destDuration");
		dto.setDestKey("destKey");
		dto.setDestSubchannel("destSubchannel");
		dto.setDestUrl("destUrl");
		dto.setHutmcc("hutmcc");
//		dto.setHutmcch("hutmcch");
//		dto.setLandingPage(true);
		dto.setPageid("pageId");
		dto.setPageLevel("pageLevel");
		dto.setProid("proid");
//		dto.setReferId("referId");
//		dto.setReferPageLevel("referPageLevel");
		dto.setReferrer("referrer");
		dto.setRowKey("rowKey");
		dto.setSrcBrowser("srcBrowser");
		dto.setSrcChannel("srcChannel");
		dto.setSrcCookie("srcCookie");
		dto.setSrcCookie("visitid");
		dto.setSrcIp("srcIp");
		dto.setSrcKey("srcKey");
		dto.setSrcOs("srcOs");
		dto.setSrcSite("srcSite");
		dto.setSrcSubchannel("srcSubchannel");
		dto.setSrcTime(System.currentTimeMillis()/1000);
		dto.setSrcUrl("srcUrl");
		List<PageView> views = new ArrayList<PageView>();
		views.add(dto);
//		PageViewDao.addPageViews(views);
	}
	
	@Test
	public void insertUAData() throws UnsupportedEncodingException{
	
		UserAction userAction = new UserAction();
        userAction.setTime(System.currentTimeMillis()/1000);
        userAction.setRowKey("cookie1" + System.currentTimeMillis());
        userAction.setUrl("url");
        userAction.setCookie("cookie");
        userAction.setType("type");
//        userAction.setClassify("classify");
//        userAction.setSubclassify("subclassify");
        
        userAction.setChannel("urlInfo.getChannel()");
        userAction.setSubchannel("urlInfo.getSubChannel()");
        userAction.setKey("urlInfo.getKey()");
        
        //交易信息
        userAction.setAmount("amount");
        userAction.setFundCode("fundCode");
        userAction.setToFundCode("toFundCode");
        userAction.setCardNo("cardNo");
        userAction.setCertType("certType");
        userAction.setCertCode("certCode");
        userAction.setTxRslt("txRslt");
        userAction.setPayType("payType");
        userAction.setCustNo("custNo");
        userAction.setContractNo("contractNo");
        userAction.setCoopId("coopId");
        userAction.setActId("actId");
        userAction.setAppVol("appVol");
        userAction.setOtrack("otrack");
        
        //用户信息
        userAction.setPhone("phone");
        userAction.setEmail("email");
        userAction.setName(URLDecoder.decode("使命", "utf-8"));
        userAction.setUserid("userId");
        
        uadao.insert(userAction);
	}
}
