package com.retronic.persistence.daos.core;

import com.retronic.persistence.entities.core.Country;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:spring.xml"})
@Transactional(transactionManager = "transactionManager")
@Rollback
public class CountryDaoTest extends AbstractTransactionalJUnit4SpringContextTests {

    @Autowired
    private IGenericDao<Country, Integer> countryDao;

    @Test
    public void testRead() throws Exception {
        Country country = countryDao.read(1);
        assertNotNull(country);
    }

    @Test
    public void testReadAll() throws Exception {
        List<Country> countrys = countryDao.list();
        assertNotNull(countrys);
        assertTrue(!countrys.isEmpty());
    }
}
