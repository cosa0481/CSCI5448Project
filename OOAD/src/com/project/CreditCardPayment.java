package com.project;

public class CreditCardPayment implements IpaymentMethod{

	CreditCardDAO cardDetails;
	@Override
	public boolean processPayment(Order order) {
		return false;
	}

}
