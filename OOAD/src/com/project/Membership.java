package com.project;

import java.util.List;

public abstract class Membership {
	private int id;
	private String type;

	abstract float getMembershipDiscount();

	abstract int getShippingCost();

	abstract List<Item> getDeals();

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public static List<Item> fetchMembershipPlans() {
		return null;
	}
}
