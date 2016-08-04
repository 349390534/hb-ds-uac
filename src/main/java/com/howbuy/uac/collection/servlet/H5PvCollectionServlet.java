package com.howbuy.uac.collection.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.howbuy.uac.collection.bean.PageView;
import com.howbuy.uac.collection.server.BufferServer;

/**
 * <pre>
 *  H5页面pv收集servlet，指在移动终端访问的h5页面
 * </pre>
 *
 */


public class H5PvCollectionServlet extends WebCollectionServlet {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
    private static final Logger log = LoggerFactory.getLogger(H5PvCollectionServlet.class);


    private void trackPageView(HttpServletRequest request, HttpServletResponse response) throws Exception {
    	
    	String domain = request.getParameter("hutmhn");
    	
    	 //只统计howbuy.com的访问
        if (!isFromhowbuy(domain) || !isFromhowbuy(EHOWBUY_DOMAIN)) {
            log.warn("/***************************************/");
            log.warn("   domain is {},it's not howbuy.com", domain);
            log.warn("/***************************************/");
            return;
        }
    	
        //浏览器
        String browser = request.getParameter("hutmbro");

        String os = request.getParameter("hutmos");

        //着陆页面
        String path = request.getParameter("hutmp");

        String ip = getCustIP(request);

        //__utma=92262275.423009506.1357350390.1357350390.1357527830.2;+__utmz=92262275.1357527830.2.2.utmcsr=baidu|utmccn=(organic)|utmcmd=organic|utmctr=%BA%C3%C2%F2;
        String hutmcc = request.getParameter("hutmcc");
        //来源url
        String srcUrl = request.getParameter("hutmr");

        String duration = request.getParameter("hutmdur");

        String referrer = request.getParameter("hutmref");
        
        String proid = request.getParameter("proid");
        
        String pageid = request.getParameter("pageid");
        
        String pagelevel = request.getParameter("pagelevel");

        String htag =  request.getParameter("HTAG");
        
        //ha.js版本,主要用来监测用户的js有没有更新
//        String version = request.getParameter("hutmwv");
        
        //推广渠道	
//        String hutmcch = request.getParameter("hutmcch");
        
        Cookie[] cookies = request.getCookies();
        
        String visitCookieVal = getValFromCookies(cookies, COOKIE_NAME);
        
        String refpageid = getValFromCookies(cookies, HB_PGID);
        
        
        String hbno = request.getParameter("hbno");
        
        String ehbno = request.getParameter("ehbno");
        
        long timestamp = System.currentTimeMillis();

        PageView pageViewDto = new PageView();
        
        pageViewDto.setSrcBrowser(browser);
        pageViewDto.setSrcOs(os);
        String protocol = "http";
        if(request.getRequestURL().toString().startsWith("https")){
        	protocol = "https";
        }
        pageViewDto.setDestUrl(protocol+"://" + domain + path);
        pageViewDto.setHutmcc(hutmcc);
        pageViewDto.setSrcUrl(srcUrl);
        pageViewDto.setSrcCookie(visitCookieVal);
        pageViewDto.setSrcIp(ip);
        pageViewDto.setDestDuration(duration);
        pageViewDto.setSrcTime(timestamp);
        pageViewDto.setReferrer(referrer);
        pageViewDto.setHtag(htag);
        pageViewDto.setEhbno(ehbno);
        pageViewDto.setHbno(hbno);
       
        pageViewDto.setRefpageid(refpageid);
        
        pageViewDto.setProid(proid);
        pageViewDto.setPageid(pageid);
        
        pageViewDto.setPageLevel(pagelevel);
        
        
        log.info(pageViewDto.toString());
        
        BufferServer.addH5PV(pageViewDto);
    }



    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Let exceptions bubble up to container, for better debugging.
        try {
            trackPageView(request, response);
        } catch (Exception e) {
            log.error("", e);
        }
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Let exceptions bubble up to container, for better debugging.
        try {
            trackPageView(request, response);
        } catch (Exception e) {
            log.error("", e);
        }
    }
    
 
}
