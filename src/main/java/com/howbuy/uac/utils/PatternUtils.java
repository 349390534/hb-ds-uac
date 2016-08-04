package com.howbuy.uac.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PatternUtils {
    public static boolean isMatch(String text, String patterns) {
        Pattern pattern = Pattern.compile(patterns);
        Matcher matcher = pattern.matcher(text);
        return matcher.find();
    }

    public static String getMatchGroup(String text, String patterns) {
        Pattern pattern = Pattern.compile(patterns);
        Matcher matcher = pattern.matcher(text);
        if (matcher.find()) {
            return matcher.group();
        }
        return "";
    }

    public static void main(String[] args) {
//        String text = "http://www.howbuy.com/opinion/2013-01-28/36463.html";
//        String patterns = "(\\d+)(\\.htm)";
//        System.out.println("text is {},and pattern is {}" + text + patterns);
//
//        System.out.println("isMatch(text,patterns) = " + isMatch(text, patterns));
//        System.out.println("getMatchGroup(text,patterns) = " + getMatchGroup(text, patterns));

        String text = "http://simu.howbuy.com/";
        String patterns = "(simu)(\\.)(howbuy)(\\.)(com|test)(\\/)$";
        System.out.println("text is {},and pattern is {}" + text + patterns);

        System.out.println("isMatch(text,patterns) = " + isMatch(text, patterns));
        System.out.println("getMatchGroup(text,patterns) = " + getMatchGroup(text, patterns));
    }
}