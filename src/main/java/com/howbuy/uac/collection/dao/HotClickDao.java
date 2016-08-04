package com.howbuy.uac.collection.dao;


import com.howbuy.hadoop.hbase.orm.connection.HConnection;
import com.howbuy.hadoop.hbase.orm.dao.HDaoImpl;
import com.howbuy.hadoop.hbase.orm.exceptions.HOrmException;
import com.howbuy.uac.collection.bean.HotClick;

/**
 * <pre>
 *  todo:请添加注释描述
 * </pre>
 *
 * @author ji.ma
 * @create 12-12-21 下午5:51
 * @modify
 * @since JDK1.6
 */
public class HotClickDao extends HDaoImpl<HotClick> {

    public void sethConnection(HConnection hConnection) {
        try {
            initDao(HotClick.class, hConnection);
        } catch (HOrmException e) {
            e.printStackTrace();
        }
    }
}
