package com.howbuy.uac.collection.queue;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import kafka.consumer.ConsumerConfig;
import kafka.consumer.ConsumerIterator;
import kafka.consumer.KafkaStream;
import kafka.javaapi.consumer.ConsumerConnector;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.PropertySetStrategy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.howbuy.hadoop.mapping.MyPropertySetStrategy;
import com.howbuy.uac.collection.adapter.PageViewAdapter;
import com.howbuy.uac.collection.bean.HotClick;
import com.howbuy.uac.collection.bean.MUserAction;
import com.howbuy.uac.collection.bean.PageView;
import com.howbuy.uac.collection.bean.UserAction;
import com.howbuy.uac.collection.kafka.KafkaProducer;
import com.howbuy.uac.collection.kafka.KafkaProp;
import com.howbuy.uac.collection.server.BufferServer;
import com.howbuy.uac.protobuf.AppActivation;
import com.howbuy.uac.protobuf.AppPvInfoZj;

/**
 * 异步缓冲队列 app web
 */
public class QueueBuff {

	private Logger log = LoggerFactory.getLogger(this.getClass());

	private KafkaProp kafkaProp;

	private static String FIELD_DELIMITER = "\u0001";

	private static String LINE_DELIMITER = "\n";

	public void setKafkaProp(KafkaProp kafkaProp) {
		this.kafkaProp = kafkaProp;
	}

	public void init() {

		initWebEventThread();
		
		initWebClickThread();
		
		//initAppClickThread();
		
		initAppActivationThread();
		
		initAppEventThread();
		
		initH5Event();
		
		initH5PV();
		
		initWebPvThread();
		
		initAppPvZjThread();
		
	}
	
	/*
	 * h5 pv thread
	 */
	private void initH5PV() {
		
		new Thread(new Runnable(){

			@Override
			public void run() {
				
				log.info("H5 pv buffer scan start");
				
				KafkaProducer producer = new KafkaProducer(kafkaProp
						.getH5pv(), kafkaProp.getBroker());

				while (true) {
					try {

						PageView pv = BufferServer.getH5PV();

						PageViewAdapter.adapterPageview(pv);

						producer.pubMessage(null, buildWebPV(pv).toString());

					} catch (Exception e) {
						log.warn("", e);
					}
				}
				
			}
			
		}).start();
	}

	/**
	 * web event thread
	 */
	private void initWebEventThread(){
		
		new Thread(new Runnable() {
			public void run() {

				log.info("web event buffer scan start");

				KafkaProducer producer = new KafkaProducer(kafkaProp
						.getWebeventtopic(), kafkaProp.getBroker());

				while (true) {
					try {

						UserAction ua = BufferServer.getWebEvent();

						PageViewAdapter.adapterUserAction(ua);

						producer.pubMessage(null, buildWebEvent(ua).toString());

					} catch (Exception e) {
						log.warn("", e);
					}
				}

			}
		}).start();
	}
	/**
	 * web click thread
	 */
	private void initWebClickThread(){
		
		new Thread(new Runnable() {

			public void run() {

				KafkaProducer producer = new KafkaProducer(
						kafkaProp.getWebclicktopic(), kafkaProp.getBroker());

				log.info("web click buffer scan start");

				while (true) {
					try {

						HotClick click = BufferServer.getWebClick();

						PageViewAdapter.adapterHotclick(click);

						producer.pubMessage(null, buildWebClick(click).toString());

					} catch (Exception e) {
						log.warn("", e);
					}
				}

			}
		}).start();
	}
	/**
	 * app click thread
	 */
	private void initAppClickThread(){
		
		new Thread() {
			public void run() {

				log.info("app click buffer scan start");

				while (true) {
					try {
						HotClick click = BufferServer.getAppClick();
						log.debug(click.toString());
					} catch (Exception e) {
						log.warn("", e);
					}
				}

			}
		}.start();

	}
	/**
	 * app activation thread
	 */
	private void initAppActivationThread(){
		
		new Thread() {
			public void run() {

				KafkaProducer producer = new KafkaProducer(
						kafkaProp.getAppactivatetopic(), kafkaProp.getBroker());

				log.info("app activation buffer scan start");

				while (true) {
					try {
						AppActivation.Activation activation = BufferServer
								.getAppActivation();


						producer.pubMessage(null,
								buildAppActivation(activation).toString());

					} catch (Exception e) {
						log.warn("", e);
					}
				}

			}
		}.start();
	}
	/**
	 * app event thread
	 * app后台推送数据
	 */
	private void initAppEventThread(){
		
		new Thread("app-event") {
			public void run() {

				KafkaProducer producer = new KafkaProducer(
						kafkaProp.getAppeventtopic(), kafkaProp.getBroker());

				Properties props = new Properties();
				props.put("zookeeper.connect", kafkaProp.getZookeeperlist());
				props.put("group.id", kafkaProp.getDataappeventgroup());
				props.put("zookeeper.session.timeout.ms", "8000");
				props.put("zookeeper.sync.time.ms", "200");
				props.put("auto.commit.interval.ms", "1000");
				ConsumerConnector consumer = kafka.consumer.Consumer
						.createJavaConsumerConnector(new ConsumerConfig(props));

				log.info("app event buffer scan start");

				Map<String, Integer> topicCountMap = new HashMap<String, Integer>();

				topicCountMap.put(kafkaProp.getDataappeventtopic(),
						new Integer(1));

				Map<String, List<KafkaStream<byte[], byte[]>>> consumerMap = consumer
						.createMessageStreams(topicCountMap);

				List<KafkaStream<byte[], byte[]>> streams = consumerMap
						.get(kafkaProp.getDataappeventtopic());

				KafkaStream<byte[], byte[]> stream = streams.get(0);

				ConsumerIterator<byte[], byte[]> it = stream.iterator();

				JsonConfig config = new JsonConfig();
				config.setRootClass(MUserAction.class);
				config.setPropertySetStrategy(new MyPropertySetStrategy(
						PropertySetStrategy.DEFAULT));

				while (it.hasNext()) {

					try {
						String str = new String(it.next().message(),"utf-8");
						
						log.debug("str:" + str);
						
						MUserAction ua = (MUserAction) JSONObject.toBean(JSONObject
								.fromObject(str),
								config);

						producer.pubMessage(null, buildAppEvent(ua).toString());
						
					} catch (Exception e) {
						log.error("", e);
					}
				}

			}
		}.start();
		
	}
	/**
	 * webpv Thread init
	 */
	private void initWebPvThread(){
		/*
		 * web pv thread
		 */

		new Thread(new Runnable() {

			@Override
			public void run() {

				log.info("web pv buffer scan start");

				KafkaProducer producer = new KafkaProducer(
						kafkaProp.getWebpvtopic(), kafkaProp.getBroker());

				while (true) {
					try {
						PageView pvt = BufferServer.getWebPageView();

						PageViewAdapter.adapterPageview(pvt);

						producer.pubMessage(null, buildWebPV(pvt).toString());

					} catch (Exception e) {
						log.warn("", e);
					}
				}
			}
		}).start();
	}
	/**
	 * 初始化掌基app pv上报线程
	 */
	private void initAppPvZjThread(){
		
		new Thread("app-pv-zj"){
			
			public void run() {

				KafkaProducer producer = new KafkaProducer(
						kafkaProp.getApppvtopic(), kafkaProp.getBroker());

				log.info("app-pv buffer scan start");

				while (true) {
					try {
						
						AppPvInfoZj.AppPvInfo.PvInfo zjPv = BufferServer.getAppPvZj();
						producer.pubMessage(null, buildAppPv(zjPv).toString());

					} catch (Exception e) {
						log.warn("", e);
					}
				}

			}
			
			
		}.start();
	}
	
	
	/**
	 * H5前台上报事件
	 */
	private void initH5Event(){
		
		new Thread("H5-event"){
			
			public void run() {

				KafkaProducer producer = new KafkaProducer(
						kafkaProp.getAppeventtopic(), kafkaProp.getBroker());

				log.info("H5 event buffer scan start");

				while (true) {
					try {
						
						UserAction ua = BufferServer.getAppEvent();

						producer.pubMessage(null, buildH5Event(ua).toString());

					} catch (Exception e) {
						log.warn("", e);
					}
				}

			}
			
			
		}.start();
	}
	
	/**
	 * web pv
	 * 
	 * @param pvt
	 * @return
	 */
	private StringBuilder buildWebPV(PageView pvt) {

		StringBuilder builder = new StringBuilder();
		builder.append(pvt.getSrcCookie());
		builder.append(FIELD_DELIMITER);
		builder.append(pvt.getSrcTime());
		builder.append(FIELD_DELIMITER);
		builder.append(pvt.getSrcUrl());
		builder.append(FIELD_DELIMITER);
		builder.append(pvt.getSrcSite());
		builder.append(FIELD_DELIMITER);
		builder.append(pvt.getSrcOs());
		builder.append(FIELD_DELIMITER);
		builder.append(pvt.getSrcBrowser());
		builder.append(FIELD_DELIMITER);
		builder.append(pvt.getSrcIp());
		builder.append(FIELD_DELIMITER);
		builder.append(pvt.getSrcKey());
		builder.append(FIELD_DELIMITER);
		builder.append(pvt.getSrcChannel());
		builder.append(FIELD_DELIMITER);
		builder.append(pvt.getSrcSubchannel());
		builder.append(FIELD_DELIMITER);
		builder.append(pvt.getHtag());
		builder.append(FIELD_DELIMITER);
		builder.append(pvt.getRefpageid());
		builder.append(FIELD_DELIMITER);
		builder.append(pvt.getDestUrl());
		builder.append(FIELD_DELIMITER);
		builder.append(pvt.getDestChannel());
		builder.append(FIELD_DELIMITER);
		builder.append(pvt.getDestSubchannel());
		builder.append(FIELD_DELIMITER);
		builder.append(pvt.getDestKey());
		builder.append(FIELD_DELIMITER);
		builder.append(pvt.getDestDuration());
		builder.append(FIELD_DELIMITER);
		builder.append(pvt.getDestLand());
		builder.append(FIELD_DELIMITER);
		builder.append(pvt.getPageid());
		builder.append(FIELD_DELIMITER);
		builder.append(pvt.getPageLevel());
		builder.append(FIELD_DELIMITER);
		builder.append(pvt.getEhbno());
		builder.append(FIELD_DELIMITER);
		builder.append(pvt.getHbno());
		builder.append(FIELD_DELIMITER);
		builder.append(pvt.getProid());
		builder.append(FIELD_DELIMITER);
		builder.append(pvt.getReserved2());
		builder.append(FIELD_DELIMITER);
		builder.append(pvt.getReserved3());
		builder.append(FIELD_DELIMITER);
		builder.append(pvt.getReserved4());
		builder.append(FIELD_DELIMITER);
		builder.append(pvt.getReserved5());
		builder.append(LINE_DELIMITER);

		return builder;
	}

	/**
	 * build web event
	 * 
	 * @param ua
	 * @return
	 */
	private StringBuilder buildWebEvent(UserAction ua) {

		StringBuilder builder = new StringBuilder();
		builder.append(ua.getCookie());
		builder.append(FIELD_DELIMITER);
		builder.append(ua.getTime());
		builder.append(FIELD_DELIMITER);
		builder.append(ua.getType());
		builder.append(FIELD_DELIMITER);
		builder.append(ua.getUrl());
		builder.append(FIELD_DELIMITER);
		builder.append(ua.getKey());
		builder.append(FIELD_DELIMITER);
		builder.append(ua.getChannel());
		builder.append(FIELD_DELIMITER);
		builder.append(ua.getSubchannel());
		builder.append(FIELD_DELIMITER);
		builder.append(ua.getAmount());
		builder.append(FIELD_DELIMITER);
		builder.append(ua.getFundCode());
		builder.append(FIELD_DELIMITER);
		builder.append(ua.getToFundCode());
		builder.append(FIELD_DELIMITER);
		builder.append(ua.getCardNo());
		builder.append(FIELD_DELIMITER);
		builder.append(ua.getCertType());
		builder.append(FIELD_DELIMITER);
		builder.append(ua.getCertCode());
		builder.append(FIELD_DELIMITER);
		builder.append(ua.getTxRslt());
		builder.append(FIELD_DELIMITER);
		builder.append(ua.getPayType());
		builder.append(FIELD_DELIMITER);
		builder.append(ua.getCustNo());
		builder.append(FIELD_DELIMITER);
		builder.append(ua.getContractNo());
		builder.append(FIELD_DELIMITER);
		builder.append(ua.getCoopId());
		builder.append(FIELD_DELIMITER);
		builder.append(ua.getActId());
		builder.append(FIELD_DELIMITER);
		builder.append(ua.getAppVol());
		builder.append(FIELD_DELIMITER);
		builder.append(ua.getUserid());
		builder.append(FIELD_DELIMITER);
		builder.append(ua.getPhone());
		builder.append(FIELD_DELIMITER);
		builder.append(ua.getEmail());
		builder.append(FIELD_DELIMITER);
		builder.append(ua.getName());
		builder.append(FIELD_DELIMITER);
		builder.append(ua.getOtrack());
		builder.append(FIELD_DELIMITER);
		builder.append(ua.getHitcount());
		builder.append(FIELD_DELIMITER);
		builder.append(ua.getSearchkey());
		builder.append(LINE_DELIMITER);

		return builder;
	}

	/**
	 * build web click
	 * 
	 * @param click
	 * @return
	 */
	private StringBuilder buildWebClick(HotClick click) {
		StringBuilder builder = new StringBuilder();
		builder.append(click.getCookie());
		builder.append(FIELD_DELIMITER);
		builder.append(click.getTime());
		builder.append(FIELD_DELIMITER);
		builder.append(click.getSrcUrl());
		builder.append(FIELD_DELIMITER);
		builder.append(click.getOs());
		builder.append(FIELD_DELIMITER);
		builder.append(click.getPrevSite());
		builder.append(FIELD_DELIMITER);
		builder.append(click.getSite());
		builder.append(FIELD_DELIMITER);
		builder.append(click.getKey());
		builder.append(FIELD_DELIMITER);
		builder.append(click.getBrowser());
		builder.append(FIELD_DELIMITER);
		builder.append(click.getIp());
		builder.append(FIELD_DELIMITER);
		builder.append(click.getDestUrl());
		builder.append(FIELD_DELIMITER);
		builder.append(click.getEhbno());
		builder.append(FIELD_DELIMITER);
		builder.append(click.getHbno());
		builder.append(FIELD_DELIMITER);
		builder.append(click.getPageid());
		builder.append(FIELD_DELIMITER);
		builder.append(click.getPagelevel());
		builder.append(FIELD_DELIMITER);
		builder.append(click.getTag());
		builder.append(FIELD_DELIMITER);
		builder.append(click.getHtag());
		builder.append(FIELD_DELIMITER);
		builder.append(click.getProid());
		builder.append(LINE_DELIMITER);

		return builder;
	}

	/*
	 * 激活
	 */
	public static StringBuilder buildAppActivation(
			AppActivation.Activation activation) {

		StringBuilder builder = new StringBuilder();

		builder.append(System.currentTimeMillis());
		builder.append(FIELD_DELIMITER);
		builder.append(activation.getProid());
		builder.append(FIELD_DELIMITER);
		builder.append(activation.getImei());
		builder.append(FIELD_DELIMITER);
		builder.append(activation.getGuid());
		builder.append(FIELD_DELIMITER);
		builder.append(activation.getOutletcode());
		builder.append(FIELD_DELIMITER);
		builder.append(activation.getType());
		builder.append(FIELD_DELIMITER);
		builder.append(activation.getCustno());
		builder.append(FIELD_DELIMITER);
		builder.append(activation.getResolution());
		builder.append(FIELD_DELIMITER);
		builder.append(activation.getAgent());
		builder.append(FIELD_DELIMITER);
		builder.append(activation.getVersion());
		builder.append(FIELD_DELIMITER);
		builder.append(activation.getNetwork());
		builder.append(FIELD_DELIMITER);
		builder.append(activation.getAppversion());
		builder.append(FIELD_DELIMITER);
		builder.append(activation.getExt());
		builder.append(LINE_DELIMITER);

		return builder;
	}

	/**
	 * build 无线app事件
	 * 
	 * @param ua
	 * @return
	 */
	private StringBuilder buildAppEvent(MUserAction ua) {

		StringBuilder builder = new StringBuilder();

		builder.append(ua.getActionTime());
		builder.append(FIELD_DELIMITER);
		builder.append(ua.getType());
		builder.append(FIELD_DELIMITER);
		builder.append(ua.getAmount());
		builder.append(FIELD_DELIMITER);
		builder.append(ua.getFundCode());
		builder.append(FIELD_DELIMITER);
		builder.append(ua.getToFundCode());
		builder.append(FIELD_DELIMITER);
		builder.append(ua.getBankAcct());
		builder.append(FIELD_DELIMITER);
		builder.append(ua.getIdType());
		builder.append(FIELD_DELIMITER);
		builder.append(ua.getIdNo());
		builder.append(FIELD_DELIMITER);
		builder.append(ua.getTxRslt());
		builder.append(FIELD_DELIMITER);
		builder.append(ua.getPayType());
		builder.append(FIELD_DELIMITER);
		builder.append(ua.getCustNo());
		builder.append(FIELD_DELIMITER);
		builder.append(ua.getContractNo());
		builder.append(FIELD_DELIMITER);
		builder.append(ua.getMobile());
		builder.append(FIELD_DELIMITER);
		builder.append(ua.getCoopId());
		builder.append(FIELD_DELIMITER);
		builder.append(ua.getActionId());
		builder.append(FIELD_DELIMITER);
		builder.append(ua.getTokenId());
		builder.append(FIELD_DELIMITER);
		builder.append(ua.getUrl());
		builder.append(FIELD_DELIMITER);
		builder.append(ua.getAppVol());
		builder.append(FIELD_DELIMITER);
		builder.append(ua.getEmail());
		builder.append(FIELD_DELIMITER);
		builder.append(ua.getProductId());
		builder.append(FIELD_DELIMITER);
		builder.append(ua.getOtag());
		builder.append(LINE_DELIMITER);

		return builder;
	}
	
	/**
	 * build 无线app事件
	 * 
	 * @param ua
	 * @return
	 */
	private StringBuilder buildH5Event(UserAction ua) {

		StringBuilder builder = new StringBuilder();

		builder.append(ua.getTime());
		builder.append(FIELD_DELIMITER);
		builder.append(ua.getType());
		builder.append(FIELD_DELIMITER);
		builder.append(ua.getAmount());
		builder.append(FIELD_DELIMITER);
		builder.append(ua.getFundCode());
		builder.append(FIELD_DELIMITER);
		builder.append(ua.getToFundCode());
		builder.append(FIELD_DELIMITER);
		builder.append(ua.getCardNo());
		builder.append(FIELD_DELIMITER);
		builder.append(ua.getCertType());
		builder.append(FIELD_DELIMITER);
		builder.append(ua.getCertCode());
		builder.append(FIELD_DELIMITER);
		builder.append(ua.getTxRslt());
		builder.append(FIELD_DELIMITER);
		builder.append(ua.getPayType());
		builder.append(FIELD_DELIMITER);
		builder.append(ua.getCustNo());
		builder.append(FIELD_DELIMITER);
		builder.append(ua.getContractNo());
		builder.append(FIELD_DELIMITER);
		builder.append(ua.getPhone());
		builder.append(FIELD_DELIMITER);
		builder.append(ua.getCoopId());
		builder.append(FIELD_DELIMITER);
		builder.append(ua.getActId());
		builder.append(FIELD_DELIMITER);
		builder.append("null");
		builder.append(FIELD_DELIMITER);
		builder.append(ua.getUrl());
		builder.append(FIELD_DELIMITER);
		builder.append(ua.getAppVol());
		builder.append(FIELD_DELIMITER);
		builder.append(ua.getEmail());
		builder.append(FIELD_DELIMITER);
		builder.append("5002");//5002为无线H5，其他值表示app上报数据
		builder.append(FIELD_DELIMITER);
		builder.append(ua.getOtrack());//H5cookie值
		builder.append(LINE_DELIMITER);

		return builder;
	}

	/*
	 * app pv 日志builder
	 */
	private StringBuffer buildAppPv(AppPvInfoZj.AppPvInfo.PvInfo pvInfo){
		StringBuffer zj = new StringBuffer();
		zj.append(pvInfo.getProid());
		zj.append(FIELD_DELIMITER);
		zj.append(pvInfo.getGuid());
		zj.append(FIELD_DELIMITER);
		zj.append(pvInfo.getUid());
		zj.append(FIELD_DELIMITER);
		zj.append(pvInfo.getPageid());
		zj.append(FIELD_DELIMITER);
		zj.append(pvInfo.getPlevel());
		zj.append(FIELD_DELIMITER);
		zj.append(pvInfo.getTag());
		zj.append(FIELD_DELIMITER);
		zj.append(pvInfo.getUrl());
		zj.append(FIELD_DELIMITER);
		zj.append(pvInfo.getNetwork());
		zj.append(FIELD_DELIMITER);
		zj.append(pvInfo.getExt());
		zj.append(FIELD_DELIMITER);
		zj.append(pvInfo.getFid());
		zj.append(FIELD_DELIMITER);
		zj.append(pvInfo.getTs());
		zj.append(LINE_DELIMITER);
		return zj;
	}

}
