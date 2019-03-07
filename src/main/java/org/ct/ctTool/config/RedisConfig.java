package org.ct.ctTool.config;

/**
 * @author Think
 * @Title: RedisConfig
 * @ProjectName easyTool
 * @Description: TODO
 * @date 2019/2/25 19:05
 * @Version 1.0
 */
public class RedisConfig {

    private String hosts ;

    private int port = 6379 ;

    private String password;

    private int database = 0;

    private int minIdle = 2;

    private int maxIdle = 10;

    private int maxTotal = 100;

    private int timeout = 500;

    private long maxWaitMillis = 10000L;

    private int expiration = 600;

    private int lockExpires = 600;

    public String getHosts() {
        return hosts;
    }

    public void setHosts(String hosts) {
        this.hosts = hosts;
    }

    public int getPort() {
            return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getDatabase() {
        return database;
    }

    public void setDatabase(int database) {
        this.database = database;
    }

    public int getMinIdle() {
        return minIdle;
    }

    public void setMinIdle(int minIdle) {
        this.minIdle = minIdle;
    }

    public int getMaxIdle() {
        return maxIdle;
    }

    public void setMaxIdle(int maxIdle) {
        this.maxIdle = maxIdle;
    }

    public int getMaxTotal() {
        return maxTotal;
    }

    public void setMaxTotal(int maxTotal) {
        this.maxTotal = maxTotal;
    }

    public int getTimeout() {
        return timeout;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    public int getExpiration() {
        return expiration;
    }

    public void setExpiration(int expiration) {
        this.expiration = expiration;
    }

    public int getLockExpires() {
        return lockExpires;
    }

    public void setLockExpires(int lockExpires) {
        this.lockExpires = lockExpires;
    }

    public long getMaxWaitMillis() {
        return maxWaitMillis;
    }

    public void setMaxWaitMillis(long maxWaitMillis) {
        this.maxWaitMillis = maxWaitMillis;
    }
}
