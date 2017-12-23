package com.krpano.build;

import java.io.File;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;

public class Utils {
	public static  Document getXMLDocument(String path) throws Exception {
		File fXmlFile = new File(path);
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse(fXmlFile);
		doc.getDocumentElement().normalize();
		return doc;
	}
	
	// Convert List to []
	public static String[] convertList2Array(List<String> list) {
        String[] appArray = new String[list.size()];
        appArray = list.toArray(appArray);
        return appArray;
	}
	
	public static boolean deleteDirectory(File path) { 
		if(!path.exists()) { 
			return false; 
		} 
		File[] files = path.listFiles(); 
		for (File file : files) { 
			if (file.isDirectory()) { 
				deleteDirectory(file); 
			} else { 
				file.delete(); 
			} 
		} 
		return path.delete(); 
	}
}
