package com.module.board.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.common.util.CommonUtils;
import com.common.util.CommonWebUtils;
import com.common.util.JProperties;
import com.common.util.PagingUtil;
import com.common.util.StringEncrypter;
import com.module.board.dto.BoardConfigDto;
import com.module.board.dto.BoardDto;
import com.module.board.service.BoardService;
import com.module.file.service.FileService;
import com.module.forum.dto.ForumDto;
import com.module.forum.service.ForumService;
import com.module.main.service.MainService;
import com.module.museum.dto.MuseumDto;
import com.module.museum.service.MuseumService;
import com.module.user.dto.UserDto;

@Controller
@RequestMapping({"/siteManage/**/board*","/front/**/board*"})
public class BoardController extends CommonWebUtils{

	private Logger log = LoggerFactory.getLogger(getClass());

	@Resource(name = "messageSourceAccessor")
	private MessageSourceAccessor message;
	
	@Autowired
	private MainService mainService;
	
	@Autowired
	private BoardService boardService;
	
	@Autowired
	private FileService fileService;
	
	@Autowired
	private MuseumService museumService;
	
	@Autowired
	private ForumService forumService;
	
	private BoardConfigDto boardConfig;
	
	/**
	 * 게시판 속성정보
	 * */
	private BoardConfigDto getBoardConfig(HttpServletRequest request, BoardDto board) throws Exception {
		BoardConfigDto boardConfigInfo = (BoardConfigDto)request.getAttribute("boardConfigInfo"); 
		if(boardConfigInfo != null){
			board.setBoard_id(boardConfigInfo.getBoard_id());
			board.setPageSize(boardConfigInfo.getPage_list_size());
			board.setPageBlockSize(boardConfigInfo.getPage_block_size());
			if(StringUtils.equals("Y", boardConfigInfo.getCate_use_yn())){
				request.setAttribute("categoryList", boardService.getBoardCategoryList(boardConfigInfo.getBoard_id()));
			}
		} else {
			throw new Exception(message.getMessage("ERROR.BOARDCONFIG.FAIL"));
		}
		return boardConfigInfo;
	}
	
	/**
	 * 게시글 읽기 권한 체크
	 * */
	private void readAuthCheck(HttpServletRequest request, BoardDto result) throws Exception{
		UserDto sessionInfo = getSession(request);
		if(StringUtils.equals("Y", result.getIs_secret())){
			if(sessionInfo != null){
				boolean readAuth = false;
				// - 최고관리자 통과
				if(StringUtils.equals("9", sessionInfo.getAuth_level()))readAuth = true;					
				
				// - 본인 작성글 통과
				if(StringUtils.equals(sessionInfo.getUser_id(),result.getReg_id())) readAuth = true;
				
				// - 본인 작성글의 답변은 통과~
				if(!readAuth){
					if(result.getOrder_step() > 0){
						BoardDto dto = new BoardDto();
						dto.setBoard_key(result.getParent_board_key());
						BoardDto parentBoardInfo = boardService.getBoardView(dto);
						if(StringUtils.equals(sessionInfo.getUser_id(),parentBoardInfo.getReg_id())) readAuth = true;
					}
				}			
				// - 접근 에러 처리
				if(!readAuth)throw new Exception("ERROR.BOARD.READ.ACCESS.FAIL");				
			} else {
				throw new Exception("ERROR.BOARD.READ.ACCESS.FAIL");
			}
		}
	} 
	
	/**
	 * 게시글 쓰기 권한 체크
	 * - 로그인 정보없음 : E01
	 * - 해당 게시판 글쓰기권한없음 : E02
	 * */
	private void postWriteAuthCheck(HttpServletRequest request, BoardConfigDto config, UserDto sessionInfo) throws Exception {
		
		if(sessionInfo == null) throw new Exception("E01");
		if(Integer.valueOf(sessionInfo.getAuth_level()) < config.getWrite_limit()) throw new Exception("E02");
		if(StringUtils.equals("reply", request.getParameter("act")) && Integer.valueOf(sessionInfo.getAuth_level()) < config.getReply_limit()) throw new Exception("E03");
	}
		
	// 에러 메시지 처리
	private void exceptionMsg(Exception e, ModelAndView mav, HttpServletRequest request) {
		if(log.isWarnEnabled())log.warn(e.toString());
		switch(e.getMessage()){
		case "E01" :
			redirectView(mav, "로그인이 필요한 서비스입니다.||로그인 페이지로 이동 하시겠습니까?", getReferer(request),"/front/login/login.do?redirectUrl="+request.getRequestURL());			
			break;
		case "E02" :
			redirectView(mav, "글작성 권한이 없습니다.", getReferer(request));
			break;
		case "E03" :
			redirectView(mav, "답글작성 권한이 없습니다.", getReferer(request));
			break;	
		default :
			redirectView(mav, message.getMessage(e.getMessage(), message.getMessage("ERROR.ACCESS.FAIL")), getReferer(request));	
			break;
		}
		
	}
	//#################################################################################
	//	게시글 관리
	//#################################################################################
	
	/**
	 * 게시글 목록 Json
	 * @param request
	 * @param boardDto 
	 * @return ModelAndView
	 * @throws Exception
	 */	
	@RequestMapping(value="/front/boardListJson",method=RequestMethod.POST)
	public ModelAndView boardListJson(HttpServletRequest request, BoardDto board) throws Exception{
		if(log.isDebugEnabled())log.debug("[START] " + this.getClass().getName() + ".boardListJson()");
		
		ModelAndView mav = new ModelAndView("jsonViewer");
		try {
			
			String siteGubun = (String)request.getAttribute("siteGubun");
			// 정상 상태 글만 조회... (사용자 화면용)
			if(!StringUtils.equals("siteManage", siteGubun)){
				board.setStatus("Y");
			}			
			// 본문글 목록
			List<BoardDto> result = boardService.getBoardList(board);
			mav.addObject("result", result);
			mav.addObject("totalPageCnt", PagingUtil.getTotalPageCnt(board));			
			
			mav.addObject("RESULT_CODE","SUCCESS");
			
		} catch (Exception e) {
			if(log.isWarnEnabled())log.warn(e.toString());
			mav.addObject("RESULT_CODE","FAIL");
		}		
		
		if(log.isDebugEnabled())log.debug("[END] " + this.getClass().getName() + ".boardListJson()");
		return mav;
	}
	
	/**
	 * 일정 게시글 목록 Json
	 * @param request
	 * @param boardDto 
	 * @return ModelAndView
	 * @throws Exception
	 */	
	@RequestMapping(method=RequestMethod.POST)
	public ModelAndView boardScheduleListJson(HttpServletRequest request, BoardDto board) throws Exception{
		if(log.isDebugEnabled())log.debug("[START] " + this.getClass().getName() + ".boardScheduleListJson()");
		
		ModelAndView mav = new ModelAndView("jsonViewer");
		try {
			
			String siteGubun = (String)request.getAttribute("siteGubun");
			// 정상 상태 글만 조회... (사용자 화면용)
			if(!StringUtils.equals("siteManage", siteGubun)){
				board.setStatus("Y");
			}			
			// 본문글 목록
			board.setSearch_start_date(CommonUtils.addMonth(Integer.parseInt(board.getSearch_year()), Integer.parseInt(board.getSearch_month()),-1, "yyyy-MM")+"-01");
			board.setSearch_end_date(CommonUtils.addMonth(Integer.parseInt(board.getSearch_year()), Integer.parseInt(board.getSearch_month()),1, "yyyy-MM")+"-31");
			List<BoardDto> result = boardService.getBoardScheduleList(board);
			mav.addObject("RESULT_DATA", result);
			mav.addObject("RESULT_CODE","SUCCESS");
			
		} catch (Exception e) {
			if(log.isWarnEnabled())log.warn(e.toString());
			mav.addObject("RESULT_CODE","FAIL");
		}		
		
		if(log.isDebugEnabled())log.debug("[END] " + this.getClass().getName() + ".boardScheduleListJson()");
		return mav;
	}
	
	/**
	 * 게시글 목록
	 * @param request
	 * @param boardDto 
	 * @return ModelAndView
	 * @throws Exception
	 */	
	@RequestMapping
	public ModelAndView boardList(HttpServletRequest request, BoardDto board) throws Exception{
		if(log.isDebugEnabled())log.debug("[START] " + this.getClass().getName() + ".boardList()");
		
		ModelAndView mav = new ModelAndView();
		try {
			
			String siteGubun = (String)request.getAttribute("siteGubun");
			boardConfig = getBoardConfig(request, board);
			
			// 일정 게시판이 아닐경우...
			if(!StringUtils.equals("schedule", boardConfig.getBoard_type())){
			
				// 정상 상태 글만 조회... (사용자 화면용)
				if(!StringUtils.equals("siteManage", siteGubun)){
					board.setStatus("Y");
				}
				
				// 본문글 목록
				List<BoardDto> result = boardService.getBoardList(board);
				mav.addObject("result", result);
				// 내부 공지글 목록
				if(StringUtils.equals("Y", boardConfig.getNotice_use_yn())){
					mav.addObject("noticeList", boardService.getNoticeBoardList(board));	
				}			
				// 게시판 타입 FAQ > 본문 내용 조회(사용자 화면용) 
				if(StringUtils.equals("faq", boardConfig.getBoard_type()) && !StringUtils.equals("siteManage", siteGubun)){
					boardService.setBoardFaqContent(result);
				}
				
				
				mav.addObject("pageNavigation", PagingUtil.printPageNavi(board, getParameter(request,"boardList.do?","board_key|page"),(String)request.getAttribute("siteGubun")));				
			}
			
			
			mav.addObject("theForm", board);
			mav.addObject("boardConfig", boardConfig);
			mav.addObject("defaultParameter", getParameter(request,"&","board_key|order_type|order_column"));
			
			// 박물관 게시판일때... 
			if(StringUtils.isNotBlank(request.getParameter("museum_no"))){
				MuseumDto museumInfo = museumService.getMuseumActiveInfo(request.getParameter("museum_no"));
				// 박물관 기본정보
				mav.addObject("museumResult", museumInfo);
				// 서브메뉴목록
				mav.addObject("museumMenuList", museumService.getMuseumMenuContList(museumInfo.getMuseum_no()));
			}
			setForward(request, mav);
			
		} catch (Exception e) {
			if(log.isWarnEnabled())log.warn(e.toString());
			redirectView(mav, message.getMessage("ERROR.BOARDCONFIG.FAIL"), getReferer(request));
		}		
		
		if(log.isDebugEnabled())log.debug("[END] " + this.getClass().getName() + ".boardList()");
		return mav;
	}
	
	/**
	 * 게시글 상세보기
	 * @param HttpServletRequest
	 * @param boardDto 
	 * @return ModelAndView 
	 * @throws Exception
	 */	
	@RequestMapping
	public ModelAndView boardView(HttpServletRequest request, BoardDto board) throws Exception{
		if(log.isDebugEnabled())log.debug("[START] " + this.getClass().getName() + ".boardView()");
		
		ModelAndView mav = new ModelAndView();
		try {
						
			boardConfig = getBoardConfig(request, board);
			BoardDto result = boardService.getBoardView(board);
			
			if(result != null){
				
				// 글 접근권한 체크
				readAuthCheck(request, result);
				// 조회수 증가
				boardService.updateReadCount(result);
				result.setRead_cnt(result.getRead_cnt()+1);
				if(StringUtils.equals("N", boardConfig.getEditor_use_yn())){
					result.setContent(CommonUtils.htmlView(result.getContent()));
				}
				// 게시판 타입 webzine > 해당 카테고리 게시글 목록 조회
				if(StringUtils.equals("webzine", boardConfig.getBoard_type())){
					board.setCate_id(result.getCate_id());
					mav.addObject("boardList", boardService.getBoardList(board));
				}
				// 첨부파일목록조회
				if(boardConfig.getFile_count_limit() > 0){
					if(result.getAttach_cnt() > 0){
						mav.addObject("fileList", fileService.getFileList(result.getBoard_key()));	
					}
				}
				
				// 박물관 게시판일때... 
				if(StringUtils.isNotBlank(request.getParameter("museum_no"))){
					MuseumDto museumInfo = museumService.getMuseumActiveInfo(request.getParameter("museum_no"));
					// 박물관 기본정보
					mav.addObject("museumResult", museumInfo);
					// 서브메뉴목록
					mav.addObject("museumMenuList", museumService.getMuseumMenuContList(museumInfo.getMuseum_no()));
				}
				
				mav.addObject("result", result);
			} else {
				throw new Exception("ERROR.NO.DATA");
			}
			mav.addObject("theForm", board);
			mav.addObject("boardConfig", boardConfig);
			mav.addObject("defaultParameter", getParameter(request,"&","board_key|page"));			
			
			setForward(request, mav);
			
		} catch (Exception e) {
			if(log.isWarnEnabled())log.warn(e.toString());
			redirectView(mav, message.getMessage(e.getMessage(), message.getMessage("ERROR.ACCESS.FAIL")), getReferer(request));
		}		
		
		if(log.isDebugEnabled())log.debug("[END] " + this.getClass().getName() + ".boardView()");
		return mav;
	}
	
	/**
	 * 게시글 등록/수정폼
	 * @param HttpServletRequest
	 * @param boardDto 
	 * @return ModelAndView 
	 * @throws Exception
	 */	
	@RequestMapping
	public ModelAndView boardForm(HttpServletRequest request, BoardDto board) throws Exception{
		if(log.isDebugEnabled())log.debug("[START] " + this.getClass().getName() + ".boardForm()");
		
		ModelAndView mav = new ModelAndView();
		try {
			UserDto sessionInfo = getSession(request);
			// 게시판 속성정보
			boardConfig = getBoardConfig(request, board);
			// 게시글 쓰기 권한 체크
			postWriteAuthCheck(request, boardConfig, sessionInfo);
			
			if(StringUtils.isNotBlank(board.getBoard_key())){
				BoardDto result = boardService.getBoardView(board);
				if(result != null){
					result.setAct("update");
					// 답글등록시
					if(StringUtils.equals("reply", board.getAct())){
						// 본문글에만 답글 작성허용
						if(result.getOrder_step() == 0){
							result.setAct("reply");
							//result.setTitle("[ Re ] "+result.getTitle()); 
							result.setReq_content(result.getContent());
							result.setContent("");
							// 작성자명은 로그인 이름으로 교체...!!!	
						}else{
							throw new Exception("ERROR.REPLY.FAIL");
						}
					}					
					// 첨부파일목록조회
					if(boardConfig.getFile_count_limit() > 0){
						if(result.getAttach_cnt() > 0){
							mav.addObject("fileList", fileService.getFileList(result.getBoard_key()));	
						}
					}					
					board = (BoardDto) result.clone();
					
				} else {
					throw new Exception("ERROR.NO.DATA");
				}
			} else {
				// 게시판 타입 Qna는 전화/이메일 기본 셋팅
				if(StringUtils.equals("qna", boardConfig.getBoard_type())){
					StringEncrypter se = new StringEncrypter(JProperties.getString("SITE.PW.KEY"), JProperties.getString("SITE.PW.INITIALVECTOR"));		
					if(StringUtils.isNotBlank(sessionInfo.getPhone())){
						board.setPhone(se.decrypt(sessionInfo.getPhone()));
					}
					if(StringUtils.isNotBlank(sessionInfo.getEmail())){
						board.setEmail(se.decrypt(sessionInfo.getEmail()));
					}
					// 이메일주소 도메인 목록
					mav.addObject("emailDomainList", mainService.getCommonCodeList("EMAIL_DOMAIN_CODE"));
				}
			}
			mav.addObject("theForm", board);
			mav.addObject("boardConfig", boardConfig);
			mav.addObject("defaultParameter", getParameter(request,"?","board_key|act"));			
			
			setForward(request, mav);
			
		} catch (Exception e) {
			exceptionMsg(e, mav, request);
		}		
		
		if(log.isDebugEnabled())log.debug("[END] " + this.getClass().getName() + ".boardForm()");
		return mav;
	}
	
	/**
	 * 게시글 등록/수정/삭제
	 * @param HttpServletRequest
	 * @param boardDto 
	 * @return ModelAndView 
	 * @throws Exception
	 */	
	@RequestMapping(method=RequestMethod.POST)
	public ModelAndView boardSave(HttpServletRequest request, BoardDto board) throws Exception{
		if(log.isDebugEnabled())log.debug("[START] " + this.getClass().getName() + ".boardSave()");
		
		ModelAndView mav = new ModelAndView();		
		String returnPage = "";
		String addParam = "";
		if(StringUtils.isNotBlank(request.getParameter("museum_no"))&&StringUtils.isNotBlank(request.getParameter("menu_code")))
			addParam = "museum_no="+request.getParameter("museum_no")+"&menu_code="+request.getParameter("menu_code");
			
		try {
			
			UserDto sessionInfo = getSession(request);
			// 게시판속성
			boardConfig = getBoardConfig(request, board);
			// 글작성권한 체크
			postWriteAuthCheck(request, boardConfig, sessionInfo);
			
			// 글등록 || 답글등록 
			if(StringUtils.equals("insert", board.getAct()) || StringUtils.equals("reply", board.getAct())){
				
				returnPage = "boardList.do"+(StringUtils.isNotBlank(addParam)?"?"+addParam:"");
				boardService.insertBoard(request, board);
				
			// 수정	
			}else if(StringUtils.equals("update", board.getAct())){
				
				returnPage = "boardView.do?board_key="+board.getBoard_key()+(StringUtils.isNotBlank(addParam)?"&"+addParam:"");
				boardService.updateBoard(request, board);
				
			}else if(StringUtils.equals("delete", board.getAct())){				
				
				returnPage = "boardList.do"+(StringUtils.isNotBlank(addParam)?"?"+addParam:"");
				boardService.deleteBoard(request, board);
				
			}
				
			redirectView(mav, "", returnPage);
			
		} catch (Exception e) {
			e.printStackTrace();
			redirectView(mav, message.getMessage(e.getMessage(), message.getMessage("ERROR.ACCESS.FAIL")), getReferer(request));
		}		
		
		if(log.isDebugEnabled())log.debug("[END] " + this.getClass().getName() + ".boardSave()");
		return mav;
	}
	
	/**
	 * 포럼 지난 커뮤니티 목록 조회
	 * @param request
	 * @param boardDto 
	 * @return ModelAndView
	 * @throws Exception
	 */	
	@RequestMapping
	public ModelAndView boardPastCommunityList(HttpServletRequest request, BoardDto board) throws Exception{
		if(log.isDebugEnabled())log.debug("[START] " + this.getClass().getName() + ".boardForumCommunityList()");
		
		ModelAndView mav = new ModelAndView();
		try {
			
			String siteGubun = (String)request.getAttribute("siteGubun");
			String forumKey = request.getParameter("forum_key");		
			
			// 지난 포럼/박람회 기본정보 조회
			ForumDto forumInfo = forumService.getForumInfo(forumKey);
			// 해당 포럼에 매핑된 게시글 조회 기간을 가져온다
			if(forumInfo != null){
				board.setSearch_start_date(forumInfo.getBoard_search_start_day());
				board.setSearch_end_date(forumInfo.getBoard_search_end_day());
				
				mav.addObject("result", boardService.getBoardPastCommunityList(board));
				mav.addObject("defaultParameter", getParameter(request,"&","board_key|order_type|order_column"));
				mav.addObject("pageNavigation", PagingUtil.printPageNavi(board, getParameter(request,"boardList.do?","board_key|page"),siteGubun));

				mav.addObject("theForm", board);				
				mav.addObject("forumInfo", forumInfo);
				mav.setViewName(siteGubun+"/board/past/boardList");
			} else {
				throw new Exception("forum Not Found!!");
			}
			
		} catch (Exception e) {
			if(log.isWarnEnabled())log.warn(e.toString());
			redirectView(mav, message.getMessage("ERROR.BOARDCONFIG.FAIL"), getReferer(request));
		}		
		
		if(log.isDebugEnabled())log.debug("[END] " + this.getClass().getName() + ".boardForumCommunityList()");
		return mav;
	}
	
	/**
	 * 포럼 지난 커뮤니티 상세 조회
	 * @param request
	 * @param boardDto 
	 * @return ModelAndView
	 * @throws Exception
	 */	
	@RequestMapping
	public ModelAndView boardPastCommunityView(HttpServletRequest request, BoardDto board) throws Exception{
		if(log.isDebugEnabled())log.debug("[START] " + this.getClass().getName() + ".boardPastCommunityView()");
		
		ModelAndView mav = new ModelAndView();
		try {
			
			String siteGubun = (String)request.getAttribute("siteGubun");
			String forumKey = request.getParameter("forum_key");
			
			// 지난 포럼/박람회 기본정보 조회
			ForumDto forumInfo = forumService.getForumInfo(forumKey);
			BoardDto result = boardService.getBoardView(board);			
			if(result != null){				
				// 글 접근권한 체크
				readAuthCheck(request, result);
				// 조회수 증가
				boardService.updateReadCount(result);
				result.setRead_cnt(result.getRead_cnt()+1);
				// 첨부파일목록조회
				if(result.getAttach_cnt() > 0){
					mav.addObject("fileList", fileService.getFileList(result.getBoard_key()));	
				}
				mav.addObject("forumInfo", forumInfo);
				mav.addObject("result", result);
			} else {
				throw new Exception("ERROR.NO.DATA");
			}
			mav.addObject("theForm", board);
			mav.addObject("defaultParameter", getParameter(request,"&","board_key|page"));
			
			mav.setViewName(siteGubun+"/board/past/boardView");
			
		} catch (Exception e) {
			if(log.isWarnEnabled())log.warn(e.toString());
			redirectView(mav, message.getMessage("ERROR.BOARDCONFIG.FAIL"), getReferer(request));
		}		
		
		if(log.isDebugEnabled())log.debug("[END] " + this.getClass().getName() + ".boardPastCommunityView()");
		return mav;
	}
	
	//#################################################################################
	//	게시판 속성 관리
	//#################################################################################
	
	/**
	 * 게시판 속성 목록
	 * @param request
	 * @param BoardConfigDto 
	 * @return ModelAndView
	 * @throws Exception
	 */	
	@RequestMapping
	public ModelAndView boardConfigList(HttpServletRequest request, BoardConfigDto boardConfig) throws Exception{
		if(log.isDebugEnabled())log.debug("[START] " + this.getClass().getName() + ".boardConfigList()");
		
		ModelAndView mav = new ModelAndView("siteManage/boardconfig/boardConfigList");
		try {
			mav.addObject("result", boardService.getBoardConfigList(boardConfig));			
			mav.addObject("theForm", boardConfig);			
		} catch (Exception e) {
			if(log.isWarnEnabled())log.warn(e.toString());
			redirectView(mav, message.getMessage("ERROR.ACCESS.FAIL"), getReferer(request));
		}		
		
		if(log.isDebugEnabled())log.debug("[END] " + this.getClass().getName() + ".boardConfigList()");
		return mav;
	}
	
	/**
	 * 게시판 속성 관리 등록/수정 폼
	 * @param request
	 * @param BoardConfigDto 
	 * @return ModelAndView
	 * @throws Exception
	 */	
	@RequestMapping
	public ModelAndView boardConfigForm(HttpServletRequest request, BoardConfigDto boardConfig) throws Exception{
		if(log.isDebugEnabled())log.debug("[START] " + this.getClass().getName() + ".boardConfigForm()");
		
		ModelAndView mav = new ModelAndView("siteManage/boardconfig/boardConfigForm");		
		
		try {
			if(StringUtils.isNotBlank(boardConfig.getBoard_id())){
				BoardConfigDto result = boardService.getBoardConfigInfo(boardConfig);
				if(result != null){
					boardConfig = result;
					boardConfig.setAct("update");
					mav.addObject("categoryList", boardService.getBoardCategoryList(boardConfig.getBoard_id()));
				} else {
					throw new Exception("boardConfig Not Found!!");
				}				
			}
			// 게시판타입
			mav.addObject("boardTypeCodeList", mainService.getCommonCodeList("BOARD_TYPE"));
			// 사용자 권한 목록
			mav.addObject("userAuthLevelCodeList", mainService.getCommonCodeList("USER_AUTH_LEVEL"));
			mav.addObject("theForm",boardConfig);
		} catch (Exception e) {
			if(log.isWarnEnabled())log.warn(e.toString());
			redirectView(mav, message.getMessage("ERROR.ACCESS.FAIL"), getReferer(request));
		}		
		
		if(log.isDebugEnabled())log.debug("[END] " + this.getClass().getName() + ".boardConfigForm()");
		return mav;
	}
	
	/**
	 * 게시판 속성 관리 등록/수정
	 * @param request
	 * @param boardDto 
	 * @return
	 * @throws Exception
	 */	
	@RequestMapping(method=RequestMethod.POST)
	public ModelAndView boardConfigSave(HttpServletRequest request, BoardConfigDto boardConfig) throws Exception{
		if(log.isDebugEnabled())log.debug("[START] " + this.getClass().getName() + ".boardConfigSave()");
		
		ModelAndView mav = new ModelAndView();
		String returnPage = "boardConfigList.do";
		try {
			if(StringUtils.equals("insert", boardConfig.getAct())){
				boardService.insertBoardConfig(request, boardConfig);	
			}else if(StringUtils.equals("update", boardConfig.getAct())){
				returnPage = "boardConfigForm.do?board_id="+boardConfig.getBoard_id();
				
				boardService.updateBoardConfig(boardConfig);
			}else if(StringUtils.equals("delete", boardConfig.getAct())){				
				boardService.deleteBoardConfig(boardConfig);
			}
			redirectView(mav, "", returnPage);
		} catch (Exception e) {
			if(log.isWarnEnabled())log.warn(e.toString());
			redirectView(mav, message.getMessage("ERROR.ACCESS.FAIL"), getReferer(request));
		}		
		
		if(log.isDebugEnabled())log.debug("[END] " + this.getClass().getName() + ".boardConfigSave()");
		return mav;
	}
	
	/**
	 * 게시판 속성 목록 Json
	 * @param request
	 * @param BoardConfigDto 
	 * @return ModelAndView
	 * @throws Exception
	 */	
	@RequestMapping(method=RequestMethod.POST)
	public ModelAndView boardConfigListJson(HttpServletRequest request, BoardConfigDto boardConfig) throws Exception{
		if(log.isDebugEnabled())log.debug("[START] " + this.getClass().getName() + ".boardConfigListJson()");
		
		ModelAndView mav = new ModelAndView("jsonViewer");
		try {
			mav.addObject("RESULT_DATA", boardService.getBoardConfigList(boardConfig));
		} catch (Exception e) {
			if(log.isWarnEnabled())log.warn(e.toString());
		}		
		
		if(log.isDebugEnabled())log.debug("[END] " + this.getClass().getName() + ".boardConfigListJson()");
		return mav;
	}
}