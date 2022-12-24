package com.myclass.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.transaction.Transactional;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.URL;

@Transactional
@Entity(name = "videos")
public class Video {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@NotBlank(message = "Vui lòng nhập tiêu đề")
	private String title;

	@NotBlank(message = "Vui lòng nhập Url")
	@URL(message = "Sai định dạng Url")
	private String url;

	// @NotEmpty(message = "Thời lượng bị trống")
	@Min(value = 0, message = "Thời lượng học >=0 ")
	@Column(name = "time_count")
	private int timeCount;

	@Column(name = "order_index")
	private int orderIndex;

	@Min(value = 0, message = "Vui long chon khoá học")
	@Column(name = "course_id")
	private int courseId;

	// @NotBlank(message = "Chọn hình ảnh")
	// private String image;

	@ManyToOne
	@JoinColumn(name = "course_id", insertable = false, updatable = false)
	private Course course;

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public Video() {
	}

	public Video(int id, String title, String url, int timeCount, int orderIndex, int courseId) {

		this.id = id;
		this.title = title;
		this.url = url;
		this.timeCount = timeCount;
		this.orderIndex = orderIndex;
		this.courseId = courseId;
		// this.image = image;
	}

	public Video(String title, String url, int timeCount, int orderIndex, int courseId) {

		this.title = title;
		this.url = url;
		this.timeCount = timeCount;
		this.orderIndex = orderIndex;
		this.courseId = courseId;

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int getTimeCount() {
		return timeCount;
	}

	public void setTimeCount(int timeCount) {
		this.timeCount = timeCount;
	}

	public int getOrderIndex() {
		return orderIndex;
	}

	public void setOrderIndex(int orderIndex) {
		this.orderIndex = orderIndex;
	}

	public int getCourseId() {
		return courseId;
	}

	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}

}
