package com.project;

import java.util.Date;
import java.util.List;

public class Sale {
	private Date startDate;
	private Date endDate;
	private double percentDiscount;
	
	public Sale(Date startDate, Date endDate, double percentDiscount) {
		this.startDate = startDate;
		this.endDate = endDate;
		this.percentDiscount = percentDiscount;
	}
	
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public double getPercentDiscount() {
		return percentDiscount;
	}
	public void setPercentDiscount(double percentDiscount) {
		this.percentDiscount = percentDiscount;
	}
	public boolean isSaleValid(Sale newSale, List<Sale> itemSales, List<Sale> categorySales) {
		return true;
	}
}
