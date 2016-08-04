package com.howbuy.uac.rdb.service;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.howbuy.uac.rdb.dao.ChannelTagrdbDao;
import com.howbuy.uac.rdb.persistence.ChannelTag;

/**
 * 外部0级tag服务类
 * @author yichao.song
 *
 */
public class ChannelTagrdbService {
	
	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	private List<ChannelTag> bufferList;
	
	private Map<String,String> bufferMap;
	
	
	private ChannelTagrdbDao tagDao;
	
	

	public void setTagDao(ChannelTagrdbDao tagDao) {
		this.tagDao = tagDao;
	}
	
	public void init(){
		bufferList = tagDao.getAll();;
		bufferMap = tagDao.getMap();
		log.info("init bufferMap : {}",bufferMap);
	}



	public List<ChannelTag> getAll(){
		return bufferList;
	}
	
	public Map<String,String> getMap(){
		return  bufferMap;
	}
}
