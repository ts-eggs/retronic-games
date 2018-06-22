package com.retronic.business.services.hero;

import com.retronic.business.services.core.IGenericService;
import com.retronic.business.services.hero.impl.ArmorService;
import com.retronic.persistence.daos.core.IGenericDao;
import com.retronic.persistence.entities.hero.Armor;
import com.retronic.persistence.mocks.hero.ArmorDaoMock;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.List;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.verify;

public class ArmorServiceTest {

    private IGenericService<Armor, Integer> armorService;
    private IGenericDao<Armor, Integer> armorDao;

    @Before
    public void setUp() {
        armorService = new ArmorService();
        armorDao = ArmorDaoMock.getMock();
        ReflectionTestUtils.setField(armorService, "genericDao", armorDao);
    }

    @Test
    public void testGet() {
        Armor armor = armorService.get(1);
        verify(armorDao).read(1);
        assertTrue(armor.getId().equals(1));
    }

    @Test
    public void testGetAll() {
        List<Armor> armors = armorService.getAll();
        verify(armorDao).list();
        assertTrue(armors.size() > 1);
    }
}