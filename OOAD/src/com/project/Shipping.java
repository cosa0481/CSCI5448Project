package com.project;

import javax.persistence.*;

@Entity
@Table(name="shipping")
public class Shipping {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="shipping_id", nullable=false, unique=true)
	private int id;

    @ManyToOne(optional=false)
    @JoinColumn(referencedColumnName="region_id",name="from_region")
	private Region from;
	
    @ManyToOne(optional=false)
    @JoinColumn(referencedColumnName="region_id",name="to_region")
	private Region to;
	
	@Column(name="DAYS")
	private int shippingDays;
	
	@Column(name="COST")
	private int shippingCost;
	
	public boolean initiateOrder(Order order) {
		//TODO
		return false;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Region getFrom() {
		return from;
	}
	public void setFrom(Region from) {
		this.from = from;
	}
	public Region getTo() {
		return to;
	}
	public void setTo(Region to) {
		this.to = to;
	}
	public int getShippingDays() {
		return shippingDays;
	}
	public void setShippingDays(int shippingDays) {
		this.shippingDays = shippingDays;
	}
	public int getShippingCost() {
		return shippingCost;
	}
	public void setShippingCost(int shippingCost) {
		this.shippingCost = shippingCost;
	}

}
