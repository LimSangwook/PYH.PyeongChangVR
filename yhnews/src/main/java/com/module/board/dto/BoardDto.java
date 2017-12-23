package com.module.board.dto;

import java.util.Date;

import org.apache.commons.lang.StringUtils;

import com.common.base.BaseForm;
import com.common.util.CommonUtils;

public class BoardDto extends BaseForm{
	
	private static final long serialVersionUID = -2413685111342910506L;

	/* 게시글 고유번호 */
	private String board_key;
	/* 게시판 아이디 */
	private String board_id;
	/* 카테고리 아이디 */
	private String cate_id;
	/* 부모게시글 번호 */
	private String parent_board_key;
	/* 게시글 그룹번호 */
	private int order_group;
	/* 게시글 깊이순서 */
	private int order_step;
	/* 게시글 정렬순서 */
	private int order_level;
	/* 제목 */
	private String title;
	/* 내용 */
	private String content;	
	/* 내부공지글 여부 */
	private String is_notice;
	/* 비밀글여부 */
	private String is_secret;
	/* 비밀번호 */
	private String passwd;
	/* 조회수 */
	private int read_cnt;
	/* 댓글수 */
	private int comment_cnt;
	/* 첨부파일수 */
	private int attach_cnt;
	/* 대표이미지 */
	private String master_image;
	/* 아이피 */
	private String ip;
	/* 상태 공개:Y, 비공개:N, 삭제:D */
	private String status;
	/* 작성자 아이디 */
	private String reg_id;
	/* 작성자명 */
	private String reg_name;
	/* 작성일시 */
	private Date reg_date;
	/* 수정일시 */
	private Date mod_date;
	/* 새글여부 */
	private String is_new_post;
	/* 이전글 */
	private BoardDto prevBoard;
	/* 다음글 */
	private BoardDto nextBoard;
		
	
	private int order_level_find;	
	
	// 본문글
	private String req_content;	
	// 시작일
	private String start_day;
	// 종료일
	private String end_day;
	// 연락처
	private String phone;
	private String phone1;
	private String phone2;
	private String phone3;
	// 이메일
	private String email;
	private String email_id;
	private String email_domain;
	// 작성자명
	private String writer;
	// 회사명
	private String company_name;
	
	private String link_address;
	private String summary;
	// 영상구분 VR : 1, 유튜브 : 2
	private String video_type;
	
	/**
	 * 게시판 특성에 따라 확장하여 사용
	 * */
	// 확장필드1
	private String add_field1;
	// 확장필드2
	private String add_field2;
	// 확장필드3
	private String add_field3;
			
	private String board_name;	
	
	public String getBoard_name() {
		return board_name;
	}
	public void setBoard_name(String board_name) {
		this.board_name = board_name;
	}
	public String getAdd_field1() {
		return add_field1;
	}
	public void setAdd_field1(String add_field1) {
		this.add_field1 = add_field1;
	}
	public String getAdd_field2() {
		return add_field2;
	}
	public void setAdd_field2(String add_field2) {
		this.add_field2 = add_field2;
	}
	public String getAdd_field3() {
		return add_field3;
	}
	public void setAdd_field3(String add_field3) {
		this.add_field3 = add_field3;
	}
	public String getSecretWriter(){
		return StringUtils.isBlank(reg_name) || !StringUtils.equals("Y", is_secret) ? reg_name : StringUtils.substring(reg_name, 0,1)+"***";
	}		
	public String getVideo_type() {
		return video_type;
	}
	public void setVideo_type(String video_type) {
		this.video_type = video_type;
	}
	public String getLink_address() {
		return link_address;
	}
	public void setLink_address(String link_address) {
		this.link_address = link_address;
	}
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	public String getDisplayRegDate(){
		return CommonUtils.dateToString(reg_date, "yyyy-MM-dd");
	}	
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getCompany_name() {
		return company_name;
	}
	public void setCompany_name(String company_name) {
		this.company_name = company_name;
	}
	public String[] getEmailArr(){
		return StringUtils.split(email, "@");
	}
	public String[] getPhoneArr(){
		return StringUtils.split(phone, "-");
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getPhone1() {
		return phone1;
	}
	public void setPhone1(String phone1) {
		this.phone1 = phone1;
	}
	public String getPhone2() {
		return phone2;
	}
	public void setPhone2(String phone2) {
		this.phone2 = phone2;
	}
	public String getPhone3() {
		return phone3;
	}
	public void setPhone3(String phone3) {
		this.phone3 = phone3;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getEmail_id() {
		return email_id;
	}
	public void setEmail_id(String email_id) {
		this.email_id = email_id;
	}
	public String getEmail_domain() {
		return email_domain;
	}
	public void setEmail_domain(String email_domain) {
		this.email_domain = email_domain;
	}
	public String getStart_day() {
		return start_day;
	}
	public void setStart_day(String start_day) {
		this.start_day = start_day;
	}
	public String getEnd_day() {
		return end_day;
	}
	public void setEnd_day(String end_day) {
		this.end_day = end_day;
	}
	public String getReq_content() {
		return req_content;
	}
	public void setReq_content(String req_content) {
		this.req_content = req_content;
	}
	public Date getMod_date() {
		return mod_date;
	}
	public void setMod_date(Date mod_date) {
		this.mod_date = mod_date;
	}
	public String getIs_new_post() {
		return is_new_post;
	}
	public void setIs_new_post(String is_new_post) {
		this.is_new_post = is_new_post;
	}	
	public int getOrder_level_find() {
		return order_level_find;
	}
	public void setOrder_level_find(int order_level_find) {
		this.order_level_find = order_level_find;
	}
	public BoardDto getPrevBoard() {
		return prevBoard;
	}
	public void setPrevBoard(BoardDto prevBoard) {
		this.prevBoard = prevBoard;
	}
	public BoardDto getNextBoard() {
		return nextBoard;
	}
	public void setNextBoard(BoardDto nextBoard) {
		this.nextBoard = nextBoard;
	}
	private String cate_name;	
	
	public String getCate_name() {
		return cate_name;
	}
	public void setCate_name(String cate_name) {
		this.cate_name = cate_name;
	}
	public String getBoard_key() {
		return board_key;
	}
	public void setBoard_key(String board_key) {
		this.board_key = board_key;
	}
	public String getBoard_id() {
		return board_id;
	}
	public void setBoard_id(String board_id) {
		this.board_id = board_id;
	}
	public String getCate_id() {
		return cate_id;
	}
	public void setCate_id(String cate_id) {
		this.cate_id = cate_id;
	}	
	
	public String getParent_board_key() {
		return parent_board_key;
	}
	public void setParent_board_key(String parent_board_key) {
		this.parent_board_key = parent_board_key;
	}
	public int getOrder_group() {
		return order_group;
	}
	public void setOrder_group(int order_group) {
		this.order_group = order_group;
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
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}	
	public String getIs_notice() {
		return StringUtils.defaultIfEmpty(is_notice, "N");		
	}
	public void setIs_notice(String is_notice) {
		this.is_notice = is_notice;
	}
	public String getIs_secret() {
		return StringUtils.defaultIfEmpty(is_secret, "N");
	}
	public void setIs_secret(String is_secret) {
		this.is_secret = is_secret;
	}
	public String getPasswd() {
		return passwd;
	}
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	public int getRead_cnt() {
		return read_cnt;
	}
	public void setRead_cnt(int read_cnt) {
		this.read_cnt = read_cnt;
	}
	public int getComment_cnt() {
		return comment_cnt;
	}
	public void setComment_cnt(int comment_cnt) {
		this.comment_cnt = comment_cnt;
	}
	public int getAttach_cnt() {
		return attach_cnt;
	}
	public void setAttach_cnt(int attach_cnt) {
		this.attach_cnt = attach_cnt;
	}
	public String getMaster_image() {
		return master_image;
	}
	public void setMaster_image(String master_image) {
		this.master_image = master_image;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getReg_id() {
		return reg_id;
	}
	public void setReg_id(String reg_id) {
		this.reg_id = reg_id;
	}
	public String getReg_name() {
		return reg_name;
	}
	public void setReg_name(String reg_name) {
		this.reg_name = reg_name;
	}
	public Date getReg_date() {
		return reg_date;
	}
	public void setReg_date(Date reg_date) {
		this.reg_date = reg_date;
	}
}