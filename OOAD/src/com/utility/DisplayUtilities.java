package com.utility;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Date;
import java.util.Scanner;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.text.ParseException;

import com.project.Cart;
import com.project.CreditCardDAO;
import com.project.Customer;
import com.project.DatabaseManager;
import com.project.Item;
import com.project.LogEntry;
import com.project.Manager;
import com.project.Order;
import com.project.Person;
import com.project.Review;
import com.project.Sale;

public class DisplayUtilities {

	public static void displayOrder() {

		Customer c = ((Customer) Manager.getInstance().getCurrentUser());
		Order.getOrderForCustomer(c);
		Collection<Order> orders = c.getOrders();

		Object[] user_orders = orders.toArray();
		if (orders.size() == 0) {
			Utility.displayToScreen("User has not placed any order");
			return;
		}
		System.out.println("You have total " + orders.size() + " orders");

		for (int i = 0; i < orders.size(); i++) {
			Order o = (Order) user_orders[i];
			System.out.println("Printing details of order#" + (i + 1));
			System.out.println("Order date: " + o.getOrder_date());
			System.out.println("Order Value: " + o.getOrderValue());
			System.out.println("Order address: " + o.getShippingAddress());

			StringBuilder itemList = new StringBuilder("");
			for (Item item : o.getOrder_items()) {
				itemList.append(item.getTitle() + ",");
			}
			String itemString = itemList.toString();

			System.out.println("Order items :"
					+ itemString.substring(0, itemString.length() - 1));
			System.out.println();
		}
	}

	public static void displayItems(List<Item> foundItems) {
		if (foundItems.size() == 0) {
			Utility.displayToScreen("Your search returned zero results.");
			return;
		}
		System.out.println(foundItems.size() + " items found");
		StringBuilder allowedInput = new StringBuilder("");

		for (int i = 0; i < foundItems.size(); i++) {
			allowedInput.append((i + 1) + ",");
			System.out.println("Item #" + (i + 1) + "\t");
			System.out.print("Title:\t" + foundItems.get(i).getTitle() + "\t");
			System.out.print("Price:\t" + foundItems.get(i).getCurrentPrice()
					+ "\t");
			System.out.println();
		}

		String input = Utility.showPromptForInput(
				"Enter the Item# to view an item", allowedInput.toString());
		int item_no = Integer.parseInt(input);

		while ((item_no > foundItems.size()) || (item_no < 0)) {
			input = Utility.showPromptForInput(
					"Enter the Item# to view an item", "1");
			item_no = Integer.parseInt(input);
		}
		displayItem(foundItems.get(item_no - 1));
	}

	public static void displayItem(Item item) {

		System.out.println("Item details:");

		System.out.println("Title:\t\t" + item.getTitle());
		System.out.println("Category:\t" + item.getCategory().getName());
		System.out.println("Rating:\t\t" + item.getNumStars());
		System.out.println("Inventory:\t" + item.getInInventory());
		System.out.println("Price:\t\t" + item.getCurrentPrice() + "\t");
		System.out.println("Reviews:");
		DisplayUtilities.displayReviews(item);

		System.out.println();
		String input = Utility.showPromptForInput(
				"Press 1 to add item to cart\nPress 2 for leave review\nPress 3 to return", "1,2,3");

		int parsed_input = Integer.parseInt(input);

		if (parsed_input == 1) {

			Person currentUser = Manager.getInstance().getCurrentUser();

			if (currentUser instanceof Customer) {
				Customer c = (Customer) currentUser;

				Cart cart;

				if (c.getCart() == null) {
					c.initalizeCart();
				} else {
					c = c.loadCartItems();
				}

				c.getCart().modifyCart(item, 1);
			}
			Utility.displayToScreen("The item has been succesfully added");

		} else if(parsed_input == 2) {
			Review review = new Review();
			review.setCreatedDate(new Date());
			input = Utility.showPromptForInput("Enter number of stars for review",
					"0,1,2,3,4,5");
			int parsed_user_input = Integer.parseInt(input);
			while(parsed_user_input < 0 || parsed_user_input > 5) {
				input = Utility.showPromptForInput("Enter number of stars for review",
						"0,1,2,3,4,5");
				parsed_user_input = Integer.parseInt(input);
			}
			review.setNumStars(parsed_user_input);
			
			input = Utility.showPromptForInput("Enter review text", "na");
			review.setPostContent(input);
			item.addReview(review);
		}
	}

	public static void displayCart() {
		Person person = Manager.getInstance().getCurrentUser();
		Customer c = (Customer) person;
		Cart cart = c.getCart();

		if (cart == null) {
			c.initalizeCart();
			cart = c.getCart();
		}

		Map<Item, Integer> items = cart.getItemCountMap();

		if (items == null || items.size() == 0) {
			Utility.displayToScreen("Your cart is empty!");
			return;
		}
		Utility.displayToScreen("Your cart has the following items!");

		StringBuilder allowedInput = new StringBuilder("");
		ArrayList<Item> itemList = new ArrayList<>();

		int j = 0;
		for (Item i : cart.getItemCountMap().keySet()) {
			itemList.add(i);
			allowedInput.append((j + 1) + ",");
			System.out.println("Item name:\t" + i.getTitle() + "\t");
			System.out.println("Quantity:\t" + cart.getItemCountMap().get(i));
			System.out.println();
			j++;
		}

		Utility.displayToScreen("Total value of your cart is "
				+ cart.getCartValue());

		String input = Utility.showPromptForInput(
				"Press 1 to update cart\nPress 2 to return", "1,2");

		int parsed_input = Integer.parseInt(input);

		if (parsed_input == 1) {
			input = Utility.showPromptForInput("Enter the Item# to delete",
					allowedInput.toString());
			int parsed_user_input = Integer.parseInt(input);

			while ((parsed_user_input > items.size())
					|| (parsed_user_input < 0)) {
				input = Utility.showPromptForInput("Enter the Item# to delete",
						allowedInput.toString());
				parsed_user_input = Integer.parseInt(input);
			}
			c.getCart().modifyCart(itemList.get(parsed_user_input-1), -1);
		}
	}

	public static CreditCardDAO displayCreditCardPrompt(
			List<CreditCardDAO> creditCardDetails) {

		StringBuilder allowedInput = new StringBuilder("");

		for (int i = 0; i < creditCardDetails.size(); i++) {
			System.out.println("Card #" + (i + 1) + "\t");
			allowedInput.append((i + 1) + ",");
			System.out.print("Card No:\t"
					+ creditCardDetails.get(i).getCreditCardNumber() + "\t");
			System.out.println();
		}

		String input = Utility.showPromptForInput(
				"Enter the Card# to select a card", allowedInput.toString());
		int card_no = Integer.parseInt(input);

		while ((card_no > creditCardDetails.size()) || (card_no < 0)) {
			input = Utility
					.showPromptForInput("Enter the Card# to select a card",
							allowedInput.toString());
			card_no = Integer.parseInt(input);
		}

		return creditCardDetails.get(card_no - 1);
	}

	public static void displayItemsToAddSale(List<Item> foundItems) {
		if (foundItems.size() == 0) {
			Utility.displayToScreen("Your search returned zero results.");
			return;
		}
		System.out.println(foundItems.size() + " items found");

		for (int i = 0; i < foundItems.size(); i++) {
			System.out.println("Item #" + (i + 1) + "\t");
			System.out.print("Title:\t" + foundItems.get(i).getTitle() + "\t");
			System.out.print("Price:\t" + foundItems.get(i).getCurrentPrice()
					+ "\t");
			System.out.println();
		}

		String input = Utility.showPromptForInput(
				"Enter the item number for the item you wish to put on sale",
				"");

		int item_no = Integer.parseInt(input);

		while ((item_no > foundItems.size()) || (item_no < 0)) {
			input = Utility.showPromptForInput(
					"Enter item number you wish to put on sale", "");
			item_no = Integer.parseInt(input);
		}
		displayItemForSale(foundItems.get(item_no - 1));
	}

	public static void displayItemForSale(Item item) {
		System.out.println("Item details:");

		System.out.println("Title:\t\t" + item.getTitle());
		System.out.println("Category:\t" + item.getCategory().getName());
		System.out.println("Inventory:\t" + item.getInInventory());
		System.out.println("Retail Price:\t" + item.getSuggestedRetailPrice()
				+ "\t");

		System.out.println();
		String input = Utility.showPromptForInput("Press 1 to schedule a sale",
				"");

		int parsed_input = Integer.parseInt(input);

		if (parsed_input == 1) {
			// new Sale
			// input for start date
			// input for end date
			// input for discount %
			// if sale is valid, recalculate item price
			// database? we'll see
			displayItemSales(item);
			Sale newSale = new Sale();
			Date startDate;
			Date endDate;
			double discount;
			DateFormat formatter = new SimpleDateFormat("yyyy-M-d");
			input = Utility.showPromptForInput(
					"Enter start date of sale in format: 2014-12-15", "");
			// make it a date
			// set it as start date
			try {
				startDate = formatter.parse(input);
				newSale.setStartDate(startDate);
			} catch (ParseException ex) {
				System.out.println("did not parse start date");
			}
			input = Utility.showPromptForInput(
					"Enter end date of sale in format: 2014-12-25", "");
			// make it a date
			// set it as end date
			try {
				endDate = formatter.parse(input);
				newSale.setEndDate(endDate);
			} catch (ParseException ex) {
				System.out.println("did not parse end date");
			}

			input = Utility.showPromptForInput(
					"Enter percentage discount for sale", "");
			// make it a float or double or w/e
			// set it as percent discount
			// discount = (double) input;
			Scanner s = new Scanner(input);
			discount = s.nextDouble();
			newSale.setPercentDiscount(discount);
			s.close();
			item.addItemSale(newSale);
			if (newSale.isSaleActive()) {
				item.calculateCurrentPrice();
				System.out.println("Sale is active, calculating price");
				System.out
						.println("Current price is " + item.getCurrentPrice());
			}
			Utility.displayToScreen("The sale has been scheduled!");

			// displayCart();
		}
	}

	public static void displayLog(String type) {
		Session s = DatabaseManager.getInstance().getSession();
		s.beginTransaction();
		
		Criteria criteria = s.createCriteria(LogEntry.class);
		criteria.add(Restrictions.eq("log_type", type));
		List<Object> database_log_entries = criteria.list();
		
		List<LogEntry> log_entries = new ArrayList<LogEntry>();
		for(Object entry : database_log_entries) {
			log_entries.add((LogEntry) entry);
		}
		
		s.close();
		
		System.out.println(type 
				+ " Log:\tTimestamp : Entry\n---------\t--------- : -----");
		for(LogEntry entry : log_entries) {
			System.out.println(entry.log_date + " : " + entry.log_entry);
		}
		System.out.println("End " + type + " Log\n");
	}
	
	public static void displayReviews(Item item) {
		Session s = DatabaseManager.getInstance().getSession();
		s.beginTransaction();
		
		s.refresh(item);
		
		for(Review r : item.getReviews()) {
			System.out.println("\tStars: " + r.getNumStars());
			System.out.println("\t" + r.getPostContent());
			System.out.println("\tCreated:" + r.getCreatedDate());
			
		}
		
		DatabaseManager.getInstance().closeSession();
	}
	
	public static void displayItemSales(Item item) {
		System.out.println("Existing sales:");
		List<Sale> allSales = item.getAllSales();
		if(allSales.isEmpty()){
			System.out.println("None");
		} else {
			for(Sale s: allSales) {
				System.out.println("Start date: \t" + s.getStartDate());
				System.out.println("End date: \t" + s.getEndDate());
				System.out.println("Discount: \t" + s.getPercentDiscount() + "%");
			}
		}
		
	}

}

