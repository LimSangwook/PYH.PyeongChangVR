package com.common.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class CommonApiUtils {
	private static Document xmlDoc = null;
	private static String XML_URL = JProperties.getString("KDA.API.URL");
	private static String XML_NODE = "RESULT";

	public static String printXmlFromUrl(String params) throws Exception{
		String xmlData = null;
		
		try {
			URL url = new URL(XML_URL.concat(params));		
			
				URLConnection urlConnection = url.openConnection();
				HttpURLConnection httpConnection = (HttpURLConnection) urlConnection;
				 // 요청 응답 타임아웃 설정
				httpConnection.setConnectTimeout(5000);
                // 읽기 타임아웃 설정
				httpConnection.setReadTimeout(3000);
				
				int responseCode = httpConnection.getResponseCode();
				if (responseCode == HttpURLConnection.HTTP_OK) {
					InputStream inputStream = httpConnection.getInputStream();

					// Parsing XML Document
					createDomParser(inputStream);
					xmlData = getByTagName(XML_NODE);
					inputStream.close();
				} else {
					System.out.println("KDA_API_CALL : "+XML_URL.concat(params));
					System.out.println("HTTP Response is not HTTP Status-Code 200: OK. responseCode : "+responseCode);
					throw new Exception("HTTP Response is not \"HTTP Status-Code 200: OK.");					
				}			
			return xmlData;
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		return xmlData;
	}

	/**
   	 * URL 데이터 가져오기
   	 * @param String strUrl
   	 * @return String
	 * @throws Exception 
   	 */
    public static String getUrlReader(String params, String header) throws Exception{
    	StringBuilder sb = new StringBuilder();
        
    	try {
			URL url = new URL(params);		
			
			URLConnection urlConnection = url.openConnection();
			
			HttpURLConnection httpConnection = (HttpURLConnection) urlConnection;
			 // 요청 응답 타임아웃 설정
			httpConnection.setConnectTimeout(3000);
            // 읽기 타임아웃 설정
			httpConnection.setReadTimeout(3000);
			
			if(header != null){
				httpConnection.setRequestProperty("Authorization", header);
			}
			
			int responseCode = httpConnection.getResponseCode();
			if (responseCode == HttpURLConnection.HTTP_OK) {
				InputStream inputStream = httpConnection.getInputStream();
				InputStreamReader isr = new InputStreamReader(inputStream, "UTF-8");
	            BufferedReader br = new BufferedReader(isr);
	            
	            String str ;
	            while((str=br.readLine()) != null) {
	            	sb.append(str);
	            }
				
				inputStream.close();
			} else {
				System.out.println("HTTP Response is not \"HTTP Status-Code 200: OK.\"");
				throw new Exception("HTTP Response is not \"HTTP Status-Code 200: OK.");					
			}
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}

    	return sb.toString();
    }
    
	private static void createDomParser(InputStream inputStream) {

		// Use factory to create a DOM document
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		factory.setIgnoringElementContentWhitespace(true);
		DocumentBuilder builder = null;

		try { // Get a DOM parser from the Factory
			builder = factory.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
			return;
		}

		try { // Request the DOM parser to parse the file
			xmlDoc = builder.parse(inputStream);
		} catch (SAXException e) {
			e.printStackTrace();
			return;
		} catch (IOException e) {
			e.printStackTrace();
			return;
		}
	}

	private static String getByTagName(String tagName) {

		if (!(xmlDoc == null)) {
			NodeList nodes = xmlDoc.getElementsByTagName(tagName);
			return nodes.item(0).getTextContent();
		}
		return null;
	}
}
