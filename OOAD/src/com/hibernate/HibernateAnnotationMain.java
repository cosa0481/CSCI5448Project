package com.hibernate;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.*;
import org.hibernate.criterion.*;

import com.project.Cart;
import com.project.Category;
import com.project.Customer;
import com.project.DatabaseManager;
import com.project.Item;
import com.project.Region;
import com.project.Seller;

public class HibernateAnnotationMain {

	public static void main(String[] args) {
		Region region = new Region();
		region.setState("CO");
		region.setTitle("UCB");
		region.setZipcode(80204);

		
		
		/*
		DatabaseManager dbman = DatabaseManager.getInstance();

		List<Criterion> criteria = new ArrayList<Criterion>();
		criteria.add(Restrictions.eq("zipcode",80204));
		List<Object> regions = dbman.retrieve(Region.class, criteria);
		for(Object region : regions) {
			Region r = (Region) region;
			System.out.println("Region ID=" + r.getId());
		}
		*/
		
		//terminate session factory, otherwise program won't end
//		sessionFactory.close();
		
/*		Customer c = new Customer();
		c.setFirstName("Rohit");
		c.setLastName("Gupta");
		c.setUsername("abcd");
		c.setPassword("abcd");
		
		Seller s1 = new Seller();
		s1.setFirstName("Rohit");
		s1.setLastName("Gupta");
		s1.setUsername("abcd");
		s1.setPassword("abcd");
		s1.setSellerRating(5);
		
		//Get Session
		SessionFactory sessionFactory = HibernateUtil.getSessionAnnotationFactory();
		Session session = sessionFactory.getCurrentSession();
		//start transaction
		session.beginTransaction();
		//Save the Model object
		session.save(c);
		//Commit transaction
	

		session.save(s1);
		//Commit transaction
		session.getTransaction().commit();
		System.out.println("Customer ID="+c.getId());
		System.out.println("Seller ID="+s1.getId());
		
		sessionFactory.close();*/
		
		//Get Session
/*		SessionFactory sessionFactory = HibernateUtil.getSessionAnnotationFactory();
		Session session = sessionFactory.getCurrentSession();
		//start transaction
		session.beginTransaction();
		
		
		Category category = new Category();
		category.setName("phone");
		session.save(category);
		
		
		Item i = new Item();
		i.setCategory(category);
		i.setCurrentPrice(50.0f);
		i.setInInventory(10);
		i.setNumStars(3);
		i.setTitle("Nokia phone");
		i.setSerial_no("1234");
		session.save(i);

		Item i2 = new Item();
		i2.setCategory(category);
		i2.setCurrentPrice(50.0f);
		i2.setInInventory(10);
		i2.setNumStars(3);
		i2.setTitle("Samsung phone");
		i2.setSerial_no("1234");
		session.save(i2);

		HashMap<Item, Integer> itemCountMap = new HashMap<>();
		itemCountMap.put(i, 2);
		itemCountMap.put(i2, 4);
		
		
		Customer c = new Customer();

		Cart cart = new Cart();
		cart.setItemCountMap(itemCountMap);
		cart.setCustomer(c);
		session.save(cart);

		
		c.setFirstName("Rohit");
		c.setLastName("Gupta");
		c.setUsername("abcd");
		c.setPassword("abcd");
		c.setCart(cart);
		
		session.save(c);


		//Save the Model object
		
		//Commit transaction
	

		//Commit transaction
		session.getTransaction().commit();
		System.out.println("Customer ID="+c.getId());*/
		List<Criterion> criteria = new ArrayList<Criterion>();
		
		List<Object> l = DatabaseManager.getInstance().retrieve(Customer.class, criteria);

		Customer c= (Customer) l.get(0);
		
		Map<Item, Integer> map =  c.getCart().getItemCountMap();
		Set<Item> itemSet = map.keySet();
		c.getCart().addItemToCart(itemSet.iterator().next(), 10);
		DatabaseManager.getInstance().closeSession();
		
		DatabaseManager.getInstance().insert(c);
		DatabaseManager.getInstance().getSessionFactory().close();
	}

}