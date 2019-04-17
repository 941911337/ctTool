package org.ct.ctTool.util.loadBalance;

import com.alibaba.fastjson.JSON;
import org.ct.ctTool.domain.Node;

import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author yzw
 * @Title: LoadBalance
 * @ProjectName ctTool
 * @Description: TODO
 * @date 2019/4/17 14:23
 * @Version 1.0
 */
public abstract class LoadBalance {

    protected CopyOnWriteArrayList<Node> nodes = new CopyOnWriteArrayList<>();

    private Object monitor = new Object();


    public void add(Node node) {
        nodes.add(node);
    }


    public void delete(Node node) {
        nodes.remove(node);
    }

    private static int index = 0;

    public synchronized static int nextInt(int size){
        int result = index % size;
        index++;
        return result;
    }










}
