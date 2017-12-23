package com.module.board.dto;

import java.util.ArrayList;
import java.util.Date;

import com.common.base.BaseForm;

public class BoardConfigDto extends BaseForm{
	
	private static final long serialVersionUID = 5705270069462283326L;
	
	/* 게시판 아이디 */
	private String board_id;
	/* 게시판 이름 */
	private String board_name;
	/* 게시판 타입 */
	private String board_type;
	/* 내부 공지글 사용여부 */
	private String notice_use_yn;
	/* 댓글 사용여부 */
	private String comment_use_yn;
	/* 답글 사용여부 */
	private String reply_use_yn;
	/* 카테고리 사용여부 */
	private String cate_use_yn;
	/* 비밀글 사용여부  */
	private String secret_use_yn;
	/* 에디터 사용여부  */
	private String editor_use_yn;
	/* 한 페이지 목록 사이즈 */
	private int page_list_size;
	/* 페이지 블럭 사이즈 */
	private int page_block_size;
	/* 글작성 권한 */
	private int write_limit;
	/* 답글작성 권한 */
	private int reply_limit;
	/* 첨부파일 수량 제한 */
	private int file_count_limit;
	/* 첨부파일 업로드 사이즈 제한 */
	private int file_size_limit;
	/* 첨부파일 허용 확장자 제한 */
	private String file_ext_limit;
	/* 상태 */
	private String status;	
	/* 등록일자 */
	private Date reg_date;	
	/* 수정일자 */
	private Date mod_date;
		
	/* 카테고리 아이디 배열 */
	private ArrayList<String> category_id_arr;
	/* 카테고리 이름 배열 */
	private ArrayList<String> category_name_arr;
	/* 카테고리 순서 배열 */
	private ArrayList<String> category_sort_arr;
	
	/* 게시판 템플릿 정보 상단내용 / 하단내용*/
	private String top_content;
	private String bottom_content;
	
	private String cate_id;
	private String cate_name;		
		
	public String getTop_content() {
		return top_content;
	}
	public void setTop_content(String top_content) {
		this.top_content = top_content;
	}
	public String getBottom_content() {
		return bottom_content;
	}
	public void setBottom_content(String bottom_content) {
		this.bottom_content = bottom_content;
	}
	public ArrayList<String> getCategory_sort_arr() {
		return category_sort_arr;
	}
	public void setCategory_sort_arr(ArrayList<String> category_sort_arr) {
		this.category_sort_arr = category_sort_arr;
	}
	public String getCate_id() {
		return cate_id;
	}
	public void setCate_id(String cate_id) {
		this.cate_id = cate_id;
	}
	public String getCate_name() {
		return cate_name;
	}
	public void setCate_name(String cate_name) {
		this.cate_name = cate_name;
	}
	public String getEditor_use_yn() {
		return editor_use_yn;
	}
	public void setEditor_use_yn(String editor_use_yn) {
		this.editor_use_yn = editor_use_yn;
	}
	public ArrayList<String> getCategory_id_arr() {
		return category_id_arr;
	}
	public void setCategory_id_arr(ArrayList<String> category_id_arr) {
		this.category_id_arr = category_id_arr;
	}
	public ArrayList<String> getCategory_name_arr() {
		return category_name_arr;
	}
	public void setCategory_name_arr(ArrayList<String> category_name_arr) {
		this.category_name_arr = category_name_arr;
	}
	public String getBoard_name() {
		return board_name;
	}
	public void setBoard_name(String board_name) {
		this.board_name = board_name;
	}
	public String getBoard_id() {
		return board_id;
	}
	public void setBoard_id(String board_id) {
		this.board_id = board_id;
	}
	public String getBoard_type() {
		return board_type;
	}
	public void setBoard_type(String board_type) {
		this.board_type = board_type;
	}
	public String getNotice_use_yn() {
		return notice_use_yn;
	}
	public void setNotice_use_yn(String notice_use_yn) {
		this.notice_use_yn = notice_use_yn;
	}
	public String getComment_use_yn() {
		return comment_use_yn;
	}
	public void setComment_use_yn(String comment_use_yn) {
		this.comment_use_yn = comment_use_yn;
	}
	public String getReply_use_yn() {
		return reply_use_yn;
	}
	public void setReply_use_yn(String reply_use_yn) {
		this.reply_use_yn = reply_use_yn;
	}
	public String getCate_use_yn() {
		return cate_use_yn;
	}
	public void setCate_use_yn(String cate_use_yn) {
		this.cate_use_yn = cate_use_yn;
	}
	public String getSecret_use_yn() {
		return secret_use_yn;
	}
	public void setSecret_use_yn(String secret_use_yn) {
		this.secret_use_yn = secret_use_yn;
	}
	public int getPage_list_size() {
		return page_list_size;
	}
	public void setPage_list_size(int page_list_size) {
		this.page_list_size = page_list_size;
	}
	public int getPage_block_size() {
		return page_block_size;
	}
	public void setPage_block_size(int page_block_size) {
		this.page_block_size = page_block_size;
	}
	public int getWrite_limit() {
		return write_limit;
	}
	public void setWrite_limit(int write_limit) {
		this.write_limit = write_limit;
	}
	public int getReply_limit() {
		return reply_limit;
	}
	public void setReply_limit(int reply_limit) {
		this.reply_limit = reply_limit;
	}
	public int getFile_count_limit() {
		return file_count_limit;
	}
	public void setFile_count_limit(int file_count_limit) {
		this.file_count_limit = file_count_limit;
	}
	public int getFile_size_limit() {
		return file_size_limit;
	}
	public void setFile_size_limit(int file_size_limit) {
		this.file_size_limit = file_size_limit;
	}
	public String getFile_ext_limit() {
		return file_ext_limit;
	}
	public void setFile_ext_limit(String file_ext_limit) {
		this.file_ext_limit = file_ext_limit;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}	
	public Date getReg_date() {
		return reg_date;
	}
	public void setReg_date(Date reg_date) {
		this.reg_date = reg_date;
	}	
	public Date getMod_date() {
		return mod_date;
	}
	public void setMod_date(Date mod_date) {
		this.mod_date = mod_date;
	}
}