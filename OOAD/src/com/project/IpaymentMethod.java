package com.project;

public interface IpaymentMethod {

	public boolean processPayment(Order order);
	public void initializePaymentDetails();
}
