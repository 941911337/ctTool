package org.ct.ctTool.util;

import org.ct.ctTool.exception.ParamException;

/**
 * @author Think
 * @Title: ParamUtil
 * @ProjectName easyTool
 * @Description: 参数校验工具类
 * @date 2019/2/23 15:45
 * @Version 1.0
 */
public abstract class ParamUtil {

    /**
     * 校验参数
     * @param param
     */
    public static void checkParam(Object[] param){
        for (Object o : param) {
            if(DataUtil.isEmpty(o)){
                throw new ParamException("传入参数为空！");
            }
        }
    }

    /**
     * 校验参数
     * @param param
     */
    public static void checkParam(Object[] param,String[] paramNames){
        for (int i = 0; i < param.length ; i++) {
            if(DataUtil.isEmpty(param[i])){
                throw new ParamException(String.format("传入参数-%s-为空!",paramNames[i]));
            }
        }
    }

    /**
     * 私有化构造方法
     */
    private ParamUtil() {
    }
}