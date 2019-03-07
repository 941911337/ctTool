package org.ct.ctTool.util.cache;

import com.alibaba.fastjson.JSON;
import org.ct.ctTool.constant.Constants;
import org.ct.ctTool.util.DataUtil;
import org.ct.ctTool.util.SerializerUtil;
import org.ehcache.Cache;
import org.ehcache.CacheManager;
import org.ehcache.config.CacheConfiguration;
import org.ehcache.config.builders.*;
import org.ehcache.config.units.MemoryUnit;
import org.ehcache.event.EventType;

import java.io.File;
import java.time.Duration;
import java.util.Collections;
import java.util.List;


/**
 * @author Think
 * @Title: Ehcache3Util
 * @ProjectName easyTool
 * @Description: ehcache工具类
 * @date 2019/2/21 14:55
 * @Version 1.0
 */
public abstract class EhCache3Util {

	/**
	 * 是否使用json序列化值 -1 不使用 使用SerializerUtil  ,1使用json 默认-1
	 */
	private static final int USE_JSON = -1;
	
	private static final Class<String> STRING_TYPE = String.class;


    /**
     * 监听器配置
     */
	private static CacheEventListenerConfigurationBuilder cacheEventListenerConfiguration = CacheEventListenerConfigurationBuilder
		    .newEventListenerConfiguration(new EhCacheEventListenerObject(), EventType.EXPIRED) 
		    .unordered().asynchronous();

    /**
     * ehcache 缓存配置 json序列化
     */
	private static final CacheConfiguration<String, String> CACHE_JSON_CONFIG = CacheConfigurationBuilder.newCacheConfigurationBuilder(STRING_TYPE, STRING_TYPE,
			  ResourcePoolsBuilder.newResourcePoolsBuilder()
              /**
               * 堆内缓存大小
               */
			  .heap(Constants.HEAP_CACHE_SIZE, MemoryUnit.KB)
              /**
               * 堆外缓存大小
               */
			  .offheap(Constants.OFF_HEAP_CACHE_SIZE, MemoryUnit.MB)
            /**
             * 文件缓存大小
             */
             .disk(Constants.DISK_CACHE_SIZE, MemoryUnit.MB)
			  )
            /**
             * 缓存超时时间
             */
            .withExpiry(ExpiryPolicyBuilder.timeToLiveExpiration(Duration.ofSeconds(Constants.EHCACHE_TTL)))
            /**
             * 统计对象大小时对象图遍历深度
             */
            .withSizeOfMaxObjectGraph(Constants.HEAP_MAX_OBJECT_GRAPH)
            /**
             * 可缓存的最大对象大小
             */
            .withSizeOfMaxObjectSize(Constants.HEAP_MAX_OBJECT_SIZE, MemoryUnit.KB)
			.add(cacheEventListenerConfiguration)
			.build();

	/**
	 * ehcache 缓存配置 序列化
	 */
	private static final CacheConfiguration<String, byte[]> CACHE_BYTE_CONFIG = CacheConfigurationBuilder.newCacheConfigurationBuilder(STRING_TYPE, byte[].class,
			ResourcePoolsBuilder.newResourcePoolsBuilder().heap(Constants.HEAP_CACHE_SIZE, MemoryUnit.KB).offheap(Constants.OFF_HEAP_CACHE_SIZE, MemoryUnit.MB)
	        )
			.withExpiry(ExpiryPolicyBuilder.timeToLiveExpiration(Duration.ofSeconds(Constants.EHCACHE_TTL)))
			.withSizeOfMaxObjectGraph(Constants.HEAP_MAX_OBJECT_GRAPH)
			.withSizeOfMaxObjectSize(Constants.HEAP_MAX_OBJECT_SIZE, MemoryUnit.KB)
			.add(cacheEventListenerConfiguration)
			.build();

    /**
     * 单例配置
     */
	private static class EhCacheHolder{
	   
		private static final  CacheManager CACHE_JSON_MANAGER = CacheManagerBuilder.newCacheManagerBuilder()
				.with(CacheManagerBuilder.persistence(new File(Constants.DISK_CACHE_DIR, Constants.EHCACHE_JSON_CACHE_NAME)))
				.withCache(Constants.EHCACHE_JSON_CACHE_NAME, CACHE_JSON_CONFIG).build(true);
		private static final  Cache<String,String> JSON_CACHE = CACHE_JSON_MANAGER.getCache(Constants.EHCACHE_JSON_CACHE_NAME, STRING_TYPE, STRING_TYPE);
        private static final  CacheManager CACHE_BYTE_MANAGER = CacheManagerBuilder.newCacheManagerBuilder()
				  .withCache(Constants.EHCACHE_BYTE_CACHE_NAME, CACHE_BYTE_CONFIG).build(true);
        private static final  Cache<String,byte[]> BYTE_CACHE = CACHE_BYTE_MANAGER.getCache(Constants.EHCACHE_BYTE_CACHE_NAME, STRING_TYPE, byte[].class);
        
    }

    /**
     * 获取缓存
     * @return
     */
	private static Cache<String,String> getJSONEhCache() {
		return EhCacheHolder.JSON_CACHE;
	}
	private static Cache<String,byte[]> getByteEhCache() {
		return EhCacheHolder.BYTE_CACHE;
	}

    /**
     * 私有化方法 禁止创建
     */
	private EhCache3Util(){ }

    /**
     * 存缓存
     * @param key 参数key
     * @param value 值
     */
	public static <T> void set(String key,Object value,Class<T> cls) {
		if(USE_JSON > 0){
			setJSONValue(key,value);
		}else{
			setByteValue(key,value,cls);
		}

	}

	/**
	 * 存对象缓存 json序列化
	 * @param key
	 * @param value
	 */
	private static void setJSONValue(String key,Object value){
		String json = JSON.toJSONString(value);
		getJSONEhCache().put(key, json);
	}

	/**
	 *  存对象缓存 byte序列化
	 * @param key
	 * @param value
	 * @param cls
	 * @param <T>
	 */
	private static <T> void setByteValue(String key,Object value,Class<T> cls){
		byte[] bytes = SerializerUtil.serialize(value,cls);
		getByteEhCache().put(key, bytes);
	}

    /**
     * 获取缓存值
     * @param key 参数key
     * @param clazz 类类型
     * @return 缓存值
     */
	public static <T>  T get(String key,Class<T> clazz) {
		if(USE_JSON > 0){
			return getJSONBean(key,clazz);
		}else{
			return getByteBean(key,clazz);
		}
	}

	/**
	 * 获取缓存对象 json序列化
	 * @param key
	 * @param clazz
	 * @param <T>
	 * @return
	 */
	private static <T> T getJSONBean(String key,Class<T> clazz){
		String json = getJSONEhCache().get(key);
		if (!DataUtil.isEmpty(json)) {
			return JSON.parseObject(json, clazz);
		}else{
			return null;
		}
	}

	/**
	 * 获取缓存对象 byte序列化
	 * @param key
	 * @param clazz
	 * @param <T>
	 * @return
	 */
	private static <T> T getByteBean(String key,Class<T> clazz){
		byte[] bytes = getByteEhCache().get(key);
		if (!DataUtil.isEmpty(bytes)) {
			return SerializerUtil.deserialize(bytes,clazz);
		}else{
			return null;
		}
	}

    /**
     * 获取list缓存值
     * @param key
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> List<T> getList(String key, Class<T> clazz) {
		if(USE_JSON > 0){
			return getJSONList(key,clazz);
		}else{
			return getByteList(key,clazz);
		}
    }


	/**
	 * 获取缓存集合 json序列化
	 * @param key
	 * @param clazz
	 * @param <T>
	 * @return
	 */
	private static <T> List<T> getJSONList(String key, Class<T> clazz) {
		String json = getJSONEhCache().get(key);
		if (!DataUtil.isEmpty(json)) {
			return JSON.parseArray(json, clazz);
		}
		return Collections.emptyList();
	}

	/**
	 * 获取缓存集合 bytes序列化
	 * @param key
	 * @param clazz
	 * @param <T>
	 * @return
	 */
	private static <T> List<T> getByteList(String key, Class<T> clazz) {
		byte[] bytes = getByteEhCache().get(key);
		if (!DataUtil.isEmpty(bytes)) {
			return SerializerUtil.deserializeList(bytes,clazz);
		}
		return Collections.emptyList();
	}
    /**
     * 清除缓存值
     * @param key 参数key
     */
	public static void remove(String key) {
		if(USE_JSON > 0){
			getJSONEhCache().remove(key);
		}else{
			getByteEhCache().remove(key);
		}

	}

    /**
     * 清除缓存值
     */
	public static void removeCache() {
		if(USE_JSON > 0){
			EhCacheHolder.CACHE_JSON_MANAGER.removeCache(Constants.EHCACHE_JSON_CACHE_NAME);
		}else{
			EhCacheHolder.CACHE_BYTE_MANAGER.removeCache(Constants.EHCACHE_BYTE_CACHE_NAME);
		}

	}



	
	

	

	
	

	
	

}
