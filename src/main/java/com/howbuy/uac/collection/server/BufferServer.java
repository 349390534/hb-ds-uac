package com.howbuy.uac.collection.server;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.howbuy.uac.collection.bean.HotClick;
import com.howbuy.uac.collection.bean.PageView;
import com.howbuy.uac.collection.bean.UserAction;
import com.howbuy.uac.protobuf.AppActivation;
import com.howbuy.uac.protobuf.AppPvInfoZj;

/**
 * 缓冲页面数据采集请求 
 * page_view
 * user_action
 * hot_click·
 * @author yichao.song
 *
 */
public class BufferServer {
    private static final Logger log = LoggerFactory.getLogger(BufferServer.class);
    
    /*
     * web端点击采集队列
     */
    private static LinkedBlockingQueue<HotClick> webclickQueue = new LinkedBlockingQueue<HotClick>();
    /*
     * web端pv采集队列
     */
    private static LinkedBlockingQueue<PageView> webpvQueue = new LinkedBlockingQueue<PageView>();
    /*
     * web端时间采集队列
     */
    private static LinkedBlockingQueue<UserAction> webeventQueue = new LinkedBlockingQueue<UserAction>();
    
    private static LinkedBlockingQueue<UserAction> appeventQueue = new LinkedBlockingQueue<UserAction>();
    /*
     * 移动端点击采集队列
     */
    private static LinkedBlockingQueue<HotClick> appclickQueue = new LinkedBlockingQueue<HotClick>();
    
    /*
     * 移动端激活采集队列
     */
    private static LinkedBlockingQueue<AppActivation.Activation> appactivationQueue = new LinkedBlockingQueue<AppActivation.Activation>();
    
    /*
     * 掌基pv上报数据采集队列
     */
    private static LinkedBlockingQueue<AppPvInfoZj.AppPvInfo.PvInfo> appPvZjQueue = new LinkedBlockingQueue<AppPvInfoZj.AppPvInfo.PvInfo>();
    
    
    private static LinkedBlockingQueue<PageView> h5PvQueue = new LinkedBlockingQueue<PageView>();
    /**
     * web pv 缓存队列取出
     * @return
     * @throws InterruptedException
     */
    public static PageView getWebPageView() throws InterruptedException{
    	return webpvQueue.take();
    }
    
    /**
     * web pv加入缓存队列
     * @param pv
     * @throws InterruptedException
     */
    public static void addWebPageview(PageView pv) throws InterruptedException{
    	boolean ret = webpvQueue.offer(pv, 2, TimeUnit.SECONDS);
    	if(!ret)
    		log.warn("webpvQueue full");
    }
    
    /**
     * web event 缓存队列取出
     * @return
     * @throws InterruptedException
     */
    public static UserAction getWebEvent() throws InterruptedException{
    	return webeventQueue.take();
    }
    
    /**
     * web event 加入缓存队列
     * @param ua
     * @throws InterruptedException
     */
    public static void addWebEvent(UserAction ua) throws InterruptedException{
    	boolean ret = webeventQueue.offer(ua,2,TimeUnit.SECONDS);
    	if(!ret)
    		log.warn("webeventQueue full");
    }
    
    /**
     * web click 队列中取出
     * @return
     * @throws InterruptedException
     */
    public static HotClick getWebClick() throws InterruptedException{
    	return webclickQueue.take();
    }
    
    /**
     * web click 加入缓存队列
     * @param hc
     * @throws InterruptedException
     */
    public static void addWebClick(HotClick hc) throws InterruptedException{
    	boolean ret = webclickQueue.offer(hc, 2, TimeUnit.SECONDS);
    	if(!ret)
    		log.warn("webclickQueue full");
    }
    
    
    /**
     * app event 加入队列
     * @param event
     * @throws InterruptedException
     */
    public static void addAppEvent(UserAction event) throws InterruptedException{
    	boolean ret = appeventQueue.offer(event, 2, TimeUnit.SECONDS);
    	if(!ret)
    		log.warn("appeventQueue full");
    }
    
    /***
     * app event 队列取出
     * @return
     * @throws InterruptedException
     */
    public static UserAction getAppEvent() throws InterruptedException{
    	return appeventQueue.take();
    }
    
    /**
     * app click 加入队列
     * @param click
     * @throws InterruptedException
     */
    public static void addAppClick(HotClick click) throws InterruptedException{
    	boolean ret = appclickQueue.offer(click, 2, TimeUnit.SECONDS);
    	if(!ret)
    		log.warn("appclickQueue full");
    }
    
    /**
     * app click 队列取出
     * @return
     * @throws InterruptedException
     */
    public static HotClick getAppClick() throws InterruptedException{
    	return appclickQueue.take();
    }
    
    /**
     * app 激活加入队列
     * @param activation
     * @throws InterruptedException
     */
    public static void addAppActivation(AppActivation.Activation activation) throws InterruptedException{
    	boolean ret = appactivationQueue.offer(activation, 2, TimeUnit.SECONDS);
    	if(!ret)
    		log.warn("appactivationQueue full");
    }
    
    /**
     * app 激活队列取出
     * @return
     * @throws InterruptedException
     */
    public static AppActivation.Activation getAppActivation() throws InterruptedException{
    	return appactivationQueue.take();
    }

    
    /**
     * 掌基app 添加数据到队列
     * @param appPvInfo
     * @throws InterruptedException
     */
    public static void addAppPvZj(AppPvInfoZj.AppPvInfo.PvInfo appPvInfo) throws InterruptedException{
    	boolean fix = appPvZjQueue.offer(appPvInfo,2,TimeUnit.SECONDS);
    	if(!fix)
    		log.warn("appPvZjQueue is full");
    }
    
    /**掌基app取数据
     * @return
     * @throws InterruptedException
     */
    public static AppPvInfoZj.AppPvInfo.PvInfo getAppPvZj() throws InterruptedException{
    	return appPvZjQueue.take();
    }
    
    /**
     * H5pv
     * @throws InterruptedException 
     */
    public static void addH5PV(PageView pv) throws InterruptedException{
    	boolean ret = h5PvQueue.offer(pv, 2, TimeUnit.SECONDS);
    	if(!ret)
    		log.warn("webpvQueue full");
    }
    
    public static PageView getH5PV() throws InterruptedException{
    	
    	return h5PvQueue.take();
    }
    
    
}