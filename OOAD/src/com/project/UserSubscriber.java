package com.project;

import java.util.Date;

public class UserSubscriber implements Subscriber {
	
	private Log userLog;

	@Override
	public void log(Order order) {
		String entry = "User " + order.getCustomer().getId() + " placed order.";
		Date timeStamp = new Date();
		userLog.addLogEntry(entry, timeStamp);
	}
	
	public UserSubscriber() {
		userLog = new Log();
		System.getInstance().attach(this);
	}
}
