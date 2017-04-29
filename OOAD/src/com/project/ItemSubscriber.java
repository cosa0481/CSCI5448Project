package com.project;

import java.util.Date;
import java.util.List;
import java.util.Set;

public class ItemSubscriber implements Subscriber {

	private Log itemLog;

	@Override
	public void log(Order order) {
		Set<Item> orderItems = order.getOrder_items();
		String entry = "Item ";
		for(Item i : orderItems) {
			entry += i.getTitle();
			entry += " ";
		}
		entry += "ordered.";
		Date timeStamp = new Date();
		itemLog.addLogEntry(entry, timeStamp);
	}

	public ItemSubscriber() {
		itemLog = new Log();
		Manager.getInstance().attach(this);
	}
}
