package com.module.file.service;

import java.util.List;

import com.module.file.dto.FileDto;

public interface FileService {
	
	/**
	 * 게시판 파일 목록을 조회한다.
	 * 
	 * @return List<FileDto>
	 * @param String <code>boardKey</code>
	 * */
	public List<FileDto> getFileList(String boardKey) throws Exception;
	
	/**
	 * 게시판 파일 정보를 조회한다.
	 * 
	 * @return FileDto
	 * @param FileDto <code>file</code>
	 * */
	public FileDto getFileInfo(FileDto file) throws Exception;
	
	/**
	 * 게시판 파일 다운로드 수를 증가한다.
	 * 
	 * @return FileDto
	 * @param FileDto <code>file</code>
	 * */
	public void updateFileDownCount(FileDto file) throws Exception;
}
