package com.retronic.persistence.mocks.hero;

import com.retronic.persistence.daos.core.IGenericDao;
import com.retronic.persistence.daos.hero.impl.ClassDao;
import com.retronic.persistence.entities.hero.Class;
import com.retronic.persistence.entities.hero.Skill;
import com.retronic.persistence.utils.MockUtils;

import java.io.Serializable;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ClassDaoMock {

    public static Map<Integer, Serializable> classes = new HashMap<Integer, Serializable>();

    static {
        createValues();
    }

    public static IGenericDao<Class, Integer> getMock() {
        IGenericDao<Class, Integer> dao = mock(ClassDao.class);

        when(dao.read(anyInt())).thenAnswer(MockUtils.buildAnswerGet(1, classes));
        when(dao.list()).thenAnswer(MockUtils.buildAnswerGetAll(classes));

        return dao;
    }

    private static void createValues() {
        classes.put(1, createClass(1, "Krieger"));
    }

    private static Class createClass(Integer id, String name) {
        Class c = new Class();
        c.setId(id);
        c.setName(name);
        c.setSkills(Arrays.asList((Skill) SkillDaoMock.skills.get(2), (Skill) SkillDaoMock.skills.get(3)));
        return c;
    }
}