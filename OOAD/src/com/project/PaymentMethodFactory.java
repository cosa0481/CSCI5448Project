package com.project;

public class PaymentMethodFactory {

	public enum Payment_Method {
		CARD, VENMO,
	};

	public static IpaymentMethod getPaymentMethod(Payment_Method method) {
		if (method == Payment_Method.CARD) {
			return new CardPayment();
		} else if (method == Payment_Method.VENMO) {
			return new VenmoPayment();
		}
		return new CardPayment();
	}
}
