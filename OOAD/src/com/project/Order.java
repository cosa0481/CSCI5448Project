package com.project;

import java.util.Date;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name="Order")
public class Order {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID", nullable=false, unique=true)
	private int orderID;
	
	//TODO
	private Customer customer;
	
	@Column(name="DATE")
	private Date orderDate;
	
	//TODO
	private List<Item> orderItems;
	
	@Column(name="ORDERVALUE")
	private float orderValue;
	
	//TODO
	private Shipping shippingMethod;
	
	@Column(name="SHIPPINGADDRESS")
	private String shippingAddress;
	
	public void getOrderInfo() {
		//TODO
	}
	
	public void showOrderInfo() {
		//TODO
	}
	
	public int getOrderID() {
		return orderID;
	}
	
	public void setID(int newID) {
		orderID = newID;
	}
	
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
