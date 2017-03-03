package com.project;

import java.util.List;


public abstract class Membership {
	int id;
	String title;

	abstract float getMembershipDiscount();
	abstract int getShippingCost();
	abstract List<Item> getDeals();
}
