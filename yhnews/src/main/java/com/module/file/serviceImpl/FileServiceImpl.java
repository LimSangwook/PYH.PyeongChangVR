package com.module.file.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.common.dao.CommonDao;
import com.module.file.dto.FileDto;
import com.module.file.service.FileService;

@Service("fileService")
public class FileServiceImpl implements FileService{

	@Autowired
	private CommonDao commonDao;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<FileDto> getFileList(String boardKey) throws Exception {
		return (List<FileDto>)commonDao.queryForObjectList("FILE.getFileList", boardKey);
	}
	
	@Override
	public FileDto getFileInfo(FileDto file) throws Exception {
		return (FileDto)commonDao.queryForObject("FILE.getFileInfo", file);
	}
	
	@Override
	public void updateFileDownCount(FileDto file) throws Exception {
		commonDao.update("FILE.updateFileDownCount", file);		
	}
}
