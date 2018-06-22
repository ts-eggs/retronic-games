package com.retronic.persistence.daos.hero.impl;

import com.retronic.persistence.daos.core.impl.GenericDao;
import com.retronic.persistence.entities.hero.Race;
import org.springframework.stereotype.Repository;

@Repository("raceDao")
public class RaceDao extends GenericDao<Race, Integer> {

    @Override
    public void init() {
        this.entityClassType = Race.class;
    }
}