package com.retronic.business.services.hero;

import com.retronic.business.services.IGenericService;
import com.retronic.business.services.hero.impl.CharacterService;
import com.retronic.persistence.daos.core.IGenericDao;
import com.retronic.persistence.entities.hero.Character;
import com.retronic.persistence.mocks.hero.CharacterDaoMock;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.List;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.verify;

public class CharacterServiceTest {

    private IGenericService<Character, Integer> characterService;
    private IGenericDao<Character, Integer> characterDao;

    @Before
    public void setUp() {
        characterService = new CharacterService();
        characterDao = CharacterDaoMock.getMock();
        ReflectionTestUtils.setField(characterService, "genericDao", characterDao);
    }

    @Test
    public void testGet() {
        Character character = characterService.get(1);
        verify(characterDao).read(1);
        assertTrue(character.getId().equals(1));
    }

    @Test
    public void testGetAll() {
        List<Character> characters = characterService.getAll();
        verify(characterDao).list();
        assertTrue(characters.size() > 0);
    }
}