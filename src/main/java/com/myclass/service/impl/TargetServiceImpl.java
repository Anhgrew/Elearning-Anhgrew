package com.myclass.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.myclass.entity.Course;
import com.myclass.entity.Target;
import com.myclass.repository.TargetRepository;
import com.myclass.service.CourseService;
import com.myclass.service.TargetService;

@Service
public class TargetServiceImpl extends GenericServiceImpl<Target, Integer> implements TargetService {

	@Autowired
	protected TargetRepository targetRepository;

	@Autowired
	protected CourseService courseService;

	@Override
	public boolean add(Target entity) {
		try {

			if (targetRepository.save(entity) != null) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public List<Target> findAllByKeyWord(String keyword) {
		return targetRepository.findByTitleContaining(keyword);
	}

	@Override
	public List<Target> findByCourseId(int id) {
		Course course = courseService.findById(id).get();
		if (course != null && course.isDelete() == 0) {
			return targetRepository.findByCourseId(id);
		} else {
			return null;
		}

	}

	@Override
	public void removeById(Integer id) {
		targetRepository.deleteById(id);

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
