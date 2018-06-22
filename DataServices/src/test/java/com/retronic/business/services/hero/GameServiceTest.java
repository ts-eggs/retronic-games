package com.retronic.business.services.hero;

import com.retronic.business.services.core.IGenericService;
import com.retronic.business.services.hero.impl.GameService;
import com.retronic.persistence.daos.core.IGenericDao;
import com.retronic.persistence.entities.hero.Game;
import com.retronic.persistence.mocks.hero.GameDaoMock;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.List;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.verify;

public class GameServiceTest {

    private IGenericService<Game, Integer> gameService;
    private IGenericDao<Game, Integer> gameDao;

    @Before
    public void setUp() {
        gameService = new GameService();
        gameDao = GameDaoMock.getMock();
        ReflectionTestUtils.setField(gameService, "genericDao", gameDao);
    }

    @Test
    public void testGet() {
        Game game = gameService.get(1);
        verify(gameDao).read(1);
        assertTrue(game.getId().equals(1));
    }

    @Test
    public void testGetAll() {
        List<Game> games = gameService.getAll();
        verify(gameDao).list();
        assertTrue(games.size() > 0);
    }
}