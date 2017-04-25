package com.project;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.hibernate.HibernateUtil;

@Entity
@Table(name="administrator", 
	   uniqueConstraints={@UniqueConstraint(columnNames={"ID"})})
public class Administrator extends Person {
	
	
	// Pointless comment to move file
	public void addSale(Sale sale, Category category) {
		
	}
	
	public void processReturn(Order order) {
		

	}
	
	public static void main(String[] args) {
		SessionFactory sessionFactory = HibernateUtil.getSessionAnnotationFactory();
		Session session = sessionFactory.getCurrentSession();
		//start transaction
		session.beginTransaction();
		
		Administrator a = new Administrator();
		a.setFirstName("admin");
		a.setLastName("admin");
		a.setUsername("admin");
		a.setPassword("admin");
		session.save(a);
		session.getTransaction().commit();
		sessionFactory.close();
	}
}
