package com.project;

public class PaymentHandler {

	 public static boolean processPayment(Order order,IpaymentMethod paymentMethod){
		 return paymentMethod.processPayment(order);
	 }
}
