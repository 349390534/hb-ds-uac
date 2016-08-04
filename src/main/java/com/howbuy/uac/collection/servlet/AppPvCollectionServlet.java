package com.howbuy.uac.collection.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.howbuy.uac.collection.server.BufferServer;
import com.howbuy.uac.protobuf.AppPvInfoZj;

/**
 * App采集pv数据
 * @author qiankun.li
 *
 */
public class AppPvCollectionServlet extends MobileCollectionServlet {
	
	
	private static Logger logger = LoggerFactory.getLogger(AppPvCollectionServlet.class);

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
		AppPvInfoZj.AppPvInfo pvInfo = AppPvInfoZj.AppPvInfo.parseFrom(protobuf);
		List<AppPvInfoZj.AppPvInfo.PvInfo> infoList =
				pvInfo.getPvInfoListList();
		for(AppPvInfoZj.AppPvInfo.PvInfo pv:infoList){
			logger.info(pv.toString());
			BufferServer.addAppPvZj(pv);
		}
		
	}

}
