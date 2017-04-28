package com.project;

import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.AttributeOverride;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.criterion.Restrictions;

@Entity
@Table(name = "customer")
@AttributeOverride(name = "id", column = @Column(name = "customer_id"))
public class Customer extends Person {

	// TODO
	@Transient
	private Membership membership;

	@OneToOne(mappedBy = "customer", cascade = { CascadeType.ALL }, fetch = FetchType.LAZY)
	private Cart cart;

	public List<Order> getOrders() {
		return null;
	}

	public void addOrder(Order order) {
	}

	public Order checkout() {
		return null;
	}

	public void writeReview(Item product, Review review) {
		product.addReview(review);
	}

	public Membership getMembership() {
		return membership;
	}

	public void setMembership(Membership membership) {
		this.membership = membership;
	}

	public Cart getCart() {
		return cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}

	public IpaymentMethod getPaymentMethod() {
		return null;
	}

	public Customer loadCartItems() {
		Session session = null;

		try {
			session = DatabaseManager.getInstance().getSession();

			Criteria criteria = session.createCriteria(Customer.class);
			criteria.add(Restrictions.eq("id", this.getId()));

			List<Object> listOfCustomer = criteria.list();

			Customer c = (Customer) listOfCustomer.get(0);

			Map<Item, Integer> items = c.getCart().getItemCountMap();
			System.out.println(items.size());
			Manager.getInstance().setCurrentUser(c);
			return c;
		} finally {
			session.close();
		}
	}

}
