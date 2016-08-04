package com.howbuy.uac.regex;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <pre>
 *  todo:请添加注释描述
 * </pre>
 *
 * @author ji.ma
 * @create 13-1-10 下午5:03
 * @modify
 * @since JDK1.6
 */
public class TestRegx {
    private static final Logger log = LoggerFactory.getLogger(TestRegx.class);

    public static void main(String[] args) {

        String s = "__hutma=92262275.423009506.1357350390.1357350390.1357527830.2;" +
                "+__hutmz=92262275.1357527830.2.2.hutmcsr=baidu|hutmccn=(organic)|hutmcmd=organic|hutmctr=%BA%C3%C2%F2";
        String s1 = " __hutmz=92262275.1357527830.2.2.hutmcsr=baidu|hutmccn=(organic)|hutmcmd=organic|hutmctr=%BA%C3%C2%F2;";
        getKey(s1);
    }

    private static String getSite(String s) {
        log.debug("hutmcc is {}", s);
        Pattern pattern = Pattern.compile("(hutmcsr=)\\w+\\|");
        Matcher matcher = pattern.matcher(s);


        boolean b = matcher.find();
        String site = "";
        if (b) {
            String group = matcher.group();
            group = group.replace("hutmcsr=", "");
            site = group.replace("|", "");
        }
        log.debug("getSite is {}", site);
        return site;
    }

    private static String getKey(String s) {
        log.debug("hutmcc is {}", s);

        Pattern pattern = Pattern.compile("(hutmctr=)\\S+");
        Matcher matcher = pattern.matcher(s);


        String key = "";
        boolean b = matcher.find();
        if (b) {
            String group = matcher.group();
            group = group.replace("hutmctr=", "");
            group = group.replace(",", "");

            try {
                key = URLDecoder.decode(group, "GBK");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }

        log.debug("getRowKey is {}", key);

        return key;
    }
}

