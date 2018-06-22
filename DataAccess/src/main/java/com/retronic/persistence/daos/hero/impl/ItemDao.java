package com.retronic.persistence.daos.hero.impl;

import com.retronic.persistence.daos.core.impl.GenericDao;
import com.retronic.persistence.entities.hero.Item;
import org.springframework.stereotype.Repository;

@Repository("itemDao")
public class ItemDao extends GenericDao<Item, Integer> {

    @Override
    public void init() {
        this.entityClassType = Item.class;
    }
}