package com.youxiunanren.yxnr.util;

public class StringUtil {

    public static String fromCamelToUnderscore(final String camelStr){
        return camelStr.replaceAll(String.format("%s|%s|%s",
                "(?<=[A-Z])(?=[A-Z][a-z])",
                "(?<=[^A-Z])(?=[A-Z])",
                "(?<=[A-Za-z])(?=[^A-Za-z])"
        ), "_").toLowerCase();
    }

    public static String fromUnderscoreToCamel(final String underscoreStr){
        String camelStr = new String(underscoreStr);
        int underscoreIndex = camelStr.lastIndexOf("_");
        while(underscoreIndex >= 0) {
            camelStr = camelStr.substring(0, underscoreIndex) + String.valueOf(camelStr.charAt(underscoreIndex + 1)).toUpperCase() + camelStr.substring(underscoreIndex + 2);
            underscoreIndex = camelStr.lastIndexOf("_");
        }
        return camelStr;
    }
}
