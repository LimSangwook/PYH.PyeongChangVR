package com.krpano.build.model;

public class VRHotSpotModel implements Cloneable {
	private String name;
	private String style;
	private double ath;
	private double atv;
	private String linkedSceneName;
	
	public VRHotSpotModel(double ath, double atv, String linkedSceneName) {
		this.ath = ath;
		this.atv = atv;
		this.linkedSceneName = linkedSceneName;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLinkedSceneTitle() {
		return linkedSceneName;
	}

	public void setLinkedSceneName(String linkedSceneName) {
		this.linkedSceneName = linkedSceneName;
	}

	public String getStyle() {
		return style;
	}
	public void setStyle(String style) {
		this.style = style;
	}
	public double getAth() {
		return ath;
	}
	public void setAth(double ath) {
		this.ath = ath;
	}
	public double getAtv() {
		return atv;
	}
	public void setAtv(double atv) {
		this.atv = atv;
	}
}
