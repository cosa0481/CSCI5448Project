package com.project;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

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
		session = sessionFactory.openSession();
		return session;
	}
	
	public void setSession(Session s) {
		session = s;
	}
	
	public boolean insert(Object object) {
		try{
			session = sessionFactory.openSession();
			session.beginTransaction();
			session.saveOrUpdate(object);
			session.getTransaction().commit();
			return session.getTransaction().wasCommitted();
		}finally{
			closeSession();
		}
	}
	
	public List<Object> retrieve(Class c, List<Criterion> criterions) {
		try{
			session = sessionFactory.openSession();
			Criteria criteria = session.createCriteria(c);
			for(Criterion criterion : criterions) {
				criteria.add(criterion);
			}
			return criteria.list();
		}finally{
			closeSession();
		}
	}
	
	public void closeSession() {
		session.close();
	}
}
