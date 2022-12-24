package com.myclass.service.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.myclass.entity.Role;
import com.myclass.repository.RoleRepository;
import com.myclass.service.RoleService;

@Service
public class RoleServiceImpl extends GenericServiceImpl<Role, String> implements RoleService {

	@Autowired
	protected RoleRepository roleRepository;

	@Override
	public boolean add(Role entity) {
		try {
			String id = UUID.randomUUID().toString();
			entity.setId(id);
			if (roleRepository.save(entity) != null) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public List<Role> findAllByKeyWord(String keyword) {
		return roleRepository.findByNameContaining(keyword);
	}

	@Override
	public void removeById(String id) {
		roleRepository.deleteById(id);

	}

	@Override
	public ResponseEntity<Object> setImage(MultipartFile fileUploaded, String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<InputStreamResource> getImage(String id) {
		// TODO Auto-generated method stub
		return null;
	}

}
