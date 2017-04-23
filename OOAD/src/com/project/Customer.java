package com.project;

import java.util.List;

import javax.persistence.AttributeOverride;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.Cascade;


@Entity
@Table(name="Customer")
@AttributeOverride(name="id", column=@Column(name="customer_id"))
public class Customer extends Person {

	//TODO
	@Transient
	private Membership membership;

	@OneToOne( mappedBy="customer",cascade={CascadeType.ALL})
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
