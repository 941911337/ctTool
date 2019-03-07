package org.ct.ctTool.util;

import com.carrotsearch.sizeof.RamUsageEstimator;
import org.ct.ctTool.enums.SizeEnum;

import java.util.Collection;

/**
 * @author Think
 * @Title: BeanSizeUtil
 * @ProjectName easyTool
 * @Description: 对象大小工具类
 * @date 2019/2/23 13:59
 * @Version 1.0
 */
public abstract class BeanSizeUtil {

    /**
     * 获取对象大小
     * @param o 对象
     * @param sizeEnum 泛型对象
     * @return 对象大小
     */
    public static double getObjectSize(Object o, SizeEnum sizeEnum){
        long byteSize;
        if(o instanceof Collection<?>){
            byteSize = RamUsageEstimator.sizeOfAll(o);
        }else{
            byteSize = RamUsageEstimator.sizeOf(o);
        }
        return sizeEnum.getFromByteSize(byteSize);
    }

    /**
     * 私有化构造函数
     */
    private BeanSizeUtil() {
    }
}
