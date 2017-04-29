package com.project;

public class PaymentMethodFactory {

	public enum Payment_Method {
		CARD, VENMO;

		public static Payment_Method getPaymentMethodForInput(String input) {
			if ("1".equals(input)) {
				return Payment_Method.CARD;
			}
			if ("2".equals(input)) {
				return Payment_Method.VENMO;
			}
			return Payment_Method.CARD;
		}
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
