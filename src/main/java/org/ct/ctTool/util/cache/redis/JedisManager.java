package org.ct.ctTool.util.cache.redis;

import org.ct.ctTool.config.RedisConfig;
import org.ct.ctTool.exception.OperationException;
import org.ct.ctTool.util.encrypt.ThreeDesUtil;
import redis.clients.jedis.*;

/**
 * @author Think
 * @Title: JedisManager
 * @ProjectName easyTool
 * @Description: TODO
 * @date 2019/2/26 9:37
 * @Version 1.0
 */
public class JedisManager{

    private static volatile RedisConfig config = null;

    /**
     * 初始化非切片池
     */
    public static  JedisPool initialPool() {
        // 池基本配置
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();


        // 最大空闲连接数, 默认8个
        jedisPoolConfig.setMaxIdle(config.getMaxIdle());

        // 最大连接数, 默认8个
        jedisPoolConfig.setMaxTotal(config.getMaxTotal());

        // 获取连接时的最大等待毫秒数(如果设置为阻塞时BlockWhenExhausted),如果超时就抛异常, 小于零:阻塞不确定的时间,
        // 默认-1
        jedisPoolConfig.setMaxWaitMillis(config.getMaxWaitMillis());

        // 在获取连接的时候检查有效性, 默认false
        jedisPoolConfig.setTestOnBorrow(true);

        // 在空闲时检查有效性, 默认false
        jedisPoolConfig.setTestWhileIdle(false);

        // 逐出扫描的时间间隔(毫秒) 如果为负数,则不运行逐出线程, 默认-1
        jedisPoolConfig.setTimeBetweenEvictionRunsMillis(-1);

        String password = ThreeDesUtil.des3DecodeECB(config.getPassword());
        return new JedisPool(jedisPoolConfig, config.getHosts(), config.getPort(),config.getTimeout(),password,config.getDatabase());
    }

    public static class JedisHolder{
        private static final JedisPool JEDIS_POOL = getJedisPool();
        private Jedis jedis;// 非切片额客户端连接

        private ShardedJedis shardedJedis;// 切片额客户端连接
        private ShardedJedisPool shardedJedisPool;// 切片连接池
    }

    private static JedisPool getJedisPool(){
        if(config != null){
            return initialPool();
        }else{
            throw new RuntimeException("错误");
        }
    }

    public static  Jedis getJedis() {
        if(JedisHolder.JEDIS_POOL != null){
            Jedis jedis = JedisHolder.JEDIS_POOL.getResource();
            return jedis;
        }else{
            throw new OperationException("未初始化");
        }
    }

    public static RedisConfig getConfig() {
        return config;
    }

    public static void setConfig(RedisConfig config) {
        JedisManager.config = config;
    }


}
