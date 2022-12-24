package com.myclass.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.myclass.entity.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
	List<Category> findByTitleStartingWith(String title);

	@Query("select u.icon from Category u where u.id = ?1")
	String getIcon(int id);

	@Transactional
	@Modifying
	@Query("update Category u set u.icon = ?1 where u.id = ?2")
	void setIcon(String path, int id);

}
