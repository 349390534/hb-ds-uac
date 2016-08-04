package com.howbuy.uac.rdb;

import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.howbuy.uac.rdb.dao.ChannelTagrdbDao;
import com.howbuy.uac.rdb.persistence.ChannelTag;
import com.howbuy.uac.rdb.service.ChannelTagrdbService;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:context/spring/applicationContext-beans.xml",
		"classpath:context/spring/applicationContext-datasource.xml",
		"classpath:context/spring/applicationContext.xml"})
public class RdbDaoTest {
	
	@Autowired
	@Qualifier("channeltagrdbDao")
	private ChannelTagrdbDao channeltagrdbDao;
	

	@Autowired
	@Qualifier("channeltagrdbService")
	private ChannelTagrdbService tagService;

	@Test
	public void getAllChannelTag(){
		List<ChannelTag> tags = tagService.getAll();
		for(ChannelTag tag : tags){
			System.out.println(tag.getTagName() + ":" + tag.getTagCode());
		}
	}
	
	@Test
	public void getMap(){
		System.out.println(tagService.getMap());
	}
	
	
}
