package com.project;

import java.util.Date;


public class OrderSubscriber implements Subscriber {
	
	public String type;
	
	@Override
	public void log(Order order) {
		String entry = "Order # " + order.getOrderID() + " placed.";
		Date timeStamp = new Date();
		LogEntry log_entry = new LogEntry();
		log_entry.addLogEntry(this.type, entry, timeStamp);	}
	
	public OrderSubscriber() {
		this.type = "Order";
		Manager.getInstance().attach(this);
	}

}
