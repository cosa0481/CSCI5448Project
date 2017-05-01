package com.project;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.project.PaymentMethodFactory.Payment_Method;
import com.utility.DisplayUtilities;
import com.utility.Utility;

public class Manager {

	private static Manager instance;

	private Person currentUser;
	private UserType currentUserType;

	private static List<Subscriber> observers;

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
	
	public void initializeObservers() {
		observers = new ArrayList<Subscriber>();
		new UserSubscriber();
		new ItemSubscriber();
		new OrderSubscriber();
	}

	// Main function
	public static void main(String[] args) {

		Utility.showPromptForInput(
				"Welcome to Marketplace. Press any key to continue", "");
		Manager.getInstance().initializeObservers();
		String user_input = Utility
				.showPromptForInput(
						"Please select user type\nPress 1 for Customer\nPress 2 for Seller\nPress 3 for Administrator",
						"1,2,3");

		processUserTypeInput(user_input);
		Utility.displayToScreen("Bye");
		DatabaseManager.getInstance().getSessionFactory().close();
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

	/**
	 * 
	 * entry point for administrator
	 */
	private static void handleAdministrator() {
		Administrator a = (Administrator) loginUser(Administrator.class);

		if (a != null) {
			System.out.println("Login succesful, Welcome " + a.getFirstName());
		} else {
			System.out.println("Incorrect Login, Please try again");
		}
	}

	/**
	 * 
	 * entry point for seller
	 */
	private static void handleSeller() {
		Seller s = (Seller) loginUser(Seller.class);

		if (s != null) {
			System.out.println("Login succesful, Welcome " + s.getFirstName());
			
			String input = Utility
					.showPromptForInput(
							"Please select one of the options\nPress 1 to add item\nPress 2 to schedule a sale\nPress 3 to return an item\nPress 0 to quit",
							"0,1,2,3");

			if (input.equals("1")) {
				input = Utility.showPromptForInput("Enter name of item to add:",
						"");
				// show categories, prompt for input?
				// set price, inventory, numStars = 0.0, set serial number?
				// i = new item, session.save();
				/*input = Utility.showPromptForInput("Enter search term for item:",
						"");
				List<Item> foundItems = Item.searchItems(input);
				DisplayUtilities.displayItems(foundItems);*/
			}
			if (input.equals("2")) {				
				input = Utility.showPromptForInput("Enter search term for item you wish to put on sale:",
				"");
				List<Item> foundItems = Item.searchItems(input);
				DisplayUtilities.displayItemsToAddSale(foundItems);
				//c.loadCartItems();
				//DisplayUtilities.displayCart();
			}
			if (input.equals("3")) {
				//List<Order> user_orders = Order.getOrderForCustomer(c);
				//DisplayUtilities.displayOrder(user_orders);
			}
			if (input.equals("0")) {

			}
			
		} else {
			System.out.println("Incorrect Login, Please try again");
		}
	}

	/**
	 * Entry point for customer
	 */
	private static void handleCustomer() {
		Customer c = (Customer) loginUser(Customer.class);

		while (true) {

			if (c != null) {
				Manager.getInstance().setCurrentUser(c);
				System.out.println("Login succesful, Welcome "
						+ c.getFirstName());
				String input = Utility
						.showPromptForInput(
								"\n\nPlease select one of the options\nPress 1 for search\nPress 2 to view cart\nPress 3 for Order History\nPress 4 to checkout\nPress 0 to quit",
								"0,1,2,3,4");

				while (true) {

					if (input.equals("1")) {
						input = Utility.showPromptForInput(
								"Enter keywod for search", "na");
						List<Item> foundItems = Item.searchItems(input);
						DisplayUtilities.displayItems(foundItems);
					}
					if (input.equals("2")) {
						c.loadCartItems();
						DisplayUtilities.displayCart();
					}
					if (input.equals("3")) {
						DisplayUtilities.displayOrder();
					}
					if (input.equals("0")) {
						System.out.println("Bye");
						System.exit(0);
					}
					if (input.equals("4")) {
						String shipping = Utility
								.showPromptForInput(
										"Please select the shipping method\nPress 1 for Basic\nPress 2 for Deluxe\nPress 3 for Premium",
										"1,2,3");
						String payment = Utility
								.showPromptForInput(
										"Please select the payment method\nPress 1 for Card Payment\nPress 2 for Venmo",
										"1,2");

						String shippingAddress = Utility.showPromptForInput(
								"Please enter shipping address", "na");
						Cart cart = c.getCart();

						if (cart == null) {
							Utility.displayToScreen("Your cart is empty");
							return;
						}
						Order customer_order = c.checkout(shipping, payment,
								shippingAddress);
						// DisplayUtilities.displayOrder();
						// DisplayUtilities.displayLog("Order");

					}
					input = Utility
							.showPromptForInput(
									"\n\nPlease select one of the options\nPress 1 for search\nPress 2 to view cart\nPress 3 for Order History\nPress 4 to checkout\nPress 0 to quit",
									"0,1,2,3,4");
					c = (Customer) Manager.getInstance().getCurrentUser();
				}

			} else {
				System.out.println("\n\nIncorrect Login, Please try again");
				c = (Customer) loginUser(Customer.class);
			}

		}
	}

	/**
	 * Prompts user for credentials and validates them
	 * 
	 * @param c
	 * @return
	 */
	public static Object loginUser(Class c) {
		String credentials = Utility.showPromptForInput(
				"Please enter userName and Password separated by whitespace!",
				"");
		String[] login_credentials = credentials.split(" ");
		if (login_credentials.length < 2) {
			return null;
		}
		return Person.login(login_credentials[0], login_credentials[1], c);
	}

}