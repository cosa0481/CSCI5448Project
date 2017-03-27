package com.project;

public class System {

	private static System instance;
	private Customer currentCustomer;

	private System() {
	}

	public static System getInstance() {
		if (instance == null) {
			instance = new System();
		}
		return instance;
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