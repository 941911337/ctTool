package org.ct.ctTool.util.cache;

import org.ehcache.event.CacheEvent;
import org.ehcache.event.CacheEventListener;

/**
 * 一级缓存监听
 */
public class EhCacheEventListenerObject implements CacheEventListener {

	@Override
	public void onEvent(CacheEvent event) {
		System.out.println("超时了,key:"+event.getKey());
		
	}
}
