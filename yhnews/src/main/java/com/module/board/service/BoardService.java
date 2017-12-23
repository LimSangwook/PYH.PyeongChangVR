package com.module.board.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.module.board.dto.BoardCategoryDto;
import com.module.board.dto.BoardConfigDto;
import com.module.board.dto.BoardDto;

public interface BoardService {

	//#################################################################################
	//	게시글 관리
	//#################################################################################
	
	/**
	 * 게시글 목록을 조회한다.
	 * 
	 * @return List<BoardDto>
	 * @param BoardDto <code>board</code>
	 * */
	public List<BoardDto> getBoardList(BoardDto board) throws Exception;
	
	/**
	 * 일정 게시글 목록을 조회한다.
	 * 
	 * @return List<BoardDto>
	 * @param BoardDto <code>board</code>
	 * */
	public List<BoardDto> getBoardScheduleList(BoardDto board) throws Exception;
	
	/**
	 * FAQ 본문내용 세팅
	 *
	 * @param BoardDto <code>board</code>
	 * */
	public void setBoardFaqContent(List<BoardDto> list) throws Exception;
		
	/**
	 * 게시글 상세보기
	 * 
	 * @return BoardDto
	 * @param BoardDto <code>board</code>
	 * */
	public BoardDto getBoardView(BoardDto board) throws Exception;
	
	/**
	 * 게시물 등록한다
	 *
	 * @param HttpServletRequest <code>request</code>
	 * @param BoardDto <code>board</code>
	 * */
	public void insertBoard(HttpServletRequest request, BoardDto board) throws Exception;
	
	/**
	 * 게시물 수정한다
	 *
	 * @param HttpServletRequest <code>request</code>
	 * @param BoardDto <code>board</code>
	 * */
	public void updateBoard(HttpServletRequest request, BoardDto board) throws Exception;
	
	/**
	 * 게시물 삭제한다
	 *
	 * @param HttpServletRequest <code>request</code>
	 * @param BoardDto <code>board</code>
	 * */
	public void deleteBoard(HttpServletRequest request, BoardDto board) throws Exception;
	
	/**
	 * 게시물 조회수를 증가한다.
	 *
	 * @param BoardDto <code>board</code>
	 * */
	public void updateReadCount(BoardDto board) throws Exception; 
	
	/**
	 * 게시글 공지사항 목록을 조회한다.
	 * 
	 * @return List<BoardDto>
	 * @param BoardDto <code>board</code>
	 * */
	public List<BoardDto> getNoticeBoardList(BoardDto board) throws Exception;
	
	/**
	 * 지난 포럼/박람회 커뮤니티 조회
	 * 
	 * @return List<BoardDto>
	 * @param BoardDto <code>board</code>
	 * */
	public List<BoardDto> getBoardPastCommunityList(BoardDto board) throws Exception;
	
	//#################################################################################
	//	게시판 속성 관리
	//#################################################################################
	
	/**
	 * 게시판 속성정보를 등록한다. 
	 * @param HttpServletRequest <code>request</code>
	 * @param boardConfig <code>BoardConfig</code>
	 * */
	public void insertBoardConfig(HttpServletRequest request, BoardConfigDto boardConfig) throws Exception;
	
	/**
	 * 게시판 속성정보를 수정한다.
	 * 
	 * @param boardConfig <code>BoardConfig</code>
	 * */
	public void updateBoardConfig(BoardConfigDto boardConfig) throws Exception;
	
	/**
	 * 게시판 속성정보를 삭제한다.
	 * 
	 * @param boardConfig <code>BoardConfig</code>
	 * */
	public void deleteBoardConfig(BoardConfigDto boardConfig) throws Exception;
		
	/**
	 * 게시판 속성정보를 조회한다.  
	 * @param boardConfig <code>BoardConfig</code>
	 * @return BoardConfigDto
	 * */
	public BoardConfigDto getBoardConfigInfo(BoardConfigDto boardConfig) throws Exception;
	
	/**
	 * 게시판 속성 목록을 조회한다.	 
	 * @param boardConfig <code>BoardConfig</code>
	 * @return List<BoardConfigDto>
	 * */
	public List<BoardConfigDto> getBoardConfigList(BoardConfigDto boardConfig) throws Exception;
	
	/**
	 * 게시판 속성 카테고리 목록을 조회한다. 
	 * @return List<BoardConfigDto>
	 * */
	public List<BoardConfigDto> getBoardConfigCateList() throws Exception;
	
	/**
	 * 해당 게시판 카테고리 목록을 조회한다.
	 * @param String <code>boardId</code>
	 * @return List<BoardCategoryDto>
	 * */
	public List<BoardCategoryDto> getBoardCategoryList(String boardId) throws Exception;
	
}