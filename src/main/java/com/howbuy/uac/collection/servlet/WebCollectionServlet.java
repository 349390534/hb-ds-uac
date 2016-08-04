package com.howbuy.uac.collection.servlet;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.StringTokenizer;
import java.util.UUID;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;

import com.howbuy.hadoop.utils.SpringContextHolder;
import com.howbuy.uac.collection.bean.RunMode;

public class WebCollectionServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected final static String COOKIE_NAME = "__hutmmobile";
	
	protected final static String O_TRACK = "_hbotrack";
	
	protected final static String ZERO_TRACK = "_zero_hbtrack";
	
	protected final static String TEST_HOWBUY_DOMAIN = "howbuy.qa";
	
	protected final static String HOWBUY_DOMAIN = "howbuy.com";
	
	protected final static String EHOWBUY_DOMAIN = "ehowbuy.com";
	
	 // The path the cookie will be available to, edit this to use a different
    // cookie path.
    protected static final String COOKIE_PATH = "/";

    // Two years in seconds.
    protected static final int COOKIE_USER_PERSISTENCE = 63072000;
	
	
	//值会在init时从配置文件读取并修改修改
    protected boolean isDebug = false;
    
    //page_id
    protected String HB_PGID = "_hb_pgid";
	
	
	// 1x1 transparent GIF
    private static final byte[] GIF_DATA = new byte[]{
            (byte) 0x47, (byte) 0x49, (byte) 0x46, (byte) 0x38, (byte) 0x39, (byte) 0x61,
            (byte) 0x01, (byte) 0x00, (byte) 0x01, (byte) 0x00, (byte) 0x80, (byte) 0xff,
            (byte) 0x00, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0x00, (byte) 0x00,
            (byte) 0x00, (byte) 0x2c, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00,
            (byte) 0x01, (byte) 0x00, (byte) 0x01, (byte) 0x00, (byte) 0x00, (byte) 0x02,
            (byte) 0x02, (byte) 0x44, (byte) 0x01, (byte) 0x00, (byte) 0x3b
    };
	
	
	  // Writes the bytes of a 1x1 transparent gif into the response.
    protected void writeGifData(HttpServletResponse response) throws IOException {
        response.addHeader(
                "Cache-Control",
                "private, no-cache, no-cache=Set-Cookie, proxy-revalidate");
        response.addHeader("Pragma", "no-cache");
        response.addHeader("Expires", "Wed, 17 Sep 1975 21:32:10 GMT");
        ServletOutputStream output = response.getOutputStream();
        output.write(GIF_DATA);
        output.flush();
    }
    
    // The last octect of the IP address is removed to anonymize the user.

    /**
     * 收集ip
     *
     * @param remoteAddress .
     * @return .
     */
    protected String getIP(String remoteAddress) {
        if (isEmpty(remoteAddress)) {
            return "";
        }
        // Capture the first three octects of the IP address and replace the forth
        // with 0, e.g. 124.455.3.123 becomes 124.455.3.0
//        String regex = "^([^.]+\\.[^.]+\\.[^.]+\\.).*";
//        Pattern getFirstBitOfIPAddress = Pattern.compile(regex);
//        Matcher m = getFirstBitOfIPAddress.matcher(remoteAddress);
//        if (m.matches()) {
//            return m.groups(1) + "0";
//        } else {
//            return "";
//        }
        return remoteAddress;
    }
    
    /**
     * 得到客户的ip.
     *
     * @param request
     * @return String
     */
    protected String getCustIP(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        // 多级反向代理
        if (!StringUtils.isEmpty(ip)) {
            StringTokenizer st = new StringTokenizer(ip, ",");
            if (st.countTokens() > 1) {
                return st.nextToken();
            }
        }
        return ip;
    }
    
 // A string is empty in our terms, if it is null, empty or a dash.
    protected boolean isEmpty(String in) {
        return in == null || "-".equals(in) || "".equals(in);
    }
    
    
    //只统计howbuy.com的访问
    protected boolean isFromhowbuy(String domain){
    	
    	return !StringUtils.isEmpty(domain) && domain.contains(HOWBUY_DOMAIN);
    }
    
    /**
     * cookie中获取name对应的值
     * @param cookies
     * @return
     */
    protected String getValFromCookies(Cookie[] cookies,String cookieName){
    	
    	if(null == cookies || StringUtils.isEmpty(cookieName)){
    		
    		return null;
    	}
    	
    	String value = null;
    	for(Cookie cok : cookies){
    		if(cookieName.equals(cok.getName())){
    			value = cok.getValue();
    			break;
    		}
    	}
    	return value;
    }
    
    // Generate a visitor id for this hit.
    // If there is a visitor id in the cookie, use that, otherwise
    // use the guid if we have one, otherwise use a random number.
    protected String getVisitorId(
            String guid, String account, String userAgent, String visitCookieVal)
            throws NoSuchAlgorithmException, UnsupportedEncodingException {

        // If there is a value in the cookie, don't change it.
        if (!StringUtils.isEmpty(visitCookieVal)) {
            return visitCookieVal;
        }

        String message;
        if (!isEmpty(guid)) {
            // Create the visitor id using the guid.
            message = guid + account;
        } else {
            // otherwise this is a new user, create a new random id.
            message = userAgent + getRandomNumber() + UUID.randomUUID().toString();
        }

        MessageDigest m = MessageDigest.getInstance("MD5");
        m.update(message.getBytes("UTF-8"), 0, message.length());
        byte[] sum = m.digest();
        BigInteger messageAsNumber = new BigInteger(1, sum);
        String md5String = messageAsNumber.toString(16);

        // Pad to make sure id is 32 characters long.
        while (md5String.length() < 32) {
            md5String = "0" + md5String;
        }

        return "0x" + md5String.substring(0, 16);
    }

    // Get a random number string.
    private static String getRandomNumber() {
        return Integer.toString((int) (Math.random() * 0x7fffffff));
    }
    
    //构建cookie 
   	protected Cookie genCookie(String cookieName,String cookieVal,int livetime,String path,boolean isDebug,String domain) {
   		Cookie cookie = new Cookie(cookieName,cookieVal);
   		cookie.setMaxAge(livetime);
   		cookie.setPath(path);
   		if(StringUtils.isEmpty(domain)){
   			
   			if (!isDebug) {
   				cookie.setDomain(HOWBUY_DOMAIN);
   			} else {
   				cookie.setDomain(TEST_HOWBUY_DOMAIN);
   			}
   		}else{
   			
   			cookie.setDomain(EHOWBUY_DOMAIN);
   		}
   		return cookie;
   	}
   	
   	
    
    @Override
    public void init() {
        RunMode runMode = SpringContextHolder.getBean("runMode");
        this.isDebug = runMode.getDebug();
    }
}
