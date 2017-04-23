package com.project;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="Seller")
public class Seller extends Person {

	@Column(name="sellerRating", length=20, nullable=true)
	private int sellerRating;
	
	public void addItem(Item product) {
		
	}
	
	public List<Review> getReviews() {
		return null;
	}

	public void setReviews(List<Review> reviews) {

	}
	
	public List<Item> getSellerItems() {
		return null;
	}

	public void setSellerItems(List<Item> sellerItems) {
		
	}

	public int getSellerRating() {
		return sellerRating;
	}

	public void setSellerRating(int sellerRating) {
		this.sellerRating = sellerRating;
	}

	public void addSale(Sale sale, Item product) {
		
	}
}
