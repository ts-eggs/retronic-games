package com.retronic.persistence.mocks.hero;

import com.retronic.persistence.daos.core.IGenericDao;
import com.retronic.persistence.daos.hero.impl.ItemDao;
import com.retronic.persistence.entities.hero.Item;
import com.retronic.persistence.entities.hero.Skill;
import com.retronic.persistence.utils.MockUtils;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ItemDaoMock {

    public static Map<Integer, Serializable> items = new HashMap<Integer, Serializable>();

    static {
        createValues();
    }

    public static IGenericDao<Item, Integer> getMock() {
        IGenericDao<Item, Integer> dao = mock(ItemDao.class);

        when(dao.read(anyInt())).thenAnswer(MockUtils.buildAnswerGet(1, items));
        when(dao.list()).thenAnswer(MockUtils.buildAnswerGetAll(items));

        return dao;
    }

    private static void createValues() {
        items.put(1, createItem(1, "Heiltrank (klein)", "Stellt 1 Vitalität wieder her.", 1, 1, 1f));
    }

    private static Item createItem(Integer id, String name, String description, Integer level, Integer costs, Float value) {
        Item item = new Item();
        item.setId(id);
        item.setName(name);
        item.setDescription(description);
        item.setLevel(level);
        item.setCosts(costs);
        item.setValue(value);
        item.setSkill((Skill) SkillDaoMock.skills.get(5));
        return item;
    }
}