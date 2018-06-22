package com.retronic.persistence.mocks.hero;

import com.retronic.persistence.daos.core.IGenericDao;
import com.retronic.persistence.daos.hero.impl.RaceDao;
import com.retronic.persistence.entities.hero.Race;
import com.retronic.persistence.entities.hero.Skill;
import com.retronic.persistence.utils.MockUtils;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class RaceDaoMock {

    public static Map<Integer, Serializable> races = new HashMap<Integer, Serializable>();

    static {
        createValues();
    }

    public static IGenericDao<Race, Integer> getMock() {
        IGenericDao<Race, Integer> dao = mock(RaceDao.class);

        when(dao.read(anyInt())).thenAnswer(MockUtils.buildAnswerGet(1, races));
        when(dao.list()).thenAnswer(MockUtils.buildAnswerGetAll(races));

        return dao;
    }

    private static void createValues() {
        races.put(1, createRace(1, "Zwerg"));
    }

    private static Race createRace(Integer id, String name) {
        Race race = new Race();
        race.setId(id);
        race.setName(name);
        race.setSkill((Skill) SkillDaoMock.skills.get(1));
        return race;
    }
}