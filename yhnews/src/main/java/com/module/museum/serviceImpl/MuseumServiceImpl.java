package com.module.museum.serviceImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.common.dao.CommonDao;
import com.common.util.JProperties;
import com.module.board.dto.BoardConfigDto;
import com.module.museum.dto.MuseumAuthMapDto;
import com.module.museum.dto.MuseumDto;
import com.module.museum.dto.MuseumMenuContDto;
import com.module.museum.dto.MuseumMenuDto;
import com.module.museum.service.MuseumService;

@Service("museumService")
public class MuseumServiceImpl implements MuseumService{

	@Autowired
	private CommonDao commonDao;
	
	// 신규 박물관 게시판정보 세팅
	private BoardConfigDto getMuseumNoticeBoardInfo(String museumName, String boardName, String boardType){
		BoardConfigDto bc = new BoardConfigDto();
		bc.setBoard_name(museumName+" "+boardName);
		bc.setBoard_type(boardType);
		bc.setComment_use_yn("N");
		bc.setReply_use_yn("N");
		bc.setCate_use_yn("N");
		bc.setSecret_use_yn("N");
		bc.setEditor_use_yn("Y");
		bc.setPage_list_size(10);
		bc.setPage_block_size(5);
		bc.setWrite_limit(7); // 브랜치관리자 이상
		bc.setReply_limit(7); // 브랜치관리자 이상
		bc.setFile_count_limit(5);
		bc.setFile_size_limit(10);
		bc.setFile_ext_limit(JProperties.getString("FILE.EXT.ALL"));
		bc.setStatus("Y");
		return bc;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void insertMuseumInfo(MuseumDto museum) throws Exception {
		
		// 박물관정보저장
		int museumNo = (Integer)commonDao.insert("MUSEUM.insertMuseumInfo", museum);
		museum.setMuseum_no(String.valueOf(museumNo));		
		
		// 메뉴정보저장
		MuseumMenuContDto cont;
		int board_id;
		List<MuseumMenuContDto> list = new ArrayList<>();
		List<MuseumMenuDto> menuList = (List<MuseumMenuDto>)commonDao.queryForObjectList("MUSEUM.getMuseumMenuList", museum);
		for (MuseumMenuDto menu : menuList) {
			cont = new MuseumMenuContDto();
			cont.setMuseum_no(museum.getMuseum_no());
			cont.setMenu_code(menu.getMenu_code());
			// 메뉴타입이 게시판일때 - 게시판 생성 후 게시판아이디 매핑
			if(!StringUtils.equals("html", menu.getKind())){		
				BoardConfigDto boardconfig = getMuseumNoticeBoardInfo(museum.getMuseum_name(), menu.getMenu_name(), StringUtils.equals("boardNotice", menu.getKind())?"default":"schedule");
				if(StringUtils.equals("boardNotice", menu.getKind())){
					boardconfig.setNotice_use_yn("Y");
				}else{
					boardconfig.setNotice_use_yn("N");
				}
				board_id = (Integer)commonDao.insert("BOARD.insertBoardConfig", boardconfig);
				cont.setBoard_id(String.valueOf(board_id));
			}
			list.add(cont);			
		}
		// 박물관 기본 메뉴내용 저장
		commonDao.batchInsert("MUSEUM.insertMuseumMenuContent", list);		
	}
	
	@Override
	public void updateMuseumInfo(MuseumDto museum) throws Exception {
		commonDao.update("MUSEUM.updateMuseumInfo", museum);		
	}
	
	@Override
	public void updateMuseumPageInfo(MuseumMenuContDto museum) throws Exception {
		commonDao.insert("MUSEUM.updateMuseumMenuContent", museum);		
	}
	
	@Override
	public MuseumDto getMuseumInfo(String museumNo) throws Exception {
		return (MuseumDto)commonDao.queryForObject("MUSEUM.getMuseumInfo", museumNo);
	}
	
	@Override
	public MuseumDto getMuseumActiveInfo(String museumNo) throws Exception {
		return (MuseumDto)commonDao.queryForObject("MUSEUM.getMuseumActiveInfo", museumNo);
	}
	
	@Override
	public MuseumMenuContDto getMuseumMenuContInfo(MuseumMenuContDto museum) throws Exception {		
		return (MuseumMenuContDto)commonDao.queryForObject("MUSEUM.getMuseumMenuContInfo", museum);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<MuseumDto> getMuseumList(String status) throws Exception {
		Map<String, String> params = new HashMap<>();
		params.put("status", status);
		return (List<MuseumDto>)commonDao.queryForObjectList("MUSEUM.getMuseumList",params);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<MuseumMenuDto> getMuseumMenuContList(String museumNo) throws Exception {
		Map<String, String> params = new HashMap<>();
		params.put("museum_no", museumNo);
		return (List<MuseumMenuDto>)commonDao.queryForObjectList("MUSEUM.getMuseumMenuContList",params);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<MuseumAuthMapDto> getMuseumAuthMapList(String userId) throws Exception {
		return (List<MuseumAuthMapDto>)commonDao.queryForObjectList("MUSEUM.getMuseumAuthMapList",userId);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<MuseumDto> getMuseumDomainList() throws Exception {
		return (List<MuseumDto>)commonDao.queryForObjectList("MUSEUM.getMuseumDomainList");
	}
}