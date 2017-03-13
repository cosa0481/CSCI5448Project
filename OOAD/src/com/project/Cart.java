package com.project;

import java.util.HashMap;

public class Cart {

	private HashMap<Item, Integer> itemCountMap;

	public HashMap<Item, Integer> getItemCountMap() {
		return itemCountMap;
	}

	public void setItemCountMap(HashMap<Item, Integer> itemCountMap) {
		this.itemCountMap = itemCountMap;
	}
	
	public void addItemToCart(Item product, int quantity) {
	}
}
