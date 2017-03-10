package com.project;

import java.util.Date;
import java.util.List;

public class Order {

	private Customer customer;
	private Date order_date;
	private List<Item> order_items;
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
		return order_date;
	}
	public void setOrder_date(Date order_date) {
		this.order_date = order_date;
	}
	public List<Item> getOrder_items() {
		return order_items;
	}
	public void setOrder_items(List<Item> order_items) {
		this.order_items = order_items;
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
