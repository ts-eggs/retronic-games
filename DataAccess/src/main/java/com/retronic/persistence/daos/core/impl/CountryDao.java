package com.retronic.persistence.daos.core.impl;

import com.retronic.persistence.entities.core.Country;
import org.springframework.stereotype.Repository;

@Repository("countryDao")
public class CountryDao extends GenericDao<Country, Integer> {

    @Override
    public void init() {
        this.entityClassType = Country.class;
    }
}
