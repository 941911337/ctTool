package org.ct.ctTool.domain.index;

import org.ct.ctTool.domain.Chain;
import org.ct.ctTool.function.ChainFunction;

import java.util.List;
import java.util.Objects;

import static org.ct.ctTool.constant.Constants.INDEX_MAX_LENGTH;

/**
 * @author yzw
 * @Title: IndexChainA
 * @ProjectName ctTool
 * @Description: TODO
 * @date 2020/1/8 9:04
 * @Version 1.0
 */
public class IndexChain extends Chain {

    public IndexChain(ChainFunction function,String id,String name,int level) {
        this.setId(id);
        this.setName(name);
        this.setLevel(level);
        this.setFunction(function);
        this.setJudgeFunction((Object... objects)-> objects[0].toString().length() > INDEX_MAX_LENGTH);
    }
}
