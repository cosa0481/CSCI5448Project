package com.project;

public class VenmoPayment implements IpaymentMethod {

	VenmoDAO venmoDetails;

	@Override
	public boolean processPayment(Order order) {
		return false;
	}

	@Override
	public void initializePaymentDetails() {

	}

}
