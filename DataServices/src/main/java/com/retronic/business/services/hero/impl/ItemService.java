package com.retronic.business.services.hero.impl;

import com.retronic.business.services.core.impl.GenericService;
import com.retronic.persistence.daos.core.IGenericDao;
import com.retronic.persistence.entities.hero.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ItemService extends GenericService<Item, Integer> {

    @Autowired
    private IGenericDao<Item, Integer> itemDao;

    @Override
    public void init() {
        this.genericDao = itemDao;
    }
}