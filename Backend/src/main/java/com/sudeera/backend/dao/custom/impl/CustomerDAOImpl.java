package com.sudeera.backend.dao.custom.impl;

import com.sudeera.backend.dao.custom.CustomerDAO;
import com.sudeera.backend.entity.Customer;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.exception.ConstraintViolationException;

import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAOImpl implements CustomerDAO {
    private final Session session;

    public CustomerDAOImpl(Session session) {
        this.session=session;
    }


    @Override
    public Customer update(Customer entity) throws ConstraintViolationException {
        Transaction transaction=session.beginTransaction();
        try {
            session.update(entity);
            transaction.commit();
            return entity;
        }catch (Exception e){
            transaction.rollback();
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Customer save(Customer entity) throws ConstraintViolationException {
        Transaction transaction;
        try {

            Customer customer = session.get(Customer.class, entity.getId());
            if (customer==null) {
                transaction=session.beginTransaction();
                int id = (int) session.save(entity);
                entity.setId(id);
                transaction.commit();
            }else {
                transaction=session.beginTransaction();
                customer.setAddress(entity.getAddress());
                customer.setName(entity.getName());
                customer.setSalary(entity.getSalary());
                session.update(customer);
                transaction.commit();
            }
            return entity;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Customer findByPk(Integer pk) {
       Transaction transaction=session.beginTransaction();
        try {
            Customer customer  = (Customer) session.get(Customer.class,pk);
            transaction.commit();
            return customer;
        }catch (Exception e){
            transaction.rollback();
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Customer delete(Customer entity) {
        Transaction transaction=session.beginTransaction();
        try {
            Customer customer = session.get(Customer.class, entity.getId());
            session.delete(customer);
            transaction.commit();
            return customer;
        }catch (Exception e){
            transaction.rollback();
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public List<Customer> getAll() {
        CriteriaQuery<Customer> query = session.getCriteriaBuilder().createQuery(Customer.class);
        query.from(Customer.class);
        List<Customer> resultList = session.createQuery(query).getResultList();
//        String SQL="From customer ";
//        List<Customer> list = session.createQuery(SQL).list();
        return resultList;
    }

}
