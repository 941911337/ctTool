package org.ct.ctTool.util;

import org.ct.ctTool.exception.ParamException;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Think
 * @Title: NumberUtil
 * @ProjectName easyTool
 * @Description: 格式化工具类
 * @date 2019/2/25 15:56
 * @Version 1.0
 */
public abstract class NumberUtil {

    /**
     * 数字匹配正则
     */
    private static final Pattern NUMBER_PATTERN = Pattern.compile("^(\\-|\\+)?\\d+(\\.\\d+)?$");
    private static final String SPOT_STR = ".";

    /**
     * 判断字符串是否是数字
     * @param str 字符串
     * @return 标识
     */
    public static boolean isNumeric(String str) {
        Matcher isNum = NUMBER_PATTERN.matcher(str);
        if (!isNum.matches()) {
            return false;
        }
        return true;
    }

    /**
     * 判断多个字符串是否全不是数字
     * @param args 字符串
     * @return 标识
     */
    public static boolean isNotNumeric(String ... args) {
        for (String arg : args) {
            if(!isNumeric(arg)){
                return true;
            }
        }
        return false;
    }

    /**
     * 去除数字中多余0;
     * @param numberStr
     * @return
     */
    public static String removeZeros(String numberStr){
        if(!isNumeric(numberStr)){
            throw new ParamException("传入字符串不是数字类型");
        }
        if(numberStr.indexOf(SPOT_STR) > 0){
            //去掉多余的0
            numberStr = numberStr.replaceAll("0+?$", "");
            //如最后一位是.则去掉
            numberStr = numberStr.replaceAll("[.]$", "");
        }
        return numberStr;
    }

    /**
     * 小于
     * @param first 前值
     * @param second 后值
     * @return 标识
     */
    public static boolean lt(String first,String second){
       return checkValue(first,second,0);
    }

    /**
     * 大于
     * @param first 前值
     * @param second 后值
     * @return 标识
     */
    public static boolean gt(String first,String second){
        return checkValue(first,second,1);
    }

    /**
     * 等于
     * @param first 前值
     * @param second 后值
     * @return 标识
     */
    public static boolean eq(String first,String second){
        return first.equals(second);
    }

    /**
     * 小于等于
     * @param first 前值
     * @param second 后值
     * @return 标识
     */
    public static boolean le(String first,String second){
        return checkValue(first,second,3);
    }

    /**
     * 大于等于
     * @param first 前值
     * @param second 后值
     * @return 标识
     */
    public static boolean ge(String first,String second){
        return checkValue(first,second,4);
    }

    /**
     * 比较两个数字大小
     * @param first 第一个值
     * @param second 第二个值
     * @param type 类型
     * @return 标识
     */
    public static boolean checkValue(String first,String second,int type){
        if(DataUtil.isEmpty(first,second)){
            return false;
        }
        if(isNotNumeric(first,second)){
            throw new ParamException("传入字符串不是数字类型");
        }
        BigDecimal a = new BigDecimal(first);
        BigDecimal b = new BigDecimal(second);
        switch (type){
                //小于
            case 0:
                return a.compareTo(b) < 0;
            //大于
            case 1:
                return a.compareTo(b) > 0;
            //等于
            case 2:
                return first.equals(second);
            //小于等于
            case 3:
                return a.compareTo(b) <= 0;
            //大于等于
            case 4:
                return a.compareTo(b) >= 0;
            default:
                return false;
        }
    }


    /**
     * 校验是否是0
     * @param value 值
     * @return 返回值
     */
    public static boolean isZero(String value){
        if(DataUtil.isEmpty(value)){
            return false;
        }
        if(isNotNumeric(value)){
            return false;
        }else{
            BigDecimal bigDecimal =  new BigDecimal(value);
            return (bigDecimal.compareTo(BigDecimal.ZERO) == 0);
        }
    }

    /**
     * 数值增加千分位字符
     * @param number 字符串
     * @return 返回值
     */
    public static String addThousandths(String number) {
        if(DataUtil.isEmpty(number) ){
            return number;
        }
        DecimalFormat df ;
        boolean hasPer = false;
        if(number.indexOf("%") >=0){
            number = number.replaceAll("%","");
            hasPer = true;
        }
        if (isNotNumeric(number)){
            return number;
        }
        if(number.indexOf(".")>=0){
            df = new DecimalFormat("#,###.00");
        }else{
            df = new DecimalFormat("#,###");
        }
        String result =  df.format(Double.parseDouble(number));
        if(hasPer){
            result = result + "%";
        }
        return result;
    }

    /**
     * 私有化构造
     */
    private NumberUtil() { }
}
