package com.myclass.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.transaction.Transactional;
import javax.validation.constraints.NotBlank;

@Entity
@Transactional
@Table(name = "categories")
public class Category {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@NotBlank(message = "Vui lòng nhập tiêu đề!")
	private String title;
	// @NotBlank(message = "Vui lòng nhập icon!")
	private String icon;

	@Column(name = "order_index")
	private int orderIndex;

	@OneToMany(mappedBy = "category", fetch = FetchType.LAZY, cascade = { CascadeType.REMOVE, CascadeType.REFRESH,
			CascadeType.PERSIST })
	private List<Course> courses;

	public Category() {
		// TODO Auto-generated constructor stub
	}

	public Category(int id, String title, String icon, int orderIndex) {

		this.id = id;
		this.title = title;
		this.icon = icon;
		this.orderIndex = orderIndex;
	}

	public Category(String title, String icon, int orderIndex) {

		this.title = title;
		this.icon = icon;
		this.orderIndex = orderIndex;
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

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public int getOrderIndex() {
		return orderIndex;
	}

	public void setOrderIndex(int orderIndex) {
		this.orderIndex = orderIndex;
	}
}
