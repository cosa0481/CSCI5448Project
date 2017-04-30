package com.project;

import java.util.Date;

public class UserSubscriber implements Subscriber {
	
	@Override
	public void log(Order order) {
		String entry = "User " + order.getCustomer().getId() 
				+ " placed order.";
		Date timeStamp = new Date();
		LogEntry log_entry = new LogEntry();
		log_entry.addLogEntry("User", entry, timeStamp);
	}
	
	public UserSubscriber() {
		Manager.getInstance().attach(this);
	}
}
