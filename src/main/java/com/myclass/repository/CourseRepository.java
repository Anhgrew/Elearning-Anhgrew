package com.myclass.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.myclass.dto.CourseDto;
import com.myclass.entity.Course;

@Repository
public interface CourseRepository extends JpaRepository<Course, Integer> {
	List<Course> findByTitleContainingAndIsDelete(String title, int isDelete);

	List<Course> findByCategoryIdAndIsDelete(int categoryId, int isDelete);

	void deleteByIdAndIsDelete(int id, int isDelete);

	@Query("SELECT new com.myclass.dto.CourseDto(u.id, u.title, u.image) FROM Course u WHERE u.image =:image")
	public CourseDto findFileByName(@Param("image") String image);

	List<Course> findByIsDelete(int isDelete);

	@Query("select u.image from Course u where u.id = ?1")
	String getImage(int id);

	@Transactional
	@Modifying
	@Query("update Course u set u.image = ?1 where u.id = ?2")
	void setImage(String path, int id);
}
