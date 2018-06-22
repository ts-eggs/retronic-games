package com.retronic.business.services.hero.impl;

import com.retronic.business.services.core.impl.GenericService;
import com.retronic.persistence.daos.core.IGenericDao;
import com.retronic.persistence.entities.hero.Armor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ArmorService extends GenericService<Armor, Integer> {

    @Autowired
    private IGenericDao<Armor, Integer> armorDao;

    @Override
    public void init() {
        this.genericDao = armorDao;
    }
}