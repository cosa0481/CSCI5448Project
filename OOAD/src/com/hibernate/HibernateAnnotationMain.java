package com.hibernate;


import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.project.Region;

public class HibernateAnnotationMain {

	public static void main(String[] args) {
		Region region = new Region();
		region.setStateCode("CO");
		region.setTitle("UCB");
		region.setZipcode(80204);

		
		//Get Session
		SessionFactory sessionFactory = HibernateUtil.getSessionAnnotationFactory();
		Session session = sessionFactory.getCurrentSession();
		//start transaction
		session.beginTransaction();
		//Save the Model object
		session.save(region);
		//Commit transaction
		session.getTransaction().commit();
		System.out.println("Region ID="+region.getId());
		
		//terminate session factory, otherwise program won't end
		sessionFactory.close();
	}

}