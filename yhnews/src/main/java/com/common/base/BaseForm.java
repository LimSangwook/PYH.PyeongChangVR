package com.common.base;

import java.util.ArrayList;
import java.util.Date;

import org.apache.commons.lang.StringUtils;

public class BaseForm extends BaseObject implements Cloneable {
			
	private static final long serialVersionUID = 9063371404357618966L;

	private String act;					// 처리액션	
	private String returnMessage;	// 리턴메시지	
	private ArrayList<String> remove;			// 삭제데이터	
	
	private int total_count;				// 총등록컨텐츠수	
	private int totalPage;				// 총페이지수
	private int page;					// 요청페이지
	private int pageSize;				// 페이지당 목록출력수
	private int pageBlockSize;			// 페이지당 페이지출력수
		
	private String order_column;		// 정렬필드
	private String order_type;			// 정렬구분	
	private Date start_date;			// 검색시작날짜
	private Date end_date;			// 검색종료날짜
	private String search_start_date;			// 검색시작날짜
	private String search_end_date;			// 검색종료날짜
	private String search_keyword;	// 검색단어
	private String search_column;	// 검색필드
	private String search_type;		// 검색구분
	private String search_type1;		// 검색구분
	private String search_type2;		// 검색구분
	private String ip;					// 아이피			
	private String photoFile;		// 사진파일정보
		
	private String token;				// token		
	private String referer;				//	referer
	private String returnUrl;			// 리턴페이지
	private String redirectUrl; 			// 재이동페이지
	
	private String search_year;
	private String search_month;
	
	/* 파일정보 */
	private String[] file;
	
	public String getSearch_year() {
		return search_year;
	}
	public void setSearch_year(String search_year) {
		this.search_year = search_year;
	}
	public String getSearch_month() {
		return search_month;
	}
	public void setSearch_month(String search_month) {
		this.search_month = search_month;
	}
	public String getRedirectUrl() {
		return redirectUrl;
	}
	public void setRedirectUrl(String redirectUrl) {
		this.redirectUrl = redirectUrl;
	}
	public String getReturnUrl() {
		return returnUrl;
	}
	public void setReturnUrl(String returnUrl) {
		this.returnUrl = returnUrl;
	}
	public String getReferer() {
		return referer;
	}
	public void setReferer(String referer) {
		this.referer = referer;
	}
	public String[] getFile() {
		return file;
	}
	public void setFile(String[] file) {
		this.file = file;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getPhotoFile() {
		return photoFile;
	}
	public void setPhotoFile(String photoFile) {
		this.photoFile = photoFile;
	}
	
	public int getFirstRowNum(){
		return (getPage() - 1) * getPageSize() + 1;
	}	
	public int getRecordRowNum(){
		return (getPage() - 1) * getPageSize();
	}
	public int getLastRowNum(){
		return getPage() * getPageSize();
	}
	public String getSearch_start_date() {
		return search_start_date;
	}
	public void setSearch_start_date(String search_start_date) {
		this.search_start_date = search_start_date;
	}
	public String getSearch_end_date() {
		return search_end_date;
	}
	public void setSearch_end_date(String search_end_date) {
		this.search_end_date = search_end_date;
	}
	public void setStart_date(Date start_date) {
		this.start_date = start_date;
	}
	public Date getStart_date() {
		return start_date;
	}
	public void setEnd_date(Date end_date) {
		this.end_date = end_date;
	}
	public Date getEnd_date() {
		return end_date;
	}
	public String getSearch_keyword() {
		return utf8(search_keyword);
	}
	public void setSearch_keyword(String search_keyword) {
		this.search_keyword = search_keyword;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getReturnMessage() {
		return returnMessage;
	}
	public void setReturnMessage(String returnMessage) {
		this.returnMessage = returnMessage;
	}	
	public ArrayList<String> getRemove() {
		return remove;
	}
	public void setRemove(ArrayList<String> remove) {
		this.remove = remove;
	}
	public String getAct() {
		return StringUtils.isBlank(act)?"insert":act;
	}
	public void setAct(String act) {
		this.act = act;
	}	
	public int getTotal_count() {
		return total_count;
	}
	public void setTotal_count(int total_count) {
		this.total_count = total_count;
	}
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	public int getPage() {
		return (this.page==0) ? 1 : this.page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getPageSize() {
		return (pageSize == 0 ) ? 10 : pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public void setPageBlockSize(int pageBlockSize) {
		this.pageBlockSize = pageBlockSize;
	}	
	public int getPageBlockSize() {
		return (pageBlockSize == 0 ) ? 5 : pageBlockSize;
	}	
	public String getSearch_column() {
		return search_column;
	}
	public void setSearch_column(String search_column) {
		this.search_column = search_column;
	}
	public String getSearch_type() {
		return search_type;
	}
	public void setSearch_type(String search_type) {
		this.search_type = search_type;
	}
	public String getSearch_type1() {
		return search_type1;
	}
	public void setSearch_type1(String search_type1) {
		this.search_type1 = search_type1;
	}
	public String getSearch_type2() {
		return search_type2;
	}
	public void setSearch_type2(String search_type2) {
		this.search_type2 = search_type2;
	}	
	public String getOrder_column() {		
		return (StringUtils.isBlank(order_column))?"1":this.order_column;
	}
	public void setOrder_column(String order_column) {
		this.order_column = order_column;
	}
	public String getOrder_type() {
		return (StringUtils.isBlank(order_type))?"D":this.order_type;
	}
	public void setOrder_type(String order_type) {
		this.order_type = order_type;
	}	
	public String getDb_search_keyword() {
		if(StringUtils.isBlank(getSearch_keyword())) {
			return "";
		}
		return "%"+getSearch_keyword()+"%";
	}
	public Object clone() {
		Object obj = null;
		try {
			obj = super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return obj;
	}	
	
	/**
	 * 인코딩 변경 8859_1 -> UTF-8
	 * @param str
	 * @return
	 */
	public String utf8(String str){
		if(str==null) return null;
		try {
			return new String(str.getBytes("8859_1"),"UTF-8");
		} catch(Exception e) {
			return null;
		}
	}
}