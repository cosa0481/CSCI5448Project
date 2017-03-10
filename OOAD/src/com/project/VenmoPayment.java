package com.project;

public class VenmoPaymentHandler implements IpaymentMethod {

	VenmoDAO venmoDetails;
	
	@Override
	public boolean processPayment(Order order) {
		return false;
	}

}
