package com.retronic.persistence.mocks.core;

import com.retronic.persistence.daos.core.IGenericDao;
import com.retronic.persistence.daos.core.impl.RoleDao;
import com.retronic.persistence.entities.core.Role;
import com.retronic.persistence.utils.MockUtils;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class RoleDaoMock {

    public static Map<Integer, Serializable> roles = new HashMap<Integer, Serializable>();

    static {
        createValues();
    }

    public static IGenericDao<Role, Integer> getMock() {
        IGenericDao<Role, Integer> dao = mock(RoleDao.class);

        when(dao.read(anyInt())).thenAnswer(MockUtils.buildAnswerGet(1, roles));
        when(dao.list()).thenAnswer(MockUtils.buildAnswerGetAll(roles));

        return dao;
    }

    private static void createValues() {
        roles.put(1, createRole(1, "test_system", 100));
        roles.put(2, createRole(2, "test_administrator", 90));
        roles.put(3, createRole(3, "test_advanced", 20));
        roles.put(4, createRole(4, "test_basic", 10));
    }

    private static Role createRole(Integer id, String name, Integer accessLevel) {
        Role role = new Role();
        role.setId(id);
        role.setName(name);
        role.setAccessLevel(accessLevel);
        return role;
    }
}