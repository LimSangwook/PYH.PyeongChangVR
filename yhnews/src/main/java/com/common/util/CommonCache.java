package com.common.util;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class CommonCache implements ApplicationContextAware {
	 private static CommonCache instance;
	 private static Map<String, Object> cacheMap = new HashMap<String, Object>(); 

	 private CommonCache() {
		 
	 }
	 
	 public static CommonCache getInstance() {
		 if(instance == null) {
			 instance = new CommonCache();
		 }
		 return instance;
	 }
	 
	 public Object getCacheMap(String key) {
		 Object value = cacheMap.get(key);
		 return value;
	 }
	 
	 public void setCacheMap(String key, Object value) {
		 cacheMap.put(key, value);
	 }

	 public void clearCache() {
		 cacheMap.clear();
	 }
	@Override
	public void setApplicationContext(ApplicationContext arg0)
			throws BeansException {
		// TODO Auto-generated method stub
		
	}
}
