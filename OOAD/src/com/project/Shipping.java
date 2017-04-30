package com.project;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.Session;

@Entity
@Table(name = "shipping")
public class Shipping {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "shipping_id", nullable = false, unique = true)
	private int id;

	@Column(name = "shippingMethod")
	private String shippingMethod;

	public String getShippingMethod() {
		return shippingMethod;
	}

	public void setShippingMethod(String shippingMethod) {
		this.shippingMethod = shippingMethod;
	}

	@Column(name = "DAYS")
	private int shippingDays;

	@Column(name = "COST")
	private int shippingCost;

	public boolean initiateOrder(Order order) {
		// TODO
		return false;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public static void main(String[] args) {

		Session session = DatabaseManager.getInstance().getSession();

		session.beginTransaction();

		Shipping s1 = new Shipping();
		s1.setShippingCost(100);
		s1.setShippingDays(5);
		s1.setShippingMethod("Basic");
		session.save(s1);

		s1 = new Shipping();
		s1.setShippingCost(200);
		s1.setShippingDays(2);
		s1.setShippingMethod("Deluxe");
		session.save(s1);

		s1 = new Shipping();
		s1.setShippingCost(300);
		s1.setShippingDays(1);
		s1.setShippingMethod("Preimum");
		session.save(s1);

		session.getTransaction().commit();

		DatabaseManager.getInstance().getSessionFactory().close();
	}

}
