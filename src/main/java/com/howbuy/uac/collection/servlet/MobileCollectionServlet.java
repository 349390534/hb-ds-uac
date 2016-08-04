package com.howbuy.uac.collection.servlet;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONException;
import net.sf.json.util.PropertySetStrategy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 移动端数据采集基类
 * @author yichao.song
 *
 */
public class MobileCollectionServlet extends HttpServlet {
	
	private static Logger logger = LoggerFactory.getLogger(MobileCollectionServlet.class);

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	/**
	 * 获取请求体字符串
	 * @param request
	 * @return
	 * @throws IOException 
	 */
	protected String getReqBodyAsString(HttpServletRequest request) throws IOException{
		
		ByteArrayOutputStream result = new ByteArrayOutputStream();
		
		byte[] buf = new byte[1024];
		
		InputStream input = request.getInputStream();
		int len = 0;
		while((len = input.read(buf)) > 0){
			result.write(buf, 0, len);
		}
		
		String ret = new String(result.toByteArray(),"utf-8");
		
		logger.info("mobild req body:" + ret);
		
		return ret;
	}
	
	protected byte[] getReqBody(HttpServletRequest request) throws IOException{
		
		ByteArrayOutputStream result = new ByteArrayOutputStream();
		
		byte[] buf = new byte[1024];
		
		InputStream input = request.getInputStream();
		int len = 0;
		while((len = input.read(buf)) > 0){
			result.write(buf, 0, len);
		}
		
		return result.toByteArray();
		
	}
	
}
