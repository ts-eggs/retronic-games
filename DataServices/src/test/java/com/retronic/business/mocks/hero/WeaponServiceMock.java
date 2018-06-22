package com.retronic.business.mocks.hero;

import com.retronic.business.services.core.IGenericService;
import com.retronic.business.services.hero.impl.WeaponService;
import com.retronic.persistence.entities.hero.Weapon;
import com.retronic.persistence.mocks.hero.WeaponDaoMock;
import com.retronic.persistence.utils.MockUtils;

import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class WeaponServiceMock {

    public static IGenericService<Weapon, Integer> getMock() {
        IGenericService<Weapon, Integer> service = mock(WeaponService.class);

        when(service.get(anyInt())).thenAnswer(MockUtils.buildAnswerGet(1, WeaponDaoMock.weapons));
        when(service.getAll()).thenAnswer(MockUtils.buildAnswerGetAll(WeaponDaoMock.weapons));

        return service;
    }
}