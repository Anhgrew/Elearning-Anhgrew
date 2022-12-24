package com.myclass.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.myclass.entity.Video;

@Repository
public interface VideoRepository extends JpaRepository<Video, Integer> {
	List<Video> findByTitleContaining(String title);

	List<Video> findByCourseId(int id);
	
}
