package com.project;

import java.util.ArrayList;
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
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import java.util.Date;

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
	private List<Sale> itemSales;

	public float getNumStars() {
		return numStars;
	}

	public void setNumStars(float numStars) {
		this.numStars = numStars;
	}

	public void addReview(Review review) {
		reviews.add(review);
	}

	public float getCurrentPrice() {
		// calculate from itemSales, categorySales, membership
		// and region discounts
		float currentPrice = this.currentPrice;
		float bestDiscount = 0;
		return currentPrice * (1 - bestDiscount);
	}

	public void setCurrentPrice(float currentPrice) {
		this.currentPrice = currentPrice;
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
		return itemSales;
	}

	public void setItemSales(List<Sale> itemSales) {
		this.itemSales = itemSales;
	}

	public void addItemSale(Date startDate, Date endDate, double percentDiscount) {

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
			return items;
		} finally {
			session.close();
		}

	}

	public static void main(String[] args) {
		Session session = DatabaseManager.getInstance().getSession();

		session.beginTransaction();

		Region region1 = new Region();
		region1.setState("CA");
		region1.setTitle("UCLA");
		region1.setZipcode(80301);
		session.save(region1);

		Region region2 = new Region();
		region2.setState("CA");
		region2.setTitle("USC");
		region2.setZipcode(80302);
		session.save(region2);

		Shipping shippping1 = new Shipping();
		shippping1.setFrom(region1);
		shippping1.setTo(region2);
		shippping1.setShippingCost(50);
		shippping1.setShippingDays(15);
		session.save(shippping1);

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
	}
}
