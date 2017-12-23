package com.module.vr.service;

import java.util.List;

import com.module.vr.dto.VrStyleGalleryDto;
import com.module.vr.dto.VrStyleGalleryImageDto;
import com.module.vr.dto.VrStyleLinkDto;
import com.module.vr.dto.VrStyleMovieDto;
import com.module.vr.dto.VrStyleMusicDto;

public interface VrStyleService {

	/*
	 * StyleLink
	 */
	public List<VrStyleLinkDto> getVrStyleLinkList(VrStyleLinkDto vrStyleLinkDto);

	public VrStyleLinkDto getVrStyleLinkForm(VrStyleLinkDto vrStyleLinkDto);

	public void insertVrStyleLink(VrStyleLinkDto vrStyleLinkDto);

	public void updateVrStyleLink(VrStyleLinkDto vrStyleLinkDto);

	public void deleteVrStyleLink(VrStyleLinkDto vrStyleLinkDto);

	/*
	 * StyleGallery
	 */
	public List<VrStyleGalleryDto> getVrStyleGalleryList(VrStyleGalleryDto vrStyleGalleryDto);

	public VrStyleGalleryDto getVrStyleGalleryForm(VrStyleGalleryDto vrStyleGalleryDto);

	public String insertVrStyleGallery(VrStyleGalleryDto vrStyleGalleryDto);

	public void updateVrStyleGallery(VrStyleGalleryDto vrStyleGalleryDto);

	public void deleteVrStyleGallery(VrStyleGalleryDto vrStyleGalleryDto);

	/*
	 * StyleGalleryImage
	 */
	public List<VrStyleGalleryImageDto> getVrStyleGalleryImage(VrStyleGalleryImageDto vrStyleGalleryImageDto);

	public void insertVrStyleGalleryImage(VrStyleGalleryImageDto vrStyleGalleryImageDto);

	/*
	 * StyleMovie
	 */
	public List<VrStyleMovieDto> getVrStyleMovieList(VrStyleMovieDto vrStyleMovieDto);

	public VrStyleMovieDto getVrStyleMovieForm(VrStyleMovieDto vrStyleMovieDto);

	public void insertVrStyleMovie(VrStyleMovieDto vrStyleMovieDto);

	public void updateVrStyleMovie(VrStyleMovieDto vrStyleMovieDto);

	public void deleteVrStyleMovie(VrStyleMovieDto vrStyleMovieDto);

	/*
	 * StyleMusic
	 */
	public List<VrStyleMusicDto> getVrStyleMusicList(VrStyleMusicDto vrStyleMusicDto);

	public VrStyleMusicDto getVrStyleMusicForm(VrStyleMusicDto vrStyleMusicDto);

	public void insertVrStyleMusic(VrStyleMusicDto vrStyleMusicDto);

	public void updateVrStyleMusic(VrStyleMusicDto vrStyleMusicDto);

	public void deleteVrStyleMusic(VrStyleMusicDto vrStyleMusicDto);

}
