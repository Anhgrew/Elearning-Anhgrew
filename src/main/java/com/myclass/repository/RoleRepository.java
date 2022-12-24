package com.myclass.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.myclass.entity.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, String> {
	List<Role> findByNameContaining(String name);

	Role findFirstByName(String name);

}
