package com.howbuy.uac.collection.servlet;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.howbuy.uac.collection.bean.UserAction;
import com.howbuy.uac.collection.server.BufferServer;

/**
 * 网站用户行为采集
 * 
 * @author yichao.song
 * 
 */
public class UserActionCollectionServlet extends WebCollectionServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private final Logger log = LoggerFactory.getLogger(this.getClass());

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Let exceptions bubble up to container, for better debugging.
		try {
			this.save(request, response);
		} catch (Exception e) {
			log.error("", e);
		}
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Let exceptions bubble up to container, for better debugging.
		try {
			this.save(request, response);
		} catch (Exception e) {
			log.error("", e);
		}
	}

	/**
	 * 保存用户行为数据
	 * 
	 * @param request
	 *            .
	 * @param response
	 *            .
	 */
	private void save(HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String cookie = request.getParameter("cookie");

		Cookie[] cookies = request.getCookies();

		if (StringUtils.isEmpty(cookie) || "null".equalsIgnoreCase(cookie)) {
			// Try and get visitor cookie from the request.

			cookie = getValFromCookies(cookies, COOKIE_NAME);

			log.debug("cookie from cookies");
		} else
			log.info("cookie from request paramter");

		if (StringUtils.isEmpty(cookie)) {

			cookie = getVisitorId(request.getHeader("X-DCMGUID"),
					request.getParameter("hutmac"),
					request.getHeader("User-Agent"), cookie);

			log.info("make up cookie:" + cookie);
		}

		long currentTime = System.currentTimeMillis();

		String reverseTimestamp = String.valueOf(Long.MAX_VALUE - currentTime);

		String type = request.getParameter("type");

		String phone = request.getParameter("phone");
		String email = request.getParameter("email");
		String url = getDecodeParamByName("url", request);
		String userId = request.getParameter("userId");
		String name = getDecodeParamByName("name", request);

		// 交易信息
		String amount = request.getParameter("amount"); // amount:
														// 交易金额（元）（部分业务类型该值为空）
		String fundCode = request.getParameter("fundCode"); // fundCode
															// （部分业务类型该值为空）
		String toFundCode = request.getParameter("toFundCode");// toFundCode
																// （转换后基金代码,只有转换业务，该字段才有值）
		String cardNo = request.getParameter("cardNo");// cardNo （部分业务类型该值为空）
		String certType = request.getParameter("certType");// certType 证件类型
		String certCode = request.getParameter("certCode"); // certCode 证件号码
		String txRslt = request.getParameter("txRslt"); // txRslt 1-成功 0-失败
														// -1-付款中（只针对交易业务才有值）
		String payType = request.getParameter("payType");// payType 1-代扣 2-网银转账
															// （只针对交易业务才有值）
		String custNo = request.getParameter("custNo");// custNo
														// （客户号，后续供活动查询联机接口使用，只有注册，且登入状态下，才会传该值)
		String contractNo = request.getParameter("contractNo"); // contractNo
																// 交易合同号
		String coopId = request.getParameter("coopId");// coopId 渠道号
		String actId = request.getParameter("actId");// actId 活动号
		String appVol = request.getParameter("appVol");// appVol 赎回
		
		//搜索
		String searchkey = request.getParameter("searchkey");
		String hitcount = request.getParameter("hitcount");

		String otrack = getValFromCookies(cookies, O_TRACK);

		writeGifData(response);

		// 保存用户行为
		UserAction userAction = new UserAction();

		userAction.setTime(currentTime);
		userAction.setRowKey(cookie + reverseTimestamp);
		userAction.setUrl(url);
		userAction.setCookie(cookie);
		userAction.setType(type);

		userAction.setOtrack(otrack);
		userAction.setCoopId(coopId);
		userAction.setActId(actId);

		//预约
		userAction.setPhone(phone);
		userAction.setEmail(email);
		userAction.setName(name);
		userAction.setUserid(userId);

		// 交易信息
		userAction.setAmount(amount);
		userAction.setFundCode(fundCode);
		userAction.setToFundCode(toFundCode);
		userAction.setCertType(certType);
		userAction.setCertCode(certCode);
		userAction.setTxRslt(txRslt);
		userAction.setPayType(payType);
		userAction.setCustNo(custNo);
		userAction.setContractNo(contractNo);
		userAction.setAppVol(appVol);
		userAction.setCardNo(cardNo);
		
		//搜索
		userAction.setHitcount(hitcount);
		userAction.setSearchkey(searchkey);
		
		log.info(userAction.toString());
		
//		BufferServer.addWebEvent(userAction);
	}

	/**
	 * 根据参数名称取得解码后的值
	 * 
	 * @param paramName
	 *            .
	 * @param request
	 *            .
	 * @return 未找到则返回null.
	 */
	private String getDecodeParamByName(String paramName,
			HttpServletRequest request) {

		String name = null;
		String parameter = request.getParameter(paramName);
		if (parameter == null) {
			return null;
		}
		try {
			// name = new String(parameter.getBytes("iso-8859-1"), "utf-8");
			name = URLDecoder.decode(parameter, "utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return name;
	}

}
