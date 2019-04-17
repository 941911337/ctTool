package org.ct.ctTool.util.loadBalance;

import com.alibaba.fastjson.JSON;
import org.ct.ctTool.domain.Node;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author yzw
 * @Title: Polling
 * @ProjectName ctTool
 * @Description: 轮询
 * @date 2019/4/17 13:54
 * @Version 1.0
 */
public class Polling extends LoadBalance {


    public Node next() {
        int index = nextInt(nodes.size());
        Node node = nodes.get(index);
        synchronized(node){
            Long connectCount =  node.getConnectCount() + 1;
            if(connectCount <= node.getMaxConnectCount()){
                node.setConnectCount(node.getConnectCount()+1);
                System.out.println(JSON.toJSONString(node));
                return node;
            }else{
                delete(node);
                return  null;
            }

        }
    }

    public static void main(String[] args) {
        Polling polling = new Polling();
        Node node = new Node(1,"node1",0L,5L,1);
        Node node1 = new Node(2,"node2",0L,10L,1);
        polling.add(node);
        polling.add(node1);
        ExecutorService es = Executors.newFixedThreadPool(15);
        for (int i = 0; i <15 ; i++) {
            es.submit(new Runnable() {
                @Override
                public void run() {
                    polling.next();
                }
            });
        }
    }



}
