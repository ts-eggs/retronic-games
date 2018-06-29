package com.retronic.business.services.core.impl;

import com.retronic.business.services.IGenericService;
import com.retronic.persistence.daos.core.IGenericDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.Serializable;
import java.util.List;

@Service("genericService")
public class GenericService<T, I extends Serializable> implements IGenericService<T, I> {

    @Autowired
    protected IGenericDao genericDao;

    @PostConstruct
    public void init() {
        // will be used for setting the genericDao by extending classes
    }

    @SuppressWarnings("unchecked")
    public T get(I id) {
        return (T) this.genericDao.read(id);
    }

    @SuppressWarnings("unchecked")
    public List<T> getAll() {
        return (List<T>) this.genericDao.list();
    }

    @SuppressWarnings("unchecked")
    public I create(T entity) {
        return (I) this.genericDao.create(entity);
    }

    @SuppressWarnings("unchecked")
    public void update(T entity) {
        this.genericDao.merge(entity);
    }

    @SuppressWarnings("unchecked")
    public void delete(T entity) {
        this.genericDao.delete(entity);
    }
}
