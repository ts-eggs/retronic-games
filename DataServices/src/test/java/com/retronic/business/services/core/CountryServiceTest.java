package com.retronic.business.services.core;

import com.retronic.business.services.core.impl.CountryService;
import com.retronic.persistence.daos.core.IGenericDao;
import com.retronic.persistence.entities.core.Country;
import com.retronic.persistence.mocks.core.CountryDaoMock;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.List;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.verify;

public class CountryServiceTest {

    private IGenericService<Country, Integer> countryService;
    private IGenericDao<Country, Integer> countryDao;

    @Before
    public void setUp() {
        countryService = new CountryService();
        countryDao = CountryDaoMock.getMock();
        ReflectionTestUtils.setField(countryService, "genericDao", countryDao);
    }

    @Test
    public void testGet() {
        Country country = countryService.get(1);
        verify(countryDao).read(1);
        assertTrue(country.getId().equals(1));
    }

    @Test
    public void testGetAll() {
        List<Country> countries = countryService.getAll();
        verify(countryDao).list();
        assertTrue(countries.size() > 1);
    }
}