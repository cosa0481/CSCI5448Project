package com.project;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import java.util.Date;
import java.util.GregorianCalendar;

import com.utility.DateUtilities;

@Entity
@Table(name = "item", uniqueConstraints = { @UniqueConstraint(columnNames = { "item_id" }) })
public class Item {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "item_id", nullable = false, unique = true, length = 11)
	private int id;

	@Column(name = "SERIALNO")
	private String serial_no;

	@Column(name = "SUGGESTEDRETAILPRICE")
	private float suggestedRetailPrice;

	@Column(name = "CURRENTPRICE")
	private float currentPrice;

	@Temporal(TemporalType.DATE)
	@Column(name = "CURRENTPRICESET")
	private Date currentPriceSet;

	@Column(name = "ININVENTORY")
	private int inInventory;

	@Column(name = "TITLE")
	private String title;

	@ManyToOne
	@JoinColumn(name = "category_id", nullable = false)
	Category category;

	@Column(name = "NUMSTARS")
	private float numStars;

	@OneToMany
	@JoinTable(name = "item_reviews", joinColumns = { @JoinColumn(name = "item_id", referencedColumnName = "item_id") }, inverseJoinColumns = { @JoinColumn(name = "review_id", referencedColumnName = "review_id", unique = true) })
	List<Review> reviews;

	@OneToMany
	@JoinTable(name = "item_sales", joinColumns = { @JoinColumn(name = "item_id", referencedColumnName = "item_id") }, inverseJoinColumns = { @JoinColumn(name = "sale_id", referencedColumnName = "sale_id", unique = true) })
	private List<Sale> itemSales = new ArrayList<Sale>();

	public float getNumStars() {
		return numStars;
	}

	public void setNumStars(float numStars) {
		this.numStars = numStars;
	}

	public void addReview(Review review) {		
		Session s = DatabaseManager.getInstance().getSession();
		s.beginTransaction();
		s.save(review);
		s.refresh(this);
		
		this.getReviews().add(review);
		
		s.saveOrUpdate(this);
		
		s.getTransaction().commit();
		DatabaseManager.getInstance().closeSession();
	}
	
	public void calculateCurrentPrice() {
		// use this method to calculate and store a current
		// price, and store the date it was set.
		float max_discount = 0.0f;
		float discount = 0.0f;
		if(this.getAllSales() != null) {
			List<Sale> allSales = this.getAllSales();
			for( Sale sale : allSales) {
				if(sale.isSaleActive()) {
					discount = (float) sale.getPercentDiscount();
					// handle cases where 40% is entered as "40", not "0.4"
					if(discount > 1) {
						discount = discount/100.0f;
					}
					if(discount > max_discount) {
						max_discount = discount;
					}
				}
			}
		}
		this.setCurrentPrice(this.getSuggestedRetailPrice() * (1 - max_discount));		
		Date today = new Date();
		this.setCurrentPriceSet(today);
	}

	public float getCurrentPrice() {
		// See if currentPrice was set today—if so, assume it is still
		// the current price, and return it. Else,
		// calculate current price, and then return it.
		if(this.getCurrentPriceSet() == null) {
			Date pastDate = new GregorianCalendar(2000, Calendar.FEBRUARY, 12).getTime();
			this.setCurrentPriceSet(pastDate);
		}
		if(DateUtilities.isDateToday(this.getCurrentPriceSet())) {
			return this.currentPrice;
		} else {
			this.calculateCurrentPrice();
			return this.currentPrice;
		}
	}

	public void setCurrentPrice(float currentPrice) {
		this.currentPriceSet = new Date();
		this.currentPrice = currentPrice;
	}
	
	public Date getCurrentPriceSet() {
		return currentPriceSet;
	}

	public void setCurrentPriceSet(Date currentPriceSet) {
		this.currentPriceSet = currentPriceSet;
	}

	public int getInInventory() {
		return inInventory;
	}

	public void setInInventory(int inInventory) {
		this.inInventory = inInventory;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSerial_no() {
		return serial_no;
	}

	public void setSerial_no(String serial_no) {
		this.serial_no = serial_no;
	}

	public float getSuggestedRetailPrice() {
		return suggestedRetailPrice;
	}

	public void setSuggestedRetailPrice(float suggestedRetailPrice) {
		this.suggestedRetailPrice = suggestedRetailPrice;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<Sale> getItemSales() {
		return itemSales;
	}

	public List<Sale> getAllSales() {
		// database stuff?
		Session s = DatabaseManager.getInstance().getSession();
		s.refresh(this);
		s.refresh(this.getCategory());
		
		List<Sale> itemAndCategorySales = new ArrayList<Sale>();
		if(this.getItemSales() != null) {
			itemAndCategorySales.addAll(this.getItemSales());
		}
		if(this.getCategory().getCategorySales() != null) {
			itemAndCategorySales.addAll(this.getCategory().getCategorySales());
		}
		
		DatabaseManager.getInstance().closeSession();
		return itemAndCategorySales;
	}

	public void setItemSales(List<Sale> itemSales) {
		this.itemSales = itemSales;
	}

	public void addItemSale(Sale itemSale) {		
		Session s = DatabaseManager.getInstance().getSession();
		s.beginTransaction();
		s.save(itemSale);
		s.refresh(this);
		
		if(!this.getItemSales().contains(itemSale)) {
			this.getItemSales().add(itemSale);
		}
		
		s.saveOrUpdate(this);
		
		s.getTransaction().commit();
		DatabaseManager.getInstance().closeSession();
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public List<Review> getReviews() {
		return reviews;
	}

	public void setReviews(List<Review> reviews) {
		this.reviews = reviews;
	}

	public static List<Item> searchItems(String keyword) {

		Session session = null;
		List<Item> items = new ArrayList<>();

		try {
			session = DatabaseManager.getInstance().getSession();

			Criteria cri = session.createCriteria(Item.class);
			cri.add(Restrictions.ilike("title", "%" + keyword + "%"));
			List<Object> results = cri.list();

			for (Object o : results) {
				items.add((Item) o);
			}
			for (Item i: items) {
				session.refresh(i);
				Hibernate.initialize(i.getItemSales());
				Hibernate.initialize(i.getCategory().getCategorySales());
			}
			return items;
		} finally {
			session.close();
		}

	}

	public static void main(String[] args) {
		Session session = DatabaseManager.getInstance().getSession();

		session.beginTransaction();

//		Region region1 = new Region();
//		region1.setState("CA");
//		region1.setTitle("UCLA");
//		region1.setZipcode(80301);
//		session.save(region1);
//
//		Region region2 = new Region();
//		region2.setState("CA");
//		region2.setTitle("USC");
//		region2.setZipcode(80302);
//		session.save(region2);

		Shipping shipping1 = new Shipping();
		shipping1.setShippingCost(50);
		shipping1.setShippingDays(15);
		shipping1.setShippingMethod("1");
		session.save(shipping1);

		Category category1 = new Category();
		category1.setName("console");
		session.save(category1);

		Category category2 = new Category();
		category2.setName("car");
		session.save(category2);

		Item i = new Item();
		i.setCategory(category1);
		i.setCurrentPrice(50.0f);
		i.setInInventory(10);
		i.setNumStars(3);
		i.setTitle("xbox");
		i.setSerial_no("1234");
		session.save(i);

		i = new Item();
		i.setCategory(category1);
		i.setCurrentPrice(150.0f);
		i.setInInventory(10);
		i.setNumStars(3);
		i.setTitle("playstation 4");
		i.setSerial_no("1234");
		session.save(i);

		i = new Item();
		i.setCategory(category1);
		i.setCurrentPrice(75.0f);
		i.setInInventory(10);
		i.setNumStars(3);
		i.setTitle("playstation 3");
		i.setSerial_no("1234");
		session.save(i);

		i = new Item();
		i.setCategory(category1);
		i.setCurrentPrice(15.0f);
		i.setInInventory(10);
		i.setNumStars(3);
		i.setTitle("playstation 2");
		i.setSerial_no("1234");
		session.save(i);

		i = new Item();
		i.setCategory(category1);
		i.setCurrentPrice(15.0f);
		i.setInInventory(10);
		i.setNumStars(3);
		i.setTitle("xbox 180");
		i.setSerial_no("1234");
		session.save(i);

		i = new Item();
		i.setCategory(category1);
		i.setCurrentPrice(15.0f);
		i.setInInventory(10);
		i.setNumStars(3);
		i.setTitle("xbox 240");
		i.setSerial_no("1234");
		session.save(i);

		Item i2 = new Item();
		i2.setCategory(category2);
		i2.setCurrentPrice(50.0f);
		i2.setInInventory(10);
		i2.setNumStars(3);
		i2.setTitle("car 1");
		i2.setSerial_no("1234");
		session.save(i2);

		i2 = new Item();
		i2.setCategory(category2);
		i2.setCurrentPrice(50.0f);
		i2.setInInventory(110);
		i2.setNumStars(3);
		i2.setTitle("car 2");
		i2.setSerial_no("1234");
		session.save(i2);

		i2 = new Item();
		i2.setCategory(category2);
		i2.setCurrentPrice(450.0f);
		i2.setInInventory(110);
		i2.setNumStars(3);
		i2.setTitle("car 3");
		i2.setSerial_no("1234");
		session.save(i2);

		i2 = new Item();
		i2.setCategory(category2);
		i2.setCurrentPrice(4500.0f);
		i2.setInInventory(110);
		i2.setNumStars(3);
		i2.setTitle("car 4");
		i2.setSerial_no("1234");
		session.save(i2);

		i2 = new Item();
		i2.setCategory(category2);
		i2.setCurrentPrice(450.0f);
		i2.setInInventory(110);
		i2.setNumStars(3);
		i2.setTitle("car 5");
		i2.setSerial_no("1234");
		session.save(i2);

		session.getTransaction().commit();
		DatabaseManager.getInstance().getSessionFactory().close();
		
//		Category cat5e = new Category();
//		
//		Item i2 = new Item();
//		i2.setCategory(cat5e);
//		i2.setSuggestedRetailPrice(4500.0f);
//		i2.setCurrentPrice(2200.0f);
//		i2.setInInventory(110);
//		i2.setNumStars(3);
//		i2.setTitle("Item 1");
//		i2.setSerial_no("1234");
//
//		Date today = new Date();
//		Date testDate1 = new GregorianCalendar(2017, Calendar.APRIL, 29).getTime();
//		Date testDate2 = new GregorianCalendar(2017, Calendar.APRIL, 28).getTime();
//		Date testDate3 = new GregorianCalendar(2017, Calendar.APRIL, 30).getTime();
//		Date testDate4 = new GregorianCalendar(2017, Calendar.MAY, 5).getTime();
//		
//		i2.setCurrentPriceSet(testDate2);
//		
//		System.out.println("Item's price is: " + i2.getCurrentPrice());
//		
//		Sale itemSale = new Sale();
//		itemSale.setStartDate(testDate2);
//		itemSale.setEndDate(testDate3);
//		itemSale.setPercentDiscount(30.0);
//		
//		// add itemSale to list of item sales. should be active
//		i2.addItemSale(itemSale);
//		i2.calculateCurrentPrice();
//
//		System.out.println("Sale added.");
//		System.out.println("Item's price is: " + i2.getCurrentPrice());
//		
//		Sale catSale = new Sale();
//		catSale.setStartDate(testDate3);
//		catSale.setEndDate(testDate4);
//		catSale.setPercentDiscount(40.0);
//		
//		cat5e.addCategorySale(catSale);
//		i2.calculateCurrentPrice();
//
//		System.out.println("Category sale added. Price shouldn't change");
//		System.out.println("Item's price is: " + i2.getCurrentPrice());
//		
//		Sale catSale2 = new Sale();
//		catSale2.setStartDate(testDate2);
//		catSale2.setEndDate(testDate4);
//		catSale2.setPercentDiscount(50.0);
//		
//		cat5e.addCategorySale(catSale2);
//		i2.calculateCurrentPrice();
//
//		System.out.println("Category sale added. Price should change");
//		System.out.println("Item's price is: " + i2.getCurrentPrice());

		// print current price
		// create item sale
		//
	}
}
