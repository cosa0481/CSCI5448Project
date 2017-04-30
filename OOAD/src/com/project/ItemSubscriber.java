package com.project;

import java.util.Date;
import java.util.Set;

public class ItemSubscriber implements Subscriber {
	
	public String type;

	@Override
	public void log(Order order) {
		Set<Item> orderItems = order.getOrder_items();
		String entry = "Items ordered: ";
		for(Item i : orderItems) {
			entry += i.getTitle();
			entry += " ";
		}
		Date timeStamp = new Date();
		LogEntry log_entry = new LogEntry();
		log_entry.addLogEntry(this.type, entry, timeStamp);
	}

	public ItemSubscriber() {
		this.type = "Item";
		Manager.getInstance().attach(this);
	}
}
