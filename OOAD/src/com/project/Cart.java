package com.project;

import java.util.HashMap;

public class Cart {

	private HashMap<Item, Integer> itemCountMap;
	boolean isReadyForCheckout;


	public boolean isReadyForCheckout() {
		return isReadyForCheckout;
	}

	public void setReadyForCheckout(boolean isReadyForCheckout) {
		this.isReadyForCheckout = isReadyForCheckout;
	}

	public HashMap<Item, Integer> getItemCountMap() {
		return itemCountMap;
	}

	public void setItemCountMap(HashMap<Item, Integer> itemCountMap) {
		this.itemCountMap = itemCountMap;
	}
	
	public void addItemToCart(Item product, int quantity) {
	}
}
