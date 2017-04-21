package com.hibernate;


import java.util.ArrayList;
import java.util.List;

import org.hibernate.*;
import org.hibernate.criterion.*;

import com.project.DatabaseManager;
import com.project.Region;

public class HibernateAnnotationMain {

	public static void main(String[] args) {
//		Region region = new Region();
//		region.setStateCode("CO");
//		region.setTitle("UCB");
//		region.setZipcode(80204);

		
		//Get Session
//		SessionFactory sessionFactory = HibernateUtil.getSessionAnnotationFactory();
//		Session session = sessionFactory.getCurrentSession();
//		//start transaction
//		session.beginTransaction();
//		//Save the Model object
//		session.save(region);
//		//Commit transaction
//		session.getTransaction().commit();
//		System.out.println("Region ID="+region.getId());
		
		DatabaseManager dbman = DatabaseManager.getInstance();

		List<Criterion> criteria = new ArrayList<Criterion>();
		criteria.add(Restrictions.eq("zipcode",80204));
		List<Object> regions = dbman.retrieve(Region.class, criteria);
		for(Object region : regions) {
			Region r = (Region) region;
			System.out.println("Region ID=" + r.getId());
		}
		
		dbman.closeSession();
		//terminate session factory, otherwise program won't end
//		sessionFactory.close();
	}

}