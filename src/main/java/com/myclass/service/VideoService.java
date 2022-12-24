package com.myclass.service;

import java.util.List;

import com.myclass.entity.Video;

public interface VideoService extends GenericService<Video, Integer> {
	List<Video> findByCourseId(int id);

	List<Video> urlId(int id);
}
