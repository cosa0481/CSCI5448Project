package com.project;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.persistence.*;

import com.utility.DateUtilities;

import org.hibernate.Session;

@Entity
@Table(name = "sale")
public class Sale {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "sale_id", nullable = false, unique = true)
	private int id;

	@Temporal(TemporalType.DATE)
	@Column(name = "STARTDATE")
	private Date startDate;

	@Temporal(TemporalType.DATE)
	@Column(name = "ENDDATE")
	private Date endDate;

	@Column(name = "DISCOUNT")
	private double percentDiscount;

	public Sale() {

	}

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
	
	public boolean isSaleActive() {
		Date today = new Date();
		Date start = this.getStartDate();
		Date end = this.getEndDate();
		return (DateUtilities.isDateWithinRange(today, start, end));
	}

	public boolean isSaleValid(Sale newSale, List<Sale> itemSales,
			List<Sale> categorySales) {
		return true;
	}

	public static void main(String[] args) {
		
		assignSales();
		/*
		Session session = DatabaseManager.getInstance().getSession();

		session.beginTransaction();

		Sale s1 = new Sale();

		Date date = new GregorianCalendar(2017, Calendar.AUGUST, 26).getTime();
		s1.setStartDate(new Date());
		s1.setEndDate(date);
		s1.setPercentDiscount(40);
		session.save(s1);

		s1 = new Sale();
		date = new GregorianCalendar(2017, Calendar.SEPTEMBER, 26).getTime();
		s1.setStartDate(new Date());
		s1.setEndDate(date);
		s1.setPercentDiscount(60);
		session.save(s1);

		s1 = new Sale();
		date = new GregorianCalendar(2017, Calendar.OCTOBER, 26).getTime();
		s1.setStartDate(new Date());
		s1.setEndDate(date);
		s1.setPercentDiscount(20);
		session.save(s1);

		s1 = new Sale();
		date = new GregorianCalendar(2017, Calendar.NOVEMBER, 26).getTime();
		s1.setStartDate(new Date());
		s1.setEndDate(date);
		s1.setPercentDiscount(80);
		session.save(s1);

		session.getTransaction().commit();
		DatabaseManager.getInstance().getSessionFactory().close();*/
	}

	public static void assignSales() {

		Session session = DatabaseManager.getInstance().getSession();
		
		session.beginTransaction();
		
		Item item = (Item) session.get(Item.class, 16);

		Sale sale1 = (Sale) session.get(Sale.class, 5);
		Sale sale2 = (Sale) session.get(Sale.class, 6);


		List<Sale> item_sales = new ArrayList<>();
		item_sales.add(sale1);
		item_sales.add(sale2);
		//item_sales.add(sale3);
		///item_sales.add(sale4);
		
		item.setItemSales(item_sales);

		session.save(item);
		session.getTransaction().commit();

		session = DatabaseManager.getInstance().getSession();
		
		session.beginTransaction();
		
		item_sales = new ArrayList<>();
		
		Sale sale3 = (Sale) session.get(Sale.class, 3);
		Sale sale4 = (Sale) session.get(Sale.class, 4);
		
		item_sales.add(sale3);
		item_sales.add(sale4);
		
		
		Category category =  (Category) session.get(Category.class, 6);
		category.setCategorySales(item_sales);
		session.getTransaction().commit();

		DatabaseManager.getInstance().getSessionFactory().close();

	}
}
