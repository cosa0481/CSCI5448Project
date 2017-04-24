package com.project;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapKeyColumn;
import javax.persistence.MapKeyJoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Table(name = "cart", uniqueConstraints = { @UniqueConstraint(columnNames = { "cart_id" }) })
public class Cart {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cart_id", nullable = false, unique = true, length = 11)
	private int id;

	@ElementCollection
	@MapKeyJoinColumn(name = "item_id")
	@Column(name = "quantity")
	@CollectionTable(name = "cart_items", joinColumns = @JoinColumn(name = "cart_id"))
	private Map<Item, Integer> itemCountMap;

	@OneToOne(optional=false)
	@JoinColumn(name="customer_id")
	private Customer customer;
	
	@Transient
	boolean isReadyForCheckout;

	public boolean isReadyForCheckout() {
		return isReadyForCheckout;
	}

	public void setReadyForCheckout(boolean isReadyForCheckout) {
		this.isReadyForCheckout = isReadyForCheckout;
	}

	public Map<Item, Integer> getItemCountMap() {
		return itemCountMap;
	}

	public void setItemCountMap(HashMap<Item, Integer> itemCountMap) {
		this.itemCountMap = itemCountMap;
	}

	public void addItemToCart(Item product, int quantity){
		if(itemCountMap.containsKey(product)){
			itemCountMap.put(product, itemCountMap.get(product)+quantity);
		}else{
			itemCountMap.put(product, quantity);
		}
	}
	
	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

}
