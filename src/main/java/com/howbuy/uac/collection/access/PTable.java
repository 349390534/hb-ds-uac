package com.howbuy.uac.collection.access;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Put;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;

/**
 * <pre>
 *
 * </pre>
 *
 * @author ji.ma
 * @create 13-1-4 下午1:38
 * @modify
 * @since JDK1.6
 */
public class PTable extends HTable {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private transient boolean inUsed;

    private transient int count = 0;

    public PTable(Configuration conf, String tableName) throws IOException {
        super(conf, tableName);
    }

    public PTable(Configuration conf, byte[] tableName) throws IOException {
        super(conf, tableName);
    }

//    public PTable(Configuration conf, byte[] tableName, ExecutorService pool) throws IOException {
//        super(conf, tableName, pool);
//    }
//
//    public PTable(byte[] tableName, HConnection connection, ExecutorService pool) throws IOException {
//        super(tableName, connection, pool);
//    }

    public boolean isInUsed() {
        return inUsed;
    }

    public void setInUsed(boolean inUsed) {
        this.inUsed = inUsed;
    }

    public void put(org.apache.hadoop.hbase.client.Put put) throws IOException {
        super.put(put);
//        count++;
//        if(count>5000){
//            super.flushCommits();
//            count = 0;
//            System.out.printf("==========================");
//        }
        super.flushCommits();
        inUsed = false;
    }

    public void put(List<Put> puts) throws IOException {
        super.put(puts);
        super.flushCommits();
        inUsed = false;
    }
}
