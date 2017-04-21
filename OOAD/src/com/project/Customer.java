package com.project;

import java.util.List;
import javax.persistence.*;

@Entity
@Table(name="Customer", 
	   uniqueConstraints={@UniqueConstraint(columnNames={"ID"})})
public class Customer extends Person {

	//TODO
	private Membership membership;
	//TODO
	private Cart cart;

	
	public List<Order> getOrders() {
		return null;
	}
	
	public void addOrder(Order order) {
	}

	public Order checkout() {
		return null;
	}

	public void writeReview(Item product, Review review) {
		product.addReview(review);
	}

	public Membership getMembership() {
		return membership;
	}

	public void setMembership(Membership membership) {
		this.membership = membership;
	}

	public Cart getCart() {
		return cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}
	
	public IpaymentMethod getPaymentMethod(){
		return null;
	}

}
