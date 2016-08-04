package com.howbuy.uac.collection.access;

import com.howbuy.uac.collection.Constants;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.*;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * <pre>
 *  HBase管理类
 * </pre>
 *
 * @author ji.ma
 * @create 13-1-10 上午9:32
 * @modify
 * @since JDK1.6
 */
public class HBaseDataHolder {
    private static final Logger log = LoggerFactory.getLogger(HBaseDataHolder.class);

    private static Configuration conf = null;

    private static HBaseAdmin admin;

    private static HTablePool viewTablePool;
    private static HTablePool landingTablePool;

    private final static int size = 50;

    /**
     * 初始化配置
     */
    static {
        conf = HBaseConfiguration.create();
        try {
            admin = new HBaseAdmin(conf);
            viewTablePool = new HTablePool(conf, Constants.TABLE_VIEW, size);
            landingTablePool = new HTablePool(conf, Constants.TABLE_LANDING, size);
        } catch (MasterNotRunningException e) {
            e.printStackTrace();
        } catch (ZooKeeperConnectionException e) {
            e.printStackTrace();
        }
    }

    public static void createTable(String tableName, String[] familys, byte[][] splits) throws Exception {
        if (admin.tableExists(tableName)) {
            log.debug("table already exists!");
        } else {
            HTableDescriptor tableDesc = new HTableDescriptor(tableName);
            for (String family : familys) {
                tableDesc.addFamily(new HColumnDescriptor(family));
            }
            if (splits != null) {
                admin.createTable(tableDesc, splits);
            } else {
                admin.createTable(tableDesc);
            }
            log.debug("create table " + tableName + " ok.");
        }
    }

    /**
     * 创建一张表
     */
    public static void createTable(String tableName, String[] familys) throws Exception {
        createTable(tableName, familys, null);
    }

    /**
     * 删除表
     */
    public static void deleteTable(String tableName) throws Exception {
//        if (tableName.equals("page_view")||tableName.equals("user_info")||tableName.equals("user_action")) {
//            log.error("======================================");
//            log.error("the table page_view can not be delete!");
//            log.error("======================================");
//            return;
//        }

        try {
            HBaseAdmin admin = new HBaseAdmin(conf);
            admin.disableTable(tableName);
            admin.deleteTable(tableName);
            log.debug("delete table " + tableName + " ok.");
        } catch (MasterNotRunningException e) {
            e.printStackTrace();
        } catch (ZooKeeperConnectionException e) {
            e.printStackTrace();
        }
    }

    /**
     * 插入一行记录
     */
    public static void addRecord(String tableName, String rowKey, String family, String qualifier, String value)
            throws Exception {
        try {
            HTable table = new HTable(conf, tableName);
            Put put = new Put(Bytes.toBytes(rowKey));
            put.add(Bytes.toBytes(family), Bytes.toBytes(qualifier), Bytes.toBytes(value));
            table.put(put);
            log.debug("insert record " + rowKey + " to table " + tableName + " ok.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private synchronized static void addRecord(Put put, HTable table) {
        try {
            table.put(put);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void addRecord(String tableName, Put put)
            throws Exception {
        viewTablePool.getTable().put(put);
    }

    public static void addLandingRecords(String tableName, List<Put> puts)
            throws Exception {
        landingTablePool.getTable().put(puts);
    }

    public static void addRecords(String tableName, List<Put> puts)
            throws Exception {
        viewTablePool.getTable().put(puts);
    }

//    public static void addUserInfoRecord(String tableName, Put put)
//            throws Exception {
////        userTablePool.getTable().put(put);
//    }


    public static Put getPut(String family, String[] qualifiers, String[] values, Put put) {
//        Put put = new Put(Bytes.toBytes(rowKey));
        for (int i = 0; i < qualifiers.length; i++) {
            String qualifier = qualifiers[i];
            String value = values[i];
            if (value == null) {
                value = "";
            }
            put.add(Bytes.toBytes(family), Bytes.toBytes(qualifier), Bytes.toBytes(value));
        }
        return put;
    }

    /**
     * 删除一行记录
     */
    public static void delRecord(String tableName, String rowKey) throws IOException {
        if (tableName.equals("page_view")) {
            log.error("======================================");
            log.error("the table page_view can not be delete!");
            log.error("======================================");
            return;
        }

        HTable table = new HTable(conf, tableName);
        List list = new ArrayList();
        Delete del = new Delete(rowKey.getBytes());
        list.add(del);
        table.delete(list);
        System.out.println("del recored " + rowKey + " ok.");
    }

    /**
     * 查找一行记录
     */
    public static void getOneRecord(String tableName, String rowKey) throws IOException {
        HTable table = new HTable(conf, tableName);
        Get get = new Get(rowKey.getBytes());
        Result rs = table.get(get);
        for (KeyValue kv : rs.raw()) {
            System.out.print(new String(kv.getRow()) + " ");
            System.out.print(new String(kv.getFamily()) + ":");
            System.out.print(new String(kv.getQualifier()) + " ");
            System.out.print(kv.getTimestamp() + " ");
            System.out.println(new String(kv.getValue()));
        }
    }

    /**
     * 显示所有数据
     */
    public static void getAllRecord(String tableName) {
        try {
            HTable table = new HTable(conf, tableName);
            Scan s = new Scan();
            ResultScanner ss = table.getScanner(s);
            for (Result r : ss) {
                for (KeyValue kv : r.raw()) {
                    System.out.print(new String(kv.getRow()) + " ");
                    System.out.print(new String(kv.getFamily()) + ":");
                    System.out.print(new String(kv.getQualifier()) + " ");
                    System.out.print(kv.getTimestamp() + " ");
                    System.out.println(new String(kv.getValue()));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] agrs) {
//        try {
//            String tablename = "scores";
//            String[] familys = {"grade", "course"};
//            HBaseDataHolder.createTable(tablename, familys);
//
//            //addPageView record zkb
//            HBaseDataHolder.addRecord(tablename, "zkb", "grade", "", "5");
//            HBaseDataHolder.addRecord(tablename, "zkb", "course", "", "90");
//            HBaseDataHolder.addRecord(tablename, "zkb", "course", "math", "97");
//            HBaseDataHolder.addRecord(tablename, "zkb", "course", "art", "87");
//            //addPageView record  baoniu
//            HBaseDataHolder.addRecord(tablename, "baoniu", "grade", "", "4");
//            HBaseDataHolder.addRecord(tablename, "baoniu", "course", "math", "89");
//
        System.out.println("===========get one record========");
        try {
            HBaseDataHolder.getOneRecord("user_info", "0x169dd40bb40c55b1zhangsan");
        } catch (IOException e) {
            e.printStackTrace();
        }
//
//            System.out.println("===========show all record========");
//            HBaseDataHolder.getAllRecord(tablename);
//
//            System.out.println("===========del one record========");
//            HBaseDataHolder.delRecord(tablename, "baoniu");
//            HBaseDataHolder.getAllRecord(tablename);
//
//            System.out.println("===========show all record========");
//            HBaseDataHolder.getAllRecord(tablename);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        try {
//            HTable table = new HTable(conf, "view");
//            Put put = new Put(Bytes.toBytes("test"));
//            put.add(Bytes.toBytes("src"), Bytes.toBytes("url"), Bytes.toBytes("www.baidu.com"));
//            put.add(Bytes.toBytes("src"), Bytes.toBytes("os"), Bytes.toBytes("xp"));
//            put.add(Bytes.toBytes("src"), Bytes.toBytes("ip"), Bytes.toBytes("192.168.1.1"));
//            put.add(Bytes.toBytes("src"), Bytes.toBytes("browser"), Bytes.toBytes("ff"));
//            table.put(put);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }

    public static PTable[] getPoolTables() {
        return viewTablePool.getAllTables();
    }
}