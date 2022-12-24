package com.myclass.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.myclass.dto.UserDto;
import com.myclass.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
	@Query("SELECT u FROM users u WHERE (u.email like :email% or u.fullname like :email%) and u.isDelete =:isDelete ")
	List<User> findAllByEmail(@Param("email") String email, @Param("isDelete") int isDelete);

	User findByEmailAndIsDelete(String email, int isDelete);

	List<User> findByIsDelete(int isDelete);

	@Transactional
	@Modifying

	@Query("UPDATE users u SET u.address =:address , u.facebook =:facebook, u.fullname =:fullname, u.phone =:phone , u.website =:website where u.id =:id")
	void updateUserDetail(@Param("address") String address, @Param("facebook") String facebook,
			@Param("fullname") String fullname, @Param("phone") String phone, @Param("website") String website,
			@Param("id") String id);

	@Transactional
	@Modifying
	@Query("UPDATE users u SET u.avatar =:avatar where u.id =:id")
	void updatePicture(@Param("avatar") String avatar, @Param("id") String id);

	@Transactional
	@Modifying
	@Query("UPDATE users u SET u.password =:password where u.id =:id")
	void updateSecurity(@Param("password") String password, @Param("id") String id);

	@Query("SELECT new com.myclass.dto.UserDto(u.id, u.fullname, u.avatar) FROM com.myclass.entity.User u WHERE u.avatar =:avatar")
	public UserDto findFileByAvatarName(@Param("avatar") String avatar);

	@Query("select e.avatar from users e where e.id= ?1")
	public String getAvatar(String id);

	@Modifying
	@Query("update users set avatar = ?1 where id= ?2")
	public void setAvatar(String uri, String id);

	@Modifying
	@Query("update users u set u.password = :password where u.id = :id")
	void updatePassword(@Param("password") String password, @Param("id") String id);

}
