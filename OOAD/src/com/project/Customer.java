package com.project;

import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
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
import javax.xml.crypto.Data;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.criterion.Restrictions;

import com.project.PaymentMethodFactory.Payment_Method;
import com.utility.Utility;

@Entity
@Table(name = "customer")
@AttributeOverride(name = "id", column = @Column(name = "customer_id"))
public class Customer extends Person {

	// TODO
	@Transient
	private Membership membership;

	@OneToOne(mappedBy = "customer", cascade = { CascadeType.ALL }, fetch = FetchType.LAZY)
	private Cart cart;

	@Transient
	private HashMap<Integer, Order> user_orders = new HashMap<>();

	public void setUser_orders(HashMap<Integer, Order> user_orders) {
		this.user_orders = user_orders;
	}

	public void addOrder(Order order) {
		if (!user_orders.containsKey(order.getOrderID())) {
			user_orders.put(order.getOrderID(), order);
		}
	}

	public Collection<Order> getOrders() {
		if (user_orders == null) {
			user_orders = new HashMap<>();
		}
		return user_orders.values();
	}


	public Order checkout(String shipping, String payment,
			String shippingAddress) {
		this.loadCartItems();
		Customer customer = ((Customer) Manager.getInstance().getCurrentUser());

		if (!customer.getCart().isReadyForCheckout()) {
			Utility.displayToScreen("Your cart is empty");
			return null;
		}
		Session s = DatabaseManager.getInstance().getSession();

		s.beginTransaction();

		Shipping shippingMethod = (Shipping) s.get(Shipping.class,
				Integer.parseInt(shipping));

		HashSet<Item> order_items = new HashSet<>();

		for (Item i : customer.getCart().getItemCountMap().keySet()) {
			order_items.add(i);
		}

		Order o = new Order();
		o.setCustomer(this);
		o.setOrder_date(new Date());
		o.setOrderValue(this.getCart().getCartValue()
				+ shippingMethod.getShippingCost());
		o.setOrder_items(order_items);
		o.setShippingAddress(shippingAddress);
		o.setShippingMethod(shippingMethod);

		IpaymentMethod paymentMethod = PaymentMethodFactory
				.getPaymentMethod(Payment_Method
						.getPaymentMethodForInput(payment));
		paymentMethod.initializePaymentDetails();

		if (paymentMethod.processPayment(o)) {
			System.out.println(s.save(o).toString());
			customer.addOrder(o);
			Manager.getInstance().logOrder(o);
			// DatabaseManager.getInstance().saveOrUpdate(o);
			customer.getCart().getItemCountMap().clear();
			s.saveOrUpdate(customer.getCart());
			s.getTransaction().commit();
		}
		s.close();

		return o;
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

	/**
	 * Populate user's cart
	 * 
	 * @return
	 */
	public Customer loadCartItems() {
		if(this.getCart() == null){
			this.initalizeCart();
		}
		if (Hibernate.isInitialized(this.getCart().getItemCountMap())) {
			return this;
		}
		Session session = null;
		try {
			session = DatabaseManager.getInstance().getSession();

			Criteria criteria = session.createCriteria(Customer.class);
			criteria.add(Restrictions.eq("id", this.getId()));

			List<Object> listOfCustomer = criteria.list();

			Customer c = (Customer) listOfCustomer.get(0);
			if (c.getCart() != null) {
				Map<Item, Integer> items = c.getCart().getItemCountMap();
				if (items != null) {
					items.size();
					Manager.getInstance().setCurrentUser(c);
				}
			}
			return c;
		} finally {
			session.close();
		}
	}

	public static void main(String[] args) {
		Session session = DatabaseManager.getInstance().getSession();

		session.beginTransaction();

		Customer c = new Customer();
		c.setFirstName("Corin");
		c.setLastName("Sandford");
		c.setUsername("cosa");
		c.setPassword("cosa");
		
		Customer m = new Customer();
		c.setFirstName("Mike");
		c.setLastName("Gore");
		c.setUsername("migo");
		c.setPassword("migo");
		
		Customer r = new Customer();
		c.setFirstName("Rohit");
		c.setLastName("Gupta");
		c.setUsername("rogu");
		c.setPassword("rogu");

		session.save(c);
		session.save(m);
		session.save(r);
		session.getTransaction().commit();
		session.close();
		session.getSessionFactory().close();
	}

	public void initalizeCart() {
		cart = new Cart();
		cart.setCustomer(this);
		this.setCart(cart);
	}
}
