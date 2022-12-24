package com.myclass.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.myclass.dto.CourseDto;
import com.myclass.entity.Course;

public interface CourseService extends GenericService<Course, Integer> {
	List<Course> findByCategoryId(int categoryId);

	List<Course> findCourseByUserId(String id);

	List<Course> findCourseByUserIdAndKeyWord(String id, String keyword);

	List<Course> findByLecturerId(String id);

	public CourseDto findFileByName(String image);

	boolean checkFile(MultipartFile[] fileDatas);

}
