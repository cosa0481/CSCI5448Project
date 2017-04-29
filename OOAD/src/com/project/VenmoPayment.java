package com.project;

import com.utility.Utility;

public class VenmoPayment implements IpaymentMethod {

	VenmoDAO venmoDetails;

	@Override
	public boolean processPayment(Order order) {
		if (venmoDetails == null) {
			Utility.displayToScreen("No venmo details found, Order not successful");
			return false;
		}
		Utility.displayToScreen("Payment of " + order.getOrderValue()
				+ " has been succssfully posted on your venmo account "
				+ venmoDetails.getVenmoUserName());
		return true;
	}

	@Override
	public void initializePaymentDetails() {
		VenmoDAO venmoDetailsForCustomerr = VenmoDAO.getVenmoForCustomer();

		if (venmoDetailsForCustomerr != null) {
			venmoDetails = venmoDetailsForCustomerr;
		}
	}
	
	public static void main(String[] args) {
		
	}

}
