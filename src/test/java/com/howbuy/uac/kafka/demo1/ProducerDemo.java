package com.howbuy.uac.kafka.demo1;

import java.util.Date;
import java.util.Properties;
import java.util.Random;
 
import java.util.concurrent.TimeUnit;

import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;
 
/**
 * 详细可以参考：https://cwiki.apache.org/confluence/display/KAFKA/0.8.0+Producer+Example
 * @author Fung
 *
 */
public class ProducerDemo {
    public static void main(String[] args) throws InterruptedException {
        Random rnd = new Random();
        int events=6000;
 
        // 设置配置属性
        Properties props = new Properties();
        props.put("metadata.broker.list","192.168.220.155:9092,192.168.220.156:9092");
        props.put("serializer.class", "kafka.serializer.StringEncoder");
        // key.serializer.class默认为serializer.class
        props.put("key.serializer.class", "kafka.serializer.StringEncoder");
        // 可选配置，如果不配置，则使用默认的partitioner
//        props.put("partitioner.class", "com.howbuy.uac.kafka.demo1.PartitionerDemo");
        
//        props.put("group.id", "group-1");
        // 触发acknowledgement机制，否则是fire and forget，可能会引起数据丢失
        // 值为0,1,-1,可以参考
        // http://kafka.apache.org/08/configuration.html
        props.put("request.required.acks", "0");//default 0
        ProducerConfig config = new ProducerConfig(props);
        
        
 
        // 创建producer
        Producer<String, String> producer = new Producer<String, String>(config);
        // 产生并发送消息
//        long start=System.currentTimeMillis();
        for (long i = 1; i <= events; i++) {
            try {
				long runtime = new Date().getTime();
				String ip = "192.168.2." + i;//rnd.nextInt(255);
				String msg = runtime + ",www.example.com," + ip;
				//如果topic不存在，则会自动创建，默认replication-factor为1，partitions为0
				KeyedMessage<String, String> data = new KeyedMessage<String, String>(
				        "tt1", ip, msg);
				
				producer.send(data);
				System.out.println("msg:" + i);
//				TimeUnit.SECONDS.sleep(2);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				System.out.println("error");
			}
            
        }
//        System.out.println("耗时:" + (System.currentTimeMillis() - start));
        // 关闭producer
//        producer.close();
    }
}
