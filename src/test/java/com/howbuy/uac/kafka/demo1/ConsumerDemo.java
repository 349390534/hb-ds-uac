package com.howbuy.uac.kafka.demo1;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
 


import kafka.consumer.Consumer;
import kafka.consumer.ConsumerConfig;
import kafka.consumer.ConsumerIterator;
import kafka.consumer.KafkaStream;
import kafka.javaapi.consumer.ConsumerConnector;
 
/**
 * 详细可以参考：https://cwiki.apache.org/confluence/display/KAFKA/Consumer+Group+Example
 * 
 * @author Fung
 *
 */
public class ConsumerDemo {
    private final ConsumerConnector consumer;
    private final String topic;
    private ExecutorService executor;
 
    public ConsumerDemo(String a_zookeeper, String a_groupId, String a_topic) {
        consumer = Consumer.createJavaConsumerConnector(createConsumerConfig(a_zookeeper,a_groupId));
        this.topic = a_topic;
    }
    
    private static ConsumerConfig createConsumerConfig(String a_zookeeper,
            String a_groupId) {
//        Properties props = new Properties();
//        props.put("zookeeper.connect", a_zookeeper);
//        props.put("group.id", a_groupId);
//        props.put("zookeeper.session.timeout.ms", "1000");
//        props.put("zookeeper.sync.time.ms", "200");
//        props.put("auto.commit.interval.ms", "1000");
        
        Properties props = new Properties();
        props.put("zookeeper.connect", "192.168.220.155:2181");
        props.put("group.id", "gt1");
        props.put("zookeeper.session.timeout.ms", "400");
        props.put("zookeeper.sync.time.ms", "200");
        props.put("auto.commit.interval.ms", "1000");
 
        return new ConsumerConfig(props);
    }
 
    public void shutdown() {
//        if (consumer != null)
//            consumer.shutdown();
//        if (executor != null)
//            executor.shutdown();
    }
 
    public void run(int numThreads) {
    	
        Map<String, Integer> topicCountMap = new HashMap<String, Integer>();
        
        topicCountMap.put(topic, new Integer(numThreads));
        
        Map<String, List<KafkaStream<byte[], byte[]>>> consumerMap = consumer
                .createMessageStreams(topicCountMap);
        
        List<KafkaStream<byte[], byte[]>> streams = consumerMap.get(topic);
 
        executor = Executors.newFixedThreadPool(numThreads);
 
        int threadNumber = 0;
        for (final KafkaStream stream : streams) {
            executor.submit(new ConsumerMsgTask(stream, threadNumber));
            threadNumber++;
        }
        
//        KafkaStream<byte[], byte[]> stream =  streams.get(0);
//        
//        ConsumerIterator<byte[], byte[]> it = stream.iterator();
//        
//        long index = 0;
//        
//        while(it.hasNext()){
//        	
//        	System.out.println("cousume--"+ ++index + ":" + new String(it.next().message()));
//        }
    }
 
    
 
    public static void main(String[] arg) {
 
        ConsumerDemo demo = new ConsumerDemo("192.168.220.155:2181", "gt1", "tt1");
        demo.run(4);
 
       
    }
}
