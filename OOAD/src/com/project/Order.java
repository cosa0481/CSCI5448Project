package com.project;

import java.util.Date;
import java.util.List;

public class Order {

	Customer customer;
	Date order_date;
	List<Item> order_items;
	float orderValue;
	Shipping shippingMethod;
	String shippingAddress;
}
