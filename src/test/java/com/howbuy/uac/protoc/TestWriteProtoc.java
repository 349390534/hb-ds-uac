/**
 * 
 */
package com.howbuy.uac.protoc;

import com.howbuy.uac.protobuf.AppPvInfoZj;

/**
 * @author qiankun.li
 *
 */
public class TestWriteProtoc {

	public static void main(String[] args) {
		AppPvInfoZj.AppPvInfo.Builder builder = AppPvInfoZj.AppPvInfo.newBuilder();
		AppPvInfoZj.AppPvInfo.PvInfo info  = AppPvInfoZj.AppPvInfo.PvInfo.newBuilder()
				.setProid("3001").setGuid("12345").setUid("12345").setPageid("91")
				.setPlevel("1").setTag("98765432234444").setUrl("A").setTs(System.currentTimeMillis()).build();
		AppPvInfoZj.AppPvInfo appInfo = builder.addPvInfoList(info).build();
		byte[] bs=appInfo.toByteArray();
		//String url = "http://127.0.0.1:8080/howbuy-uac/appha.do";
		String url = "http://www.howbuy.com/uac/appha.do";
					  http://www.howbuy.com/howbuy-uac/appha.do
		com.howbuy.uac.utils.HttpUtil.getHttpUtil().requstPostObj(url, bs);
		
	}
}
