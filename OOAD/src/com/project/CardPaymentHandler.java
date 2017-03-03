package com.project;

public class CardPaymentHandler implements IpaymentMethod {

	@Override
	public boolean processPayment(Order order) {
		return false;
	}

}
