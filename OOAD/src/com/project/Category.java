package com.project;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import java.util.Date;

@Entity
@Table(name="Category", 
	   uniqueConstraints={@UniqueConstraint(columnNames={"ID"})})
public class Category {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID", nullable=false, unique=true, length=11)
	private int category_id;

	@Column(name="NAME")
	private String name;

	@Column(name="IMAGE")
	private String category_image;
	//TODO
	private List<Sale> categorySales;
	
	public int getCategory_id() {
		return category_id;
	}
	public void setCategory_id(int category_id) {
		this.category_id = category_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCategory_image() {
		return category_image;
	}
	public void setCategory_image(String category_image) {
		this.category_image = category_image;
	}
	public List<Sale> getCategorySales() {
		return categorySales;
	}
	public void setCategorySales(List<Sale> categorySales) {
		this.categorySales = categorySales;
	}
	public void addCategorySale(Date startDate, Date endDate, double percentDiscount) {

	}
}
