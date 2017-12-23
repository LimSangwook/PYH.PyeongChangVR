package com.common.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.security.SecureRandom;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

public class CommonUtils {

	private static final String[] HEADERS_TO_TRY = { "X-Forwarded-For", "Proxy-Client-IP", "WL-Proxy-Client-IP", "HTTP_X_FORWARDED_FOR", "HTTP_X_FORWARDED", "HTTP_X_CLUSTER_CLIENT_IP", "HTTP_CLIENT_IP", "HTTP_FORWARDED_FOR", "HTTP_FORWARDED", "HTTP_VIA", "REMOTE_ADDR" };
	private static final String[] WEEK_NAME = {"일요일","월요일","화요일","수요일","목요일","금요일","토요일"};
	
	public static String getClientIpAddress(HttpServletRequest request) {
		for (String header : HEADERS_TO_TRY) {
			String ip = request.getHeader(header);
			//System.out.println("##### "+header+", "+ip);
			if (ip != null && ip.length() != 0 && !"unknown".equalsIgnoreCase(ip)) return ip; 
		}
		return request.getRemoteAddr();
	}
	
	/**
	 * 인코딩 변경 8859_1 -> UTF-8
	 * @param str
	 * @return
	 */
	public static String utf8(String str){
		if(str==null) return null;
		try {
			return new String(str.getBytes("8859_1"),"UTF-8");
		} catch(Exception e) {
			return null;
		}
	}
	
	/**
	 * 길이를 초과하는 문자열을 길이만큼 자른후 꼬리문자열을 붙인다.
	 * 
	 * @param source 원본 문자열
	 * @param length 최대 길이
	 * @param tail 꼬리문자열
	 * @param charset 문자셋
	 * @return 길이만큼 잘라진 문자열
	 */
	public static String crop(String source, int length, String tail, String charset){		
		if (source == null) return null;
		String result = source;
		try {
			
			int sLength = 0;
			int bLength = 0;
			char c;
			
			if ( result.getBytes(charset).length > length) {
			    length = length - tail.length();
				while ( (bLength + 1) <= length) {
					c = result.charAt(sLength);
					bLength++;
					sLength++;
					if (c > 127) {
						bLength++;
						bLength++;
					}
				}
				result = result.substring(0, sLength).concat(tail);
			}	
		} catch (Exception e) {
			return "";
		}		
		return result;
	}
	
	public static String htmlView(String str) {
		if (str == null) {
			return "";
		} else {
			return str.replaceAll("\n", "<p/>")
					.replaceAll("\t", "&nbsp;&nbsp;&nbsp;&nbsp;")
					.replaceAll("  ", "&nbsp;&nbsp;")
					.replaceAll("\"", "&quot;");
		}
	}
	
	/**
	 * 로컬도메인여부
	 * @param request
	 * @return
	 */
	public static boolean isLocalDomain(HttpServletRequest request) {
		return request.getServerName().indexOf("localhost") != -1 ? true:false;
	}
	
	public static void printOut(HttpServletResponse response, String content) {		
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = null;
		try 	{
			out = response.getWriter();
			out.println(content);
			out.close();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			if(out != null) {
				out.close();
			}
		}
	}
	
	/**
	 * 메시지를 받아서 alert창에 띄우고 해당 페이지로 이동
	 * 
	 * @param sAlert
	 * @return
	 * @throws Exception
	 */
	public static String fnAlertAndLocation(final String sAlert, final String url) throws Exception {		
		StringBuilder sContents = new StringBuilder();
		try {
			sContents.append("<script type='text/javascript'>");		
			sContents.append("alert('" + sAlert + "');");
			sContents.append("document.location = '"+url+"'");
			sContents.append("</script>");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sContents.toString();
	}
	
	/**
	 * 컨펌 메시지를 받아서 alert창에 띄우고 해당 페이지로 이동
	 * 
	 * @param sAlert
	 * @param confirmUrl - 확인 후 이동주소
	 * @param defaultUrl - 기본 이동주소
	 * @return string
	 * @throws Exception
	 */
	public static String fnConfirmAlertAndLocation(final String sAlert, final String confirmUrl, String defaultUrl) throws Exception {		
		StringBuilder sContents = new StringBuilder();
		try {
			
			sContents.append("<script type='text/javascript'>");			
			sContents.append("if(confirm('"+sAlert+"')){");
			sContents.append("document.location = '"+confirmUrl+"'");
			sContents.append("}else{");
			sContents.append("document.location = '"+defaultUrl+"'");
			sContents.append("}");
			sContents.append("</script>");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sContents.toString();
	}
	
	/**
	 * 해당 페이지로 이동
	 * 
	 * @param sAlert
	 * @return
	 * @throws Exception
	 */
	public static String fnlocation(final String url) throws Exception {
		StringBuilder sContents = new StringBuilder();
		try {
			sContents.append("<script type='text/javascript'>");
			sContents.append("document.location = '"+url+"'");
			sContents.append("</script>");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sContents.toString();
	}
	
	/**
	 * 메세지를 받아서 alert창에 띄우고 자바스크립트 호출
	 * 
	 * @param sAlert
	 * @return
	 * @throws Exception
	 */
	public static String fnAlertAndjavascriptFunction(final String sAlert, final String jsFunction) throws Exception {
		StringBuilder sContents = new StringBuilder();

		try {
			sContents.append("<script type='text/javascript'>\n");
			sContents.append("alert('" + sAlert + "');\n");
			sContents.append(jsFunction);
			sContents.append("</script>\n");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sContents.toString();
	}
	
	/**
	 * 자바스크립트 호출
	 * 
	 * @param sAlert
	 * @return
	 * @throws Exception
	 */
	public static String fnJavascriptFunction(final String jsFunction) throws Exception {
		StringBuilder sContents = new StringBuilder();

		try {
			sContents.append("<script type='text/javascript'>\n");			
			sContents.append(jsFunction);
			sContents.append("</script>\n");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sContents.toString();
	}
	
	/**
   	 * URL 데이터 가져오기
   	 * @param reqUrl
   	 * @return String
	 * @throws Exception 
   	 */
    public static String getUrlReader(String reqUrl) throws Exception{
    	final int TIMEOUT_VAL = 1000*50;
    	StringBuilder sb = new StringBuilder();        
    	try {
			URL url = new URL(reqUrl);		
			
			URLConnection urlConnection = url.openConnection();
			HttpURLConnection httpConnection = (HttpURLConnection) urlConnection;
			// 요청 응답 타임아웃 설정
			httpConnection.setConnectTimeout(TIMEOUT_VAL);
            // 읽기 타임아웃 설정
			httpConnection.setReadTimeout(TIMEOUT_VAL);
			
			int responseCode = httpConnection.getResponseCode();
			if (responseCode == HttpURLConnection.HTTP_OK) {
				InputStream inputStream = httpConnection.getInputStream();
				InputStreamReader isr = new InputStreamReader(inputStream, "UTF-8");
	            BufferedReader br = new BufferedReader(isr);
	            
	            String str = "";
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
    
    /** 
     * format에 맞는 현재 날짜 및 시간을 리턴      
     * @param format 
     * @return String
     */
    public static String getCurrentDateTime(String format) {
        return new SimpleDateFormat(format).format(new Date());
    }
    
    /**
     * date 더하기 빼기
     *
     * @param 	date	date객체
     * @param 	amount	date객체에 반영될 값
     * @return 	Date	
     */
    public static Date addDate(Date date, int amount) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, amount);
        return calendar.getTime();
    }
    
    /**
     * 이전 다음달
     *
     * @param 	date	date객체
     * @param 	amount	date객체에 반영될 값
     * @return 	Date	
     */
    public static String addMonth(int year, int month, int amount) {                
        return addMonth(year, month, amount, "yyyyMM");
    }
    
    /**
     * 이전 다음달
     *
     * @param 	date	date객체
     * @param 	amount	date객체에 반영될 값
     * @return 	Date	
     */
    public static String addMonth(int year, int month, int amount, String format) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month-1,1);        
        calendar.add(Calendar.MONTH, amount);
        
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);        
        return dateFormat.format(calendar.getTime());
    }
    
    /**
     * 특정 년월 마지막 날짜
     *
     * @param 	year	년도
     * @param 	month 월
     * @return 	Integer
     */
    public static int getLastDay(int year, int month) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month-1);
        calendar.set(Calendar.DATE, 1);
        return calendar.getActualMaximum(Calendar.DATE);
    }
    
    /**
     * 요일구하기
     *
     * @param 	year	년도
     * @param 	month 월
     * @param 	dya 일
     * @return 	Integer
     */
    public static int getWeek(int year, int month, int day) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month-1);
        cal.set(Calendar.DATE, day);
        return cal.get(Calendar.DAY_OF_WEEK)-1;
    }
    public static String getWeekName(int year, int month, int day) {
    	return WEEK_NAME[getWeek(year, month, day)];
    }
    
    /**
     * 포맷에 맞는 문자열을 java.util.Date 형으로 변환한다.
     *
     * @param  strDate
     * @param  format
     * @return Date
     */
    public static Date stringToDate(String strDate, String format) {

        Date date = null;

        if (strDate == null) {
            return null;

        } else {
            try {
                SimpleDateFormat formatter = new SimpleDateFormat(format);
                date = formatter.parse(strDate);
            } catch (ParseException e) {
                return null;
            }
        }
        return date;
    }
    
    /**
     * Date 값을 특정한 format 으로 변환 시켜준다. 
     * Date가 Null 이면 공백 String을 Return 한다.
     * @param  date
     * @param  format
     * @return String
     */
    public static String dateToString(Date date, String format) {
        if (date == null) {
            return "";
        } else {
            SimpleDateFormat formatter = new SimpleDateFormat(format);
            return formatter.format(date);
        }
    }
    
    /**
     * <p>문자열을 날짜형으로 변환한다.</p> 
     * @param date
     * @param format
     * @return
     * @throws ParseException
     */
    public static Date parseDate(String date, String format) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.parse(date);
	}
    
    /**
     * <p>에러메시지를 JSONObject로 반환한다.</p>
     * @param result
     * @param code
     * @param message
     * @return
     */
    public static JSONObject errorMessageJSON(String code, String message) {
    	JSONObject result = new JSONObject();
    	result.put("RESULT_CODE", code);
		result.put("RESULT_MESSAGE", message);
		return result;
    }
    
    public static String convertFileSize(long size) {
        if(size <= 0) return "0";
        final String[] units = new String[] { "B", "kB", "MB", "GB", "TB" };
        int digitGroups = (int) (Math.log10(size)/Math.log10(1024));
        return new DecimalFormat("#,##0.#").format(size/Math.pow(1024, digitGroups)) + " " + units[digitGroups];
    }
    
    // 랜덤키생성
    public static String getSecureRandom(){
    	SecureRandom random = new SecureRandom();
    	return new BigInteger(130, random).toString(32);
    }
    
    /**
   	 * 임시 비밀번호 추출 
   	 * @return String
   	 */
   	public static String getTempPassword() {
   		String milliNo = new Double(Math.abs(Math.random())).toString(); // 난수값 출력
   		milliNo = milliNo.substring(milliNo.length() - 3, milliNo.length());
   		StringBuffer buf = new StringBuffer();
   		for (int i = 0; i < 3; i++) {
   			buf.append((char) ((int) (Math.random() * 26) + 65));
   		}
   		return buf.toString().toLowerCase() + milliNo;
   	}
   	
   	/**
   	 * 두 날짜 사이 수를 구한다
   	 * - format yyyyMMdd, yyyy-MM-dd ...
   	 * */
   	public static long diffOfDate(String begin, String end, String format) {   		
   		try {
   			SimpleDateFormat formatter = new SimpleDateFormat(format);
   		   
   	   		Date beginDate = formatter.parse(begin);
   	   		Date endDate = formatter.parse(end);
   	   
   	   		long diff = endDate.getTime() - beginDate.getTime();
   	   		long diffDays = diff / (24 * 60 * 60 * 1000);
   	   		
   	   		return diffDays;
   	   		
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
    }
}
