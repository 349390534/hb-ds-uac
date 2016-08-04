package com.howbuy.uac.collection.servlet;

import java.io.IOException;
import java.net.URI;
import java.util.Map;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.howbuy.hadoop.utils.SpringContextHolder;
import com.howbuy.uac.collection.bean.PageView;
import com.howbuy.uac.collection.server.BufferServer;
import com.howbuy.uac.rdb.service.ChannelTagrdbService;

/**
 * <pre>
 *  pv收集servlet
 * </pre>
 *
 * @author ji.ma
 * @create 13-1-21 上午9:22
 * @modify
 * @since JDK1.6
 */


public class PageViewCollectionServlet extends WebCollectionServlet {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static final String OTHER_CHANNEL = "0.9999999998";
	
	private static Pattern URL_DOMAIN_PATTERN = Pattern.compile("(?:http|https)://.*(?=\\?)");

    private static final Logger log = LoggerFactory.getLogger(PageViewCollectionServlet.class);


    private void save(String visitorId, HttpServletRequest request, boolean isLandingPage,String htag,String domain) throws Exception {

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
        pageViewDto.setSrcCookie(visitorId);
        pageViewDto.setSrcIp(ip);
        pageViewDto.setDestDuration(duration);
        pageViewDto.setSrcTime(timestamp);
        pageViewDto.setReferrer(referrer);
        pageViewDto.setDestLand(isLandingPage ? "1" : "0");
        pageViewDto.setHtag(htag);
        pageViewDto.setEhbno(request.getParameter("ehbno"));
        pageViewDto.setHbno(request.getParameter("hbno"));
        String pageid = request.getParameter("pageid");
        String refpageid = getValFromCookies(request.getCookies(), HB_PGID);
        pageViewDto.setRefpageid(refpageid);
        
        pageViewDto.setProid(proid);
        pageViewDto.setPageid(pageid);
        
        pageViewDto.setPageLevel(request.getParameter("pagelevel"));
        
        pageViewDto.setRowKey(visitorId + (Long.MAX_VALUE-timestamp));
        
        BufferServer.addWebPageview(pageViewDto);
    }

    // Track a page view, updates all the cookies and campaign tracker,
    // makes a server side request to Google Analytics and writes the transparent
    // gif byte data to the response.
    private void trackPageView(
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
    	
    	 String domain = request.getParameter("hutmhn");

         //只统计howbuy.com的访问
         if (!isFromhowbuy(domain)) {
             log.warn("/***************************************/");
             log.warn("   domain is {},it's not howbuy.com", domain);
             log.warn("/***************************************/");
             return;
         }
         
         String servletpath = request.getServletPath();
         
         //判断请求来源是否是H5页面(proid=5002)
         String cookiedomain = servletpath.equals("/ha5.do") ? EHOWBUY_DOMAIN : null;

//    	response.setHeader("P3P", "CP=CAO PSA OUR");
    	
        String account = request.getParameter("hutmac");
        
        String userAgent = request.getHeader("User-Agent");
        if (isEmpty(userAgent)) {
            userAgent = "";
        }
        log.debug("account {}", account);

        // Try and get visitor cookie from the request.
        Cookie[] cookies = request.getCookies();
        
        String visitCookieVal = getValFromCookies(cookies, COOKIE_NAME);

        boolean isLandingPage = true;
        
        if (!StringUtils.isEmpty(visitCookieVal)) {
            isLandingPage = false;
        }

        String visitorId = getVisitorId(
                request.getHeader("X-DCMGUID"), account, userAgent, visitCookieVal);
        log.debug("visitorId {}", visitorId);
        
        //response.setHeader("P3P", "CP=CAO PSA OUR");


        // Always try and addPageView the cookie to the response.
        Cookie visitCookie = genCookie(COOKIE_NAME, visitorId, COOKIE_USER_PERSISTENCE, COOKIE_PATH, isDebug,cookiedomain);
        
        response.addCookie(visitCookie);
        
        String pageid = request.getParameter("pageid");
        if(!StringUtils.isEmpty(pageid)){
        	Cookie pageCookie = genCookie(HB_PGID, pageid, -1, COOKIE_PATH, isDebug,cookiedomain);
        	response.addCookie(pageCookie);
        }
        
        //o_track
        
        String htag = request.getParameter("HTAG");
        
        
        if(!StringUtils.isEmpty(htag)){
        	
        	String otrack = genOtrack(cookies,htag);
        	
        	Cookie otrackCookie = genCookie(O_TRACK,otrack,-1,COOKIE_PATH,isDebug,cookiedomain);
        	
        	response.addCookie(otrackCookie);
        	
        	//0级htag 同步到otrack
        	if(htag.startsWith("0.")){
        		
        		Cookie storedOtrack = genCookie(ZERO_TRACK, htag, 30 * 24 * 60 * 60, COOKIE_PATH, isDebug,cookiedomain);
        		
        		response.addCookie(storedOtrack);
        	}
        	
        	
        }else{//无htag情况
        	
        		String updateVal = null;
        		
        		
        		String zero_tag =  getValFromCookies(cookies,ZERO_TRACK);
        		
        		if(StringUtils.isEmpty(zero_tag)){
        			
        			String refurl = request.getParameter("hutmref");
        			
            		if(!StringUtils.isEmpty(refurl)){
            			
            			String tmpRef = refurl.trim();
            			
            			URI uri = new URI(tmpRef);
            			
            			String domainurl = uri.getHost();
            			
            			if(!StringUtils.isEmpty(domainurl) && !domainurl.contains("howbuy")){
            				
            				Map<String,String> channel_htag = channelTagrdbService.getMap();
            				
            				
            				for(Map.Entry<String, String> entry : channel_htag.entrySet()){
            					
            					if(domainurl.contains(entry.getKey())){
            						updateVal = entry.getValue();
            						break;
            					}
            				}
            				
            				//其他渠道
            				if(updateVal == null)
            					updateVal = OTHER_CHANNEL;
            				
            			}
            		}
					
				}else{
					
					updateVal = zero_tag;
				}
        		
				
				if(null != updateVal){
					
					
					//更新0级tag
					if(zero_tag == null && !updateVal.equals(OTHER_CHANNEL)){
						
						Cookie storedOtrack = genCookie(ZERO_TRACK, updateVal, 30 * 24 * 60 * 60, COOKIE_PATH, isDebug,cookiedomain);
						
						response.addCookie(storedOtrack);
					}
					
					//更新otrack
					
					String[] param = getOtrackParam(null, updateVal);
					
					Cookie otrackCookie = genCookie(O_TRACK,formatOtrack(param),-1,COOKIE_PATH,isDebug,cookiedomain);
					
					response.addCookie(otrackCookie);
				}
        }
        

        // Finally write the gif data to the response.
        writeGifData(response);
        log.debug("[PageViewCollectionServlet]:trackPageView end");
        save(visitorId, request, isLandingPage,htag,domain);
    }


	/**
	 * 获取0-0-0-0.1格式的otrack
	 * @param cookies
	 * @param htag
	 * @return
	 */
    private String genOtrack(Cookie[] cookies,String htag) {
    	
    	String otrack = getValFromCookies(cookies,O_TRACK);
    	
    	String[] params = getOtrackParam(otrack, htag);
    	if(null == params)
    		return null;
    	
    	otrack = formatOtrack(params);
    	
		log.debug("otrack:{}",otrack);
		
		return otrack;
	}

	private String formatOtrack(String[] params) {
		String format = "%1$s-%2$s-%3$s-%4$s.%5$s";
    	return String.format(format, params);
	}
    
    private String[] getOtrackParam(String otrack,String htag){
    	
    	if(StringUtils.isEmpty(htag))
    		return null;
    	
    	try {
    		
			String[] params = htag.split("\\.");
			int pageLevel = Integer.parseInt(params[0]);
			String suffix = params[1];
			
			if(StringUtils.isEmpty(otrack)){
				String ret[] = new String[]{"0","0","0","0","0"};
				ret[pageLevel] = suffix;
				ret[ret.length-1] = pageLevel + "";
				return ret;
			}else{
				String[] dot = otrack.split("\\.");
				
				String[] predotArr = dot[0].split("\\-");
				
				String[] newArr = new String[predotArr.length+1];
				
				predotArr[pageLevel] = suffix;
				
				for(int i = pageLevel + 1; i < predotArr.length; i++){
					predotArr[i] = "0";
				}
				System.arraycopy(predotArr, 0, newArr, 0, predotArr.length);
				newArr[newArr.length-1] = pageLevel + "";
				
				return newArr;
			}
		} catch (Exception e) {
			log.warn("otrack update",e);
			return new String[]{"9","9","9","9","0"};
		}
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
    
    
    
    private ChannelTagrdbService channelTagrdbService;
    
    
    @Override
	public void init() {
		super.init();
		channelTagrdbService = SpringContextHolder.getBean("channeltagrdbService");
	}

	public static void main(String[] args){
    	PageViewCollectionServlet sv = new PageViewCollectionServlet();
    	String[] s = sv.getOtrackParam("00500100019123456-0-0-0.0", "1.79823238");
    	String format = "%1$s-%2$s-%3$s-%4$s.%5$s";
    	System.out.println(String.format(format, s));
    }
    
}
