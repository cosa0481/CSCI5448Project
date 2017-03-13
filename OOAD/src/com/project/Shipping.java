package com.project;

public class Shipping {

	private int id;
	private Region from;
	private Region to;
	private int shippingDays;
	private int shippingCost;
	
	public boolean initiateOrder(Order order) {
		return false;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Region getFrom() {
		return from;
	}
	public void setFrom(Region from) {
		this.from = from;
	}
	public Region getTo() {
		return to;
	}
	public void setTo(Region to) {
		this.to = to;
	}
	public int getShippingDays() {
		return shippingDays;
	}
	public void setShippingDays(int shippingDays) {
		this.shippingDays = shippingDays;
	}
	public int getShippingCost() {
		return shippingCost;
	}
	public void setShippingCost(int shippingCost) {
		this.shippingCost = shippingCost;
	}

}
