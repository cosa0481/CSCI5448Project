package com.project;

public class CardPayment implements IpaymentMethod {

	CreditCardDAO cardDetails;

	@Override
	public boolean processPayment(Order order) {
		return false;
	}

}
