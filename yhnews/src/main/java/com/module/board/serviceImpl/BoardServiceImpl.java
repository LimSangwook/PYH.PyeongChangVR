package com.module.board.serviceImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
/*import org.slf4j.Logger;
import org.slf4j.LoggerFactory;*/
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.common.dao.CommonDao;
import com.common.util.CommonUtils;
import com.common.util.CommonWebUtils;
import com.common.util.JProperties;
import com.common.util.StringEncrypter;
import com.module.board.dto.BoardCategoryDto;
import com.module.board.dto.BoardConfigDto;
import com.module.board.dto.BoardDto;
import com.module.board.service.BoardService;
import com.module.file.dto.FileDto;
import com.module.user.dto.UserDto;

@Service("boardService")
public class BoardServiceImpl implements BoardService{

	//private Logger log = LoggerFactory.getLogger(getClass());	
	
	@Autowired
	private CommonDao commonDao;	
	
	@Override
	public void insertBoard(HttpServletRequest request, BoardDto board) throws Exception {
		// 세션정보조회
		UserDto sessionInfo = CommonWebUtils.getSession(request);
				
		board.setReg_id(sessionInfo.getUser_id());
		board.setReg_name(StringUtils.defaultIfBlank(board.getReg_name(), sessionInfo.getUser_name()));
		board.setIp(CommonUtils.getClientIpAddress(request));	
		
		int boardKey = (Integer)commonDao.insert("BOARD.insertBoard", board);
		
		// 답글인경우
		if(StringUtils.isNotBlank(board.getBoard_key()) && StringUtils.equals("reply", board.getAct())){
			BoardDto parentBoardView = (BoardDto)commonDao.queryForObject("BOARD.getBoardView", board);
			Integer level = (Integer)commonDao.queryForObject("BOARD.getReplyChildPost",parentBoardView);
			
			if(level == null) {
				level = (Integer)commonDao.queryForObject("BOARD.getReplyLevelChildPostNotFound",parentBoardView);
			} else {
				parentBoardView.setOrder_level_find(level);
				level = (Integer)commonDao.queryForObject("BOARD.getReplyLevelChildPostFound",parentBoardView);
			}
			
			if(level != null) {
				parentBoardView.setOrder_level(level);
			}
			commonDao.update("BOARD.updateReplyLevel", parentBoardView);
			
			board.setBoard_key(String.valueOf(boardKey));
			board.setParent_board_key(parentBoardView.getBoard_key());
			board.setOrder_group(parentBoardView.getOrder_group());
			board.setOrder_step(parentBoardView.getOrder_step()+1);
			board.setOrder_level(parentBoardView.getOrder_level()+1);
		} else {
			board.setBoard_key(String.valueOf(boardKey));
			board.setParent_board_key(String.valueOf(boardKey));
			board.setOrder_group(boardKey);
		}
		commonDao.update("BOARD.updateCurrentPostGroupLvl", board);
				
		// 첨부파일저장
		saveFileInfo(board);
		
		// 게시판 첨부파일수 업데이트
		commonDao.update("BOARD.updateFileCount", board);
		
		// 게시글 작성자 정보등록
		setBoardWriterEncrypt(board);
		board.setWriter(board.getReg_name());		
		commonDao.insert("BOARD.insertBoardWriter", board);
	}	
	
	@Override
	public void updateBoard(HttpServletRequest request, BoardDto board) throws Exception {
		// 세션정보조회
		UserDto sessionInfo = CommonWebUtils.getSession(request);
		board.setReg_id(sessionInfo.getUser_id());
		board.setReg_name(StringUtils.defaultIfBlank(board.getReg_name(), sessionInfo.getUser_name()));
		
		commonDao.update("BOARD.updateBoard", board);
		
		// 첨부파일저장
		saveFileInfo(board);
		
		// 게시판 첨부파일수 업데이트
		commonDao.update("BOARD.updateFileCount", board);
	}
	
	@Override
	public void deleteBoard(HttpServletRequest request, BoardDto board) throws Exception {
		// 세션정보조회
		UserDto sessionInfo = CommonWebUtils.getSession(request);
		board.setReg_id(sessionInfo.getUser_id());
		
		// 해당글 삭제
		commonDao.update("BOARD.deleteBoard", board);		
		// 자식글 삭제
		commonDao.update("BOARD.deleteChildBoard", board);
	}
	
	@Override
	public BoardDto getBoardView(BoardDto board) throws Exception {
		BoardDto result = (BoardDto)commonDao.queryForObject("BOARD.getBoardView", board);
		if(result != null){
			board.setOrder_group(result.getOrder_group());			
			// 이전글
			result.setPrevBoard((BoardDto)commonDao.queryForObject("BOARD.getBoardPrev", board));
			// 다음글
			result.setNextBoard((BoardDto)commonDao.queryForObject("BOARD.getBoardNext", board));
			
			BoardDto writer = (BoardDto)commonDao.queryForObject("BOARD.getBoardWriter",result);
			// 작성자 정보 조회
			if(writer != null){
				setBoardWriterDecrypt(writer);	
				result.setEmail(writer.getEmail());
				result.setPhone(writer.getPhone());
			}			
		}
		return result;
	}
	
	
	@Override
	public void updateReadCount(BoardDto board) throws Exception {
		commonDao.update("BOARD.updateReadCount", board);		
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<BoardDto> getBoardList(BoardDto board) throws Exception {
		List<BoardDto> result = null;
		int totalCnt = (Integer)commonDao.queryForObject("BOARD.getBoardTotalCnt", board);
		if(totalCnt > 0){
			result = (List<BoardDto>)commonDao.queryForObjectList("BOARD.getBoardList", board);
			board.setTotal_count(totalCnt);
		}
		return result;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<BoardDto> getBoardScheduleList(BoardDto board) throws Exception {		
		return (List<BoardDto>)commonDao.queryForObjectList("BOARD.getBoardScheduleList", board);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void setBoardFaqContent(List<BoardDto> list) throws Exception {
		List<BoardDto> result = null;
		if(list != null){
			List<String> boardKeyList = new ArrayList<>();
			for (BoardDto data : list) {
				boardKeyList.add(data.getBoard_key());
			}
			Map<String,Object> params = new HashMap<>();
			params.put("boardKeyList", boardKeyList);
			result = (List<BoardDto>)commonDao.queryForObjectList("BOARD.getBoardFaqContentList", params);
			
			for (BoardDto data : list) {
				for (BoardDto cont : result) {
					if(StringUtils.equals(data.getBoard_key(), cont.getBoard_key())){
						data.setContent(cont.getContent());
					}
				}
			}
		}		
	}	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<BoardDto> getNoticeBoardList(BoardDto board) throws Exception {		
		return (List<BoardDto>)commonDao.queryForObjectList("BOARD.getNoticeBoardList", board);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<BoardDto> getBoardPastCommunityList(BoardDto board) throws Exception {
		List<BoardDto> result = null;
		int totalCnt = (Integer)commonDao.queryForObject("BOARD.getBoardPastCommunityTotalCount", board);
		if(totalCnt > 0){
			result = (List<BoardDto>)commonDao.queryForObjectList("BOARD.getBoardPastCommunityList", board);
			board.setTotal_count(totalCnt);
		}
		return result;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<BoardConfigDto> getBoardConfigList(BoardConfigDto boardConfig) throws Exception {
		return (List<BoardConfigDto>)commonDao.queryForObjectList("BOARD.getBoardConfigList", boardConfig);
	}
	
	@Override
	public List<BoardConfigDto> getBoardConfigCateList() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public BoardConfigDto getBoardConfigInfo(BoardConfigDto boardConfig) throws Exception {
		return (BoardConfigDto)commonDao.queryForObject("BOARD.getBoardConfigInfo", boardConfig);
	}
	
	@Override
	public void insertBoardConfig(HttpServletRequest request, BoardConfigDto boardConfig) throws Exception {
		// 게시판 속성 등록
		int boardKey = (Integer)commonDao.insert("BOARD.insertBoardConfig", boardConfig);
		boardConfig.setBoard_id(String.valueOf(boardKey));
		// 게시판 템플릿 정보저장 (상단/하단내용)
		commonDao.insert("BOARD.insertBoardTemplateInfo", boardConfig);		
		// 게시판 카테고리 등록
		categoryEdit(boardConfig);		
	}
	
	@Override
	public void updateBoardConfig(BoardConfigDto boardConfig) throws Exception {
		// 게시판 속성 수정
		commonDao.update("BOARD.updateBoardConfig", boardConfig);
		// 게시판 템플릿 정보저장 (상단/하단내용)
		commonDao.insert("BOARD.insertBoardTemplateInfo", boardConfig);
		// 게시판 카테고리 수정
		categoryEdit(boardConfig);		
	}
	
	@Override
	public void deleteBoardConfig(BoardConfigDto boardConfig) throws Exception {
		commonDao.update("BOARD.deleteBoardConfig", boardConfig);		
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<BoardCategoryDto> getBoardCategoryList(String boardId) throws Exception {
		return (List<BoardCategoryDto>)commonDao.queryForObjectList("BOARD.getBoardCategoryList", boardId);
	}
	
	// 카테고리 정보처리
	private void categoryEdit(BoardConfigDto boardConfig) {
		// 게시판 카테고리 수정
		if(boardConfig.getCategory_name_arr() != null && StringUtils.equals("Y", boardConfig.getCate_use_yn())){
			List<BoardCategoryDto> list = new ArrayList<>();
			List<BoardCategoryDto> updateList = new ArrayList<>();
			BoardCategoryDto cate = null;
			int i=0;
			for (String cateName : boardConfig.getCategory_name_arr()) {
				cate = new BoardCategoryDto();
				cate.setBoard_id(String.valueOf(boardConfig.getBoard_id()));				
				cate.setCate_name(cateName);
				cate.setOrder_level(boardConfig.getCategory_sort_arr().get(i));
				// 카테고리 수정일 경우
				if(!StringUtils.equals("-", boardConfig.getCategory_id_arr().get(i))){	
					cate.setCate_id(boardConfig.getCategory_id_arr().get(i));
					updateList.add(cate);
				// 카테고리 등록일 경우
				} else {
					list.add(cate);	
				}				
				
				i++;
			}
			// 카테고리 등록
			commonDao.batchInsert("BOARD.insertBoardCategory", list);
			// 카테고리 수정
			commonDao.batchUpdate("BOARD.updateBoardCategory", updateList);			
		}
		// 카테고리 삭제
		if(boardConfig.getRemove() != null){
			commonDao.batchUpdate("BOARD.deleteBoardCategory", boardConfig.getRemove());	
		}
	}
	
	// 파일정보 처리
	private void saveFileInfo(BoardDto board) {
		// 1) 첨부파일저장
		if(board.getFile() != null){
			FileDto file = null;
			List<FileDto> list = new ArrayList<FileDto>();
			int i=1;
			for (String data : board.getFile()) {
				file = new FileDto();
				String[] fileArr = StringUtils.split(data,"|");
				/**
				 * file[0] = real_file_name
				 * file[1] = save_file_name
				 * file[2] = file_path
				 * file[3] = file_size
				 * file[4] = image_width_size
				 * file[5] = image_height_size
				 * file[6] = file_ext
				 * */
				file.setReal_file_name(fileArr[0]);
				file.setSave_file_name(fileArr[1]);
				file.setFile_path(fileArr[2]);
				file.setFile_size(Integer.valueOf(fileArr[3]));
				file.setImage_width_size(Integer.valueOf(fileArr[4]));
				file.setImage_height_size(Integer.valueOf(fileArr[5]));
				file.setFile_ext(fileArr[6]);
				
				file.setOrder_level(i++);
				file.setBoard_key(board.getBoard_key());
				
				list.add(file);
				
				// 게시글 대표이미지가 없다면 첫번째 이미지를 사용한다
				if(StringUtils.isBlank(board.getMaster_image()) && file.getImage_width_size() > 0){
					board.setMaster_image(file.getFile_path()+""+file.getSave_file_name());
					commonDao.update("BOARD.updateBoardMasterImage", board);
				}
			}			
			commonDao.batchInsert("FILE.insertFile", list);
		}
		// 2) 첨부파일 삭제데이터 처리
		if(board.getRemove() != null){
			commonDao.update("FILE.deleteFileInfo", board);
		}
		// 3) 첨부파일 일련번호 세팅		
		@SuppressWarnings("unchecked")
		List<FileDto> fileList = (List<FileDto>)commonDao.queryForObjectList("FILE.getFileList", board.getBoard_key());
		if(fileList != null){
			int i=1;
			for (FileDto data : fileList) {
				data.setOrder_level(i++);			
			}	
			commonDao.batchUpdate("FILE.updateFileOrderLevel", fileList);
		}		
	}
	
	// 작성자 정보 암호화
	private void setBoardWriterEncrypt(BoardDto board) throws Exception {
		StringEncrypter se = new StringEncrypter(JProperties.getString("SITE.PW.KEY"), JProperties.getString("SITE.PW.INITIALVECTOR"));
		
		if(StringUtils.isNotBlank(board.getPhone1()) && StringUtils.isNotBlank(board.getPhone2()) && StringUtils.isNotBlank(board.getPhone3())){
			board.setPhone(se.encrypt(board.getPhone1()+"-"+board.getPhone2()+"-"+board.getPhone3()));
		}			
		if(StringUtils.isNotBlank(board.getEmail_id()) && StringUtils.isNotBlank(board.getEmail_domain())){
			board.setEmail(se.encrypt(board.getEmail_id()+"@"+board.getEmail_domain()));
		}			
	}
	
	// 작성자 정보 복호화
	private void setBoardWriterDecrypt(BoardDto board) throws Exception {
		StringEncrypter se = new StringEncrypter(JProperties.getString("SITE.PW.KEY"), JProperties.getString("SITE.PW.INITIALVECTOR"));
		
		if(StringUtils.isNotBlank(board.getPhone())){
			board.setPhone(se.decrypt(board.getPhone()));
		}			
		if(StringUtils.isNotBlank(board.getEmail())){
			board.setEmail(se.decrypt(board.getEmail()));
		}			
	}
}