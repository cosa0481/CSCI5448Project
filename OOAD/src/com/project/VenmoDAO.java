package com.project;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

@Entity
@Table(name = "venmo_details")
public class VenmoDAO {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "venmo_id", nullable = false, unique = true)
	private int id;

	@Column(name = "venmo_user_name", nullable = false)
	private String venmoUserName;

	@OneToOne(optional = false)
	@JoinColumn(name = "customer_id", nullable = false)
	private Customer customer;

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public String getVenmoUserName() {
		return venmoUserName;
	}

	public void setVenmoUserName(String venmoUserName) {
		this.venmoUserName = venmoUserName;
	}

	public static VenmoDAO getVenmoForCustomer() {
		Session s = null;

		try {

			s = DatabaseManager.getInstance().getSession();
			Customer c = (Customer) Manager.getInstance().getCurrentUser();

			Criteria cri = s.createCriteria(VenmoDAO.class);
			cri.add(Restrictions.eq("customer", c));
			cri.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
			List<Object> venmo_list = cri.list();

			if (venmo_list.size() > 0) {
				return (VenmoDAO) venmo_list.get(0);
			}
			return null;
		} finally {
			s.close();
		}
	}

	public static void main(String[] args) {
		Session session = DatabaseManager.getInstance().getSession();

		session.beginTransaction();

		Customer c = (Customer) session.get(Customer.class, 1);

		VenmoDAO venmo = new VenmoDAO();
		venmo.setVenmoUserName("rohitatcu");
		venmo.setCustomer(c);
		session.save(venmo);

		session.getTransaction().commit();

		DatabaseManager.getInstance().closeSession();
		DatabaseManager.getInstance().getSessionFactory().close();
	}
}
