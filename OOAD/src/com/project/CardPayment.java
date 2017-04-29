package com.project;

import java.util.List;

import com.utility.DisplayUtilities;
import com.utility.Utility;

public class CardPayment implements IpaymentMethod {

	CreditCardDAO cardDetails;

	@Override
	public boolean processPayment(Order order) {
		if (cardDetails == null) {
			Utility.displayToScreen("No credit card found, Order not successful");
			return false;
		}
		Utility.displayToScreen("Payment of " + order.getOrderValue()
				+ " has been succssfully posted on your credit card# "
				+ cardDetails.getCreditCardNumber());
		return true;
	}

	@Override
	public void initializePaymentDetails() {
		List<CreditCardDAO> creditCardDetails = CreditCardDAO
				.getCreditCardForCustomer();

		if (creditCardDetails.size() > 1) {
			Utility.displayToScreen("You have more than one credit cards on file,Please select a credit card");
			cardDetails = DisplayUtilities
					.displayCreditCardPrompt(creditCardDetails);
		} else if (creditCardDetails.size() == 1) {
			cardDetails = creditCardDetails.get(0);
		}
	}

}
