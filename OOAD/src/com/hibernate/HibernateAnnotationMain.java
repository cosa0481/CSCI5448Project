package com.hibernate;


import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.sql.Select;

import com.project.Cart;
import com.project.Category;
import com.project.Customer;
import com.project.DatabaseManager;
import com.project.Item;
import com.project.Order;
import com.project.Region;
import com.project.Review;
import com.project.Seller;
import com.project.Shipping;

public class HibernateAnnotationMain {

	public static void main(String[] args) {
	/*
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
		
		//Get Session*	
	
		/*
		SessionFactory sessionFactory = HibernateUtil.getSessionAnnotationFactory();
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
		c.setFirstName("Rohit");
		c.setLastName("Gupta");
		c.setUsername("abcd");
		c.setPassword("abcd");
		
		session.save(c);

		Cart cart = new Cart();
		cart.setItemCountMap(itemCountMap);
		cart.setCustomer(c);
		c.setCart(cart);

		session.save(cart);

		

		session.getTransaction().commit();
		//Save the Model object
		
		//Commit transaction
	

		//Commit transaction
		System.out.println("Customer ID="+c.getId());
	
		 session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		List<Criterion> criteria = new ArrayList<Criterion>();
		
		Criteria crit_1 =  session.createCriteria(Customer.class);
		List<Object> l = crit_1.list();

		 c= (Customer) l.get(0);
		
		Map<Item, Integer> map =  c.getCart().getItemCountMap();
		Set<Item> itemSet = map.keySet();
		c.getCart().addItemToCart(itemSet.iterator().next(), 10);

		session.saveOrUpdate(c);
		session.getTransaction().commit();
		sessionFactory.close();
		
		//DatabaseManager.getInstance().saveOrUpdate(c);
		
		//DatabaseManager.getInstance().getSessionFactory().close();
		//;*/
		createData();
	}
	
	public static void createData(){
		SessionFactory sessionFactory = HibernateUtil.getSessionAnnotationFactory();
		Session session = sessionFactory.getCurrentSession();
		//start transaction
		session.beginTransaction();
		
		Region region1 = new Region();
		region1.setState("CO");
		region1.setTitle("UCB");
		region1.setZipcode(80204);
		session.save(region1);
		
		
		Region region2 = new Region();
		region2.setState("CO");
		region2.setTitle("Glenlake");
		region2.setZipcode(80301);
		session.save(region2);
		
		Shipping shippping1 = new Shipping();
		shippping1.setFrom(region1);
		shippping1.setTo(region2);
		shippping1.setShippingCost(100);
		shippping1.setShippingDays(7);
		session.save(shippping1);

		
		Category category1 = new Category();
		category1.setName("phone");
		session.save(category1);
		
		Category category2 = new Category();
		category2.setName("book");
		session.save(category2);

		Item i = new Item();
		i.setCategory(category1);
		i.setCurrentPrice(50.0f);
		i.setInInventory(10);
		i.setNumStars(3);
		i.setTitle("Nokia phone");
		i.setSerial_no("1234");
		session.save(i);

		Item i2 = new Item();
		i2.setCategory(category2);
		i2.setCurrentPrice(50.0f);
		i2.setInInventory(10);
		i2.setNumStars(3);
		i2.setTitle("Crime and Punishment");
		i2.setSerial_no("1234");
		session.save(i2);

		HashMap<Item, Integer> itemCountMap = new HashMap<>();
		itemCountMap.put(i, 2);
		itemCountMap.put(i2, 4);
		
		
		Customer c = new Customer();
		c.setFirstName("Rohit");
		c.setLastName("Gupta");
		c.setUsername("abcd");
		c.setPassword("1234");
		session.save(c);

		System.out.println(c.getCart());
		
		Cart cart = new Cart();
		cart.setItemCountMap(itemCountMap);
		cart.setCustomer(c);
		session.save(cart);
		
		
		//session.refresh(c);
		System.out.println(c.getCart());
		
		
		Review r1 = new Review();
		r1.setNumStars(4);
		r1.setPostContent("review for book");
		r1.setReviewer(c);
		r1.setCreatedDate(new Date());
		session.save(r1);

		Review r2 = new Review();
		r2.setNumStars(3);
		r2.setPostContent("review for book");
		r2.setReviewer(c);
		r2.setCreatedDate(new Date());
		session.save(r2);

		List<Review> reviews = new ArrayList<>();
		reviews.add(r1);
		reviews.add(r2);
		
		i2.setReviews(reviews);
		
		
		Review r3 = new Review();
		r3.setNumStars(3);
		r3.setPostContent("review for phone");
		r3.setReviewer(c);
		r3.setCreatedDate(new Date());
		session.save(r3);

		reviews = new ArrayList<>();
		reviews.add(r3);;
		
		i.setReviews(reviews);
		
		session.save(i);
		session.save(i2);
		
		Order o1 = new Order();
		o1.setCustomer(c);
		o1.setOrder_date(new Date());
		o1.setOrderValue(120);
		o1.setShippingMethod(shippping1);
		o1.setShippingAddress("address for order 1");
		
		List<Item> items = new ArrayList<>();
		items.add(i);
		o1.setOrder_items(items);
		
		session.save(o1);
		
		Order o2 = new Order();
		o2.setCustomer(c);
		o2.setOrder_date(new Date());
		o2.setOrderValue(120);
		o2.setShippingMethod(shippping1);
		o2.setShippingAddress("address for order 1");
		
		items = new ArrayList<>();
		items.add(i);
		items.add(i2);
		o2.setOrder_items(items);
		
		session.save(o2);
		
		
		Seller s= new Seller();
		s.setFirstName("seller");
		s.setLastName("seller");
		s.setUsername("seller");
		s.setPassword("seller");
		session.save(s);
		
		session.getTransaction().commit();
		
		sessionFactory.close();
		
	}

}