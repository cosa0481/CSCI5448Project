package com.project;

import java.util.List;
import java.util.Date;

public class Category {
	private int category_id;
	private String name;
	private String category_image;
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
