package com.project;

import java.util.List;

public class System {

	private static System instance;
	private Customer currentCustomer;
	List<Subscriber> observers;

	private System() {
	}

	public static System getInstance() {
		if (instance == null) {
			instance = new System();
		}
		return instance;
	}
	
	public void attach(Subscriber sub) {
		observers.add(sub);
	}
	
	public void logOrder(Order order) {
		for(Subscriber sub : observers) {
			sub.log(order);
		}
	}

	public Customer getCurrentCustomer() {
		return currentCustomer;
	}

	static public void search(String searchString) {

	}

	static public void onSuccessfulPurchase(Order order) {
		generateInvoice(order);
	}

	static private void generateInvoice(Order order) {

	}

	// Main function
	public static void main(String[] args) {

	}
}