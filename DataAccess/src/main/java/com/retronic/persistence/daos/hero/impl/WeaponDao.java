package com.retronic.persistence.daos.hero.impl;

import com.retronic.persistence.daos.core.impl.GenericDao;
import com.retronic.persistence.entities.hero.Weapon;
import org.springframework.stereotype.Repository;

@Repository("weaponDao")
public class WeaponDao extends GenericDao<Weapon, Integer> {

    @Override
    public void init() {
        this.entityClassType = Weapon.class;
    }
}