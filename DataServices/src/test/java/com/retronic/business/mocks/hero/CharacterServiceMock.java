package com.retronic.business.mocks.hero;

import com.retronic.business.services.core.IGenericService;
import com.retronic.business.services.hero.impl.CharacterService;
import com.retronic.persistence.entities.hero.Character;
import com.retronic.persistence.mocks.hero.CharacterDaoMock;
import com.retronic.persistence.utils.MockUtils;

import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CharacterServiceMock {

    public static IGenericService<Character, Integer> getMock() {
        IGenericService<Character, Integer> service = mock(CharacterService.class);

        when(service.get(anyInt())).thenAnswer(MockUtils.buildAnswerGet(1, CharacterDaoMock.characters));
        when(service.getAll()).thenAnswer(MockUtils.buildAnswerGetAll(CharacterDaoMock.characters));

        return service;
    }
}