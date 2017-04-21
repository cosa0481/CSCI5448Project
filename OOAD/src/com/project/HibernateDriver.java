package com.project;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import com.configuration.HibernateUtil;

public class HibernateDriver
{
	public static void run()
	{
		// Write the Customer object into the database
		Customer customer = new Customer();
		customer.setFirstName("Corin");
		customer.setLastName("Sandford");
		customer.setUsername("cosa");
		customer.setPassword("Imcool");
		
		// Set up hibernate database session
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		// This would save the Customer object into the database
		session.save(customer);
		
		// To update,
		Customer customerChange = (Customer)session.get("Customer", 2);
		customerChange.setFirstName("CorinCool");
		customerChange.setLastName("SandfordCool");
		session.update(customerChange);
		session.getTransaction().commit();
		session.close();
		sessionFactory.close();
		
		System.out.println("Done.");
	}
}