package com.howbuy.uac.kafka.demo1;

import kafka.consumer.ConsumerIterator;
import kafka.consumer.KafkaStream;
 
public class ConsumerMsgTask implements Runnable {
    private KafkaStream m_stream;
    private int m_threadNumber;
    
    int num = 0;
 
    public ConsumerMsgTask(KafkaStream stream, int threadNumber) {
        m_threadNumber = threadNumber;
        m_stream = stream;
    }
 
    public void run() {
        ConsumerIterator<byte[], byte[]> it = m_stream.iterator();
//        System.out.println("enter " + Thread.currentThread().getId());
        while (it.hasNext()){
        	
//            System.out.println(Thread.currentThread().getId() + ": " + new String(it.next().message()));
        	it.next();
        	System.out.println("consumer--" + Thread.currentThread().getId() + ": " + ++num);
//        System.out.println("Shutting down Thread: " + m_threadNumber);
        }
    }
}