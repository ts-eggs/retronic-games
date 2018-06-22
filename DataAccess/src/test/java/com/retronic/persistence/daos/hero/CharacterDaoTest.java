package com.retronic.persistence.daos.hero;

import com.retronic.persistence.daos.core.IGenericDao;
import com.retronic.persistence.entities.hero.Character;
import com.retronic.persistence.entities.hero.Class;
import com.retronic.persistence.entities.hero.Race;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:spring.xml"})
@Transactional(transactionManager = "transactionManager")
@Rollback
public class CharacterDaoTest extends AbstractTransactionalJUnit4SpringContextTests {

    @Autowired
    private IGenericDao<Character, Integer> characterDao;

    @Autowired
    private IGenericDao<Race, Integer> raceDao;

    @Autowired
    private IGenericDao<Class, Integer> classDao;

    @Test
    public void testCrud() throws Exception {
        Character character = createCharacter();
        Integer maxId = characterDao.getMaxId();
        character.setId(maxId);

        Integer id = characterDao.create(character);
        character.setId(id);
        assertTrue(id > 0);

        Character createdCharacter = characterDao.read(id);
        assertNotNull(createdCharacter);

        String editName = "my_name_is_edited";
        createdCharacter.setName(editName);
        characterDao.merge(createdCharacter);

        Character updatedCharacter = characterDao.read(id);
        assertNotNull(updatedCharacter);
        assertEquals(editName, updatedCharacter.getName());

        characterDao.delete(updatedCharacter);

        Character deletedCharacter = characterDao.read(id);
        assertNull(deletedCharacter);
    }

    private Character createCharacter() {
        Character character = new Character();
        character.setName("test_char");
        character.setLevel(1);
        character.setStrength(2);
        character.setVitality(3);
        character.setDexterity(1);
        character.setRace(raceDao.read(1));
        character.setHeroClass(classDao.read(1));
        return character;
    }
}