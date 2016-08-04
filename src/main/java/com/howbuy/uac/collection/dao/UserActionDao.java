package com.howbuy.uac.collection.dao;

import com.howbuy.hadoop.hbase.orm.connection.HConnection;
import com.howbuy.hadoop.hbase.orm.dao.HDaoImpl;
import com.howbuy.hadoop.hbase.orm.exceptions.HOrmException;
import com.howbuy.uac.collection.bean.UserAction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <pre>
 *  todo:请添加注释描述
 * </pre>
 *
 * @author ji.ma
 * @create 13-1-25 下午12:42
 * @modify
 * @since JDK1.6
 */
public class UserActionDao extends HDaoImpl<UserAction> {

    public void sethConnection(HConnection hConnection) {
        try {
            initDao(UserAction.class, hConnection);
        } catch (HOrmException e) {
            e.printStackTrace();
        }
    }
}
