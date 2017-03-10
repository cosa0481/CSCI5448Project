package com.project;

import java.util.List;

public class Customer extends Person {

	private Membership membership;
	private Cart cart;
	
	public List<Order> getOrders(){
		return null;
	}
	
	public void checkout() {
		
	}

	public void writeReview(Item product, Review review) {
		product.addReview(review);
	}
	
}
