package com.retronic.persistence.mocks.hero;

import com.retronic.persistence.daos.core.IGenericDao;
import com.retronic.persistence.daos.hero.impl.CharacterDao;
import com.retronic.persistence.entities.core.User;
import com.retronic.persistence.entities.hero.*;
import com.retronic.persistence.entities.hero.Character;
import com.retronic.persistence.entities.hero.Class;
import com.retronic.persistence.mocks.core.UserDaoMock;
import com.retronic.persistence.utils.MockUtils;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CharacterDaoMock {

    public static Map<Integer, Serializable> characters = new HashMap<Integer, Serializable>();

    static {
        createValues();
    }

    public static IGenericDao<Character, Integer> getMock() {
        IGenericDao<Character, Integer> dao = mock(CharacterDao.class);

        when(dao.read(anyInt())).thenAnswer(MockUtils.buildAnswerGet(1, characters));
        when(dao.list()).thenAnswer(MockUtils.buildAnswerGetAll(characters));

        return dao;
    }

    private static void createValues() {
        characters.put(1, createCharacter(1, "Hakbar", (User) UserDaoMock.users.get(4)));
        characters.put(2, createCharacter(2, "Noktar", null));
    }

    private static Character createCharacter(Integer id, String name, User user) {
        Character character = new Character();
        character.setId(id);
        character.setName(name);
        character.setUser(user);
        character.setLevel(1);
        character.setFixed(false);
        character.setStrength(2);
        character.setVitality(3);
        character.setDexterity(1);
        character.setRace((Race) RaceDaoMock.races.get(1));
        character.setHeroClass((Class) ClassDaoMock.classes.get(1));
        character.setArmor(Arrays.asList((Armor) ArmorDaoMock.armors.get(1), (Armor) ArmorDaoMock.armors.get(2), (Armor) ArmorDaoMock.armors.get(3)));
        character.setWeapons(Collections.singletonList((Weapon) WeaponDaoMock.weapons.get(1)));
        return character;
    }
}