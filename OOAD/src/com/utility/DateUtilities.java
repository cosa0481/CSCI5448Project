package com.utility;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Date;

public class DateUtilities {
	public static boolean isDateToday(Date inDate) {
		Date today = new Date();
		return areDatesEqual(inDate, today);
	}
	
	public static boolean areDatesEqual(Date inDate1, Date inDate2) {
		// the Date.getYear(), etc. methods are deprecated, so
		// will cast to a Calendar to get this functionality
		// (should've used a different Date library)
		Calendar inCal1 = Calendar.getInstance();
		Calendar inCal2 = Calendar.getInstance();
		inCal1.setTime(inDate1);
		inCal2.setTime(inDate2);
		
		if (inCal1.get(Calendar.YEAR) != inCal2.get(Calendar.YEAR)) {
			return false;
		} else if (inCal1.get(Calendar.MONTH) != inCal2.get(Calendar.MONTH)) {
			return false;
		} else if (inCal1.get(Calendar.DATE) != inCal2.get(Calendar.DATE)) {
			return false;
		} else {
			return true;
		}
	}
	
	public static boolean isDateWithinRange(Date inDate, Date startDate, Date endDate) {
		if (areDatesEqual(inDate, startDate) || areDatesEqual(inDate, endDate)) {
			return true;
		} else {
			return startDate.before(inDate) && endDate.after(inDate);
		}
	}
	
	// Main method for testing
	public static void main(String[] args) {
		Date today = new Date();
		Date testDate1 = new GregorianCalendar(2017, Calendar.APRIL, 29).getTime();
		Date testDate2 = new GregorianCalendar(2017, Calendar.APRIL, 28).getTime();
		Date testDate3 = new GregorianCalendar(2017, Calendar.APRIL, 30).getTime();
		Date testDate4 = new GregorianCalendar(2017, Calendar.MAY, 5).getTime();
		Date testDate5 = new GregorianCalendar(2017, Calendar.FEBRUARY, 12).getTime();

		System.out.println("Compare today as newDate to today as Gregorian");
		System.out.println(areDatesEqual(today, testDate1));
		System.out.println("Compare today between yesterday and tomorrow (true)");
		System.out.println(isDateWithinRange(today, testDate2, testDate3));
		System.out.println("Compare today between yesterday and today (true)");
		System.out.println(isDateWithinRange(today, testDate2, testDate1));
		System.out.println("Compare today between today and tomorrow (true)");
		System.out.println(isDateWithinRange(today, testDate1, testDate3));
		System.out.println("Compare today between February 5 and yesterday (false)");
		System.out.println(isDateWithinRange(today, testDate5, testDate2));
		System.out.println("Compare today between tomorrow and May 5 (false)");
		System.out.println(isDateWithinRange(today, testDate3, testDate4));

	}

}
