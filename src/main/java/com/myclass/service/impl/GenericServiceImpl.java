package com.myclass.service.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import com.myclass.service.GenericService;

public abstract class GenericServiceImpl<T, K extends Serializable> implements GenericService<T, K> {

	@Autowired
	protected JpaRepository<T, K> genericRepository;

	@Override
	public List<T> findAll() {
		return genericRepository.findAll();
	}

	@Override
	public abstract boolean add(T entity);

	@Override
	public Optional<T> findById(K id) {
		return genericRepository.findById(id);
	}

	@Override
	public boolean update(T entity) {
		if (genericRepository.saveAndFlush(entity) != null) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public abstract void removeById(K id);

	@SuppressWarnings("unchecked")
	@Override
	public List<T> findAllById(K id) {
		return genericRepository.findAllById((Iterable<K>) id);
	}

	@Override
	public abstract List<T> findAllByKeyWord(String keyword);

	@Override

	public abstract ResponseEntity<Object> setImage(MultipartFile fileUploaded, K id);

	@Override
	public abstract ResponseEntity<InputStreamResource> getImage(K id);
}
