package com.sudeera.backend;

import com.sudeera.backend.entity.Customer;
import com.sudeera.backend.entity.Item;
import com.sudeera.backend.entity.OrderDetails;
import com.sudeera.backend.entity.Orders;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class FactoryConfiguration {
    public static FactoryConfiguration factoryConfiguration;
    private final SessionFactory sessionFactory;
    private FactoryConfiguration(){
        Configuration configuration=new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Customer.class)
                .addAnnotatedClass(Item.class)
                .addAnnotatedClass(OrderDetails.class)
                .addAnnotatedClass(Orders.class);
        sessionFactory=configuration.buildSessionFactory();
    }
    public static FactoryConfiguration getInstance(){
        return factoryConfiguration ==null ? factoryConfiguration =new FactoryConfiguration() : factoryConfiguration;
    }
    public Session getSession(){
        return sessionFactory.openSession();
    }
}
