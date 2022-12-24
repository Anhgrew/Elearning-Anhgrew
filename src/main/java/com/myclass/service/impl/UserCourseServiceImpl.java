package com.myclass.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.myclass.entity.Course;
import com.myclass.entity.UserCourse;

import com.myclass.repository.UserCourseRepository;
import com.myclass.service.CourseService;
import com.myclass.service.UserCourseService;

@Service
public class UserCourseServiceImpl extends GenericServiceImpl<UserCourse, Integer> implements UserCourseService {

	@Autowired
	private UserCourseRepository userCourseRepository;

	@Autowired
	CourseService courseService;

	@Override
	public boolean add(UserCourse entity) {
		try {

			UserCourse userCourse = userCourseRepository.findByUserIdAndCourseId(entity.getUserId(),
					entity.getCourseId());
			if (userCourse == null && userCourseRepository.save(entity) != null) {
				Course curCourse = courseService.findById(entity.getCourseId()).get();
				curCourse.setViewCount(curCourse.getViewCount() + 1);
				courseService.update(curCourse);
				return true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public List<UserCourse> findAllByKeyWord(String keyword) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<UserCourse> findByCourseId(int id) {
		return userCourseRepository.findByCourseId(id);
	}

	@Override
	public List<UserCourse> findByUserId(String userId) {
		return userCourseRepository.findByUserId(userId);
	}

	@Override
	public int countByUserId(String userId) {
		return userCourseRepository.countByUserId(userId);
	}

	@Override
	public int countByCourseId(int courseId) {
		return userCourseRepository.countByCourseId(courseId);
	}

	@Override
	public void removeById(Integer id) {
		userCourseRepository.deleteById(id);

	}

	@Override
	public UserCourse findByUserIdAndCourseId(String userId, int courseId) {
		return userCourseRepository.findByUserIdAndCourseId(userId, courseId);
	}

	@Override
	public void delete(int id) {
		userCourseRepository.deleteById(id);

	}

	@Override
	public void deleteByUserIdAndCourseId(String userId, int courseId) {
		userCourseRepository.deleteByUserIdAndCourseId(userId, courseId);
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
