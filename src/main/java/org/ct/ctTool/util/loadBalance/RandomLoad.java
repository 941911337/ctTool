package org.ct.ctTool.util.loadBalance;

import com.alibaba.fastjson.JSON;
import org.ct.ctTool.domain.Node;
import org.ct.ctTool.util.RandomUtil;

/**
 * @author yzw
 * @Title: RandomLoad
 * @ProjectName ctTool
 * @Description: 随机负载均衡
 * @date 2019/4/17 17:04
 * @Version 1.0
 */
public class RandomLoad extends LoadBalance {

    private static int index = 0;

    private static int lastSize = 0;

    private static RandomUtil.CTRandom ctRandom = null;

    public synchronized static int nextIndex(int size){
        if(lastSize !=  size){
            ctRandom = new RandomUtil.CTRandom(size);
        }
        return ctRandom.nextInt();
    }

    public Node next() {
        int index = nextIndex(nodes.size());
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
}
