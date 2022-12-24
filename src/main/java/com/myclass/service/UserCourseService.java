package com.myclass.service;

import java.util.List;

import com.myclass.entity.UserCourse;

public interface UserCourseService extends GenericService<UserCourse, Integer> {
	List<UserCourse> findByCourseId(int id);

	List<UserCourse> findByUserId(String userId);

	UserCourse findByUserIdAndCourseId(String userId, int courseId);

	void deleteByUserIdAndCourseId(String userId, int courseId);

	int countByUserId(String userId);

	int countByCourseId(int courseId);

	void delete(int id);

}
