package com.project;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Scanner;

import org.hibernate.Session;

public class Manager {

	private static Manager instance;

	private Person currentUser;
	private UserType currentUserType;

	private List<Subscriber> observers;

	public enum UserType {
		CUSTOMER, SELLER, ADMINISTRATOR
	};

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
		for (Subscriber sub : Manager.getInstance().getObservers()) {
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

		showPromptForInput("Welcome to marketplace, Press any key to continue",
				"");
		String user_input = showPromptForInput(
				"Please select user type\nPress 1 for Customer\nPress 2 for Seller\nPress 3 for Administrator",
				"1,2,3");

		processUserTypeInput(user_input);
		displayToScreen("Bye");
	}

	private static void processUserTypeInput(String user_input) {
		if (user_input.equals("1")) {
			handleCustomer();
		}
		if (user_input.equals("2")) {
			handleSeller();
		}
		if (user_input.equals("3")) {
			handleAdministrator();
		}
	}

	private static void handleAdministrator() {

	}

	private static void handleSeller() {
		Seller s = (Seller) loginUser(Seller.class);

		if (s != null) {
			System.out
					.println("Login succesful, Welcome " + s.getFirstName());
		}
	}

	private static void handleCustomer() {
		Customer c = (Customer) loginUser(Customer.class);

		if (c != null) {
			System.out
					.println("Login succesful, Welcome " + c.getFirstName());
			Session s = DatabaseManager.getInstance().getSession();
			s.update(c);
		}
	}

	public static Object loginUser(Class c) {
		String credentials = showPromptForInput(
				"Please enter userName and Password separatedby whitespace!",
				"");
		String[] login_credentials = credentials.split(" ");
		return Person.login(login_credentials[0], login_credentials[1], c);
	}

	/**
	 * Validate UserInput against accepted values
	 * 
	 * @param user_input
	 * @param accepted_valued
	 * @return
	 */
	public static boolean validateInput(String user_input,
			String accepted_valued) {

		if (accepted_valued.equals("")) {
			return true;
		}

		if (user_input == null || user_input.equals("")) {
			return false;
		}

		String[] inputOptions = accepted_valued.split(",");

		for (String inputOption : inputOptions) {
			if (inputOption.equals(user_input)) {
				return true;
			}
		}
		return false;

	}

	/**
	 * Take user's input and validate
	 * 
	 * @param prompt
	 * @param accepetedValues
	 * @return
	 */
	public static String showPromptForInput(String prompt,
			String accepetedValues) {
		Scanner reader = new Scanner(System.in); // Reading from System.in
		String input = "";
		do {
			displayToScreen(prompt);
			input = reader.nextLine().trim();
		} while (!validateInput(input, accepetedValues));
		return input;
	}

	/**
	 * Show message on screen
	 * 
	 * @param msg
	 */
	public static void displayToScreen(String msg) {
		System.out.println(msg);
	}
}