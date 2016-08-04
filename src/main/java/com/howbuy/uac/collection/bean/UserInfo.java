package com.howbuy.uac.collection.bean;

import com.howbuy.hadoop.hbase.orm.annotation.HColumn;
import com.howbuy.hadoop.hbase.orm.annotation.HTable;

/**
 * <pre>
 *  todo:请添加注释描述
 * </pre>
 *
 * @author ji.ma
 * @create 13-1-22 下午2:24
 * @modify
 * @since JDK1.6
 */
@HTable(tableName = "user_info")
public class UserInfo {
    @HColumn(id = true)
    private String rowKey;

    @HColumn(familyName = "user", qualifierName = "cookie")
    private String cookie;

    @HColumn(familyName = "user", qualifierName = "time")
    private String time;

    @HColumn(familyName = "user", qualifierName = "id")
    private String id;

    @HColumn(familyName = "user", qualifierName = "name")
    private String name;

    @HColumn(familyName = "user", qualifierName = "phone")
    private String phone;

    @HColumn(familyName = "user", qualifierName = "email")
    private String email;

    public String getRowKey() {
        return rowKey;
    }

    public void setRowKey(String rowKey) {
        this.rowKey = rowKey;
    }

    public String getCookie() {
        return cookie;
    }

    public void setCookie(String cookie) {
        this.cookie = cookie;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "rowKey='" + rowKey + '\'' +
                ", cookie='" + cookie + '\'' +
                ", time='" + time + '\'' +
                ", id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
