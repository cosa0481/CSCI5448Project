package com.project;

import java.util.Date;

public class OrderSubscriber implements Subscriber {
	
	@Override
	public void log(Order order) {
		String entry = "Order # " + order.getOrderID() + " placed.";
		Date timeStamp = new Date();
		LogEntry log_entry = new LogEntry();
		log_entry.addLogEntry("Order", entry, timeStamp);	}
	
	public OrderSubscriber() {
		Manager.getInstance().attach(this);
	}

}
