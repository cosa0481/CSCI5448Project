package com.utility;

import java.util.List;
import java.util.Map;

import com.project.Cart;
import com.project.Customer;
import com.project.Item;
import com.project.Manager;
import com.project.Order;
import com.project.Person;

public class DisplayUtilities {

	public static void displayOrder() {
		List<Order> orders = Order.getOrderForCustomer((Customer) Manager
				.getInstance().getCurrentUser());

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
			System.out.println("Item#" + (i + 1) + "\t");
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

		System.out.println("Printing item details");

		System.out.println("Title:\t" + item.getTitle());
		System.out.println("Category:\t" + item.getCategory().getName());
		System.out.println("Rating:\t" + item.getNumStars());
		System.out.println("Inventory:\t" + item.getInInventory());
		System.out.println("Price:\t" + item.getCurrentPrice() + "\t");

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
}
