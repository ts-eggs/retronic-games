package com.retronic.persistence.mocks.core;

import com.retronic.persistence.daos.core.IGenericDao;
import com.retronic.persistence.daos.core.impl.CountryDao;
import com.retronic.persistence.entities.core.Country;
import com.retronic.persistence.utils.MockUtils;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CountryDaoMock {

    public static Map<Integer, Serializable> countries = new HashMap<Integer, Serializable>();

    static {
        createValues();
    }

    public static IGenericDao<Country, Integer> getMock() {
        IGenericDao<Country, Integer> dao = mock(CountryDao.class);

        when(dao.read(anyInt())).thenAnswer(MockUtils.buildAnswerGet(1, countries));
        when(dao.list()).thenAnswer(MockUtils.buildAnswerGetAll(countries));

        return dao;
    }

    private static void createValues() {
        countries.put(1, createCountry(1, "test_deutschland", "de"));
        countries.put(2, createCountry(2, "test_großbritanien", "uk"));
    }

    private static Country createCountry(Integer id, String name, String domain) {
        Country country = new Country();
        country.setId(id);
        country.setName(name);
        country.setDomain(domain);
        return country;
    }
}