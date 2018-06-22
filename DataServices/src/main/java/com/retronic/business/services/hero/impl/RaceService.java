package com.retronic.business.services.hero.impl;

import com.retronic.business.services.core.impl.GenericService;
import com.retronic.persistence.daos.core.IGenericDao;
import com.retronic.persistence.entities.hero.Race;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RaceService extends GenericService<Race, Integer> {

    @Autowired
    private IGenericDao<Race, Integer> raceDao;

    @Override
    public void init() {
        this.genericDao = raceDao;
    }
}