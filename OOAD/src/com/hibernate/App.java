package com.hibernate;

import org.hibernate.Session;

public class App 
{
    public static void main( String[] args )
    {
        System.out.println("Maven + Hibernate + MySQL");
        Session session = HibernateUtil.getSessionFactory().openSession();
        
        session.beginTransaction();
        Stock stock = new Stock();
        
        stock.setStockCode("abcd");
        stock.setStockName("1234");
        
        session.save(stock);
        session.getTransaction().commit();
    }
}
