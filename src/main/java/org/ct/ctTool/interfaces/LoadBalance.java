package org.ct.ctTool.interfaces;

import org.ct.ctTool.domain.Node;

/**
 * @author yzw
 * @Title: LoadBalance
 * @ProjectName ctTool
 * @Description: 负载均衡
 * @date 2019/4/17 13:33
 * @Version 1.0
 */
public interface LoadBalance {

    void add(Node node);

    void delete(Node node);

    Node next();


}
