package com.howbuy.uac.collection.bean;

/**
 * <pre>
 *  用户action的枚举类
 * </pre>
 *
 * @author ji.ma
 * @create 13-1-25 下午1:48
 * @modify
 * @since JDK1.6
 */
public enum  ActionType {

    /**
     * 登录
     */
    login("1","登录"),
    /**
     * 预约
     */
    yuyue("2","预约"),

    /**
     * 订阅
     */
   subscribe("3","订阅"),
 
    /**
     * 开户
     */
    register("4","开户"),
    
    /**
     * 绑卡
     */
    bindCard("5","绑卡"),
    
    /**
     * 买基金
     */
    buyFund("6","买基金"),
    
    /**
     * 赎回
     */
    sellFund("7","赎回"),
    
    /**
     * 赎回
     */
    piggyBuy("8","储蓄罐存入 "),
    
    /**
     * 赎回
     */
    piggySell("9","储蓄罐取现 "),
    
    /**
     * 定投
     */
    savingPlan("10","定投"),
    
    /**
     * 转换 
     */
    exchange("11","转换 "),
    /**
     * 撤单
     */
    withdraw("12","撤单"),
    
    savingbox2demanddeposit("13","储蓄罐活期转定期"),
    
    savingbox2deposit("14","储蓄罐直接购买定期"),
    
    tradelogin("15","交易账号登录"),
    
    search("16","搜索");

    private String value;

    /**
     * 显示名
     */
    private String view;

    ActionType(String value) {
        this.value = value;
    }

    ActionType(String value, String viewName) {
        this.value = value;
        this.view = viewName;
    }

    public String getView() {
        return view;
    }

    public String getValue() {
        return value;
    }

    /**
     * 通过枚举值获取枚举对象
     *
     * @param value 枚举值
     * @param defaultType 默认枚举
     * @return 枚举 - 通过(value)枚举值找不到此枚举时返回默认(defaultType)枚举
     */
    public static ActionType getByValue(String value, ActionType defaultType) {
        for(ActionType type: ActionType.values()){
            if(type.getValue().equals(value)){
                return type;
            }
        }
        return defaultType;
    }
}
