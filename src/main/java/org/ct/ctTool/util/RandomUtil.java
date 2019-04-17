package org.ct.ctTool.util;

import org.ct.ctTool.exception.ParamException;

import java.util.Random;

/**
 * @author yzw
 * @Title: RandomUtil
 * @ProjectName ctTool
 * @Description: 随机工具类
 * @date 2019/4/17 10:01
 * @Version 1.0
 */
public abstract class RandomUtil {


    /**
     * 生成min - max 范围内 不重复n个随机数
     * @param min 最小值
     * @param max 最大值
     * @param n 随机数个数
     * @return
     */
    private static int[] randomArrayNonRepetition(int min,int max,int n){
        //范围长度
        int len = (max - min) + 1;
        if(max < min || n > len){
            throw new ParamException("参数错误");
        }
        //初始化给定范围的待选数组
        int[] source = new int[len];
        for (int i = min; i < min+len; i++){
            source[i-min] = i;
        }

        int[] result = new int[n];
        Random rd = new Random();
        int index ;
        for (int i = 0; i < result.length; i++) {
            //待选数组0到(len-2)随机一个下标
            index = Math.abs(rd.nextInt() % len--);
            //将随机到的数放入结果集
            result[i] = source[index];
            //将待选数组中被随机到的数，用待选数组(len-1)下标对应的数替换
            source[index] = source[len];
        }
        return result;
    }

    public static class CTRandom{
        private int size;

        private int[]  randomArray;

        private int  index = 0;

        public CTRandom(int size) {
            this.size = size;
            this.randomArray = randomArrayNonRepetition(0,size-1,size);
        }

        public int nextInt(){
            return randomArray[index++ % size];
        }

        private CTRandom() throws IllegalAccessException {
            throw new IllegalAccessException();
        }

    }


}
