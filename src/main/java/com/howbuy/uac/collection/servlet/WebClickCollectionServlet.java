package com.howbuy.uac.collection.servlet;

import com.howbuy.uac.collection.bean.HotClick;
import com.howbuy.uac.collection.server.BufferServer;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * 页面点击数据收集
 * @author yichao.song
 *
 */


public class WebClickCollectionServlet extends WebCollectionServlet {

    /**
	 * 
	 */
	private static final long serialVersionUID = 6748461770109007777L;
	
	
	private static final Logger log = LoggerFactory.getLogger(WebClickCollectionServlet.class);



    private void collect(
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        long currentTime = System.currentTimeMillis();
        
        
        writeGifData(response);
        
        String domain = request.getParameter("hutmhn");

        //只统计howbuy.com的访问
        if (!isFromhowbuy(domain)) {
            log.warn("/***************************************/");
            log.warn("   domain is {},it's not howbuy.com", domain);
            log.warn("/***************************************/");
            return;
        }
        
        String visitorId = getVisitId(request);
        
        if(StringUtils.isEmpty(visitorId)){
        	log.warn("visitorId is empty");
        	return;
        }
        

        String htag = request.getParameter("HTAG");
      //着陆页面
        String path = request.getParameter("hutmp");
        
        //浏览器
        String browser = request.getParameter("hutmbro");

        String os = request.getParameter("hutmos");


        String ip = getCustIP(request);

        //__utma=92262275.423009506.1357350390.1357350390.1357527830.2;+__utmz=92262275.1357527830.2.2.utmcsr=baidu|utmccn=(organic)|utmcmd=organic|utmctr=%BA%C3%C2%F2;
        String hutmcc = request.getParameter("hutmcc");
        //来源url
        String srcUrl = request.getParameter("hutmr");

        String referrer = request.getParameter("hutmref");

        String pagelevel = request.getParameter("pagelevel");
        
        String pageid = request.getParameter("pageid");
        
        String tag = request.getParameter("tag");
        
        String proid = request.getParameter("proid");
        
        
        HotClick click = new HotClick();
        
        click.setBrowser(browser);
        click.setCookie(visitorId);
        click.setEhbno(request.getParameter("ehbno"));
        click.setHbno(request.getParameter("hbno"));
        click.setHtag(htag);
        click.setIp(ip);
        click.setOs(os);
        
        if(StringUtils.isEmpty(pageid)){//网站url参数找不到，从cookie中取
        	Cookie[] cookies = request.getCookies();
        	if(null != cookies){
        		for(Cookie cookie : cookies){
        			if(HB_PGID.equals(cookie.getName())){
        				pageid = cookie.getValue();
        				break;
        			}
        		}
        	}
        	
        }
        
        click.setPageid(pageid);
        
        click.setPagelevel(pagelevel);
        click.setProid(proid);
        click.setRowKey(visitorId + String.valueOf(Long.MAX_VALUE - currentTime));
        click.setSrcUrl(srcUrl);
        click.setTag(tag);
        click.setTime(currentTime);
        String protocol = "http";
        if(request.getRequestURL().toString().startsWith("https")){
        	protocol = "https";
        }
        click.setDestUrl(protocol+"://" + domain + path);
        click.setHutmcc(hutmcc);
        click.setReferrer(referrer);
        
        BufferServer.addWebClick(click);
        
    }



    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
        	collect(request, response);
        } catch (Exception e) {
            log.error("", e);
            e.printStackTrace();
        }
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Let exceptions bubble up to container, for better debugging.
        try {
        	collect(request, response);
        } catch (Exception e) {
            log.error("", e);
            e.printStackTrace();
        }
    }
    
    public String getVisitId(HttpServletRequest request){
    	
    	Cookie[] cookies = request.getCookies();
    	String visitid = null;
    	if (cookies != null) {
    		for (int i = 0; i < cookies.length; i++) {
    			if (cookies[i].getName().equals(COOKIE_NAME)) {
    				Cookie cookie = cookies[i];
    				visitid = cookie.getValue();
    				log.debug("cookie {}", cookie);
    				break;
    			}
    		}
    	}
    	
    	return visitid;
    }
    
}
