package com.howbuy.uac.collection.kafka;

import java.util.Properties;

import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.howbuy.common.util.StringUtil;

public class KafkaProducer {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	

	private kafka.javaapi.producer.Producer<String, String> producer;
	
	  private String topic;
	  
	  private final Properties props = new Properties();

	  public KafkaProducer(String topic,String broker) {
		  
	    props.put("serializer.class", "kafka.serializer.StringEncoder");
	    
	    props.put("metadata.broker.list", broker);
	    
	    producer = new Producer<String, String>(new ProducerConfig(props));
	    
	    this.topic = topic;
	  }
	  
	  public void pubMessage(String key,String message){
		  
		  
		  if(StringUtil.isEmpty(key)){
			  
			  producer.send(new KeyedMessage<String, String>(topic, message));
		  }
		  else{
			  
			  producer.send(new KeyedMessage<String, String>(topic,key,message));
		  }
		  
		  logger.info("publish mes topic : {}, mes: {}",topic,message);
	  }

}
