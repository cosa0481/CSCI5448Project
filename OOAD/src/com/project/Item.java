package com.project;

import java.util.List;

public class Item {

	int id;
	String serial_no;
	float suggestedRetailPrice;
	String title;
	List<Sale> itemSales;

	Category category;
	List<Review> reviews;
	
	public float getCurrentPrice() {
		// calculate from itemSales, categorySales, membership
		// and region discounts
		float currentPrice = suggestedRetailPrice;
		float bestDiscount = 0;
		return currentPrice * (1 - bestDiscount);
	}
}
