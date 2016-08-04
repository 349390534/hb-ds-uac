package com.howbuy.uac.utils;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <pre>
 *  todo:请添加注释描述
 * </pre>
 *
 * @author ji.ma
 * @create 13-1-24 上午10:54
 * @modify
 * @since JDK1.6
 */
public class URIUtils {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    public static String getHostDir(String host){
        if(StringUtils.isEmpty(host)||!host.contains(".")){
            return "";
        }

        return host.substring(0,host.indexOf("."));
    }

    public  static String getFirstPath(String path){
        if(StringUtils.isEmpty(path)){
            return "";
        }

        if (!path.startsWith("/")){
            path = "/" + path;
        }
        return PatternUtils.getMatchGroup(path, "\\/\\w+\\/").replace("/","");
    }
    
    
    
    /**
     * 解析出url参数中的键值对
     * 如 "index.jsp?Action=del&id=123"，解析出Action:del,id:123存入map中
     * @param URL  url地址
     * @return  url请求参数部分
     */
    public static Map<String, String> getURLRequest(String URL){
        Map<String, String> mapRequest = new HashMap<String, String>();
        String[] arrSplit=null;
        String strUrlParam=getTruncateUrlPage(URL);
        if(strUrlParam==null){
            return mapRequest;
        }
        
        //每个键值为一组 www.2cto.com
        arrSplit=strUrlParam.split("[&]");
        for(String strSplit:arrSplit){
	          String[] arrSplitEqual=null;         
	          arrSplitEqual= strSplit.split("[=]");
	          //解析出键值
	          if(arrSplitEqual.length>1){
	              //正确解析
	              mapRequest.put(arrSplitEqual[0], arrSplitEqual[1]);
	          }else{
	              if(arrSplitEqual[0]!=""){
	                //只有参数没有值，不加入
	                mapRequest.put(arrSplitEqual[0], "");       
	              }
	          }
       }   
       return mapRequest;   
    }
   
    /**
     * 去掉url中的路径，留下请求参数部分
     * @param strURL url地址
     * @return url请求参数部分
     */
    public static String getTruncateUrlPage(String strURL){
        String strAllParam=null;
        if(strURL!=null){
	        String[] arrSplit=null;
	        strURL=strURL.trim().toLowerCase();
	        arrSplit=strURL.split("[?]");
	        if(strURL.length()>1){
	          if(arrSplit.length>1){
	              if(arrSplit[1]!=null){
	                strAllParam=arrSplit[1];
	              }
	          }
	       }
        }
       return strAllParam;   
    }

    public static void main(String[] args) {
        String firstPath = getFirstPath("http://www.howbuy.test/fund/newissue/");
        System.out.println("firstPath = " + firstPath);

        String hostDir = getHostDir("simu.howbuy.com");
        System.out.println("hostDir = " + hostDir);
        
        String url = "index.jsp?Action=del&id=123";
        Map<String, String>  paramMap = getURLRequest(url);
        System.out.println("id="+paramMap.get("id"));
    }
}
