package com.retronic.persistence.daos.core;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;

import java.io.Serializable;
import java.util.List;

public interface IGenericDao<T, I extends Serializable> {

    void init();

    I getMaxId();

    T read(I id);

    List<T> list();

    I create(T entity);

    void update(T entity);

    void merge(T entity);

    void delete(T entity);

    T findByCriterion(Order order, Criterion... criterion);

    T findByCriterion(Criterion... criterion);

    List<T> findListByCriterion(Order order, Criterion... criterion);

    List<T> findListByCriterion(Criterion... criterion);
}
