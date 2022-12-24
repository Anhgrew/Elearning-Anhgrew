package com.myclass.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.myclass.entity.Category;
import com.myclass.repository.CategoryRepository;
import com.myclass.service.CategoryService;

@Service
public class CategoryServiceImpl extends GenericServiceImpl<Category, Integer> implements CategoryService {

	private final String SRC = "D:/upload/image/category/";

	@Autowired
	protected CategoryRepository categoryRepository;

	@Override
	public boolean add(Category entity) {
		try {
			if (categoryRepository.save(entity) != null) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public List<Category> findAllByKeyWord(String keyword) {
		return categoryRepository.findByTitleStartingWith(keyword);
	}

	@Override
	public void removeById(Integer id) {
		categoryRepository.deleteById(id);

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
				categoryRepository.setIcon(path, id);
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
			String path = categoryRepository.getIcon(id);
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

}
