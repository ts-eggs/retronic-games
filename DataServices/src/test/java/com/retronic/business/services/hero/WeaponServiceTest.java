package com.retronic.business.services.hero;

import com.retronic.business.services.IGenericService;
import com.retronic.business.services.hero.impl.WeaponService;
import com.retronic.persistence.daos.core.IGenericDao;
import com.retronic.persistence.entities.hero.Weapon;
import com.retronic.persistence.mocks.hero.WeaponDaoMock;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.List;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.verify;

public class WeaponServiceTest {

    private IGenericService<Weapon, Integer> weaponService;
    private IGenericDao<Weapon, Integer> weaponDao;

    @Before
    public void setUp() {
        weaponService = new WeaponService();
        weaponDao = WeaponDaoMock.getMock();
        ReflectionTestUtils.setField(weaponService, "genericDao", weaponDao);
    }

    @Test
    public void testGet() {
        Weapon weapon = weaponService.get(1);
        verify(weaponDao).read(1);
        assertTrue(weapon.getId().equals(1));
    }

    @Test
    public void testGetAll() {
        List<Weapon> weapons = weaponService.getAll();
        verify(weaponDao).list();
        assertTrue(weapons.size() > 0);
    }
}