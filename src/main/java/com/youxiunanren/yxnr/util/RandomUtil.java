package com.youxiunanren.yxnr.util;

import java.util.UUID;

public class RandomUtil {

    public static String unique(){
        UUID uuid = UUID.randomUUID();
        return uuid.toString().replaceAll("-", "");
    }
}
