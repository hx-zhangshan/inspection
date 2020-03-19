package com.viewhigh.inspection.util;

import java.util.UUID;
/**
 * @author zhangS
 * @Description 
 * @date 2019-12-10 15:00
 */
public class UUIDUtils {
    public static String getUUID(){
        return UUID.randomUUID().toString().replace("-", "");
    }
}
