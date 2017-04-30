package com.project;

import java.util.Date;
import java.util.List;
import java.util.Set;

public class ItemSubscriber implements Subscriber {

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
		log_entry.addLogEntry("Item", entry, timeStamp);
	}

	public ItemSubscriber() {
		Manager.getInstance().attach(this);
	}
}
