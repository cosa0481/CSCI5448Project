package com.project;

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

import java.util.Date;

@Entity
@Table(name="item", 
	   uniqueConstraints={@UniqueConstraint(columnNames={"item_id"})})
public class Item {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="item_id", nullable=false, unique=true, length=11)
	private int id;
	
	@Column(name="SERIALNO")
	private String serial_no;
	
	@Column(name="SUGGESTEDRETAILPRICE")
	private float suggestedRetailPrice;
	
	@Column(name="CURRENTPRICE")
	private float currentPrice;
	
	@Temporal(TemporalType.DATE)
	@Column(name="CURRENTPRICESET")
	private Date currentPriceSet;
	
	@Column(name="ININVENTORY")
	private int inInventory;
	
	@Column(name="TITLE")
	private String title;
	
	@ManyToOne
	@JoinColumn(name="category_id", nullable=false)
	Category category;

	@Column(name="NUMSTARS")
	private float numStars;

	@OneToMany
	@JoinTable(name = "item_reviews", joinColumns = { @JoinColumn(name = "item_id", referencedColumnName = "item_id") }, inverseJoinColumns = { @JoinColumn(name = "review_id", referencedColumnName = "review_id", unique = true) })
	List<Review> reviews;
	
	@OneToMany
	@Transient
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
