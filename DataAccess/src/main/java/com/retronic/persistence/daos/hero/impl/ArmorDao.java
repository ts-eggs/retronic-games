package com.retronic.persistence.daos.hero.impl;

import com.retronic.persistence.daos.core.impl.GenericDao;
import com.retronic.persistence.entities.hero.Armor;
import org.springframework.stereotype.Repository;

@Repository("armorDao")
public class ArmorDao extends GenericDao<Armor, Integer> {

    @Override
    public void init() {
        this.entityClassType = Armor.class;
    }
}