package com.configuration;

import org.hibernate.SessionFactory; 
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.*; 
import org.hibernate.cfg.Configuration; 
import javax.persistence.metamodel.*; 
import org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor;
import org.hibernate.service.ServiceRegistry; 
 
public class HibernateUtil
{ 
    private static SessionFactory sessionFactory = null;
    
    public static synchronized SessionFactory getSessionFactory() {
        if (sessionFactory == null)
            setup();
        return sessionFactory;
    }
    public static synchronized void setup() {
        // A SessionFactory is set up once for an application!
        final StandardServiceRegistry registry 
            = new StandardServiceRegistryBuilder()
            .configure("hibernate.cfg.xml")
            .build();
        try {
            sessionFactory = new MetadataSources( registry ).buildMetadata().buildSessionFactory();
        }
        catch (Exception e) {
            // The registry would be destroyed by the SessionFactory, but we had trouble building the SessionFactory
            // so destroy it manually.
            StandardServiceRegistryBuilder.destroy( registry );
        }
    }
}