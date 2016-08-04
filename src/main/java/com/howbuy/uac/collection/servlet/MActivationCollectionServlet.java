package com.howbuy.uac.collection.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.howbuy.uac.collection.server.BufferServer;
import com.howbuy.uac.protobuf.AppActivation;

/**
 * 无线端激活采集
 * @author yichao.song
 *
 */
public class MActivationCollectionServlet extends MobileCollectionServlet {
	
	
	private static Logger logger = LoggerFactory.getLogger(MobileCollectionServlet.class);

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		try {
			doProcess(req,resp);
		} catch (Exception e) {
			logger.error("",e);
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		try {
			doProcess(req,resp);
		} catch (Exception e) {
			logger.error("",e);
		}
	}
	
	
	
	protected void doProcess(HttpServletRequest req,HttpServletResponse resp) throws Exception{

		
		byte[] protobuf = getReqBody(req);
		AppActivation.Activation activate = AppActivation.Activation.parseFrom(protobuf);
		
//		logger.info("proid:{},guid:{},custNo:{},resolution:{},agent:{},version:{},network:{},ext:{},"
//				+ "outletcode:{},imei:{},appversion:{},type:{}",
//				activate.getProid(),activate.getGuid(),activate.getCustno(),activate.getResolution(),
//				activate.getAgent(),activate.getVersion(),activate.getNetwork(),activate.getExt(),activate.getOutletcode(),
//				activate.getImei(),activate.getAppversion(),activate.getType());
		
		BufferServer.addAppActivation(activate);
	}

}
