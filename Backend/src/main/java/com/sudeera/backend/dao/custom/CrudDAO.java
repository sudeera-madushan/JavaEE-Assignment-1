package com.sudeera.backend.dao.custom;


import com.sudeera.backend.dao.util.SuperDAO;
import com.sudeera.backend.dao.util.SuperEntity;
import org.hibernate.exception.ConstraintViolationException;

import java.io.Serializable;
import java.util.List;

public interface CrudDAO<T extends SuperEntity,ID extends Serializable> extends SuperDAO {

    T save(T entity) throws ConstraintViolationException;

    T update(T entity) throws ConstraintViolationException;

    T findByPk(ID pk) ;


    public List<T> getAll();
}