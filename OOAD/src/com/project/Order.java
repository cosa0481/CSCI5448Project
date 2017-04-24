package com.project;

import java.util.Date;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name="customer_order")
public class Order {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="order_id", nullable=false, unique=true)
	private int orderID;

	@ManyToOne
	@JoinColumn(name="customer_id", nullable=false)
	private Customer customer;
	
	@Column(name="orderDate")
	@Temporal(TemporalType.DATE)
	private Date orderDate;
	
	@ManyToMany
	@JoinTable(name = "order_items", joinColumns = { @JoinColumn(name = "order_id", referencedColumnName = "order_id") }, 
	inverseJoinColumns = { @JoinColumn(name = "item_id", referencedColumnName = "item_id") })
	private List<Item> orderItems;
	
	@Column(name="ORDERVALUE")
	private float orderValue;

	@ManyToOne
	@JoinColumn(name="shipping_id",nullable=false)
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
