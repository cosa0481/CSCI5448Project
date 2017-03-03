package com.project;

import java.util.List;

public class BasicMembership extends Membership {

	@Override
	float getMembershipDiscount() {
		return 0;
	}

	@Override
	int getShippingCost() {
		return 0;
	}

	@Override
	List<Item> getDeals() {
		return null;
	}

}
