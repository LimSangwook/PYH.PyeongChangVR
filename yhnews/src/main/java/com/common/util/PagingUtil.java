package com.common.util;

import org.apache.commons.lang.StringUtils;

import com.common.base.BaseForm;

public class PagingUtil {
		
	/**
	 * Page Navigation 
	 * 
	 * @param entity
	 * @param linkString
	 * @return
	 */
	public static String printPageNavi(BaseForm command, String linkString) {
		return printPageNavi(command.getPage(), command.getTotal_count(), command.getPageSize(), command.getPageBlockSize(), linkString, null);
	}
	
	/**
	 * Page Navigation 
	 * 
	 * @param entity
	 * @param linkString
	 * @return
	 */
	public static String printPageNavi(BaseForm command, String linkString, String siteType) {
		return printPageNavi(command.getPage(), command.getTotal_count(), command.getPageSize(), command.getPageBlockSize(), linkString, siteType);
	}
	
	/**
	 * Page Navigation 
	 * 
	 * @param entity
	 * @param linkString
	 * @return
	 */
	public static String printPageNaviAjax(BaseForm command, String linkString) {
		return printPageNaviAjax(command.getPage(), command.getTotal_count(), command.getPageSize(), command.getPageBlockSize(), linkString);
	}

	public static int getTotalPageCnt(BaseForm command){
		return (command.getTotal_count() / command.getPageSize()) + (command.getTotal_count() % command.getPageSize() > 0 ? 1 : 0);
	}
	/**
	 * Page Navigation 페이지 번호 프린트
	 * 
	 * @param page_no
	 *            페이지 번호
	 * @param tot_cnt
	 *            목록의 총 개수
	 * @param pageSize
	 *            한 페이지당 보여줄 목록의 수
	 * @param blockSize
	 *            한 화면에 보여줄 페이지 번호들의 수
	 * @param paramString
	 *            페이지 파라미터
	 * @param imageType
	 *            페이징 이미지 타입
	 * @return HTML code
	 */
	public static String printPageNavi(int pageNo, int totCnt, int pageSize, int blockSize, String linkString, String siteType) {
			
		int totPageCnt = (totCnt / pageSize) + (totCnt % pageSize > 0 ? 1 : 0);
		int totBlockCnt = (totPageCnt / blockSize) + (totPageCnt % blockSize > 0 ? 1 : 0);
		int blockNo = (pageNo / blockSize) + (pageNo % blockSize > 0 ? 1 : 0);
		int startPageNo = (blockNo - 1) * blockSize + 1;
		int endPageNo = blockNo * blockSize;

		if (endPageNo > totPageCnt)
			endPageNo = totPageCnt;

		int prevBlockPageNo = (blockNo - 1) * blockSize;
		int nextBlockPageNo = blockNo * blockSize + 1;

		//StringBuffer strHTML = new StringBuffer();
		StringBuilder strHTML = new StringBuilder(1024);
						
		linkString = StringUtils.isBlank(linkString) ? "?" : linkString+"&amp;";
		//linkString = StringUtils.isBlank(linkString) ? "?" : linkString+"&";
		// 첫페이지
		/*<ul class="pagenation">
		<a href="#" class="ico ico_arrow_prev">이전</a>
		<a href="#">1</a>
		<span>2</span>
		<a href="#">3</a>
		<a href="#">4</a>
		<a href="#">5</a>
		<a href="#">6</a>
		<a href="#">7</a>
		<a href="#">8</a>
		<a href="#">9</a>
		<a href="#">10</a>
		<a href="#" class="ico ico_arrow_next">다음</a>
	</ul>*/
		if(StringUtils.equals("front", siteType)){
			if (totPageCnt > 1) {
				strHTML.append("<a href='"+linkString+"page=1' class='ico ico_arrow_first'>처음</a>\n");
			} else {
				strHTML.append("<a href='javascript:;' class='ico ico_arrow_first'>이전</a>\n");
			}	
		}
		
		// 이전페이지
		if (blockNo > 1) {
			strHTML.append("<a href='"+linkString+"page="+prevBlockPageNo+"' class='ico ico_arrow_prev'>이전</a>\n");
		} else {
			strHTML.append("<a href='javascript:;' class='ico ico_arrow_prev'>이전</a>\n");
		}
		
		for (int i = startPageNo; i <= endPageNo; i++) {			
			if (i == pageNo) {
				strHTML.append("<span>" + i + "</span>\n");
			}else{								
				strHTML.append("<a href='"+linkString+"page="+i+"'>" + i + "</a>\n");
			}
		}
		if (totCnt == 0) {
			strHTML.append("<span>1</span>\n");
		}
		
		if (blockNo < totBlockCnt) {
			strHTML.append("<a href='"+linkString+"page="+nextBlockPageNo+"' class='ico ico_arrow_next'>다음</a>\n");
		} else {
			strHTML.append("<a href='javascript:;' class='ico ico_arrow_next'>다음</a>\n");
		}	
		
		if(StringUtils.equals("front", siteType)){
			if (totPageCnt > 1) {
				strHTML.append("<a href='"+linkString+"page="+totPageCnt+"' class='ico ico_arrow_last'>마지막</a>\n");
			} else {
				strHTML.append("<a href='javascript:;' class='ico ico_arrow_last'>마지막</a>\n");
			}
		}
		
		return strHTML.toString();
	}
	
	/**
	 * Page Navigation 페이지 번호 프린트
	 * 
	 * @param page_no
	 *            페이지 번호
	 * @param tot_cnt
	 *            목록의 총 개수
	 * @param pageSize
	 *            한 페이지당 보여줄 목록의 수
	 * @param blockSize
	 *            한 화면에 보여줄 페이지 번호들의 수
	 * @param paramString
	 *            페이지 파라미터
	 * @param imageType
	 *            페이징 이미지 타입
	 * @return HTML code
	 */
	public static String printPageNaviAjax(int pageNo, int totCnt, int pageSize, int blockSize, String linkString) {
			
		int totPageCnt = (totCnt / pageSize) + (totCnt % pageSize > 0 ? 1 : 0);
		int totBlockCnt = (totPageCnt / blockSize) + (totPageCnt % blockSize > 0 ? 1 : 0);
		int blockNo = (pageNo / blockSize) + (pageNo % blockSize > 0 ? 1 : 0);
		int startPageNo = (blockNo - 1) * blockSize + 1;
		int endPageNo = blockNo * blockSize;

		if (endPageNo > totPageCnt)
			endPageNo = totPageCnt;

		int prevBlockPageNo = (blockNo - 1) * blockSize;
		int nextBlockPageNo = blockNo * blockSize + 1;

		StringBuilder strHTML = new StringBuilder(1024);
						
		linkString = StringUtils.isBlank(linkString) ? "?" : linkString+"&amp;";
		//linkString = StringUtils.isBlank(linkString) ? "?" : linkString+"&";
		// 첫페이지
		/*
		 * <li><a href="#">&laquo;</a></li>
           <li class="active"><a href="#">1</a></li>
           <li><a href="#">2</a></li>
           <li><a href="#">3</a></li>
           <li><a href="#">&raquo;</a></li>
		 * */
		if (totPageCnt > 1) {
			strHTML.append("<li><a href='javascript:;' onclick='fnPage(1)'>&lt;&lt;</a></li>\n ");
		} else {
			strHTML.append("<li><a href='javascript:;'>&lt;&lt;</a></li>\n ");
		}
		// 이전페이지
		if (blockNo > 1) {
			strHTML.append("<li><a href='javascript:;' onclick='fnPage("+prevBlockPageNo+")'>&lt;</a></li>\n");
		} else {
			strHTML.append("<li><a href='javascript:;'>&lt;</a></li>\n");
		}
		
		for (int i = startPageNo; i <= endPageNo; i++) {			
			if (i == pageNo) {
				strHTML.append("<li class='active'><a href='javascript:;'>" + i + "</a>\n");
			}else{								
				strHTML.append("<li><a href='javascript:;' onclick='fnPage("+i+")'>" + i + "</a>\n");
			}
		}
		if (totCnt == 0) {
			strHTML.append("<li class='active'><a href='javascript:;'>1</a></li>\n");
		}
		
		if (blockNo < totBlockCnt) {
			strHTML.append("<li><a href='javascript:;' onclick='fnPage("+nextBlockPageNo+")'>&gt;</a></li>\n");
		} else {
			strHTML.append("<li><a href='javascript:;'>&gt;</a></li>\n");
		}	

		if (totPageCnt > 1) {
			strHTML.append("<li><a href='javascript:;' onclick='fnPage("+totPageCnt+")'>&gt;&gt;</a></li>\n");
		} else {
			strHTML.append("<li><a href='javascript:;'>&gt;&gt;</a></li>\n");
		}
		
		return strHTML.toString();
	}
}