package com.howbuy.uac.collection.access;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.client.HTable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * <pre>
 *  todo:请添加注释描述
 * </pre>
 *
 * @author ji.ma
 * @create 13-1-4 下午12:49
 * @modify
 * @since JDK1.6
 */
public class HTablePool {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private volatile int lastIndex;
    private Configuration conf;

    private String tableName;
    private volatile PTable[] tables;

    private int tableNum;

    public HTablePool(Configuration conf, String tableName, int tableNum) {
        this.conf = conf;
        this.tableName = tableName;
        this.tableNum = tableNum;
        try {
            initPool();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void initPool() throws IOException {
        tables = new PTable[tableNum];
        for (int i = 0; i < tableNum; i++) {
            tables[i] = new PTable(conf, tableName);
            tables[i].setWriteBufferSize(5 * 1024 * 1024); //5MB
            tables[i].setAutoFlush(false);
            tables[i].setInUsed(false);
        }
        lastIndex = 0;
    }

    public synchronized HTable getTable() throws IOException {
        if (tables == null) {
            initPool();
        }

        int index = lastIndex;
        log.debug("last index is " + lastIndex);

        while (tables[index].isInUsed()) {
            if (index + 1 < tableNum - 1) {
                index ++;
            }else {
                index = 0;
            }
            log.debug("index is " + index);
        }

        lastIndex = index;
        tables[index].setInUsed(true);
        return tables[index];
    }

    public PTable[] getAllTables(){
        return tables;
    }
}
