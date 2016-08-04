<%@ page language="java"
         contentType="image/gif"
         import="com.howbuy.uac.collection.bean.PageView,
                 com.howbuy.uac.collection.server.BufferServer,
                 org.slf4j.Logger,
                 org.slf4j.LoggerFactory,
                 javax.servlet.http.Cookie,
                 java.io.IOException,
                 java.io.UnsupportedEncodingException,
                 java.math.BigInteger,
                 java.net.URL,
                 java.net.URLConnection,
                 java.net.URLDecoder,
                 java.security.MessageDigest,
                 java.security.NoSuchAlgorithmException,
                 java.util.UUID" %>
<%!

    /**
     Copyright 2009 Google Inc. All Rights Reserved.
     **/

    // Tracker version.
    private static final String version = "4.4sj";

    private static final Logger log = LoggerFactory.getLogger("JSP");


    private static final String COOKIE_NAME = "__hutmmobile";

    // The destUrl the cookie will be available to, edit this to use a different
    // cookie destUrl.
    private static final String COOKIE_PATH = "/";

    // Two years in seconds.
    private static final int COOKIE_USER_PERSISTENCE = 63072000;

    // 1x1 transparent GIF
    private static final byte[] GIF_DATA = new byte[]{
            (byte) 0x47, (byte) 0x49, (byte) 0x46, (byte) 0x38, (byte) 0x39, (byte) 0x61,
            (byte) 0x01, (byte) 0x00, (byte) 0x01, (byte) 0x00, (byte) 0x80, (byte) 0xff,
            (byte) 0x00, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0x00, (byte) 0x00,
            (byte) 0x00, (byte) 0x2c, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00,
            (byte) 0x01, (byte) 0x00, (byte) 0x01, (byte) 0x00, (byte) 0x00, (byte) 0x02,
            (byte) 0x02, (byte) 0x44, (byte) 0x01, (byte) 0x00, (byte) 0x3b
    };

    // A string is empty in our terms, if it is null, empty or a dash.
    private static boolean isEmpty(String in) {
        return in == null || "-".equals(in) || "".equals(in);
    }

    // The last octect of the IP address is removed to anonymize the user.
    private static String getIP(String remoteAddress) {
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

    // Generate a visitor rowKey for this hit.
    // If there is a visitor rowKey in the cookie, use that, otherwise
    // use the guid if we have one, otherwise use a random number.
    private static String getVisitorId(
            String guid, String account, String userAgent, Cookie cookie)
            throws NoSuchAlgorithmException, UnsupportedEncodingException {

        // If there is a value in the cookie, don't change it.
        if (cookie != null && cookie.getValue() != null) {
            return cookie.getValue();
        }

        String message;
        if (!isEmpty(guid)) {
            // Create the visitor rowKey using the guid.
            message = guid + account;
        } else {
            // otherwise this is a new user, create a new random rowKey.
            message = userAgent + getRandomNumber() + UUID.randomUUID().toString();
        }

        MessageDigest m = MessageDigest.getInstance("MD5");
        m.update(message.getBytes("UTF-8"), 0, message.length());
        byte[] sum = m.digest();
        BigInteger messageAsNumber = new BigInteger(1, sum);
        String md5String = messageAsNumber.toString(16);

        // Pad to make sure rowKey is 32 characters long.
        while (md5String.length() < 32) {
            md5String = "0" + md5String;
        }

        return "0x" + md5String.substring(0, 16);
    }

    // Get a random number string.
    private static String getRandomNumber() {
        return Integer.toString((int) (Math.random() * 0x7fffffff));
    }

    // Writes the bytes of a 1x1 transparent gif into the response.
    private void writeGifData(HttpServletResponse response) throws IOException {
        response.addHeader(
                "Cache-Control",
                "private, no-cache, no-cache=Set-Cookie, proxy-revalidate");
        response.addHeader("Pragma", "no-cache");
        response.addHeader("Expires", "Wed, 17 Sep 1975 21:32:10 GMT");
        ServletOutputStream output = response.getOutputStream();
        output.write(GIF_DATA);
        output.flush();
    }

    // Make a tracking request to Google Analytics from this server.
    // Copies the headers from the original request to the new one.
    // If request containg hutmdebug parameter, exceptions encountered
    // communicating with Google Analytics are thown.
    private void sendRequestToGoogleAnalytics(
            String hutmUrl, HttpServletRequest request) throws Exception {
        try {
            URL url = new URL(hutmUrl);
            URLConnection connection = url.openConnection();
            connection.setUseCaches(false);

            connection.addRequestProperty("User-Agent",
                    request.getHeader("User-Agent"));
            connection.addRequestProperty("Accepts-Language",
                    request.getHeader("Accepts-Language"));

            connection.getContent();
        } catch (Exception e) {
            if (request.getParameter("hutmdebug") != null) {
                throw new Exception(e);
            }
        }
    }

    private void save(String visitorId, HttpServletRequest request) throws Exception {

        String domain = request.getParameter("hutmhn");

        //只统计howbuy.com的访问
        if (domain == null || (!domain.contains("howbuy.com"))) {
            log.warn("/***************************************/");
            log.warn("   domain is {},it's not howbuy.com", domain);
            log.warn("/***************************************/");
            return;
        }

        //浏览器
        String srcBrowser = request.getParameter("hutmbro");

        String srcOs = request.getParameter("hutmos");

        //着陆页面
        String destUrl = request.getParameter("hutmp");

        String srcIp = getIP(request.getRemoteAddr());

        //__utma=92262275.423009506.1357350390.1357350390.1357527830.2;+__utmz=92262275.1357527830.2.2.utmcsr=baidu|utmccn=(organic)|utmcmd=organic|utmctr=%BA%C3%C2%F2;
        String hutmcc = request.getParameter("hutmcc");
        //来源url
        String srcUrl = request.getParameter("hutmr");

        String destDuration = request.getParameter("hutmdur");

        PageView pageViewDto = new PageView();
        pageViewDto.setSrcBrowser(srcBrowser);
        pageViewDto.setSrcOs(srcOs);
        pageViewDto.setDestUrl(destUrl);
        pageViewDto.setHutmcc(hutmcc);
        pageViewDto.setSrcUrl(srcUrl);
        pageViewDto.setSrcCookie(visitorId);
        pageViewDto.setSrcIp(srcIp);
        pageViewDto.setDestDuration(destDuration);
        pageViewDto.setSrcTime(String.valueOf(System.currentTimeMillis()));
        pageViewDto.setDestDomain(domain);

        //Thread.sleep(10*1000);

        BufferServer.getInstance().add(pageViewDto);
    }

    // Track a page view, updates all the cookies and campaign tracker,
    // makes a server side request to Google Analytics and writes the transparent
    // gif byte data to the response.
    private void trackPageView(
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        log.debug("[JSP]:trackPageView start");
        String timeStamp = Long.toString(System.currentTimeMillis() / 1000);
        String domainName = request.getServerName();
        if (isEmpty(domainName)) {
            domainName = "";
        }

        log.debug("domain name is {}", domainName);

        // Get the referrer from the hutmr parameter, this is the referrer to the
        // page that contains the tracking pixel, not the referrer for tracking
        // pixel.
        String documentReferer = request.getParameter("hutmr");
        if (isEmpty(documentReferer)) {
            documentReferer = "-";
        } else {
            documentReferer = URLDecoder.decode(documentReferer, "UTF-8");
        }

        log.debug("documentReferer {}", documentReferer);

        String documentPath = request.getParameter("hutmp");
        if (isEmpty(documentPath)) {
            documentPath = "";
        } else {
            documentPath = URLDecoder.decode(documentPath, "UTF-8");
        }
        log.debug("documentPath {}", documentPath);

        String account = request.getParameter("hutmac");
        String userAgent = request.getHeader("User-Agent");
        if (isEmpty(userAgent)) {
            userAgent = "";
        }
        log.debug("account {}", account);

        // Try and get visitor cookie from the request.
        Cookie[] cookies = request.getCookies();
        Cookie cookie = null;
        if (cookies != null) {
            for (int i = 0; i < cookies.length; i++) {
                if (cookies[i].getName().equals(COOKIE_NAME)) {
                    cookie = cookies[i];
                    log.debug("cookie {}", cookie);
                }
            }
        }

        String visitorId = getVisitorId(
                request.getHeader("X-DCMGUID"), account, userAgent, cookie);
        log.debug("visitorId {}", visitorId);


        // Always try and addPageView the cookie to the response.
        Cookie newCookie = new Cookie(COOKIE_NAME, visitorId);
        newCookie.setMaxAge(COOKIE_USER_PERSISTENCE);
        newCookie.setPath(COOKIE_PATH);
        response.addCookie(newCookie);

//        String hutmGifLocation = "http://www.google-analytics.com/__hutm.gif";
//
//        // Construct the gif hit url.
//        String hutmUrl = hutmGifLocation + "?" +
//                "hutmwv=" + version +
//                "&hutmn=" + getRandomNumber() +
//                "&hutmhn=" + URLEncoder.encode(domainName, "UTF-8") +
//                "&hutmr=" + URLEncoder.encode(documentReferer, "UTF-8") +
//                "&hutmp=" + URLEncoder.encode(documentPath, "UTF-8") +
//                "&hutmac=" + account +
//                "&hutmcc=__hutma%3D999.999.999.999.999.1%3B" +
//                "&hutmvid=" + visitorId +
//                "&hutmip=" + getIP(request.getRemoteAddr());

        //endRequestToGoogleAnalytics(hutmUrl, request);

        // If the debug parameter is on, addPageView a header to the response that contains
        // the url that was used to contact Google Analytics.
        if (request.getParameter("hutmdebug") != null) {
            response.setHeader("X-GA-MOBILE-URL", "");
        }
        // Finally write the gif data to the response.
        writeGifData(response);
        log.debug("[JSP]:trackPageView end");
        save(visitorId, request);
    }
%>

<%
    // Let exceptions bubble up to container, for better debugging.
    try {
        trackPageView(request, response);
    } catch (Exception e) {
        log.error("", e);
        e.printStackTrace();
    }
%>
