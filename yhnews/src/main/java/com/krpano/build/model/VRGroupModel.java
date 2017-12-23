package com.krpano.build.model;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class VRGroupModel {
	private String name;
	private String title;
	private String description;
	
	public Element getXMLElement(Document doc){
		Element element = doc.createElement("panoramagroup");
		element.setAttribute("name", name);
		element.setAttribute("title", title);
		element.setAttribute("description", description);
		return element;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
}
