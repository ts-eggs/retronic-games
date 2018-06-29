package com.retronic.business.mocks.core;

import com.retronic.business.services.IGenericService;
import com.retronic.business.services.core.impl.RoleService;
import com.retronic.persistence.entities.core.Role;
import com.retronic.persistence.mocks.core.RoleDaoMock;
import com.retronic.persistence.utils.MockUtils;

import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class RoleServiceMock {

    public static IGenericService<Role, Integer> getMock() {
        IGenericService<Role, Integer> service = mock(RoleService.class);

        when(service.get(anyInt())).thenAnswer(MockUtils.buildAnswerGet(1, RoleDaoMock.roles));
        when(service.getAll()).thenAnswer(MockUtils.buildAnswerGetAll(RoleDaoMock.roles));

        return service;
    }
}