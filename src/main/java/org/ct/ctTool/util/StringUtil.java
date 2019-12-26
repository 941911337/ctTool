package org.ct.ctTool.util;

/**
 * @author yzw
 * @Title: StringUtil
 * @ProjectName ctTool
 * @Description: TODO
 * @date 2019/12/23 17:29
 * @Version 1.0
 */
public abstract class StringUtil {


    /**
     * 长度小于
     * @param first 前值
     * @param second 后值
     * @return 标识
     */
    public static boolean lengthLt(String first,String second){
        return checkLength(first,second,0);
    }

    /**
     * 长度大于
     * @param first 前值
     * @param second 后值
     * @return 标识
     */
    public static boolean lengthGt(String first,String second){
        return checkLength(first,second,1);
    }

    /**
     * 长度等于
     * @param first 前值
     * @param second 后值
     * @return 标识
     */
    public static boolean lengthEq(String first,String second){
        return checkLength(first,second,2);
    }

    /**
     * 长度小于等于
     * @param first 前值
     * @param second 后值
     * @return 标识
     */
    public static boolean lengthLe(String first,String second){
        return checkLength(first,second,3);
    }

    /**
     * 长度大于等于
     * @param first 前值
     * @param second 后值
     * @return 标识
     */
    public static boolean lengthGe(String first,String second){
        return checkLength(first,second,4);
    }

    /**
     * 校验长度
     * @param value 值
     * @param length 长度限定
     * @param type 类型 大于小于
     * @return 返回值
     */
    public static boolean checkLength(String value,String length,int type){
        if(value == null){
            return false;
        }
        int len = Integer.parseInt(length);
        switch (type){
            //小于
            case 0:
                return value.length() < len;
            //大于
            case 1:
                return value.length() > len;
            //等于
            case 2:
                return value.length() == len;
            //小于等于
            case 3:
                return value.length() <= len;
            //大于等于
            case 4:
                return value.length() >= len;
            default:
                return false;
        }

    }






    /**
     * 私有化构造
     */
    private StringUtil() {}
}
