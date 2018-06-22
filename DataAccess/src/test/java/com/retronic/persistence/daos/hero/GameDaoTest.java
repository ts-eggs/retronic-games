package com.retronic.persistence.daos.hero;

import com.retronic.persistence.daos.core.IGenericDao;
import com.retronic.persistence.entities.hero.Game;
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
public class GameDaoTest extends AbstractTransactionalJUnit4SpringContextTests {

    @Autowired
    private IGenericDao<Game, Integer> gameDao;

    @Test
    public void testCrud() throws Exception {
        Game game = createGame();
        Integer maxId = gameDao.getMaxId();
        game.setId(maxId);

        Integer id = gameDao.create(game);
        game.setId(id);
        assertTrue(id > 0);

        Game createdGame = gameDao.read(id);
        assertNotNull(createdGame);

        String editName = "my_name_is_edited";
        createdGame.setName(editName);
        gameDao.merge(createdGame);

        Game updatedGame = gameDao.read(id);
        assertNotNull(updatedGame);
        assertEquals(editName, updatedGame.getName());

        gameDao.delete(updatedGame);

        Game deletedGame = gameDao.read(id);
        assertNull(deletedGame);
    }

    private Game createGame() {
        Game game = new Game();
        game.setName("test_game");
        game.setDifficult(1);
        return game;
    }
}