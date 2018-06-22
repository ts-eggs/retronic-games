package com.retronic.persistence.mocks.hero;

import com.retronic.persistence.daos.core.IGenericDao;
import com.retronic.persistence.daos.hero.impl.SkillDao;
import com.retronic.persistence.entities.hero.Skill;
import com.retronic.persistence.utils.MockUtils;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class SkillDaoMock {

    public static Map<Integer, Serializable> skills = new HashMap<Integer, Serializable>();

    static {
        createValues();
    }

    public static IGenericDao<Skill, Integer> getMock() {
        IGenericDao<Skill, Integer> dao = mock(SkillDao.class);

        when(dao.read(anyInt())).thenAnswer(MockUtils.buildAnswerGet(1, skills));
        when(dao.list()).thenAnswer(MockUtils.buildAnswerGetAll(skills));

        return dao;
    }

    private static void createValues() {
        skills.put(1, createSkill(1, "Betäubung", "Der Gegner kann in dieser Kampfrunde keine Fähigkeiten einsetzen.", 1, 13, 0f));
        skills.put(2, createSkill(2, "Schildwall", "Erhöht Verteidigung um 1 für diese Kampfrunde.", 1, 1, 1f));
        skills.put(3, createSkill(3, "Waffengeschick", "Der Spieler kann verbesserte Waffen anlegen.", 3, 12, 0f));
        skills.put(4, createSkill(4, "Verteidigung", "Erhöht Verteidigung um 1 für diese Kampfrunde.", 1, 1, 1f));
        skills.put(5, createSkill(5, "Heilung", "Heilt den Spieler um 1 Vitalität.", 1, 4, 1f));
    }

    private static Skill createSkill(Integer id, String name, String desc, Integer level, Integer action, Float value) {
        Skill skill = new Skill();
        skill.setId(id);
        skill.setName(name);
        skill.setDescription(desc);
        skill.setLevel(level);
        skill.setAction(action);
        skill.setValue(value);
        return skill;
    }
}