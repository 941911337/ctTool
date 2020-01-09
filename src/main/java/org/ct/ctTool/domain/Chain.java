package org.ct.ctTool.domain;

import org.ct.ctTool.function.ChainFunction;
import org.ct.ctTool.function.ChainJudgeFunction;
import org.ct.ctTool.util.DataUtil;

import java.util.List;

/**
 * @author yzw
 * @Title: Chain
 * @ProjectName ctTool
 * @Description: TODO
 * @date 2020/1/7 14:57
 * @Version 1.0
 */
public abstract class Chain {

    private String id;

    private String name;

    private int level;

    private Chain next;

    private ChainFunction function;

    private ChainJudgeFunction judgeFunction;

    public String choose(List<String> list){
        String result = this.function.doSomething(list);
        if(!DataUtil.isEmpty(next) && this.judgeFunction.Judge(result) ){
            result =  this.next.choose(list);
        }
        return result;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public Chain getNext() {
        return next;
    }

    public void setNext(Chain next) {
        this.next = next;
    }

    public ChainFunction getFunction() {
        return function;
    }

    public void setFunction(ChainFunction function) {
        this.function = function;
    }

    public ChainJudgeFunction getJudgeFunction() {
        return judgeFunction;
    }

    public void setJudgeFunction(ChainJudgeFunction judgeFunction) {
        this.judgeFunction = judgeFunction;
    }
}
