package com.howbuy.uac.collection.adapter;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLDecoder;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.howbuy.hadoop.mapping.MappingServer;
import com.howbuy.hadoop.mapping.dto.UrlInfo;
import com.howbuy.uac.collection.bean.HotClick;
import com.howbuy.uac.collection.bean.PageView;
import com.howbuy.uac.collection.bean.UserAction;
import com.howbuy.uac.utils.MessyCodeCheckUtils;

/**
 * <pre>
 *  帮助类
 * </pre>
 *
 * @author ji.ma
 * @create 13-1-9 下午3:50
 * @modify
 * @since JDK1.6
 */
public class PageViewAdapter {
    private static final Logger log = LoggerFactory.getLogger(PageViewAdapter.class);

    /**
     * 取得来源网站及关键字，解析频道及子频道
     *
     * @param bean .
     * @return .
     */
    public static PageView adapterPageview(PageView bean) {


        //0表示本站
        if ("0".equals(bean.getSrcUrl())) {
            //simu.howbuy.com -> howbuy.com
//            bean.setSrcSite(getSourceSite(bean.getReferrer()));

            //只对来源为本站的url解析频道及子频道
            UrlInfo urlInfo = getUrlInfo(bean.getReferrer());
            if(urlInfo != null){
            	
            	bean.setSrcChannel(urlInfo.getChannel());
            	bean.setSrcSubchannel(urlInfo.getSubChannel());
            }
            
            bean.setSrcUrl(bean.getReferrer());
        }else if ("-".equals(bean.getSrcUrl())){//直接访问
        	bean.setSrcUrl(null);
        }else if(!StringUtils.isEmpty(bean.getSrcUrl()) && !StringUtils.isEmpty(bean.getReferrer())){
        	 //__utma=92262275.423009506.1357350390.1357350390.1357527830.2;+__utmz=92262275.1357527830.2.2.utmcsr=baidu|utmccn=(organic)|utmcmd=organic|utmctr=%BA%C3%C2%F2;
            String hutmcc = bean.getHutmcc();
            //取得搜索引擎的名称及搜索关键字
            String searchsite = getSearchSite(hutmcc);
            if(searchsite != null && !searchsite.contains("howbuy")){
            	
            	bean.setSrcSite(searchsite);
            	bean.setSrcKey(getSearchKey(hutmcc));
            	log.info("getSearchKey is {},and cookie is {}", bean.getSrcKey(), bean.getSrcCookie());
            }
            
        }else {
        	log.debug("hotclick getSearchKey is {},searchsite is {}",bean.getSrcKey(),bean.getSrcSite());
        }

        //取得着陆页面的频道及子频道
        UrlInfo urlInfo = getUrlInfo(bean.getDestUrl());
        if(urlInfo != null){
        	
        	bean.setDestChannel(urlInfo.getChannel());
        	bean.setDestSubchannel(urlInfo.getSubChannel());
        	bean.setDestKey(urlInfo.getKey());
        }

        return bean;
    }
    
    public static HotClick adapterHotclick(HotClick bean) {


        //0表示本站 
        if ("0".equals(bean.getSrcUrl())) {
        	
        	 bean.setSrcUrl(bean.getReferrer());

        }else if("-".equals(bean.getSrcUrl())){//直接访问
        	bean.setSrcUrl(null);
        }else if(!StringUtils.isEmpty(bean.getSrcUrl()) && !StringUtils.isEmpty(bean.getReferrer())){
        	 //__utma=92262275.423009506.1357350390.1357350390.1357527830.2;+__utmz=92262275.1357527830.2.2.utmcsr=baidu|utmccn=(organic)|utmcmd=organic|utmctr=%BA%C3%C2%F2;
            String hutmcc = bean.getHutmcc();
            //取得搜索引擎的名称及搜索关键字
            String searchsite = getSearchSite(hutmcc);
            if(!searchsite.contains("howbuy")){
            	
            	bean.setSite(searchsite);
            	bean.setKey(getSearchKey(hutmcc));
            	log.debug("pv getSearchKey is {},searchsite is {}",bean.getKey(),bean.getSite());
            }
        }else {
           log.warn("srcurl:{},referer:{},desturl:{}",bean.getSrcUrl(),bean.getReferrer(),bean.getDestUrl());
        }

        return bean;
    }
    
    
    public static UserAction adapterUserAction(UserAction ua){
    	UrlInfo urlInfo = getUrlInfo(ua.getUrl());
    	if(null != urlInfo){
    		
    		ua.setChannel(urlInfo.getChannel());
    		ua.setSubchannel(urlInfo.getSubChannel());
    		ua.setKey(urlInfo.getKey());
    	}
        return ua;
    }

    /**
     * www.sina.com.cn -> sina.com.cn
     * simu.howbuy.com -> howbuy.com
     *
     * @param referrer 来源url
     * @return site
     */
    public static String getSourceSite(String referrer) {
        if (StringUtils.isEmpty(referrer)) {
            return null;
        }
        String site = "";
        try {
            URI uri = new URI(referrer);
            site = uri.getHost();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        if (!StringUtils.isEmpty(site) && site.contains(".")) {
            site = site.substring(site.indexOf(".") + 1);
        }

        return site;
    }

    
    private static UrlInfo  getUrlInfo(String url){
    	UrlInfo urlInfo = MappingServer.getChannel(url);
    	return urlInfo;
    }

    /**
     * 根据来源字符串匹配来源网站
     *
     * @param s 来源字符串
     * @return 来源网站
     */
    private static Pattern hutmcsr_pattern = Pattern.compile("(hutmcsr=)\\w+\\|");
    public static String getSearchSite(String s) {
        log.debug("hutmcc is {}", s);
        Matcher matcher = hutmcsr_pattern.matcher(s);

        boolean b = matcher.find();
        String site = null;
        if (b) {
            String group = matcher.group();
            group = group.replace("hutmcsr=", "");
            site = group.replace("|", "");
        }
        log.debug("getSearchSite is {}", site);
        return site;
    }

    /**
     * 根据来源字符串匹配关键字信息
     *
     * @param s 来源字符串
     * @return 关键字
     */
    private static Pattern hutmctr_pattern = Pattern.compile("(hutmctr=)\\S+");
    private static String getSearchKey(String s) {
        log.debug("hutmcc is {}", s);

        Matcher matcher = hutmctr_pattern.matcher(s);


        String key = "";
        boolean b = matcher.find();
        if (b) {
            String group = matcher.group();
            group = group.replace("hutmctr=", "");
            group = group.replace(";", "");
            try {

                String utf8Key = URLDecoder.decode(group, "UTF-8");
                String gbkKey = URLDecoder.decode(group, "GBK");
                //当前无法解决用户使用搜索引擎的编码问题
                //先用utf-8解码，如果乱码再使用GBK解码
                //如果都不能解码则把原码保存
                if (!MessyCodeCheckUtils.isMessyCode(utf8Key)) {
                    key = utf8Key;
                    log.info("key decode by utf-8 is {}", utf8Key);
                } else if (!MessyCodeCheckUtils.isMessyCode(gbkKey)) {
                    key = gbkKey;
                    log.info("key decode by gbk is {}", gbkKey);
                } else {
                    key = group;
                    log.error("key is messy code!!!");
                }

            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }

        log.debug("getSearchKey is {}", key);

        return key;
    }

    public static void main(String[] args) {
//        String s = "92262275.1358758992.1.2.hutmcsr=baidu|hutmccn=(organic)|hutmcmd=organic|hutmctr=新基金发行一览";
//
//        log.debug("key is {}", getSearchKey(s));
//        log.debug("key is {}", getSearchSite(s));

//        boolean b = isMatch();
//        System.out.println("b = " + b);
//        boolean isMatch = isMatch("http://www.howbuy.test/fund/310308/", "/fund/(\\d+)/");
//        System.out.println("isMatch = " + isMatch);
//        String groups = getGroups("http://www.howbuy.test/fund/310308/", "/fund/(\\d+)/");
//        System.out.println("groups = " + groups);

        String sourceSite = getSourceSite(null);
        System.out.println(sourceSite);
    }

}
