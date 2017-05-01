package com.project;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

@Entity
@Table(name = "credit_card_details", uniqueConstraints = { @UniqueConstraint(columnNames = { "credit_card_id" }) })
public class CreditCardDAO {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "credit_card_id", nullable = false, unique = true, length = 11)
	private int id;

	@Column(name = "NAME")
	private String name;

	@Column(name = "CREDITCARDNUMBER")
	private String creditCardNumber;

	@Column(name = "CCV")
	private int ccv;

	@Column(name = "EXPIRATIONDATE")
	private String expirationDate;

	@Column(name = "BILLINGADDRESS")
	private String billingAddress;

	@ManyToOne(optional = false)
	@JoinColumn(name = "customer_id", nullable = false)
	Customer customer;

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCreditCardNumber() {
		return creditCardNumber;
	}

	public void setCreditCardNumber(String creditCardNumber) {
		this.creditCardNumber = creditCardNumber;
	}

	public int getCcv() {
		return ccv;
	}

	public void setCcv(int ccv) {
		this.ccv = ccv;
	}

	public String getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(String expirationDate) {
		this.expirationDate = expirationDate;
	}

	public String getBillingAddress() {
		return billingAddress;
	}

	public void setBillingAddress(String billingAddress) {
		this.billingAddress = billingAddress;
	}

	public static List<CreditCardDAO> getCreditCardForCustomer() {

		Session s = null;
		try {
			s = DatabaseManager.getInstance().getSession();
			Customer c = (Customer) Manager.getInstance().getCurrentUser();

			Criteria cri = s.createCriteria(CreditCardDAO.class);
			cri.add(Restrictions.eq("customer", c));
			cri.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
			List<Object> user_credit_cards = cri.list();

			List<CreditCardDAO> creditCards = new ArrayList<>();

			for (Object o : user_credit_cards) {
				creditCards.add((CreditCardDAO) o);
			}

			return creditCards;
		} finally {
			s.close();
		}
	}

	public static void main(String[] args) {
		Session session = DatabaseManager.getInstance().getSession();

		session.beginTransaction();

		Customer c = (Customer) session.get(Customer.class, 1);

		CreditCardDAO creditCard = new CreditCardDAO();
		creditCard.setBillingAddress("Other Address");
		creditCard.setCcv(323);
		
		creditCard.setCreditCardNumber("112243445566752");
		creditCard.setCustomer(c);

		session.save(creditCard);

		creditCard = new CreditCardDAO();
		creditCard.setBillingAddress("My Address");
		creditCard.setCcv(133);
		creditCard.setCreditCardNumber("223333445566752");
		creditCard.setCustomer(c);

		session.getTransaction().commit();

		DatabaseManager.getInstance().closeSession();
		DatabaseManager.getInstance().getSessionFactory().close();
	}

}
