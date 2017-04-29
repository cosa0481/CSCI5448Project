package com.project;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

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

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.criterion.Restrictions;

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

	@OneToOne(optional = false)
	@JoinColumn(name = "customer_id")
	private Customer customer;

	@Transient
	boolean isReadyForCheckout;

	public boolean isReadyForCheckout() {
		if(itemCountMap == null || itemCountMap.size() == 0){
			return false;
		}
		return true;
	}

	public void setReadyForCheckout(boolean isReadyForCheckout) {
		this.isReadyForCheckout = isReadyForCheckout;
	}

	public Map<Item, Integer> getItemCountMap() {
		return itemCountMap;
	}

	public void setItemCountMap(Map<Item, Integer> itemCountMap) {
		this.itemCountMap = itemCountMap;
	}

	public void modifyCart(Item product, int quantity) {

		Item selectedItem = null;

		if(itemCountMap == null){
			itemCountMap = new HashMap<Item, Integer>();
		}
		
		for (Item i : itemCountMap.keySet()) {
			if (i.getId() == product.getId()) {
				selectedItem = i;
			}
		}
		if (itemCountMap.containsKey(selectedItem)) {
			itemCountMap.put(selectedItem, itemCountMap.get(selectedItem)
					+ quantity);
		} else {
			itemCountMap.put(product, quantity);
		}
		DatabaseManager.getInstance().saveOrUpdate(this);
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	// TODO
	public float getCartValue() {
		return 100;
	}

}
