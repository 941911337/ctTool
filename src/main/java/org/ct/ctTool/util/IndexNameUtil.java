package org.ct.ctTool.util;

import org.ct.ctTool.domain.index.IndexChain;
import org.ct.ctTool.exception.OperationException;
import org.ct.ctTool.function.ChainFunction;

import java.util.LinkedList;
import java.util.List;

/**
 * @author yzw
 * @Title: IndexNameUtil
 * @ProjectName ctTool
 * @Description: TODO
 * @date 2020/1/8 9:02
 * @Version 1.0
 */
public abstract class IndexNameUtil {

    private static final ChainFunction strategyA = (Object object)->{
        List<String> list = (List<String>) object;
        StringBuilder index = new StringBuilder("IDX");
        list.forEach(it ->index.append("_").append(it.toUpperCase()));
        String idx =  index.toString();
        return idx;};
    private static final ChainFunction strategyB = (Object object)->{
        List<String> list = (List<String>) object;
        StringBuilder index = new StringBuilder("IDX");
        list.forEach(it ->index.append("_").append(it.substring(0,1).toUpperCase()));
        String idx =  index.toString();
        return idx;};
    private static final ChainFunction strategyC = (Object object)->{
        List<String> list = (List<String>) object;
        StringBuilder index = new StringBuilder("IDX");
        list.forEach(it ->index.append(it.substring(0,1).toUpperCase()));
        String idx =  index.toString();
        return idx;
    };
    private static final ChainFunction strategyD = (Object object)->{
        List<String> list = (List<String>) object;
        StringBuilder index = new StringBuilder("IDX");
        list.forEach(it ->index.append(it.substring(0,1).toUpperCase()));
        String idx =  index.toString();
        idx = idx.substring(0,30);
        return idx;
    };
    private static final IndexChain indexChain = buildIndexChain(strategyA,strategyB,strategyC,strategyD);

    /**
     * 生成策略
     * @param chainFunctions 每个策略具体逻辑
     * @return 策略入口
     */
    public static IndexChain buildIndexChain(ChainFunction... chainFunctions){
        if (chainFunctions.length > 7){
            throw new OperationException("策略链长请少于7个");
        }
        LinkedList<IndexChain> list = new LinkedList<>();
        int id = 1;
        for (ChainFunction chainFunction : chainFunctions) {
            String idStr = String.valueOf(id);
            String name = String.format("Index-Chain-%d",id);
            IndexChain indexChain = new IndexChain( chainFunction,idStr,name,id);
            list.add(indexChain);
            id++;
        }
        IndexChain last =  list.removeLast();
        IndexChain result = null;
        while(list.size() > 0){
            IndexChain pre =  list.removeLast();
            //当前节点不是首节点
            if (pre != null){
                pre.setNext(last);
            }
            last = pre;
            result = last;

        }
        return result;
    }

    /**
     * 生成索引名称
     * @param cols 列名集合
     * @return 索引名称
     */
    public static String generateIndexName(List<String> cols){
       return indexChain.choose(cols);
    }

    public IndexNameUtil() {
    }


}
