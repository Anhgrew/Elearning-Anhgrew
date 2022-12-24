package com.myclass.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.myclass.entity.Video;
import com.myclass.repository.VideoRepository;
import com.myclass.service.VideoService;

@Service
public class VideoServiceImpl extends GenericServiceImpl<Video, Integer> implements VideoService {
	@Autowired
	protected VideoRepository videoRepository;

	@Override
	public boolean add(Video entity) {
		try {
			if (videoRepository.save(entity) != null) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public List<Video> findAllByKeyWord(String keyword) {
		return videoRepository.findByTitleContaining(keyword);
	}

	@Override
	public List<Video> findByCourseId(int id) {
		return videoRepository.findByCourseId(id);
	}

	@Override
	public List<Video> urlId(int id) {
		List<Video> videos = videoRepository.findByCourseId(id);
		String tmpUrl;
		for (Video video : videos) {
			tmpUrl = ((video.getUrl()).split("be/"))[1];
			video.setUrl(tmpUrl);
		}

		return videos;
	}

	@Override
	public void removeById(Integer id) {
		videoRepository.deleteById(id);

	}

	@Override
	public ResponseEntity<Object> setImage(MultipartFile fileUploaded, Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<InputStreamResource> getImage(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

}
