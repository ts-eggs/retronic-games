package com.retronic.persistence.daos.hero;

import com.retronic.persistence.daos.core.IGenericDao;
import com.retronic.persistence.entities.hero.Weapon;
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
public class WeaponDaoTest extends AbstractTransactionalJUnit4SpringContextTests {

    @Autowired
    private IGenericDao<Weapon, Integer> weaponDao;

    @Test
    public void testRead() throws Exception {
        Weapon weapon = weaponDao.read(1);
        assertNotNull(weapon);
    }

    @Test
    public void testReadAll() throws Exception {
        List<Weapon> weapons = weaponDao.list();
        assertNotNull(weapons);
        assertTrue(!weapons.isEmpty());
    }
}
