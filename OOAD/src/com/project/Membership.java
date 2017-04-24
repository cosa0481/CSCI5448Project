package com.project;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@MappedSuperclass
public abstract class Membership {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="membership_id", nullable=false, unique=true, length=11)
	private int id;
	
	@Column(name="TYPE")
	private String type;

	abstract float getMembershipDiscount();

	abstract int getShippingCost();
	//TODO
	abstract List<Item> getDeals();

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public static List<Item> fetchMembershipPlans() {
		return null;
	}
}
