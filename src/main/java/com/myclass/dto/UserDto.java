package com.myclass.dto;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.myclass.entity.Course;
import com.myclass.entity.Role;
import com.myclass.entity.User;
import com.myclass.entity.UserCourse;

public class UserDto {

	private String id;

	private String email;

	private String fullname;

	private String password;

	private String confirm;

	public UserDto(String id, String fullname, String avatar) {
		super();
		this.id = id;
		this.fullname = fullname;
		this.avatar = avatar;
	}

	private String avatar;

	private int isDelete;

	private String phone;
	private String address;
	private String website;
	private String facebook;
	private MultipartFile[] fileDatas;

	public MultipartFile[] getFileDatas() {
		return fileDatas;
	}

	public void setFileDatas(MultipartFile[] fileDatas) {
		this.fileDatas = fileDatas;
	}

	private List<UserCourse> userCourses;
	private List<Course> courseOfLecturer;

	public UserDto(User user) {
		this.id = user.getId();
		this.email = user.getEmail();
		this.fullname = user.getFullname();
		this.password = user.getPassword();
		this.confirm = user.getConfirm();
		this.avatar = user.getAvatar();
		this.isDelete = user.isDelete();
		this.phone = user.getPhone();
		this.address = user.getAddress();
		this.website = user.getWebsite();
		this.facebook = user.getFacebook();
		this.userCourses = user.getUserCourses();
		this.courseOfLecturer = user.getCourseOfLecturer();
		this.roleId = user.getRoleId();
		this.role = user.getRole();
	}

	private String roleId;

	private Role role;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirm() {
		return confirm;
	}

	public void setConfirm(String confirm) {
		this.confirm = confirm;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public int getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(int isDelete) {
		this.isDelete = isDelete;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public String getFacebook() {
		return facebook;
	}

	public void setFacebook(String facebook) {
		this.facebook = facebook;
	}

	public List<UserCourse> getUserCourses() {
		return userCourses;
	}

	public void setUserCourses(List<UserCourse> userCourses) {
		this.userCourses = userCourses;
	}

	public List<Course> getCourseOfLecturer() {
		return courseOfLecturer;
	}

	public void setCourseOfLecturer(List<Course> courseOfLecturer) {
		this.courseOfLecturer = courseOfLecturer;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public UserDto() {
		super();
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public UserDto(String id, String avatar) {
		super();
		this.id = id;
		this.avatar = avatar;
	}

	public UserDto(String id, String email, String fullname, String phone, String address, String website,
			String facebook) {
		super();
		this.id = id;
		this.email = email;
		this.fullname = fullname;
		this.phone = phone;
		this.address = address;
		this.website = website;
		this.facebook = facebook;
	}

}
