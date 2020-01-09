package org.ct.ctTool.function;

/**
 * @author yzw
 * @Title: ChainFunction
 * @ProjectName ctTool
 * @Description: TODO
 * @date 2020/1/7 15:16
 * @Version 1.0
 */
@FunctionalInterface
public interface ChainJudgeFunction {
     boolean Judge(Object...objects);
}
