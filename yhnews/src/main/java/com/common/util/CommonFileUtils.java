package com.common.util;

import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.imgscalr.Scalr;
import org.imgscalr.Scalr.Method;
import org.imgscalr.Scalr.Mode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

public class CommonFileUtils {
	private static Logger log = LoggerFactory.getLogger("CommonFileUtils");
	
	private static final int BUFFER_SIZE 		= 4*1024; //2048,8192;	
	private static long MAXSIZE 					= 10; //MB
	private static final String CHARSET 			= "UTF-8";
	private static String file_ext_all		 			= JProperties.getString("FILE.EXT.ALL");
	private static String file_root_path			= JProperties.getString("FILE.ROOT.PATH");
	private static String file_default_dir			= JProperties.getString("FILE.DEFAULT.DIR");
	private static String file_cont_dir				= JProperties.getString("FILE.CONT.DIR");
	private static String file_thumb_dir			= JProperties.getString("FILE.THUMB.DIR");
	private static int image_cont_width			= JProperties.getInt("IMAGE.CONT.WIDTH");
	private static int image_thumb_width		= JProperties.getInt("IMAGE.THUMB.WIDTH");
	private static String sep 							= System.getProperty("file.separator");	
	private static int fileNameKeyIncrement 	= 1;

	private static int org_image_width;
	private static int org_image_height;
	
	public static String getTarget(HttpServletRequest request) {
		String target = "";
		if(StringUtils.isEmpty(file_root_path)) {
			ServletContext context = request.getSession().getServletContext();			
			target = context.getRealPath(sep.concat(file_default_dir));
		} else {
			target = file_root_path.concat(sep).concat(file_default_dir); 
		}		
		return target;
	}
	
	/**
	 * 파일 업로드
	 * @param mpRequest MultipartHttpServletRequest
	 * @param realPath 업로드 파일명
	 * @return
	 * @throws Exception
	 */
	public static List<Map<String,Object>> uploadFiles(MultipartHttpServletRequest mpRequest) throws Exception {
		String target = getTarget((HttpServletRequest)mpRequest);		
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		Iterator<String> fileNames = null;
		
		String fileMaxSize = mpRequest.getParameter("file_max_size");
		String fileExtLimit = mpRequest.getParameter("permission");
		
		//첨부파일최대용량
		if(!StringUtils.isBlank(fileMaxSize)) {
			MAXSIZE = Integer.parseInt(fileMaxSize);
		}		
		// 확장자 허용
		if(StringUtils.isBlank(fileExtLimit)) {
			fileExtLimit = file_ext_all;
		}
				
		String[] arrayValidFile = fileExtLimit.split("[|]");		
		fileNames = mpRequest.getFileNames();		
		
		while (fileNames.hasNext()) {
			org_image_width = 0;
			org_image_height = 0;
			String formName = (String) fileNames.next();
			MultipartFile formFile = mpRequest.getFile(formName);
			
			String orgFileName = formFile.getOriginalFilename();
			String fileExt = getExtension(orgFileName);				
			
			if (!formFile.isEmpty()) {	
				//파일확장자체크
				boolean fileExeFlag = false;
				for(int i=0; i<arrayValidFile.length; i++) {						
					if(fileExt.equalsIgnoreCase(arrayValidFile[i])) {
						fileExeFlag = true;
					}
				}
				if(!fileExeFlag) {
					throw new Exception(StringUtils.replace(file_ext_all, "[|]", ", ") + " 확장자 파일만 등록 가능합니다.");
				}
				//파일사이즈체크 무제한:999				
				System.out.println("maxSize : "+ MAXSIZE*1024*1024 + ", fileSize :" +formFile.getSize());
				if(MAXSIZE < 999){
					if((MAXSIZE*1024*1024) < formFile.getSize()) {					
						throw new Exception("파일용량은 최고 "+MAXSIZE + "MB까지 업로드 가능합니다.");										
					}	
				}				
				Map<String,Object> file = uploadFormFile(formFile, target);
				list.add(file);
			}
		}
		return list;
	}
	
	public static String getExtension(String fileName){
		if(StringUtils.isEmpty(fileName))return "";		
		return StringUtils.substring(fileName, StringUtils.lastIndexOf(fileName, ".")+1).toLowerCase();
	}
	
	public static String generateKey(int seq) {
		DecimalFormat decimalFormat = new DecimalFormat("000");
		return String.valueOf(System.currentTimeMillis()) + decimalFormat.format(seq);
	}
	
	private static Map<String, Object> uploadFormFile(MultipartFile formFile, String target) {
		
		InputStream stream = null;
		String orgFileName = formFile.getOriginalFilename();
		String fileExt = getExtension(orgFileName);
		String year = getDate(new Date(), "yyyy");
		String mmdd = getDate(new Date(), "MMdd");
		String yyyymmdd = getDate(new Date(), "yyyyMMdd");
		String saveFileName = yyyymmdd + generateKey(fileNameKeyIncrement++) + "." + fileExt;
		String realPath = target + sep + year + sep + mmdd;		
		realPath = realPath.replaceAll("//", "/");
		
		try {
			// 디렉토리 생성
			File subdir = new File(realPath);
			checkDirAndCreate(subdir);
			
			stream = formFile.getInputStream();
			
			System.out.println("uploadFormFile uploadFileInfo >>>>> "+realPath+sep+saveFileName);
			
			OutputStream bos = new FileOutputStream(realPath+sep+saveFileName);
			int bytesRead = 0;
			byte[] buffer = new byte[BUFFER_SIZE];
			while ((bytesRead = stream.read(buffer, 0, BUFFER_SIZE)) != -1) {
				bos.write(buffer, 0, bytesRead);
			}
			bos.close();
			stream.close();
		} catch (FileNotFoundException e) {			
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		// 이미지 파일인 경우 썸네일만들기
		if (((fileExt.equalsIgnoreCase("gif") ||
				fileExt.equalsIgnoreCase("jpeg") ||
				fileExt.equalsIgnoreCase("jpg") ||
				fileExt.equalsIgnoreCase("bmp") ||
				fileExt.equalsIgnoreCase("png")))) {
			// 섬네일이미지
			uploadTransThumbFile(target, year+sep+mmdd, saveFileName, image_thumb_width, file_thumb_dir);
			// 본문이미지
			uploadTransThumbFile(target, year+sep+mmdd, saveFileName, image_cont_width, file_cont_dir);	
			
		}
				
		// 파일정보셋팅
		Map<String,Object> file = new HashMap<String, Object>();
		file.put("real_file_name", formFile.getOriginalFilename());
		file.put("save_file_name", saveFileName);
		file.put("file_path", "/"+year+"/"+mmdd+"/");
		file.put("file_ext", fileExt);
		file.put("file_size", formFile.getSize());
		file.put("image_width_size", org_image_width);
		file.put("image_height_size", org_image_height);
		return file;
	}
	
	// 썸네일이미지생성
	public static boolean uploadTransThumbFile(String target, String realPath, String saveFileName, int thumbWidthLimit, String transPath) {		
		boolean success = false;
		try {
			
			File dir = new File(target+sep+transPath+sep+realPath);
			if (!dir.exists()) {
				dir.mkdirs();
			}
			
			int thumbWidth = 0;
			int thumbHeight = 0;
			int orgWidth = 0;
			int width=0;
			int height=0;
			
			BufferedInputStream bufferedis = null;
			FileInputStream fileis = null;
			BufferedImage bufferedimg = null;
			
			try {
				fileis = new FileInputStream(target+sep+realPath+sep+saveFileName);
				bufferedis = new BufferedInputStream(fileis);
				bufferedimg = ImageIO.read(bufferedis);
					
				width = bufferedimg == null? 0 : bufferedimg.getWidth();
				height = bufferedimg == null? 0 : bufferedimg.getHeight();
				orgWidth = width;					
			
				org_image_width = width;
				org_image_height = height;
				
				if(width > thumbWidthLimit) { 
					thumbWidth = thumbWidthLimit;					
				} else {
					thumbWidth = width;					
				}			
				
				if (orgWidth > thumbWidth && thumbWidth > 0) {					
					imgscalr(bufferedimg, target+sep+transPath+sep+realPath+sep+saveFileName, getExtension(saveFileName), thumbWidth, thumbHeight);
				} else {
					copyFile(target+sep+realPath+sep+saveFileName, target+sep+transPath+sep+realPath+sep+saveFileName);
				}
				
			} catch (Exception e) {
				if(log.isDebugEnabled())log.debug("Error at alice.UploadServlet caculate image");
				e.printStackTrace();
			} finally {
				if (fileis != null) try { fileis.close(); } catch (Exception e){}
				if (bufferedis != null) try { bufferedis.close(); } catch (Exception e){}
			}		
			
			success = true;
		} catch (Exception e) {
			e.printStackTrace();
			if(log.isDebugEnabled())log.debug("Error at alice.UploadServlet make thumbnail");
		}

		return success;
	}
		
	/**
	 * 썸네일생성(imgscalr 사용)
	 * */	
	protected static boolean imgscalr(BufferedImage srcImage, String destPath, String imageFormat, int destWidth, int destHeight){
	
		boolean result = true;
		OutputStream out = null;
		try {
			//System.setProperty(Scalr.DEBUG_PROPERTY_NAME, "true");
			out = new FileOutputStream(destPath);
			ImageIO.write(Scalr.resize(srcImage, Method.ULTRA_QUALITY, Mode.FIT_TO_WIDTH, destWidth ),imageFormat, out);
			out.close();
			out = null;
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return result;
	}
	
	/**
	 * 파일복사
	 * */
	private static void copyFile(String srFile, String dtFile) {
		InputStream in = null;
		OutputStream out = null;
		
		try {
		   
			File f1 = new File(srFile);
			File f2 = new File(dtFile);
			in = new FileInputStream(f1);
			out = new FileOutputStream(f2);

			byte[] buf = new byte[BUFFER_SIZE];
			int len;
			while ((len = in.read(buf)) > 0){
				out.write(buf, 0, len);
			}		
			
			//if(log.isDebugEnabled())log.debug("File copied.");
		} catch(FileNotFoundException ex) {
			System.out.println(ex.getMessage() + " in the specified directory.");						   		   
		} catch(IOException e) {
			System.out.println(e.getMessage());			  
		} finally {
			if (in != null) try { in.close(); } catch (Exception e){}
			if (out != null) try { out.close(); } catch (Exception e){}
		}
	}
	
	public static void download(HttpServletRequest request, HttpServletResponse response, String fileUploadPath, String saveFileName, String realFileName) throws ServletException, IOException {
			
		String filePath = download(request, fileUploadPath, saveFileName);
		filePath = filePath.replaceAll("//", "/");
		
		System.out.println("downLoadFile >>>> "+filePath);
		
		download(request, response, new File(filePath), realFileName);
	}
	
	public static String download(HttpServletRequest request, String fileUploadPath, String saveFileName) throws ServletException, IOException {						
		String target 	= getTarget(request);
		/*String yyyy = saveFileName.substring(0,4);
		String mmdd = saveFileName.substring(4,8);
		String filePath = yyyy+sep+mmdd;*/
		return target+fileUploadPath+saveFileName;
	}

	public static void download(HttpServletRequest request,
			HttpServletResponse response, File file, String fileName)
			throws ServletException, IOException {
		
		if (file == null || !file.exists() || file.length() <= 0 || file.isDirectory()) {			
			
			throw new IOException("Not Found File Data..");						
		}

		InputStream is = null;

		try {
			is = new FileInputStream(file);
			
			//파일다운로드
			download(request, response, is, fileName, file.length());			
			
		} finally {
			try {
				is.close();
			} catch (Exception ex) {

			}
		}
	}
	
	public static void downloadImg(HttpServletRequest request,
			HttpServletResponse response, InputStream is, String filename,
			long filesize) throws ServletException,
			IOException {
		
		byte[] buffer = new byte[BUFFER_SIZE];
		String mime = "";		
		if (filename.indexOf("gif") != -1)
		{
			mime = "image/gif";
		}else if (filename.indexOf("jpg") != -1)
		{
			mime = "image/jpeg";
		}else if (filename.indexOf("swf") != -1)
		{
			mime = "Application/x-shockwave-flash";
		}		

		response.setHeader("Pragma", "no-cache"); // HTTP 1.0
		response.setDateHeader("Expires", 0);
		response.setHeader("Cache-Control", "No-cache"); // HTTP 1.1
		response.setContentType(mime + "; charset=" + CHARSET);		
		
		// 파일 사이즈가 정확하지 않을때는 아예 지정하지 않는다.
		if (filesize > 0) {
			response.setHeader("Content-Length", "" + filesize);
		}

		BufferedInputStream fin = null;
		BufferedOutputStream outs = null;

		try {
			fin = new BufferedInputStream(is);
			outs = new BufferedOutputStream(response.getOutputStream());
			int read = 0;

			while ((read = fin.read(buffer)) != -1) {
				outs.write(buffer, 0, read);
			}
		} finally {
			try {
				outs.close();
			} catch (Exception ex1) {
			}

			try {
				fin.close();
			} catch (Exception ex2) {

			}
		}
	}
	
	public static void download(HttpServletRequest request,
			HttpServletResponse response, InputStream is, String filename,
			long filesize) throws ServletException,
			IOException {
		
		byte[] buffer = new byte[BUFFER_SIZE];
		String mime = "application/octet-stream";

		response.setContentType(mime + "; charset=" + CHARSET);	
		
		// 아래 부분에서 euc-kr 을 utf-8 로 바꾸거나 URLEncoding을 안하거나 등의 테스트를
		// 해서 한글이 정상적으로 다운로드 되는 것으로 지정한다.
		String userAgent = request.getHeader("User-Agent");		
		if (userAgent.indexOf("MSIE 5.5") > -1) { // MS IE 5.5 이하
			response.setHeader("Content-Disposition", "filename=" + URLEncoder.encode(filename, "UTF-8") + ";");
		} else if (userAgent.indexOf("MSIE") > -1) { // MS IE (보통은 6.x 이상 가정)
			filename=filename.replaceAll(" ","plmkijnhyrtfsdwerg578jh80jhrt56ghb");
			String filename2 = java.net.URLEncoder.encode(filename, "UTF-8");
			filename2=filename2.replaceAll("plmkijnhyrtfsdwerg578jh80jhrt56ghb"," ");
			response.setHeader("Content-Disposition", "attachment; filename=" + filename2 + ";");
		} else { // 모질라 || 오페라 || 크롬
			filename=filename.replaceAll(" ","plmkijnhyrtfsdwerg578jh80jhrt56ghb").replaceAll(",", ".");
			String filename2 = new String(filename.getBytes("KSC5601"), "8859_1");
			filename2=filename2.replaceAll("plmkijnhyrtfsdwerg578jh80jhrt56ghb"," ");
			response.setHeader("Content-Disposition", "attachment; filename=" + filename2 + ";");
			//response.setHeader("Content-Disposition", "attachment; filename=" + new String(filename.getBytes(CHARSET), "latin1") + ";");
		}	
		
		// 파일 사이즈가 정확하지 않을때는 아예 지정하지 않는다.
		if (filesize > 0) {
			response.setHeader("Content-Length", "" + filesize);
		}

		BufferedInputStream fin = null;
		BufferedOutputStream outs = null;

		try {
			fin = new BufferedInputStream(is);
			outs = new BufferedOutputStream(response.getOutputStream());
			int read = 0;

			while ((read = fin.read(buffer)) != -1) {
				outs.write(buffer, 0, read);
			}
		} finally {
			try {
				outs.close();
			} catch (Exception ex1) {
			}

			try {
				fin.close();
			} catch (Exception ex2) {

			}
		} // end of try/catch
	}
	
	/**
	 * 날짜를 해당하는 포맷에 맞게 가져온다.
	 * 
	 * @param date
	 * @param format
	 * @return
	 */
	public static String getDate(java.util.Date date, String format) {
		if (date == null || format == null)
			return "";

		SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.KOREA);
		return sdf.format(date);
	}
	
	/**
	 * 디렉토리 생성
	 * 
	 * @param dir
	 */
	public static void checkDirAndCreate(File dir) {
		if (!dir.exists())
			dir.mkdirs();
	}
	
	/**
	 * 파일삭제
	 * 
	 * @param dir
	 */
	public static void deleteFile(String deleteFile) {
		File dir = new File(deleteFile);		
		if (dir.exists())
			dir.delete();
	}
	
	/**
	 * 파일삭제
	 * 
	 * @param dir
	 */
	public static void removeDIR(String source) {		 
		try {
			File dir = new File(source);
			if (dir.exists()){
				File[] listFile = new File(source).listFiles();
				if(listFile.length > 0){
					for(int i = 0 ; i < listFile.length ; i++){
						if(listFile[i].isFile()){
							listFile[i].delete(); 
						}else{
							removeDIR(listFile[i].getPath());
						}
						listFile[i].delete();
					}
				}	
			}			
		} catch(Exception e) {
			e.printStackTrace();			 
		}
	}
}
