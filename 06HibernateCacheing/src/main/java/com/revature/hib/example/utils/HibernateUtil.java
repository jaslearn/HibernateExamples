package com.revature.hib.example.utils;

import java.util.Properties;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;



public class HibernateUtil {
    private static SessionFactory sessionFactory;
    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration();
                // Hibernate settings equivalent to hibernate.cfg.xml's properties
                Properties settings = new Properties();
                settings.put(Environment.DRIVER, "org.postgresql.Driver");
                settings.put(Environment.URL, "jdbc:postgresql://database-1.cyxwbmqwxsuz.us-east-2.rds.amazonaws.com:5432/forHibernate");
                settings.put(Environment.USER, "postgres");
                settings.put(Environment.PASS, "postgres123");
                settings.put(Environment.DIALECT, "org.hibernate.dialect.PostgreSQLDialect");
                settings.put(Environment.SHOW_SQL, "true");
               settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
                settings.put(Environment.HBM2DDL_AUTO, "update");
             // Enable second level cache 
                settings.put(Environment.USE_SECOND_LEVEL_CACHE, true);
                
                // Specify cache region factory class
                settings.put(Environment.CACHE_REGION_FACTORY,
                      "org.hibernate.cache.jcache.JCacheRegionFactory");
                
                // Specify cache provider
                settings.put("hibernate.javax.cache.provider", 
                      "org.ehcache.jsr107.EhcacheCachingProvider");
                configuration.setProperties(settings);
				
                configuration.addAnnotatedClass(com.revature.hib.example.entity.Person.class);
               
                
                
                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                    .applySettings(configuration.getProperties()).build();
                sessionFactory = configuration.buildSessionFactory(serviceRegistry);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return sessionFactory;
    }
}