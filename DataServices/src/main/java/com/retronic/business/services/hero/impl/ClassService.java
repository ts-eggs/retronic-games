package com.retronic.business.services.hero.impl;

import com.retronic.business.services.core.impl.GenericService;
import com.retronic.persistence.daos.core.IGenericDao;
import com.retronic.persistence.entities.hero.Class;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ClassService extends GenericService<Class, Integer> {

    @Autowired
    private IGenericDao<Class, Integer> classDao;

    @Override
    public void init() {
        this.genericDao = classDao;
    }
}