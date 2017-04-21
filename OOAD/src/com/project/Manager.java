package com.project;

import java.util.List;

public class Manager {

	private static Manager instance;
	private Person currentUser;
	private List<Subscriber> observers;

	private Manager() {
	}

	public static Manager getInstance() {
		if (instance == null) {
			instance = new Manager();
		}
		return instance;
	}
	
	public void attach(Subscriber sub) {
		observers.add(sub);
	}
	
	public List<Subscriber> getObservers() {
		return observers;
	}
	
	public void setObservers(List<Subscriber> observerList) {
		observers = observerList;
	}
	
	public void logOrder(Order order) {
		for(Subscriber sub : Manager.getInstance().getObservers()) {
			sub.log(order);
		}
	}

	public Person getCurrentUser() {
		return currentUser;
	}
	
	public void setCurrentUser(Person user) {
		currentUser = user;
	}

	static public void search(String searchString) {

	}

	static public void onSuccessfulPurchase(Order order) {
		generateInvoice(order);
		Manager.getInstance().logOrder(order);
	}

	static private void generateInvoice(Order order) {

	}

	// Main function
	public static void main(String[] args) {
		HibernateDriver.run();
	}
}