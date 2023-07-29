package com.sudeera.backend.dao.custom.impl;

import com.sudeera.backend.dao.custom.ItemDAO;
import com.sudeera.backend.entity.Item;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.exception.ConstraintViolationException;

import java.util.ArrayList;
import java.util.List;

public class ItemDAOImpl implements ItemDAO {
    private final Session session;

    public ItemDAOImpl(Session session) {
        this.session=session;
    }


    @Override
    public Item update(Item entity) throws ConstraintViolationException {
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
    public Item save(Item entity) throws ConstraintViolationException {
        Transaction transaction=session.beginTransaction();
        try {
            int id = (int) session.save(entity);
            transaction.commit();
            entity.setId(id);
            return entity;
        }catch (Exception e){
            transaction.rollback();
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Item findByPk(String pk) {
        Transaction transaction=session.beginTransaction();
        try {
            Item item  = (Item) session.get(Item.class,pk);
            transaction.commit();
            return item;
        }catch (Exception e){
            transaction.rollback();
            e.printStackTrace();
            return null;
        }
    }
    @Override
    public List<Item> getAll() {
        String SQL="From item ";
        List<Item> list = session.createQuery(SQL).list();
        return new ArrayList<>(list);
    }

}
