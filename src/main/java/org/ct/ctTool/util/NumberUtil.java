package org.ct.ctTool.util;

import org.ct.ctTool.exception.ParamException;

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
     * @param str
     * @return
     */
    public static boolean isNumeric(String str) {
        Matcher isNum = NUMBER_PATTERN.matcher(str);
        if (!isNum.matches()) {
            return false;
        }
        return true;
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
     * 私有化构造
     */
    private NumberUtil() { }
}
