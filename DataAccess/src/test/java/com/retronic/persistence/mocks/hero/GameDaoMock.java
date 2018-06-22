package com.retronic.persistence.mocks.hero;

import com.retronic.persistence.daos.core.IGenericDao;
import com.retronic.persistence.daos.hero.impl.GameDao;
import com.retronic.persistence.entities.hero.Character;
import com.retronic.persistence.entities.hero.Game;
import com.retronic.persistence.utils.MockUtils;

import java.io.Serializable;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class GameDaoMock {

    public static Map<Integer, Serializable> games = new HashMap<Integer, Serializable>();

    static {
        createValues();
    }

    public static IGenericDao<Game, Integer> getMock() {
        IGenericDao<Game, Integer> dao = mock(GameDao.class);

        when(dao.read(anyInt())).thenAnswer(MockUtils.buildAnswerGet(1, games));
        when(dao.list()).thenAnswer(MockUtils.buildAnswerGetAll(games));

        return dao;
    }

    private static void createValues() {
        games.put(1, createGame(1, "game1"));
    }

    private static Game createGame(Integer id, String name) {
        Game game = new Game();
        game.setId(id);
        game.setName(name);
        game.setDifficult(1);
        game.setCharacters(Arrays.asList((Character) CharacterDaoMock.characters.get(1), (Character) CharacterDaoMock.characters.get(2)));
        return game;
    }
}