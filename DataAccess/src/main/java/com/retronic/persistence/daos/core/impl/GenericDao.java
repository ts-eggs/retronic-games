package com.retronic.persistence.daos.core.impl;

import com.retronic.persistence.daos.core.IGenericDao;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.io.Serializable;
import java.util.List;

@Repository("genericDao")
public class GenericDao<T, I extends Serializable> implements IGenericDao<T, I> {

    @Autowired
    @Qualifier("sessionFactory")
    protected SessionFactory sessionFactory;

    protected Class<T> entityClassType;

    @PostConstruct
    public void init() {
        // will be used for setting the entityClassType by extending classes
    }

    @SuppressWarnings("unchecked")
    public I getMaxId() {
        String queryString = String.format("SELECT max(a.id) FROM %s a", this.entityClassType.getSimpleName());
        I id = (I) this.sessionFactory.getCurrentSession().createQuery(queryString).list().get(0);

        if (id != null) {
            return id;
        }

        return (I) Integer.valueOf(1);
    }

    public T read(I id) {
        return this.sessionFactory.getCurrentSession().get(entityClassType, id);
    }

    public List<T> list() {
        return findListByCriterion();
    }

    @SuppressWarnings("unchecked")
    public I create(T entity) {
        return (I) this.sessionFactory.getCurrentSession().save(entity);
    }

    public void update(T entity) {
        this.sessionFactory.getCurrentSession().update(entity);
    }

    public void merge(T entity) {
        this.sessionFactory.getCurrentSession().merge(entity);
    }

    public void delete(T entity) {
        this.sessionFactory.getCurrentSession().delete(entity);
    }

    @SuppressWarnings("unchecked")
    public T findByCriterion(Order order, Criterion... criterion) {
        return (T) createCriteria(order, criterion).uniqueResult();
    }

    @SuppressWarnings("unchecked")
    public T findByCriterion(Criterion... criterion) {
        return findByCriterion(null, criterion);
    }

    @SuppressWarnings("unchecked")
    public List<T> findListByCriterion(Order order, Criterion... criterion) {
        return (List<T>) createCriteria(order, criterion).list();
    }

    @SuppressWarnings("unchecked")
    public List<T> findListByCriterion(Criterion... criterion) {
        return findListByCriterion(null, criterion);
    }

    private Criteria createCriteria(Order order, Criterion... criterion) {
        Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(this.entityClassType);
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);

        for (Criterion c : criterion) {
            criteria.add(c);
        }

        if (order != null) {
            criteria.addOrder(order);
        }

        return criteria;
    }
}
