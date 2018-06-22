package com.retronic.business.mocks.core;

import com.retronic.business.services.core.IGenericService;
import com.retronic.business.services.core.impl.CountryService;
import com.retronic.persistence.entities.core.Country;
import com.retronic.persistence.mocks.core.CountryDaoMock;
import com.retronic.persistence.utils.MockUtils;

import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CountryServiceMock {

    public static IGenericService<Country, Integer> getMock() {
        IGenericService<Country, Integer> service = mock(CountryService.class);

        when(service.get(anyInt())).thenAnswer(MockUtils.buildAnswerGet(1, CountryDaoMock.countries));
        when(service.getAll()).thenAnswer(MockUtils.buildAnswerGetAll(CountryDaoMock.countries));

        return service;
    }
}