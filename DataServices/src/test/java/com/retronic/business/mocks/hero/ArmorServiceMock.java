package com.retronic.business.mocks.hero;

import com.retronic.business.services.IGenericService;
import com.retronic.business.services.hero.impl.ArmorService;
import com.retronic.persistence.entities.hero.Armor;
import com.retronic.persistence.mocks.hero.ArmorDaoMock;
import com.retronic.persistence.utils.MockUtils;

import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ArmorServiceMock {

    public static IGenericService<Armor, Integer> getMock() {
        IGenericService<Armor, Integer> service = mock(ArmorService.class);

        when(service.get(anyInt())).thenAnswer(MockUtils.buildAnswerGet(1, ArmorDaoMock.armors));
        when(service.getAll()).thenAnswer(MockUtils.buildAnswerGetAll(ArmorDaoMock.armors));

        return service;
    }
}