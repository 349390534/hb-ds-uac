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


import java.util.Properties;
import java.util.concurrent.TimeUnit;

import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Producer extends Thread {
	
  
//	private org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(Producer.class);
	
	Logger logger = LoggerFactory.getLogger("producer");
	
  private final kafka.javaapi.producer.Producer<Integer, String> producer;
  private final String topic;
  private final Properties props = new Properties();

  public Producer(String topic)
  {
    props.put("serializer.class", "kafka.serializer.StringEncoder");
    //props.put("group.id", "group1");
//    props.put("num.partitions", "1");
    props.put("metadata.broker.list", KafkaProperties.kafkaServerURL + ":" + KafkaProperties.kafkaServerPort);
    // Use random partitioner. Don't need the key type. Just set it to Integer.
    // The message is of type String.
    producer = new kafka.javaapi.producer.Producer<Integer, String>(new ProducerConfig(props));
    this.topic = topic;
  }
  
  public void run() {
    int messageNo = 1;
    while(true)
    {
    
     try {
		TimeUnit.MILLISECONDS.sleep(1000);
	} catch (InterruptedException e) {
		
	}
      String messageStr = new String("Message_" + (messageNo++));
      logger.info("produce: "+messageStr);
      producer.send(new KeyedMessage<Integer, String>(topic, messageStr));
     // messageNo++;
//      System.out.println("produce:" + messageStr);
    }
  }
  
  
  public static void main(String[] args){
	  Producer pro = new Producer(KafkaProperties.topic);
	  pro.start();
  }

}
