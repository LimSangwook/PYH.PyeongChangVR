package com.module.vr.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.common.dao.CommonDao;
import com.module.vr.dto.VrStyleGalleryDto;
import com.module.vr.dto.VrStyleGalleryImageDto;
import com.module.vr.dto.VrStyleLinkDto;
import com.module.vr.dto.VrStyleMovieDto;
import com.module.vr.dto.VrStyleMusicDto;
import com.module.vr.service.VrStyleService;

@Service("VrStyleService")
public class VrStyleServiceImpl implements VrStyleService {
	@Autowired
	private CommonDao commonDao;

	@SuppressWarnings("unchecked")
	@Override
	public List<VrStyleLinkDto> getVrStyleLinkList(VrStyleLinkDto vrStyleLinkDto) {
		List<VrStyleLinkDto> result = null;
		result = (List<VrStyleLinkDto>) commonDao.queryForObjectList("VRSTYLE.getVrStyleLinkList", vrStyleLinkDto);
		vrStyleLinkDto.setTotal_count(result.size());
		return result;
	}

	@Override
	public VrStyleLinkDto getVrStyleLinkForm(VrStyleLinkDto vrStyleLinkDto) {
		VrStyleLinkDto result = (VrStyleLinkDto) commonDao.queryForObject("VRSTYLE.getVrStyleLinkForm", vrStyleLinkDto);
		return result;
	}

	@Override
	public void insertVrStyleLink(VrStyleLinkDto vrStyleLinkDto) {
		commonDao.insert("VRSTYLE.insertVrStyleLink", vrStyleLinkDto);
	}

	@Override
	public void updateVrStyleLink(VrStyleLinkDto vrStyleLinkDto) {
		commonDao.update("VRSTYLE.updateVrStyleLink", vrStyleLinkDto);
	}

	@Override
	public void deleteVrStyleLink(VrStyleLinkDto vrStyleLinkDto) {
		commonDao.delete("VRSTYLE.deleteVrStyleLink", vrStyleLinkDto);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<VrStyleGalleryDto> getVrStyleGalleryList(VrStyleGalleryDto vrStyleGalleryDto) {
		List<VrStyleGalleryDto> result = null;
		result = (List<VrStyleGalleryDto>) commonDao.queryForObjectList("VRSTYLE.getVrStyleGalleryList", vrStyleGalleryDto);
		vrStyleGalleryDto.setTotal_count(result.size());
		return result;
	}

	@Override
	public VrStyleGalleryDto getVrStyleGalleryForm(VrStyleGalleryDto vrStyleGalleryDto) {
		VrStyleGalleryDto result = (VrStyleGalleryDto) commonDao.queryForObject("VRSTYLE.getVrStyleGalleryForm",
				vrStyleGalleryDto);
		return result;
	}

	@Override
	public String insertVrStyleGallery(VrStyleGalleryDto vrStyleGalleryDto) {
		return (String) commonDao.insert("VRSTYLE.insertVrStyleGallery", vrStyleGalleryDto);
	}

	@Override
	public void updateVrStyleGallery(VrStyleGalleryDto vrStyleGalleryDto) {
		commonDao.update("VRSTYLE.updateVrStyleGallery", vrStyleGalleryDto);
	}

	@Override
	public void deleteVrStyleGallery(VrStyleGalleryDto vrStyleGalleryDto) {
		commonDao.delete("VRSTYLE.deleteVrStyleGallery", vrStyleGalleryDto);
	}

	
	@SuppressWarnings("unchecked")
	@Override
	public List<VrStyleGalleryImageDto> getVrStyleGalleryImage(VrStyleGalleryImageDto vrStyleGalleryImageDto) {
		List<VrStyleGalleryImageDto> result = null;
		result = (List<VrStyleGalleryImageDto>) commonDao.queryForObjectList("VRSTYLE.getVrStyleGalleryImageList", vrStyleGalleryImageDto);
		return result;
	}
	@Override
	public void insertVrStyleGalleryImage(VrStyleGalleryImageDto vrStyleGalleryImageDto) {
		commonDao.insert("VRSTYLE.insertVrStyleGalleryImage", vrStyleGalleryImageDto);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<VrStyleMovieDto> getVrStyleMovieList(VrStyleMovieDto vrStyleMovieDto) {
		List<VrStyleMovieDto> result = null;
		result = (List<VrStyleMovieDto>) commonDao.queryForObjectList("VRSTYLE.getVrStyleMovieList", vrStyleMovieDto);
		vrStyleMovieDto.setTotal_count(result.size());
		return result;
	}

	@Override
	public VrStyleMovieDto getVrStyleMovieForm(VrStyleMovieDto vrStyleMovieDto) {
		VrStyleMovieDto result = (VrStyleMovieDto) commonDao.queryForObject("VRSTYLE.getVrStyleMovieForm", vrStyleMovieDto);
		return result;
	}

	@Override
	public void insertVrStyleMovie(VrStyleMovieDto vrStyleMovieDto) {
		commonDao.insert("VRSTYLE.insertVrStyleMovie", vrStyleMovieDto);
	}

	@Override
	public void updateVrStyleMovie(VrStyleMovieDto vrStyleMovieDto) {
		commonDao.update("VRSTYLE.updateVrStyleMovie", vrStyleMovieDto);
	}

	@Override
	public void deleteVrStyleMovie(VrStyleMovieDto vrStyleMovieDto) {
		commonDao.delete("VRSTYLE.deleteVrStyleMovie", vrStyleMovieDto);
	}


	@SuppressWarnings("unchecked")
	@Override
	public List<VrStyleMusicDto> getVrStyleMusicList(VrStyleMusicDto vrStyleMusicDto) {
		List<VrStyleMusicDto> result = null;
		result = (List<VrStyleMusicDto>) commonDao.queryForObjectList("VRSTYLE.getVrStyleMusicList", vrStyleMusicDto);
		vrStyleMusicDto.setTotal_count(result.size());
		return result;
	}

	@Override
	public VrStyleMusicDto getVrStyleMusicForm(VrStyleMusicDto vrStyleMusicDto) {
		VrStyleMusicDto result = (VrStyleMusicDto) commonDao.queryForObject("VRSTYLE.getVrStyleMusicForm", vrStyleMusicDto);
		return result;
	}

	@Override
	public void insertVrStyleMusic(VrStyleMusicDto vrStyleMusicDto) {
		commonDao.insert("VRSTYLE.insertVrStyleMusic", vrStyleMusicDto);
	}

	@Override
	public void updateVrStyleMusic(VrStyleMusicDto vrStyleMusicDto) {
		commonDao.update("VRSTYLE.updateVrStyleMusic", vrStyleMusicDto);
	}

	@Override
	public void deleteVrStyleMusic(VrStyleMusicDto vrStyleMusicDto) {
		commonDao.delete("VRSTYLE.deleteVrStyleMusic", vrStyleMusicDto);
	}

}
