package com.myclass.dto;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.myclass.entity.Category;
import com.myclass.entity.Target;
import com.myclass.entity.User;
import com.myclass.entity.UserCourse;
import com.myclass.entity.Video;

public class CourseDto {
	private int id;
	private String title;
	private int leturesCount;
	private String imageUrl;
	private String author;
	private MultipartFile[] fileDatas;
	private int hourCount;
	private int viewCount;
	private long price;
	private int discount;
	private long promotionPrice;

	public CourseDto(int id, String title, int leturesCount, String imageUrl, String author, int hourCount,
			int viewCount, long price, int discount, long promotionPrice, String description, String content,
			String lecturerId) {
		super();
		this.id = id;
		this.title = title;
		this.leturesCount = leturesCount;
		this.imageUrl = imageUrl;
		this.author = author;
		this.hourCount = hourCount;
		this.viewCount = viewCount;
		this.price = price;
		this.discount = discount;
		this.promotionPrice = promotionPrice;
		this.description = description;
		this.content = content;
		this.lecturerId = lecturerId;
	}

	private String description;
	private String content;
	private int categoryId;
	private Category category;
	List<Video> videos;
	private List<Target> targets;
	private List<UserCourse> userCourses;
	private int isDelete;

	String lecturerId;
	User lecturer;

	public CourseDto() {
		super();
	}

	public CourseDto(int id, String title, String imageUrl) {
		super();
		this.id = id;
		this.title = title;
		this.imageUrl = imageUrl;
	}

	public CourseDto(int id, String title, int leturesCount, String imageUrl, String author, int hourCount,
			int viewCount, long price, int discount, long promotionPrice, String description, String content,
			int categoryId, int isDelete, MultipartFile[] fileDatas) {
		super();
		this.id = id;
		this.title = title;
		this.leturesCount = leturesCount;
		this.imageUrl = imageUrl;
		this.author = author;
		this.hourCount = hourCount;
		this.viewCount = viewCount;
		this.price = price;
		this.discount = discount;
		this.promotionPrice = promotionPrice;
		this.description = description;
		this.content = content;
		this.categoryId = categoryId;
		this.isDelete = isDelete;
		this.fileDatas = fileDatas;
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

	public int getLeturesCount() {
		return leturesCount;
	}

	public void setLeturesCount(int leturesCount) {
		this.leturesCount = leturesCount;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

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

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
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

	public int getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(int isDelete) {
		this.isDelete = isDelete;
	}

	public MultipartFile[] getFileDatas() {
		return fileDatas;
	}

	public void setFileDatas(MultipartFile[] fileDatas) {
		this.fileDatas = fileDatas;
	}

}
