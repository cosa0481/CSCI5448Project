package com.utility;

import java.util.List;

import com.project.Item;
import com.project.Order;

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
			System.out.print("Item#" + (i + 1) + "\t");
			System.out.print(foundItems.get(i).getTitle() + "\t");
			System.out.print(foundItems.get(i).getCurrentPrice() + "\t");
			System.out.println();
		}
	}
}
