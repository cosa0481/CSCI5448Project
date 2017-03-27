package com.project;

public class PaymentHandler {

	private IpaymentMethod paymentMethod;

	public PaymentHandler(IpaymentMethod paymentMethod) {
		this.paymentMethod = paymentMethod;
		initialize();
	}

	private void initialize() {
		paymentMethod.initializePaymentDetails();
	}

	public boolean processPayment(Order order) {
		return paymentMethod.processPayment(order);
	}
}
