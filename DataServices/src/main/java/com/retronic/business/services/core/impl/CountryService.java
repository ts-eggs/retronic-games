package com.retronic.business.services.core.impl;

import com.retronic.persistence.daos.core.IGenericDao;
import com.retronic.persistence.entities.core.Country;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("countryService")
public class CountryService extends GenericService<Country, Integer> {

    @Autowired
    private IGenericDao<Country, Integer> countryDao;

    @Override
    public void init() {
        this.genericDao = countryDao;
    }
}
