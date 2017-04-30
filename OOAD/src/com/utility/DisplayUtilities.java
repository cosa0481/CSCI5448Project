package com.utility;

import java.util.List;
import java.util.Map;
import java.util.Date;
import java.util.Scanner;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.text.ParseException;

import com.project.Cart;
import com.project.Customer;
import com.project.Item;
import com.project.Manager;
import com.project.Order;
import com.project.Person;
import com.project.Sale;

public class DisplayUtilities {

	public static void displayOrder(List<Order> orders) {
		if (orders.size() == 0) {
			Utility.displayToScreen("User has not placed any order");
			return;
		}
		System.out.println("You have total " + orders.size() + " orders");

		for (int i = 0; i < orders.size(); i++) {
			System.out.println("Printing details of order#" + (i + 1));
			System.out.println("Order date: " + orders.get(i).getOrder_date());
			System.out.println("Order Value: " + orders.get(i).getOrderValue());
			System.out.println("Order address: "
					+ orders.get(i).getShippingAddress());

			StringBuilder itemList = new StringBuilder("");
			for (Item item : orders.get(i).getOrder_items()) {
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

		for (int i = 0; i < foundItems.size(); i++) {
			System.out.println("Item #" + (i + 1) + "\t");
			System.out.print("Title:\t" + foundItems.get(i).getTitle() + "\t");
			System.out.print("Price:\t" + foundItems.get(i).getCurrentPrice()
					+ "\t");
			System.out.println();
		}

		String input = Utility.showPromptForInput(
				"Enter the Item# to view an item", "");
		int item_no = Integer.parseInt(input);

		while ((item_no > foundItems.size()) || (item_no < 0)) {
			input = Utility.showPromptForInput(
					"Enter the Item# to view an item", "");
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

		System.out.println();
		String input = Utility.showPromptForInput(
				"Press 1 to add item to cart", "");

		int parsed_input = Integer.parseInt(input);

		if (parsed_input == 1) {

			Person currentUser = Manager.getInstance().getCurrentUser();

			if (currentUser instanceof Customer) {
				Customer c = (Customer) currentUser;
				c = c.loadCartItems();
				displayCart();

				c.getCart().modifyCart(item, 1);
			}
			Utility.displayToScreen("The item has been succesfully added");
			
			displayCart();
		}
	}

	public static void displayCart() {
		Person person = Manager.getInstance().getCurrentUser();
		Customer c = (Customer) person;
		Cart cart = c.getCart();

		Map<Item, Integer> items = cart.getItemCountMap();

		if (items.size() == 0) {
			Utility.displayToScreen("Your cart is empty!");
			return;
		}
		Utility.displayToScreen("Your cart has the following items!");

		for (Item i : cart.getItemCountMap().keySet()) {
			System.out.println("Item name:\t" + i.getTitle() + "\t");
			System.out.println("Quantity:\t" + cart.getItemCountMap().get(i));
			System.out.println();
		}

		Utility.displayToScreen("Total value of your cart is "
				+ cart.getCartValue());
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
				"Enter the item number for the item you wish to put on sale", "");
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
		System.out.println("Retail Price:\t" + item.getSuggestedRetailPrice() + "\t");

		System.out.println();
		String input = Utility.showPromptForInput(
				"Press 1 to schedule a sale", "");

		int parsed_input = Integer.parseInt(input);

		if (parsed_input == 1) {			
			// new Sale
			// input for start date
			// input for end date
			// input for discount %
			// if sale is valid, recalculate item price
			// database? we'll see
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
			}catch(ParseException ex){
				System.out.println("did not parse start date");
			}
			input = Utility.showPromptForInput(
					"Enter end date of sale in format: 2014-12-25", "");
			// make it a date
			// set it as end date
			try {
				endDate = formatter.parse(input);
				newSale.setEndDate(endDate);
			}catch(ParseException ex){
				System.out.println("did not parse end date");
			}

			input = Utility.showPromptForInput(
					"Enter percentage discount for sale", "");
			// make it a float or double or w/e
			// set it as percent discount
			//discount = (double) input;
			Scanner s = new Scanner(input);
			discount = s.nextDouble();
			newSale.setPercentDiscount(discount);
			s.close();
			item.addItemSale(newSale);
			if(newSale.isSaleActive()) {
				item.calculateCurrentPrice();
				System.out.println("Sale is active, calculating price");
				System.out.println("Current price is " + item.getCurrentPrice());
			}
			Utility.displayToScreen("The sale has been scheduled!");
			
			//displayCart();
		}
	}
}
