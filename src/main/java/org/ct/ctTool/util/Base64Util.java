package org.ct.ctTool.util;

import java.util.Base64;

/**
 * @author Think
 * @Title: Base64Util
 * @ProjectName easyTool
 * @Description: Base64转换
 * @date 2019/2/26 9:05
 * @Version 1.0
 */
public abstract class Base64Util {

    /**
     * base64 编码
     * @param str
     * @return
     */
    public static byte[]  base64Decode(String str){
        return Base64.getDecoder().decode(str);
    }

    /**
     * base64 解码
     * @param bytes
     * @return
     */
    public static String  base64Encode(byte[] bytes){
        return Base64.getEncoder().encodeToString(bytes);
    }

    /**
     * 私有化构造方法
     */
    private Base64Util() {
    }
}
