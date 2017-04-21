package com.project;

import java.util.HashMap;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name="Cart", 
	   uniqueConstraints={@UniqueConstraint(columnNames={"ID"})})
public class Cart {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID", nullable=false, unique=true, length=11)
	private int id;
	//TODO
	private HashMap<Item, Integer> itemCountMap;
	boolean isReadyForCheckout;


	public boolean isReadyForCheckout() {
		return isReadyForCheckout;
	}

	public void setReadyForCheckout(boolean isReadyForCheckout) {
		this.isReadyForCheckout = isReadyForCheckout;
	}

	public HashMap<Item, Integer> getItemCountMap() {
		return itemCountMap;
	}

	public void setItemCountMap(HashMap<Item, Integer> itemCountMap) {
		this.itemCountMap = itemCountMap;
	}
	
	public void addItemToCart(Item product, int quantity) {
	}
}
