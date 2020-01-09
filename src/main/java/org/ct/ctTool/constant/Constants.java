package org.ct.ctTool.constant;

/**
 * @author Think
 * @Title: Constant
 * @ProjectName easyTool
 * @Description: 常量类
 * @date 2019/2/23 16:32
 * @Version 1.0
 */
public class Constants {

    /**
     * 堆缓存大小 单位KB
     */
    public static final int HEAP_CACHE_SIZE = 10;


    /**
     * 堆外缓存大小 单位MB
     */
    public static final int OFF_HEAP_CACHE_SIZE = 20;

    /**
     * 磁盘缓存大小 单位MB
     */
    public static final int DISK_CACHE_SIZE = 100;

    /**
     * 堆可缓存的最大对象大小 单位MB
     */
    public static final long HEAP_MAX_OBJECT_SIZE = 1L;


    /**
     * 统计对象大小时对象图遍历深度
     */
    public static final long HEAP_MAX_OBJECT_GRAPH = 1000L;


    /**
     * 磁盘文件路径
     */
    public static final String DISK_CACHE_DIR = "/usr/cache";


    /**
     *ehcache缓存超时时间 单位秒
     */
    public static final int EHCACHE_TTL = 120;


    /**
     *ehcache名称
     */
    public static final String EHCACHE_JSON_CACHE_NAME = "easyToolJSONEHCache";

    /**
     *ehcache名称
     */
    public static final String EHCACHE_BYTE_CACHE_NAME = "easyToolBYTEEHCache";

    /**
     * 项目默认字符集
     */
    public static final String DEFAULT_CHARACTER_SET = "UTF-8";

    /**
     * 默认线程名
     */
    public static final String DEFAULT_THREAD_NAME = "global";

    /**
     * 索引名称最大长度
     */
    public final static int INDEX_MAX_LENGTH = 30;

    private Constants() { }
}
