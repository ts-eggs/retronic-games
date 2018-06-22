package com.retronic.business.mocks.core;

import com.retronic.business.services.core.IUserService;
import com.retronic.business.services.core.impl.UserService;
import com.retronic.persistence.mocks.core.UserDaoMock;
import com.retronic.persistence.utils.MockUtils;

import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class UserServiceMock {

    public static IUserService getMock() {
        IUserService service = mock(UserService.class);

        when(service.get(anyInt())).thenAnswer(MockUtils.buildAnswerGet(1, UserDaoMock.users));
        when(service.getAll()).thenAnswer(MockUtils.buildAnswerGetAll(UserDaoMock.users));

        return service;
    }
}