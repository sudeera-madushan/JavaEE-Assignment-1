package com.sudeera.backend.dao.custom.impl;

import com.sudeera.backend.dao.custom.ItemDAO;
import com.sudeera.backend.entity.Customer;
import com.sudeera.backend.entity.Item;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.exception.ConstraintViolationException;

import javax.persistence.criteria.CriteriaQuery;
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
        Transaction transaction;
        try {

            Item item = session.get(Item.class, entity.getId());
            if (item==null) {
                transaction=session.beginTransaction();
                int id = (int) session.save(entity);
                entity.setId(id);
                transaction.commit();
            }else {
                transaction=session.beginTransaction();
                item.setQtyOnHand(entity.getQtyOnHand());
                item.setName(entity.getName());
                item.setUnitPrice(entity.getUnitPrice());
                session.update(item);
                transaction.commit();
            }
            return entity;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Item findByPk(Integer pk) {
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
    public Item delete(Item entity) {
        Transaction transaction=session.beginTransaction();
        try {
            Item item = session.get(Item.class, entity.getId());
            session.delete(item);
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
        CriteriaQuery<Item> query = session.getCriteriaBuilder().createQuery(Item.class);
        query.from(Item.class);
        List<Item> resultList = session.createQuery(query).getResultList();
        return resultList;
    }
}
