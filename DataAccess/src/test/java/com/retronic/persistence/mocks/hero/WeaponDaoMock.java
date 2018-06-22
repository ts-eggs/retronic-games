package com.retronic.persistence.mocks.hero;

import com.retronic.persistence.daos.core.IGenericDao;
import com.retronic.persistence.daos.hero.impl.WeaponDao;
import com.retronic.persistence.entities.hero.Weapon;
import com.retronic.persistence.utils.MockUtils;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class WeaponDaoMock {

    public static Map<Integer, Serializable> weapons = new HashMap<Integer, Serializable>();

    static {
        createValues();
    }

    public static IGenericDao<Weapon, Integer> getMock() {
        IGenericDao<Weapon, Integer> dao = mock(WeaponDao.class);

        when(dao.read(anyInt())).thenAnswer(MockUtils.buildAnswerGet(1, weapons));
        when(dao.list()).thenAnswer(MockUtils.buildAnswerGetAll(weapons));

        return dao;
    }

    private static void createValues() {
        weapons.put(1, createWeapon(1, "Eisenschwert", "Erhöht Angriff um 1.", 1, 1, 1, 1f));
        weapons.put(2, createWeapon(2, "Hammer", "Erhöht Angriff um 2.", 1, 2, 2, 2f));
    }

    private static Weapon createWeapon(Integer id, String name, String description, Integer level, Integer hands, Integer costs, Float value) {
        Weapon weapon = new Weapon();
        weapon.setId(id);
        weapon.setName(name);
        weapon.setDescription(description);
        weapon.setLevel(level);
        weapon.setHands(hands);
        weapon.setCosts(costs);
        weapon.setValue(value);
        return weapon;
    }
}