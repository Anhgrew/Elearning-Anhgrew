package com.myclass.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.transaction.Transactional;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Transactional
@Table(name = "courses")
public class Course {
	@Override
	public String toString() {
		return "Course [id=" + id + ", title=" + title + ", image=" + image + ", leturesCount=" + leturesCount
				+ ", author=" + author + ", hourCount=" + hourCount + ", viewCount=" + viewCount + ", price=" + price
				+ ", discount=" + discount + ", promotionPrice=" + promotionPrice + ", description=" + description
				+ ", content=" + content + ", categoryId=" + categoryId + ", lastUpdate=" + lastUpdate + ", category="
				+ category + ", videos=" + videos + ", targets=" + targets + ", userCourses=" + userCourses + "]";
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@NotBlank(message = "Vui lòng nhập tiêu đề")
	private String title;

	// @NotBlank(message = "Vui lòng đăng hình ảnh")
	private String image;

	@NotNull(message = "Nhập số lượng bài học")
	@Min(value = 0, message = "Số bài học không được âm")
	@Column(name = "letures_count")
	private int leturesCount;

	@NotBlank(message = "Vui lòng nhập tên tác giả")
	private String author;

	String lecturerId;

	private int isDelete;

	public int isDelete() {
		return isDelete;
	}

	public void setDelete(int isDelete) {
		this.isDelete = isDelete;
	}

	@ManyToOne
	@JoinColumn(name = "lecturerId", insertable = false, updatable = false)
	User lecturer;

	public String getLecturerId() {
		return lecturerId;
	}

	public void setLecturerId(String lecturerId) {
		this.lecturerId = lecturerId;
	}

	public User getLecturer() {
		return lecturer;
	}

	public void setLecturer(User lecturer) {
		this.lecturer = lecturer;
	}

	public Course(int id, @NotBlank(message = "Vui lòng nhập tiêu đề") String title, String image,
			@NotNull(message = "Nhập số lượng bài học") @Min(value = 0, message = "Số bài học không được âm") int leturesCount,
			@NotBlank(message = "Vui lòng nhập tên tác giả") String author,
			@Min(value = 0, message = "Giờ học >= 0") int hourCount, int viewCount,
			@Min(value = 0, message = "Giá tiền không được âm") long price, int discount, long promotionPrice,
			@NotBlank(message = "Vui lòng nhập mô tả") String description, String content,
			@Min(value = 0, message = "Vui lòng chọn danh mục bạn muốn học") int categoryId, Date lastUpdate,
			Category category) {
		super();
		this.id = id;
		this.title = title;
		this.image = image;
		this.leturesCount = leturesCount;
		this.author = author;
		this.hourCount = hourCount;
		this.viewCount = viewCount;
		this.price = price;
		this.discount = discount;
		this.promotionPrice = promotionPrice;
		this.description = description;
		this.content = content;
		this.categoryId = categoryId;
		this.lastUpdate = lastUpdate;
		this.category = category;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public List<Video> getVideos() {
		return videos;
	}

	public void setVideos(List<Video> videos) {
		this.videos = videos;
	}

	public List<Target> getTargets() {
		return targets;
	}

	public void setTargets(List<Target> targets) {
		this.targets = targets;
	}

	public List<UserCourse> getUserCourses() {
		return userCourses;
	}

	public void setUserCourses(List<UserCourse> userCourses) {
		this.userCourses = userCourses;
	}

	@Min(value = 0, message = "Giờ học >= 0")
	@Column(name = "hour_count")
	private int hourCount;

	@Column(name = "view_count")
	private int viewCount;

	@Min(value = 0, message = "Giá tiền không được âm")
	private long price;

	private int discount;

	@Column(name = "promotion_price")
	private long promotionPrice;

	@NotBlank(message = "Vui lòng nhập mô tả")
	private String description;
	private String content;

	@Min(value = 0, message = "Vui lòng chọn danh mục bạn muốn học")
	@Column(name = "category_id")
	private int categoryId;

	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd-MMM-YYYY")
	@Column(name = "last_update")
	private Date lastUpdate;

	@ManyToOne
	@JoinColumn(name = "category_id", insertable = false, updatable = false)
	private Category category;

	@OneToMany(mappedBy = "course", fetch = FetchType.LAZY)
	List<Video> videos;

	public Category getCategory() {
		return category;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "course")
	private List<Target> targets;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "course")
	private List<UserCourse> userCourses;

	public Course(int id, @NotBlank(message = "Vui lòng nhập tiêu đề") String title, String image,
			@NotNull(message = "Nhập số lượng bài học") @Min(value = 0, message = "Số bài học không được âm") int leturesCount,
			@NotBlank(message = "Vui lòng nhập tên tác giả") String author,
			@Min(value = 0, message = "Giờ học >= 0") int hourCount,
			@Min(value = 0, message = "Giá tiền không được âm") long price, int discount,
			@NotBlank(message = "Vui lòng nhập mô tả") String description, String content,
			@Min(value = 0, message = "Vui lòng chọn danh mục bạn muốn học") int categoryId) {
		super();
		this.id = id;
		this.title = title;
		this.image = image;
		this.leturesCount = leturesCount;
		this.author = author;
		this.hourCount = hourCount;
		this.price = price;
		this.discount = discount;
		this.description = description;
		this.content = content;
		this.categoryId = categoryId;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Course() {
		super();
	}

	public Course(int id, String title, String image, int leturesCount, int hourCount, int viewCount, long price,
			int discount, long promotionPrice, String description, String content, int categoryId, Date lastUpdate) {

		this.id = id;
		this.title = title;
		this.image = image;
		this.leturesCount = leturesCount;
		this.hourCount = hourCount;
		this.viewCount = viewCount;
		this.price = price;
		this.discount = discount;
		this.promotionPrice = promotionPrice;
		this.description = description;
		this.content = content;
		this.categoryId = categoryId;
		this.lastUpdate = lastUpdate;
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

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public int getLeturesCount() {
		return leturesCount;
	}

	public void setLeturesCount(int leturesCount) {
		this.leturesCount = leturesCount;
	}

	public int getHourCount() {
		return hourCount;
	}

	public void setHourCount(int hourCount) {
		this.hourCount = hourCount;
	}

	public int getViewCount() {
		return viewCount;
	}

	public void setViewCount(int viewCount) {
		this.viewCount = viewCount;
	}

	public long getPrice() {
		return price;
	}

	public void setPrice(long price) {
		this.price = price;
	}

	public int getDiscount() {
		return discount;
	}

	public void setDiscount(int discount) {
		this.discount = discount;
	}

	public long getPromotionPrice() {
		return promotionPrice;
	}

	public void setPromotionPrice(long promotionPrice) {
		this.promotionPrice = promotionPrice;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public Date getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(Date lastUpdate) {
		this.lastUpdate = lastUpdate;
	}
}
