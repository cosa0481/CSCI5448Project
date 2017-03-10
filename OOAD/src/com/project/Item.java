package com.project;

import java.util.List;

public class Item {

	int id;
	String serial_no;
	float suggestedRetailPrice;
	private int inInventory;
	String title;
	List<Sale> itemSales;
	float numStars;

	public float getNumStars() {
		return numStars;
	}

	public void setNumStars(float numStars) {
		this.numStars = numStars;
	}

	Category category;
	List<Review> reviews;
	
	public void addReview(Review review) {
	}
	
	public float getCurrentPrice() {
		// calculate from itemSales, categorySales, membership
		// and region discounts
		float currentPrice = suggestedRetailPrice;
		float bestDiscount = 0;
		return currentPrice * (1 - bestDiscount);
	}
	
	public int getInInventory() {
		return inInventory;
	}

	public void setInInventory(int inInventory) {
		this.inInventory = inInventory;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSerial_no() {
		return serial_no;
	}

	public void setSerial_no(String serial_no) {
		this.serial_no = serial_no;
	}

	public float getSuggestedRetailPrice() {
		return suggestedRetailPrice;
	}

	public void setSuggestedRetailPrice(float suggestedRetailPrice) {
		this.suggestedRetailPrice = suggestedRetailPrice;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<Sale> getItemSales() {
		return itemSales;
	}

	public void setItemSales(List<Sale> itemSales) {
		this.itemSales = itemSales;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public List<Review> getReviews() {
		return reviews;
	}

	public void setReviews(List<Review> reviews) {
		this.reviews = reviews;
	}
}
