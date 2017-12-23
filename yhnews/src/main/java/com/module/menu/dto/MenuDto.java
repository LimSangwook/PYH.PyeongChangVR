package com.module.menu.dto;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.common.base.BaseForm;

public class MenuDto extends BaseForm{
			
	private static final long serialVersionUID = 1184758061190368614L;
	
	/* 메뉴코드 */
	private String menu_code;
	/* 메뉴명 */
	private String menu_name;
	/* 부모메뉴코드 */
	private String parent_menu_code;
	/* 메뉴위치코드 */
	private String menu_location_code;
	/* 메뉴타입 */
	private String menu_kind; // 메뉴:menu, HTML:html, 게시판:board
	/* 정렬깊이 */
	private int order_step;
	/* 정렬순서 */
	private int order_level;
	/* 연결주소 */
	private String link_address;
	/* 새창열림여부 */
	private String is_new_window;	
	/* 게시판 아이디 */
	private String board_id;
	/* 콘텐츠 아이디 */
	private String page_seq;	
	/* 노출시작일자 */
	private String start_show_date;
	/* 노출종료일자 */
	private String end_show_date;
	/* 시스템 구분 */
	private String system_gubun;
	/* 상태 (노출:Y, 노출안함:N, 삭제:D) */
	private String status;	
	/* 시스템 구분 */
	private String menu_auth_level; // 1|2 , 일반사용자|관리자
	/* 메뉴코드 배열 */
	private ArrayList<String> menu_code_arr;
	/* 메뉴스텝 배열 */
	private ArrayList<String> menu_step_arr;
	/* 메뉴접근권한 배열 */
	private ArrayList<String> menu_auth_arr;
	
	/* 게시판 이름 */
	private String board_name;
	/* 콘텐츠 제목 */
	private String html_title;
	
	/* 하위 메뉴 목록 */
	private List<MenuDto> children_menu_list = new ArrayList<>(); 
	
	private String depth1;
	private String depth2;
	private String depth3;	
	
	public String getMenuAuthJoin(){
		return StringUtils.join(menu_auth_arr,"|");
	}
	public ArrayList<String> getMenu_auth_arr() {
		return menu_auth_arr;
	}
	public void setMenu_auth_arr(ArrayList<String> menu_auth_arr) {
		this.menu_auth_arr = menu_auth_arr;
	}
	public String getMenu_auth_level() {
		return menu_auth_level;
	}
	public void setMenu_auth_level(String menu_auth_level) {
		this.menu_auth_level = menu_auth_level;
	}
	public String getHtml_title() {
		return html_title;
	}
	public void setHtml_title(String html_title) {
		this.html_title = html_title;
	}
	public String getPage_seq() {
		return page_seq;
	}
	public void setPage_seq(String page_seq) {
		this.page_seq = page_seq;
	}
	public String getStart_show_date() {
		return start_show_date;
	}
	public void setStart_show_date(String start_show_date) {
		this.start_show_date = start_show_date;
	}
	public String getEnd_show_date() {
		return end_show_date;
	}
	public void setEnd_show_date(String end_show_date) {
		this.end_show_date = end_show_date;
	}
	public String getMenu_kind() {
		return menu_kind;
	}
	public void setMenu_kind(String menu_kind) {
		this.menu_kind = menu_kind;
	}
	
	public String getMenu_location_code() {
		return menu_location_code;
	}
	public void setMenu_location_code(String menu_location_code) {
		this.menu_location_code = menu_location_code;
	}
	public List<MenuDto> getChildren_menu_list() {
		return children_menu_list;
	}
	public void setChildren_menu_list(List<MenuDto> children_menu_list) {
		this.children_menu_list = children_menu_list;
	}
	public ArrayList<String> getMenu_step_arr() {
		return menu_step_arr;
	}
	public void setMenu_step_arr(ArrayList<String> menu_step_arr) {
		this.menu_step_arr = menu_step_arr;
	}
	public String getBoard_name() {
		return board_name;
	}
	public void setBoard_name(String board_name) {
		this.board_name = board_name;
	}
	public ArrayList<String> getMenu_code_arr() {
		return menu_code_arr;
	}
	public void setMenu_code_arr(ArrayList<String> menu_code_arr) {
		this.menu_code_arr = menu_code_arr;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getLink_address() {
		return link_address;
	}
	public void setLink_address(String link_address) {
		this.link_address = link_address;
	}
	public String getIs_new_window() {
		return is_new_window;
	}
	public void setIs_new_window(String is_new_window) {
		this.is_new_window = is_new_window;
	}	
	public String getBoard_id() {
		return board_id;
	}
	public void setBoard_id(String board_id) {
		this.board_id = board_id;
	}	
	public String getMenu_code() {
		return menu_code;
	}
	public void setMenu_code(String menu_code) {
		this.menu_code = menu_code;
	}
	public String getMenu_name() {
		return menu_name;
	}
	public void setMenu_name(String menu_name) {
		this.menu_name = menu_name;
	}
	public String getParent_menu_code() {
		return parent_menu_code;
	}
	public void setParent_menu_code(String parent_menu_code) {
		this.parent_menu_code = parent_menu_code;
	}
	public int getOrder_step() {
		return order_step;
	}
	public void setOrder_step(int order_step) {
		this.order_step = order_step;
	}
	public int getOrder_level() {
		return order_level;
	}
	public void setOrder_level(int order_level) {
		this.order_level = order_level;
	}
	public String getDepth1() {
		return depth1;
	}
	public void setDepth1(String depth1) {
		this.depth1 = depth1;
	}
	public String getDepth2() {
		return depth2;
	}
	public void setDepth2(String depth2) {
		this.depth2 = depth2;
	}
	public String getDepth3() {
		return depth3;
	}
	public void setDepth3(String depth3) {
		this.depth3 = depth3;
	}
	public String getSystem_gubun() {
		return system_gubun;
	}
	public void setSystem_gubun(String system_gubun) {
		this.system_gubun = system_gubun;
	}
}
