package com.project;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.hibernate.HibernateUtil;

public class DatabaseManager {
	private static DatabaseManager instance;
	private SessionFactory sessionFactory;
	private Session session;
	
	private DatabaseManager() {
		sessionFactory = HibernateUtil.getSessionAnnotationFactory();
		session = sessionFactory.getCurrentSession();
	}
	
	public static DatabaseManager getInstance() {
		if(instance == null) {
			instance = new DatabaseManager();
		}
		return instance;
	}
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	
	public void setSessionFactory(SessionFactory sF) {
		sessionFactory = sF;
	}
	
	public Session getSession() {
		return session;
	}
	
	public void setSession(Session s) {
		session = s;
	}
	
	public boolean insert(Object object) {
		session.beginTransaction();
		session.saveOrUpdate(object);
		session.getTransaction().commit();
		return session.getTransaction().wasCommitted();
	}
	
	public Object retrieve() {
		Object object = new Object();
		return object;
	}
	
	public void closeSession() {
		sessionFactory.close();
	}
}
