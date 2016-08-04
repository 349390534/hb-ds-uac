package com.howbuy.uac.rdb.dao;

import java.util.List;
import java.util.Map;

import com.howbuy.rdb.database.dao.impl.BaseDaoImpl;
import com.howbuy.uac.rdb.persistence.ChannelTag;

/**
 * 渠道tag查询DAO
 * @author yichao.song
 *
 */
public class ChannelTagrdbDao extends BaseDaoImpl {


	/**
	 * 获取所有渠道
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<ChannelTag> getAll(){
		return getSqlMapClientTemplate().queryForList("ChannelTag.selectAllChannelTag");
		
	}
	
	/**
	 * 获取所有渠道数据，以map返回
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Map<String,String> getMap(){
		return getSqlMapClientTemplate().queryForMap("ChannelTag.selectAllMap",null,"tagName","tagCode");
	}
}
