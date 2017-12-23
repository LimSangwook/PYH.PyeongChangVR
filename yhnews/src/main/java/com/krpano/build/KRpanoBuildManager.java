package com.krpano.build;

import java.lang.Thread.State;
import java.util.HashMap;
import java.util.Map;

public class KRpanoBuildManager {
	// Singleton Pattern - private static 로 선언.
	private static KRpanoBuildManager instance = new KRpanoBuildManager();
	private Map<String, KRpanoBuilder> threadMap = new HashMap<String, KRpanoBuilder>();
	
	private KRpanoBuildManager () {
		System.out.println( "call Singleton KrapanoBuilder constructor." );
	}
	public static KRpanoBuildManager getInstance () {
		return instance;
	}
	
	public void addBuilder(String name, KRpanoBuilder builder) {
		if (threadMap.containsKey(name) == true) {
			killBuilder(name);
		}
		threadMap.put(name, builder);
	}
	private void killBuilder(String name) {
		// TODO Auto-generated method stub
		
	}
	public boolean isContains(String key) {
		return threadMap.containsKey(key);
	}
	
	public void startBuild(String name) {
		KRpanoBuilder bulder = threadMap.get(name);
		bulder.start();
	}
	
	public State getStatus(String builderName) {
		Thread aThread = threadMap.get(builderName);
		return aThread.getState();
	}
	
	public void removeBuilder(String name) {
		threadMap.remove(name);
	}
	
	public void addAndStartBuild(String name, KRpanoBuilder builder) {
		addBuilder(name, builder);
		startBuild(name);
	}
}
