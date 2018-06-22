package com.retronic.persistence.daos.hero.impl;

import com.retronic.persistence.daos.core.impl.GenericDao;
import com.retronic.persistence.entities.hero.Class;
import org.springframework.stereotype.Repository;

@Repository("classDao")
public class ClassDao extends GenericDao<Class, Integer> {

    @Override
    public void init() {
        this.entityClassType = Class.class;
    }
}