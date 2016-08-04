/**
 * 
 */
package com.howbuy.uac.utils;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.ResourceBundle;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.conn.routing.HttpRoute;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.PoolingClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

/**
 * @author qiankun.li
 * 
 */
public class HttpUtil {

	private static final Logger LOGGER = Logger.getLogger(HttpUtil.class);
	
	private static HttpUtil httpUtil = null;
	/**
	 * 访问客户魔法api接口认证key
	 */
	private static final String appId_Key="X-DataX-AppId";
	private static String appId;
	/**
	 * 访问客户魔法api接口认证value
	 */
	private static final String secret_Key="X-DataX-Secret-Key";
	private static String secretKey;
	
	private HttpUtil() {}
	
	static{
		
		initRequestManager();
		httpUtil = new HttpUtil();
		try {
			ResourceBundle rb = ResourceBundle.getBundle("properties/application", Locale.getDefault());
			appId = rb.getString("appid");
			secretKey = rb.getString("secret_key");
		} catch (Exception e) {
			LOGGER.error(e);
		}
	}
	
	public static HttpUtil getHttpUtil(){
		
		return httpUtil;
	}

	
	private static HttpClient httpClient;
	
	private static void initRequestManager(){
		SchemeRegistry schemeRegistry = new SchemeRegistry();
		schemeRegistry.register(
		new Scheme("http", 80, PlainSocketFactory.getSocketFactory()));
		schemeRegistry.register(
		new Scheme("https", 443, SSLSocketFactory.getSocketFactory()));
		PoolingClientConnectionManager cm = new PoolingClientConnectionManager(schemeRegistry);
		// Increase max total connection to 200
		cm.setMaxTotal(200);
		// Increase default max connection per route to 20
		cm.setDefaultMaxPerRoute(20);
		// Increase max connections for localhost:80 to 50
		HttpHost localhost = new HttpHost("locahost", 80);
		cm.setMaxPerRoute(new HttpRoute(localhost), 50);
		httpClient = new DefaultHttpClient(cm);
	}
	

	public String requestGet(String url){
		String result=null;
		HttpGet httpGet = new HttpGet(url);
		httpGet.setHeader(appId_Key,appId);
		httpGet.setHeader(secret_Key,secretKey);
		try {
			HttpResponse response = httpClient.execute(httpGet);
			LOGGER.info("request Method Get URL ["+url+"],is:"+response.getStatusLine());
			HttpEntity entity =	response.getEntity();
			result = EntityUtils.toString(entity, "UTF-8");
			 // and ensure it is fully consumed
            EntityUtils.consume(entity);
		} catch (ClientProtocolException e) {
			LOGGER.error(e);
		} catch (IOException e) {
			LOGGER.error(e);
		}
		return result;
	
	}
	
	public String requestGet(String url,Map<String, String> paramsMap){
		String result = null;
		URIBuilder builder = new URIBuilder();
		if(StringUtils.isNotBlank(url)){
			url = url.replace("http://", "");
		}
		builder.setScheme("http");
		builder.setHost(url);
		URI uri = null;
		try {
			if(null!=paramsMap && !paramsMap.isEmpty()){
				Set<Entry<String, String>> sets= paramsMap.entrySet();
				for(Entry<String, String> en :sets){
					String key = en.getKey();
					String value = en.getValue();
					builder.setParameter(key, value);
				}
			}
			uri = builder.build();
		} catch (URISyntaxException e) {
			LOGGER.error(e);
		}
		if(null!=uri){
			HttpGet httpGet = new HttpGet(uri);
			httpGet.setHeader(appId_Key,appId);
			httpGet.setHeader(secret_Key,secretKey);
			try {
				HttpResponse response = httpClient.execute(httpGet);
				LOGGER.info("request Method Get URL ["+url+"],is:"+response.getStatusLine());
				HttpEntity entity =	response.getEntity();
				result = EntityUtils.toString(entity, "UTF-8");
				 // and ensure it is fully consumed
	            EntityUtils.consume(entity);
			} catch (ClientProtocolException e) {
				LOGGER.error(e);
			} catch (IOException e) {
				LOGGER.error(e);
			}
		}
		return result;
	}
	public String requestGet(String url,String param){
		String result = null;
		URIBuilder builder = new URIBuilder();
		if(StringUtils.isNotBlank(url)){
			url = url.replace("http://", "");
		}
		builder.setScheme("http");
		builder.setHost(url);
		URI uri = null;
		try {
			if(StringUtils.isNotBlank(param)){
				builder.setQuery(param);
			}
			uri = builder.build();
		} catch (URISyntaxException e) {
			LOGGER.error(e);
		}
		if(null!=uri){
			HttpGet httpGet = new HttpGet(uri);
			httpGet.setHeader(appId_Key,appId);
			httpGet.setHeader(secret_Key,secretKey);
			try {
				HttpResponse response = httpClient.execute(httpGet);
				LOGGER.info("request Method Get URL ["+url+"],is:"+response.getStatusLine());
				HttpEntity entity =	response.getEntity();
				result = EntityUtils.toString(entity,"UTF-8");
				// and ensure it is fully consumed
				EntityUtils.consume(entity);
			} catch (ClientProtocolException e) {
				LOGGER.error(e);
			} catch (IOException e) {
				LOGGER.error(e);
			}
		}
		return result;
	}
	
	
	public String requstPost(String url,Map<String, String> paramMap){
		String result = null;
		try {
            HttpPost httpPost = new HttpPost(url);
            httpPost.setHeader(appId_Key,appId);
            httpPost.setHeader(secret_Key,secretKey);
            if(null!=paramMap && !paramMap.isEmpty()){
            	List <NameValuePair> nvps = new ArrayList <NameValuePair>();
            	Set<Entry<String, String>> pSet = paramMap.entrySet();
            	for(Entry<String, String> en:pSet){
            		String key = en.getKey();
            		String value = en.getValue();
            		nvps.add(new BasicNameValuePair(key, value));
            	}
            	httpPost.setEntity(new UrlEncodedFormEntity(nvps));
            }
            HttpResponse response = httpClient.execute(httpPost);

            LOGGER.info("request Method Post URL ["+url+"],is:"+response.getStatusLine());
            HttpEntity entity =  response.getEntity();
            result = EntityUtils.toString(entity);
            // and ensure it is fully consumed
            EntityUtils.consume(entity);
        }catch(Exception e){
        	 LOGGER.error(e);
        }
		return result;
	}
	public String requstPostObj(String url,byte[] bs){
		String result = null;
		try {
			HttpPost httpPost = new HttpPost(url);
		    httpPost.setHeader(appId_Key,appId);
            httpPost.setHeader(secret_Key,secretKey);
			if(null!=bs && bs.length>=1){
				ByteArrayEntity reqEntity = new ByteArrayEntity(bs);
	            reqEntity.setContentType("binary/octet-stream");
	            reqEntity.setChunked(true);
				httpPost.setEntity(reqEntity);
			}
			HttpResponse response = httpClient.execute(httpPost);
			
			LOGGER.info("request Method Post URL ["+url+"],is:"+response.getStatusLine());
			HttpEntity entity =  response.getEntity();
			result = EntityUtils.toString(entity);
			// and ensure it is fully consumed
			EntityUtils.consume(entity);
		}catch(Exception e){
			LOGGER.error(e);
		}
		return result;
	}
	
	
	public static void main(String[] args) {
		//Map<String, String> paramMap = new HashMap<String, String>();
		String url ="http://192.168.220.220:8018/datax-rest/api/report/outletcode/cust";
		String param="pageIndex=1&pageSize=1000&bizDimension=1&statdtStart=20151201&statdtEnd=20151221&gid=31";
		/*paramMap.put("proid_", "proid_");
		paramMap.put("imei_", "imei_");
		paramMap.put("guid_", "guid_");
		
		new HttpUtil().requstPost(url, paramMap);*/
		/*String s = new HttpUtil().requestGet(url);
		System.out.println(s);
		String sq=new HttpUtil().requestGet(url,param);
		System.out.println(sq);*/
		//String urlCh="http://10.50.50.27:8080/datax-rest/api/report/outletcode/cust";
		String urlCh="http://10.50.50.27:8080/datax-rest/api/admin/queryapi/query?queryNo=API_JOB_FUND_TYPE";
		String sqp=new HttpUtil().requestGet(urlCh);
		System.out.println(sqp);
		
		List<String> list  = new ArrayList<String>(){
			{
				add("1");
				add("2");
				add("3");
			}
		};
		
	}
}
