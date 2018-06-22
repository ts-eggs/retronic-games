package com.retronic.business.services.hero.impl;

import com.retronic.business.services.core.impl.GenericService;
import com.retronic.persistence.daos.core.IGenericDao;
import com.retronic.persistence.entities.hero.Weapon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class WeaponService extends GenericService<Weapon, Integer> {

    @Autowired
    private IGenericDao<Weapon, Integer> weaponDao;

    @Override
    public void init() {
        this.genericDao = weaponDao;
    }
}