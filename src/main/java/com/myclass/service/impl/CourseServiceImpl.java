package com.myclass.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.myclass.dto.CourseDto;
import com.myclass.entity.Course;
import com.myclass.entity.User;
import com.myclass.entity.UserCourse;
import com.myclass.repository.CourseRepository;
import com.myclass.service.CourseService;
import com.myclass.service.UserCourseService;
import com.myclass.service.UserService;

@Service
public class CourseServiceImpl extends GenericServiceImpl<Course, Integer> implements CourseService {

	private final String SRC = "D:/upload/image/category/";

	@Autowired
	protected CourseRepository courseRepository;

	@Autowired
	protected UserCourseService userCourseService;

	@Autowired
	protected UserService userService;

	@Autowired
	CourseService courseService;

	@Override
	public List<Course> findAll() {
		return courseRepository.findByIsDelete(0);
	}

	@Override
	public boolean add(Course entity) {
		try {
			Date date = new Date();
			entity.setLastUpdate(date);
			entity.setPromotionPrice(entity.getPrice() - entity.getPrice() * entity.getDiscount() / 100);
			entity.setDelete(0);
			if (courseRepository.save(entity) != null) {

				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean update(Course entity) {
		try {
			entity.setPromotionPrice(entity.getPrice() - entity.getPrice() * entity.getDiscount() / 100);
			Date date = new Date();
			entity.setLastUpdate(date);
			entity.setDelete(0);
			if (courseRepository.saveAndFlush(entity) != null) {

				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public List<Course> findAllByKeyWord(String keyword) {
		return courseRepository.findByTitleContainingAndIsDelete(keyword, 0);
	}

	@Override
	public List<Course> findByCategoryId(int categoryId) {
		return courseRepository.findByCategoryIdAndIsDelete(categoryId, 0);
	}

	@Override
	public List<Course> findCourseByUserId(String id) {
		User user = userService.findById(id).get();
		List<Course> courses = new LinkedList<Course>();
		List<UserCourse> userCourse = user.getUserCourses();
		for (UserCourse item : userCourse) {
			Course tmpCourse = courseRepository.findById(item.getCourseId()).get();
			if (tmpCourse.isDelete() == 0) {
				courses.add(tmpCourse);
			}
		}
		return courses;
	}

	@Override
	public List<Course> findCourseByUserIdAndKeyWord(String id, String keyword) {
		List<Course> tmpCourse = courseService.findCourseByUserId(id);
		List<Course> results = new LinkedList<Course>();
		for (Course course : tmpCourse) {
			if (course.getTitle().contains(keyword) && course.isDelete() == 0) {
				results.add(course);
			}
		}
		return results;
	}

	@Override
	public void removeById(Integer id) {
		Course course = courseRepository.findById(id).get();
		List<UserCourse> enrollList = userCourseService.findByCourseId(id);
		for (UserCourse userCourse : enrollList) {
			userCourseService.delete(userCourse.getId());
		}
		course.setDelete(1);
		course.setViewCount(0);
		courseRepository.saveAndFlush(course);
	}

	@Override
	public List<Course> findByLecturerId(String id) {
		User lecturer = userService.findById(id).get();
		List<Course> courses = new LinkedList<Course>();
		if (lecturer != null) {
			for (Course course : lecturer.getCourseOfLecturer()) {
				if (course.isDelete() == 0) {
					courses.add(course);
				}
			}

		}

		return courses;
	}

	@Override
	public CourseDto findFileByName(String image) {
		return courseRepository.findFileByName(image);
	}

	@Override
	@Transactional
	public ResponseEntity<Object> setImage(MultipartFile fileUploaded, Integer id) {
		if (fileUploaded != null || id != null) {
			String type = fileUploaded.getContentType().replace("image/", "");
			File data = new File(SRC + id + "." + type);
			String path = id + "." + type;
			try {
				data.createNewFile();
				FileOutputStream fos = new FileOutputStream(data);
				fos.write(fileUploaded.getBytes());
				courseRepository.setImage(path, id);
				fos.close();
			} catch (Exception e) {
				e.printStackTrace();
				return new ResponseEntity<Object>("Unknown errors!", HttpStatus.EXPECTATION_FAILED);
			}
			return new ResponseEntity<Object>(HttpStatus.OK);
		} else {
			return new ResponseEntity<Object>("Failed data!", HttpStatus.BAD_REQUEST);
		}
	}

	@Override
	public ResponseEntity<InputStreamResource> getImage(Integer id) {
		if (id != null) {
			String pathDefault = "007.png";
			String path = courseRepository.getImage(id);
			File data = null;
			if (path != null) {
				data = new File(SRC + path);
			} else {
				data = new File(SRC + pathDefault);
			}
			if (data.exists()) {
				try {
					InputStream is = new FileInputStream(data);
					return ResponseEntity.ok().contentLength(data.length()).contentType(MediaType.IMAGE_JPEG)
							.contentType(MediaType.IMAGE_PNG).body(new InputStreamResource(is));
				} catch (Exception e) {
					e.printStackTrace();
					return new ResponseEntity<InputStreamResource>(HttpStatus.EXPECTATION_FAILED);
				}

			} else {
				return new ResponseEntity<InputStreamResource>(HttpStatus.EXPECTATION_FAILED);
			}
		} else {
			return new ResponseEntity<InputStreamResource>(HttpStatus.BAD_REQUEST);
		}
	}

	@Override
	public boolean checkFile(MultipartFile[] fileDatas) {
		for (MultipartFile file : fileDatas) {
			if (!file.isEmpty()) {
				return false;
			}
		}
		return true;
	}

}
