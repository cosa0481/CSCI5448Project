package com.project;

import java.util.Date;

public class UserSubscriber implements Subscriber {
	
	public String type;
	
	@Override
	public void log(Order order) {
		String entry = "User " + order.getCustomer().getId() 
				+ " placed order.";
		Date timeStamp = new Date();
		LogEntry log_entry = new LogEntry();
		log_entry.addLogEntry(this.type, entry, timeStamp);
	}
	
	public UserSubscriber() {
		type = "User";
		Manager.getInstance().attach(this);
	}
}
