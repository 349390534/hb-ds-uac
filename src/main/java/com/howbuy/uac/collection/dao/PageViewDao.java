package com.howbuy.uac.collection.dao;


import com.howbuy.hadoop.hbase.orm.connection.HConnection;
import com.howbuy.hadoop.hbase.orm.dao.HDaoImpl;
import com.howbuy.hadoop.hbase.orm.exceptions.HOrmException;
import com.howbuy.uac.collection.bean.PageView;

/**
 * <pre>
 *  PageViewDto 对象 DAO
 * </pre>
 *
 * @author ji.ma
 * @create 13-1-10 上午9:32
 * @modify
 * @since JDK1.6
 */
public class PageViewDao extends HDaoImpl<PageView>{

    public void sethConnection(HConnection hConnection) {
        try {
            initDao(PageView.class, hConnection);
        } catch (HOrmException e) {
            e.printStackTrace();
        }
    }

}