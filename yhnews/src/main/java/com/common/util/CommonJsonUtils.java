package com.common.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CommonJsonUtils {
	private static Logger log = LoggerFactory.getLogger(CommonJsonUtils.class);
	
	/**
     * <p>파일 내용을 읽어 JSONObject 로 반환한다.</p>
     * @param fileName
     * @return
     */
    public static JSONObject getFileToJsonObject(String fileRootPath, String fileName) {
    	JSONObject result = null;
    	
    	try {
    		String content = fileToString(fileRootPath, fileName);
    		if(StringUtils.isNotBlank(content)) {
    			result = JSONObject.fromObject(content);
    		}
    	} catch (Exception e) {
    		if(log.isErrorEnabled()) log.error(e.getMessage());
    	}
    	
    	return result;
    }
    
    /**
     * <p>파일 내용을 읽어 JSONArray 로 반환한다.</p>
     * @param fileName
     * @return
     */
    public static JSONArray getFileToJsonArray(String fileRootPath, String fileName) {
    	JSONArray result = null;
    	
    	try {
    		String content = fileToString(fileRootPath, fileName);
    		if(StringUtils.isNotBlank(content)) {
    			result = JSONArray.fromObject(content);
    		}
    	} catch (Exception e) {
    		if(log.isErrorEnabled()) log.error(e.getMessage());
    	}
    	
    	return result;
    }
    
    /**
     * <p>파일 내용을 읽어 문자열로 반환한다.</p> 
     * @param fileName
     * @return
     */
    public static String fileToString(String fileRootPath, String fileName) {
    	StringBuilder result = new StringBuilder();
    	
		BufferedReader bufferedReader = null;
		String fileAbsolutePath = null;
		String thisLine = "";

		try {
			fileAbsolutePath = fileRootPath +"/"+ fileName;
			bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(fileAbsolutePath),"UTF8"));
			
			while ((thisLine = bufferedReader.readLine()) != null) { 
				result.append(thisLine);
			}
			if(log.isDebugEnabled())log.debug(fileAbsolutePath);
		} catch (IOException e) {
			if(log.isErrorEnabled()) log.error(e.getMessage());
		} finally {
			try {
				if(bufferedReader != null) bufferedReader.close();
			} catch (IOException e) {
			}
		}
		return result.toString();
	}
    
    /**
     * <p>문자열을 파일로 변경한다.</p>
     * @param fileName
     * @param content
     */
    public static void stringToFile(String fileRootPath, String fileName, String content) {
    	BufferedWriter bufferedWriter = null;
    	String fileAbsolutePath = null;
    	
    	File file = new File(fileRootPath);

    	if (!file.exists()) {
    		file.mkdirs();
    	}

    	try{
    		fileAbsolutePath = fileRootPath +"/"+ fileName;
    		bufferedWriter = new BufferedWriter(new OutputStreamWriter (new FileOutputStream(fileAbsolutePath),"UTF8"));
    		bufferedWriter.write(content);
    		bufferedWriter.close();
    		
    		if(log.isDebugEnabled())log.debug(fileAbsolutePath);
    	} catch(IOException e){
    		if(log.isErrorEnabled()) log.error(e.getMessage());
    	} finally {
    		try {
				if(bufferedWriter != null) bufferedWriter.close();
			} catch (IOException e) {
			}
    	}
    }
    
    /**
     * <p>배포된 JSON 파일을 읽어서 반환하고 파일이 존재하지 않을 경우 신규로 생성한다.</p>
     * @param object
     * @param methodName
     * @param fileHeader
     * @param params
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
	public static JSONObject getCommonDeployJsonList(Object object, String methodName, String fileHeader, Map<String, Object> params) throws Exception {
    	JSONObject result = null;
    	JSONArray jsonArray = null;
    	boolean reload = false;
    	
    	if(StringUtils.isNotBlank((String)params.get("key"))) {
    		String fileName = new StringBuilder().append(fileHeader).append("_").append(params.get("key")).append(".json").toString();
    		
    		boolean deploy = !"0".equals(StringUtils.defaultString((String)params.get("deploy"), "0"));
    		boolean cache = !"N".equals(StringUtils.defaultString((String)params.get("cache"), "N"));
    		
    		if(deploy) {
    			if(cache) {
    				result = (JSONObject) CommonCache.getInstance().getCacheMap(fileName);
    			} else {
    				result = CommonJsonUtils.getFileToJsonObject((String)params.get("UPLOAD_DIR_JSON"), fileName);
    				//if(log.isDebugEnabled())log.debug(result+"");
    			}
        		
        		// 배포시간을 체크할 경우 재배포한다.
    			if(result != null && StringUtils.isNotBlank((String)params.get("deploy"))) {
    				if((System.currentTimeMillis() - NumberUtils.toLong(result.getString("DEPLOY_TIME"))) / 1000 / 60 > NumberUtils.toInt((String)params.get("deploy"), 5)) {
    					reload = true;
    				}
    			}
    		}

    		// 결과가 없을 경우 DB에서 조회후 JSON 파일 생성
    		if(reload || result == null) {
    			Class<?> clazz = object.getClass();
    			Method invokeMethod = clazz.getMethod(methodName, new HashMap<String, Object>().getClass());
    			List<?> objectList = (List<?>) invokeMethod.invoke(object, params);
    			
    			if(objectList != null && objectList.size() > 0) {
    				jsonArray = JSONArray.fromObject(objectList);
    				result = new JSONObject();
    				result.put("RESULT_DATA", jsonArray);
    				result.put("DEPLOY_TIME", System.currentTimeMillis());
    				
    				// JSON 파일 생성
    				if(deploy) {
    					if(cache) {
    						CommonCache.getInstance().setCacheMap(fileName, result);
    					} else {
    						CommonJsonUtils.stringToFile((String)params.get("UPLOAD_DIR_JSON"), fileName, result.toString());
    					}
    				}
    			}
    		} else {
    			jsonArray = JSONArray.fromObject(result.get("RESULT_DATA"));
    		}
    		
    		// 결과내에 기간이 지난 데이터를 제거함
    		if(jsonArray != null && !jsonArray.isEmpty()) {
    			
    			if("y".equalsIgnoreCase((String)params.get("validate"))) {
    				long now = System.currentTimeMillis();
    				
    				Iterator<JSONObject> iterator = jsonArray.iterator();
    				while(iterator.hasNext()) {
    					JSONObject value = iterator.next();
    					
    					Date start = CommonUtils.parseDate(value.getString("DISPLAY_START_DT"), "yyyyMMddHHmmss");
    					Date end = CommonUtils.parseDate(value.getString("DISPLAY_END_DT"), "yyyyMMddHHmmss");
    					
    					if(start.getTime() > now || end.getTime() < now) {
    						iterator.remove();
    					}
    				}
    			}
    			
    			if(jsonArray != null && !jsonArray.isEmpty()) {
    				result.put("RESULT_CODE", "SUCCESS");
    				
    				// 출력 형태가 랜덤일 경우
    				if("y".equalsIgnoreCase((String)params.get("random"))) {
    					int index = (int) (Math.random() * jsonArray.size());
    					result.put("RESULT_DATA", jsonArray.getJSONObject(index));
    				} else if("n".equalsIgnoreCase((String)params.get("random"))) {
    					result.put("RESULT_DATA", jsonArray.getJSONObject(0));
    				} else {
    					result.put("RESULT_DATA", jsonArray);
    				}
    			} else {
    				result = CommonUtils.errorMessageJSON("FAILURE", "data is null");
    			}
    			
    		} else {
    			result = CommonUtils.errorMessageJSON("FAILURE", "data is null");
    		}
    		
    	} else {
    		result = CommonUtils.errorMessageJSON("FAILURE", "request parameter not found");
    	}

    	return result;
    }
    
    /**
     * <p>배포된 JSON 파일을 읽어서 반환하고 파일이 존재하지 않을 경우 신규로 생성한다.</p>
     * @param object
     * @param methodName
     * @param fileHeader
     * @param params
     * @return
     * @throws Exception
     */    
	public static JSONObject getCommonDeployJsonMap(Object object, String methodName, String fileHeader, Map<String, Object> params) throws Exception {
    	JSONObject result = null;
    	JSONObject jsonOject = null;
    	boolean reload = false;
    	
    	if(StringUtils.isNotBlank((String)params.get("key"))) {
    		String fileName = new StringBuilder().append(fileHeader).append("_").append(params.get("key")).append(".json").toString();
    		
    		boolean deploy = !"0".equals(StringUtils.defaultString((String)params.get("deploy"), "0"));
    		boolean cache = !"N".equals(StringUtils.defaultString((String)params.get("cache"), "N"));
    		
    		if(deploy) {
    			if(cache) {
    				result = (JSONObject) CommonCache.getInstance().getCacheMap(fileName);
    			} else {
    				
    				result = CommonJsonUtils.getFileToJsonObject((String)params.get("UPLOAD_DIR_JSON"), fileName);
    			}
    		}
    		
    		// 배포시간을 체크할 경우 재배포한다.
			if(result != null && StringUtils.isNotBlank((String)params.get("deploy"))) {
				if((System.currentTimeMillis() - NumberUtils.toLong(result.getString("DEPLOY_TIME"))) / 1000 / 60 > NumberUtils.toInt((String)params.get("deploy"), 5)) {
					reload = true;
				}
			}

    		// 결과가 없을 경우 DB에서 조회후 JSON 파일 생성
    		if(reload || result == null) {
    			Class<?> clazz = object.getClass();
    			Method invokeMethod = clazz.getMethod(methodName, new HashMap<String, Object>().getClass());
    			Map<?,?> objectMap = (Map<?,?>) invokeMethod.invoke(object, params);
    			
    			if(objectMap != null && objectMap.size() > 0) {
    				jsonOject = JSONObject.fromObject(objectMap);
    				result = new JSONObject();
    				result.put("RESULT_DATA", jsonOject);
    				result.put("DEPLOY_TIME", System.currentTimeMillis());
    				
    				// JSON 파일 생성
    				if(deploy) {
    					if(cache) {
    						CommonCache.getInstance().setCacheMap(fileName, result);
    					} else {
    						CommonJsonUtils.stringToFile((String)params.get("UPLOAD_DIR_JSON"), fileName, result.toString());
    					}
    				}
    			}
    		} else {
    			jsonOject = JSONObject.fromObject(result.get("RESULT_DATA"));
    		}
    		
    		if(jsonOject != null && !jsonOject.isEmpty()) {
				result.put("RESULT_CODE", "SUCCESS");
			} else {
				result = CommonUtils.errorMessageJSON("FAILURE", "data is null");
			}
    		
    	} else {
    		result = CommonUtils.errorMessageJSON("FAILURE", "request parameter not found");
    	}

    	return result;
    }
}
