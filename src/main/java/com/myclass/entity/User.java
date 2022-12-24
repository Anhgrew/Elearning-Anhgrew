package com.myclass.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Table(uniqueConstraints = @UniqueConstraint(columnNames = "email"))
@Entity(name = "users")
public class User {
	@Id
	private String id;

	@NotBlank(message = "Vui lòng nhập email!")
	@Email(message = "Email không đúng định dạng!")
	private String email;

	// @NotBlank(message = "Vui lòng nhập họ tên!")
	private String fullname;

	private String password;

	private String confirm;
	private String avatar;

	private int isDelete;

	public int isDelete() {
		return isDelete;
	}

	public void setDelete(int isDelete) {
		this.isDelete = isDelete;
	}

	@OneToMany(mappedBy = "lecturer", fetch = FetchType.LAZY)
	private List<Course> courseOfLecturer;

	public List<Course> getCourseOfLecturer() {
		return courseOfLecturer;
	}

	public void setCourseOfLecturer(List<Course> courseOfLecturer) {
		this.courseOfLecturer = courseOfLecturer;
	}

	public User(String id,
			@NotBlank(message = "Vui lòng nhập email!") @Email(message = "Email không đúng định dạng!") String email,
			String fullname, String password, String confirm, String avatar, String phone, String address,
			String website, String facebook, String roleId, Role role) {
		super();
		this.id = id;
		this.email = email;
		this.fullname = fullname;
		this.password = password;
		this.confirm = confirm;
		this.avatar = avatar;
		this.phone = phone;
		this.address = address;
		this.website = website;
		this.facebook = facebook;
		this.roleId = roleId;
		this.role = role;
	}

	public String getConfirm() {
		return confirm;
	}

	public void setConfirm(String confirm) {
		this.confirm = confirm;
	}

	public List<UserCourse> getUserCourses() {
		return userCourses;
	}

	public void setUserCourses(List<UserCourse> userCourses) {
		this.userCourses = userCourses;
	}

	private String phone;
	private String address;
	private String website;
	private String facebook;

	@Column(name = "role_id")
	@NotBlank(message = "Vui lòng chọn loại người dùng!")
	private String roleId;

	@ManyToOne
	@JoinColumn(name = "role_id", insertable = false, updatable = false)
	private Role role;

	public User() {
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public User(String id, String email, String fullname, String password, String avatar, String phone, String address,
			String website, String facebook, String roleId) {

		this.id = id;
		this.email = email;
		this.fullname = fullname;
		this.password = password;
		this.avatar = avatar;
		this.phone = phone;
		this.address = address;
		this.website = website;
		this.facebook = facebook;
		this.roleId = roleId;
	}

	public User(String email, String fullname, String password, String avatar, String phone, String address,
			String website, String facebook, String roleId) {

		this.email = email;
		this.fullname = fullname;
		this.password = password;
		this.avatar = avatar;
		this.phone = phone;
		this.address = address;
		this.website = website;
		this.facebook = facebook;
		this.roleId = roleId;
	}

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

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
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

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
	private List<UserCourse> userCourses;

	@Override
	public String toString() {
		return "User [id=" + id + ", email=" + email + ", fullname=" + fullname + ", password=" + password + ", avatar="
				+ avatar + ", phone=" + phone + ", address=" + address + ", website=" + website + ", facebook="
				+ facebook + ", roleId=" + roleId + ", role=" + role + "]";
	}
}
