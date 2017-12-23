package com.krpano.build;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class XMLHelper {
	public static Node getScene(Document doc, String sceneName) {
		NodeList nl = doc.getDocumentElement().getChildNodes();
		for (int i = 0 ; i < nl.getLength() ; i++) {
			Node node = nl.item(i);
			NamedNodeMap attrs = node.getAttributes();
			if (attrs != null) {
				Node name = attrs.getNamedItem("name");
				if (name != null && sceneName.compareTo(name.getNodeValue()) == 0){
					return node;
				}
			}
		}
		return null;	
	}
}
