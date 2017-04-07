package com.project;

import java.util.Date;

public class OrderSubscriber implements Subscriber {
	
	private Log orderLog;

	@Override
	public void log(Order order) {
		String entry = "Order # " + order.getOrderID() + " placed.";
		Date timeStamp = new Date(); // Deprecated?
		orderLog.addLogEntry(entry, timeStamp);
	}
	
	public OrderSubscriber() {
		orderLog = new Log();
		System.getInstance().attach(this);
	}

}
