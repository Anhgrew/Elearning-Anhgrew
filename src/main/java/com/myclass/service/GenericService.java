package com.myclass.service;

import java.util.List;
import java.util.Optional;

import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

public interface GenericService<T, K> {
	List<T> findAll();

	List<T> findAllById(K id);

	List<T> findAllByKeyWord(String keyword);

	boolean add(T entity);

	Optional<T> findById(K id);

	boolean update(T entity);

	void removeById(K id);

	ResponseEntity<Object> setImage(MultipartFile fileUploaded, K id);

	ResponseEntity<InputStreamResource> getImage(K id);

}
