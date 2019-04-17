package org.ct.ctTool.domain;

/**
 * @author yzw
 * @Title: Node
 * @ProjectName ctTool
 * @Description: 节点对象
 * @date 2019/4/17 13:35
 * @Version 1.0
 */
public class Node {

    private Integer id;

    private String name;

    private Long connectCount;

    private Long maxConnectCount;

    private Integer weight;

    private Object data;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getConnectCount() {
        return connectCount;
    }

    public void setConnectCount(Long connectCount) {
        this.connectCount = connectCount;
    }

    public Long getMaxConnectCount() {
        return maxConnectCount;
    }

    public void setMaxConnectCount(Long maxConnectCount) {
        this.maxConnectCount = maxConnectCount;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Node(Integer id, String name, Long connectCount, Long maxConnectCount, Integer weight) {
        this.id = id;
        this.name = name;
        this.connectCount = connectCount;
        this.maxConnectCount = maxConnectCount;
        this.weight = weight;
    }
}
