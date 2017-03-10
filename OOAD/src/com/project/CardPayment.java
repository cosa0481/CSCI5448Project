package com.project;

public class CardPaymentHandler implements IpaymentMethod {

	CreditCardDAO cardDetails;

	@Override
	public boolean processPayment(Order order) {
		return false;
	}

}
