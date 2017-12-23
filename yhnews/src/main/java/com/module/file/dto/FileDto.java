package com.module.file.dto;

import java.io.Serializable;
import java.util.Date;

public class FileDto implements Serializable {

	private static final long serialVersionUID = -2840934044562480179L;
	
	/* 첨부파일PK */
	private String file_key;		
	/* 게시글일련번호 */
	private String board_key;		
	/* 실제파일명 */
	private String real_file_name;	
	/* 저장파일명 */
	private String save_file_name;	
	/* 파일사이즈 */
	private long file_size;			
	/* 파일확장자 */
	private String file_ext;			
	/* 파일경로 */
	private String file_path;		
	/* 이미지파일가로사이즈 */
	private int image_width_size;
	/* 이미지파일세로사이즈 */
	private int image_height_size;	
	/* 정렬순서 */
	private int order_level;				
	/* 파일다운로드수 */
	private int file_down_cnt;			
	/* 상태 */	
	private String status;
	/* 등록일자 */
	private Date reg_date;
	
	/* 가맹점번호 */
	private String franchise_key;	
	/* 상품코드 */
	private String goods_code;	
	/* 서비스 언어코드 */
	private String lang_code;
	
	
	public String getLang_code() {
		return lang_code;
	}
	public void setLang_code(String lang_code) {
		this.lang_code = lang_code;
	}
	public String getGoods_code() {
		return goods_code;
	}
	public void setGoods_code(String goods_code) {
		this.goods_code = goods_code;
	}
	public String getFranchise_key() {
		return franchise_key;
	}
	public void setFranchise_key(String franchise_key) {
		this.franchise_key = franchise_key;
	}
	public String getFile_key() {
		return file_key;
	}
	public void setFile_key(String file_key) {
		this.file_key = file_key;
	}
	
	public String getBoard_key() {
		return board_key;
	}
	public void setBoard_key(String board_key) {
		this.board_key = board_key;
	}
	public String getReal_file_name() {
		return real_file_name;
	}
	public void setReal_file_name(String real_file_name) {
		this.real_file_name = real_file_name;
	}
	public String getSave_file_name() {
		return save_file_name;
	}
	public void setSave_file_name(String save_file_name) {
		this.save_file_name = save_file_name;
	}
	public long getFile_size() {
		return file_size;
	}
	public void setFile_size(long file_size) {
		this.file_size = file_size;
	}
	public String getFile_ext() {
		return file_ext;
	}
	public void setFile_ext(String file_ext) {
		this.file_ext = file_ext;
	}
	public String getFile_path() {
		return file_path;
	}
	public void setFile_path(String file_path) {
		this.file_path = file_path;
	}
	public int getImage_width_size() {
		return image_width_size;
	}
	public void setImage_width_size(int image_width_size) {
		this.image_width_size = image_width_size;
	}
	public int getImage_height_size() {
		return image_height_size;
	}
	public void setImage_height_size(int image_height_size) {
		this.image_height_size = image_height_size;
	}
	public int getOrder_level() {
		return order_level;
	}
	public void setOrder_level(int order_level) {
		this.order_level = order_level;
	}
	public int getFile_down_cnt() {
		return file_down_cnt;
	}
	public void setFile_down_cnt(int file_down_cnt) {
		this.file_down_cnt = file_down_cnt;
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
}