package com.project;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import java.util.Date;

@Entity
@Table(name="Item", 
	   uniqueConstraints={@UniqueConstraint(columnNames={"ID"})})
public class Item {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID", nullable=false, unique=true, length=11)
	private int id;
	@Column(name="SERIALNO")
	private String serial_no;
	@Column(name="SUGGESTEDRETAILPRICE")
	private float suggestedRetailPrice;
	@Column(name="CURRENTPRICE")
	private float currentPrice;
	@Column(name="CURRENTPRICESET")
	private Date currentPriceSet;
	@Column(name="ININVENTORY")
	private int inInventory;
	@Column(name="TITLE")
	private String title;
	//TODO
	private List<Sale> itemSales;
	@Column(name="NUMSTARS")
	private float numStars;

	public float getNumStars() {
		return numStars;
	}

	public void setNumStars(float numStars) {
		this.numStars = numStars;
	}

	Category category;
	List<Review> reviews;
	
	public void addReview(Review review) {
	}
	
	public float getCurrentPrice() {
		// calculate from itemSales, categorySales, membership
		// and region discounts
		float currentPrice = suggestedRetailPrice;
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
}
