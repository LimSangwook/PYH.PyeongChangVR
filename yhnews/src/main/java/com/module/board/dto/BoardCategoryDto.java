package com.module.board.dto;

import java.io.Serializable;

public class BoardCategoryDto implements Serializable{
	
	private static final long serialVersionUID = 8921689428407175182L;

	/** 카테고리 아이디 */
	private String cate_id;
	/** 카테고리명 */
	private String cate_name;
	/** 게시판 아이디 */
	private String board_id;
	/** 정렬순서 */
	private String order_level;
	/** 상태 */
	private String status;
	
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
	public String getBoard_id() {
		return board_id;
	}
	public void setBoard_id(String board_id) {
		this.board_id = board_id;
	}	
	public String getOrder_level() {
		return order_level;
	}
	public void setOrder_level(String order_level) {
		this.order_level = order_level;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}	
}