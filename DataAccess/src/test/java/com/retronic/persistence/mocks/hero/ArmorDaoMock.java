package com.retronic.persistence.mocks.hero;

import com.retronic.persistence.daos.core.IGenericDao;
import com.retronic.persistence.daos.hero.impl.ArmorDao;
import com.retronic.persistence.entities.hero.Armor;
import com.retronic.persistence.entities.hero.Skill;
import com.retronic.persistence.utils.MockUtils;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ArmorDaoMock {

    public static Map<Integer, Serializable> armors = new HashMap<Integer, Serializable>();

    static {
        createValues();
    }

    public static IGenericDao<Armor, Integer> getMock() {
        IGenericDao<Armor, Integer> dao = mock(ArmorDao.class);

        when(dao.read(anyInt())).thenAnswer(MockUtils.buildAnswerGet(1, armors));
        when(dao.list()).thenAnswer(MockUtils.buildAnswerGetAll(armors));

        return dao;
    }

    private static void createValues() {
        armors.put(1, createArmor(1, "Lederhelm", "Erhöht Verteidigung um 1.", 1, 1, 1, 1f));
        armors.put(2, createArmor(2, "Lederrüstung", "Erhöht Verteidigung um 1.", 1, 1, 1, 1f));
        armors.put(3, createArmor(3, "Holzschild", "Erhöht Verteidigung um 1.", 1, 1, 1, 1f));
    }

    private static Armor createArmor(Integer id, String name, String description, Integer level, Integer hands, Integer costs, Float value) {
        Armor armor = new Armor();
        armor.setId(id);
        armor.setName(name);
        armor.setDescription(description);
        armor.setLevel(level);
        armor.setHands(hands);
        armor.setCosts(costs);
        armor.setValue(value);
        armor.setSkill((Skill) SkillDaoMock.skills.get(4));
        return armor;
    }
}