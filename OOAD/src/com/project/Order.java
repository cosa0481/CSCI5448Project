package com.project;

import java.util.Date;
import java.util.List;

public class Order {

	private Customer customer;
	private Date orderDate;
	private List<Item> orderItems;
	private float orderValue;
	private Shipping shippingMethod;
	private String shippingAddress;
	
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public Date getOrder_date() {
		return orderDate;
	}
	public void setOrder_date(Date orderDate) {
		this.orderDate = orderDate;
	}
	public List<Item> getOrder_items() {
		return orderItems;
	}
	public void setOrder_items(List<Item> orderItems) {
		this.orderItems = orderItems;
	}
	public float getOrderValue() {
		return orderValue;
	}
	public void setOrderValue(float orderValue) {
		this.orderValue = orderValue;
	}
	public Shipping getShippingMethod() {
		return shippingMethod;
	}
	public void setShippingMethod(Shipping shippingMethod) {
		this.shippingMethod = shippingMethod;
	}
	public String getShippingAddress() {
		return shippingAddress;
	}
	public void setShippingAddress(String shippingAddress) {
		this.shippingAddress = shippingAddress;
	}
	
}
