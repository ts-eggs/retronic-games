package com.retronic.business.mocks.hero;

import com.retronic.business.services.IGenericService;
import com.retronic.business.services.hero.impl.GameService;
import com.retronic.persistence.entities.hero.Game;
import com.retronic.persistence.mocks.hero.GameDaoMock;
import com.retronic.persistence.utils.MockUtils;

import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class GameServiceMock {

    public static IGenericService<Game, Integer> getMock() {
        IGenericService<Game, Integer> service = mock(GameService.class);

        when(service.get(anyInt())).thenAnswer(MockUtils.buildAnswerGet(1, GameDaoMock.games));
        when(service.getAll()).thenAnswer(MockUtils.buildAnswerGetAll(GameDaoMock.games));

        return service;
    }
}