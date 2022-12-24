package com.myclass.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.myclass.entity.UserCourse;

@Repository
public interface UserCourseRepository extends JpaRepository<UserCourse, Integer> {
	List<UserCourse> findByCourseId(int courseId);

	List<UserCourse> findByUserId(String userId);

	int countByUserId(String userId);

	int countByCourseId(int courseId);

	UserCourse findByUserIdAndCourseId(String userId, int courseId);

	@Modifying
	@Transactional
	@Query("delete from UserCourse u where u.userId = ?1 and u.courseId = ?2")
	void deleteByUserIdAndCourseId(String userId, int courseId);

	@Query(value = "insert into user_courses (user_id,course_id,role_id) values (:userId,:courseId,:roleId) ", nativeQuery = true)
	@Transactional
	boolean insert(@Param("userId") String userId, @Param("courseId") int courseId, @Param("roleId") String roleId);

}
