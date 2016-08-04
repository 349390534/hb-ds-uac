/**
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 * 
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.howbuy.uac.kafka.demo;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kafka.consumer.ConsumerConfig;
import kafka.consumer.ConsumerIterator;
import kafka.consumer.KafkaStream;
import kafka.javaapi.consumer.ConsumerConnector;


public class Consumer extends Thread
{
//	private org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(Consumer.class);
	
	Logger logger = LoggerFactory.getLogger("consumer");
	
  private final ConsumerConnector consumer;
  private final String topic;
  
  public Consumer(String topic)
  {
    consumer = kafka.consumer.Consumer.createJavaConsumerConnector(
            createConsumerConfig());
    this.topic = topic;
  }

  private static ConsumerConfig createConsumerConfig()
  {
    Properties props = new Properties();
    props.put("zookeeper.connect", "192.168.220.155:2181");
    props.put("group.id", "gt1");
    props.put("zookeeper.session.timeout.ms", "400");
    props.put("zookeeper.sync.time.ms", "200");
    props.put("auto.commit.interval.ms", "1000");

    return new ConsumerConfig(props);

  }
 
  public void run() {
	  
    Map<String, Integer> topicCountMap = new HashMap<String, Integer>();
    
    topicCountMap.put(topic, new Integer(1));
    
    Map<String, List<KafkaStream<byte[], byte[]>>> consumerMap = consumer.createMessageStreams(topicCountMap);
    
    List<KafkaStream<byte[], byte[]>> streams = consumerMap.get(topic);
    
    KafkaStream<byte[], byte[]> stream =  streams.get(0);
    
    ConsumerIterator<byte[], byte[]> it = stream.iterator();
    
    long index = 0;
    
    while(it.hasNext()){
    	
    	logger.info("cousume--"+ ++index + ":" + new String(it.next().message()));
    }
  }
  
  public static void main(String[] args){
	  
	  Consumer com = new Consumer("tt1");
	  com.start();
	  
  }
}
