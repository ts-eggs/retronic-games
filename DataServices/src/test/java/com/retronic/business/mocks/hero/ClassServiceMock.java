package com.retronic.business.mocks.hero;

import com.retronic.business.services.core.IGenericService;
import com.retronic.business.services.hero.impl.ClassService;
import com.retronic.persistence.entities.hero.Class;
import com.retronic.persistence.mocks.hero.ClassDaoMock;
import com.retronic.persistence.utils.MockUtils;

import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ClassServiceMock {

    public static IGenericService<Class, Integer> getMock() {
        IGenericService<Class, Integer> service = mock(ClassService.class);

        when(service.get(anyInt())).thenAnswer(MockUtils.buildAnswerGet(1, ClassDaoMock.classes));
        when(service.getAll()).thenAnswer(MockUtils.buildAnswerGetAll(ClassDaoMock.classes));

        return service;
    }
}