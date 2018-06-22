package com.retronic.business.mocks.hero;

import com.retronic.business.services.core.IGenericService;
import com.retronic.business.services.hero.impl.RaceService;
import com.retronic.persistence.entities.hero.Race;
import com.retronic.persistence.mocks.hero.RaceDaoMock;
import com.retronic.persistence.utils.MockUtils;

import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class RaceServiceMock {

    public static IGenericService<Race, Integer> getMock() {
        IGenericService<Race, Integer> service = mock(RaceService.class);

        when(service.get(anyInt())).thenAnswer(MockUtils.buildAnswerGet(1, RaceDaoMock.races));
        when(service.getAll()).thenAnswer(MockUtils.buildAnswerGetAll(RaceDaoMock.races));

        return service;
    }
}