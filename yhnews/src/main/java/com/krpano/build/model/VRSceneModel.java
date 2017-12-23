package com.krpano.build.model;

import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class VRSceneModel {
	private String groupName;
	private String title;
	private String name;
	private String panoramaFilePath;
	
	private List<VRHotSpotModel> hotspotList = new ArrayList<VRHotSpotModel>();
	
	public String getPanoramaFilePath() {
		return panoramaFilePath;
	}
	public void setPanoramaFilePath(String panoramaFilePath) {
		this.panoramaFilePath = panoramaFilePath;
	}
	public void addHotspot(VRHotSpotModel hs) {
		hotspotList.add(hs);
	}
	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public VRSceneModel() {
	}
	public void addHotspot(Document doc, Element scene) {
		for (VRHotSpotModel hsm : hotspotList) { 
			Element he = doc.createElement("hotspot");
			he.setAttribute("name", hsm.getName());
			he.setAttribute("linkedscene", hsm.getLinkedSceneTitle());
			he.setAttribute("atv", Double.toString(hsm.getAtv()));
			he.setAttribute("ath", Double.toString(hsm.getAth()));
			he.setAttribute("style", hsm.getStyle());
			scene.appendChild(he);
		}
	}
}
