package com.retronic.business.services.core;

import com.retronic.business.services.IGenericService;
import com.retronic.business.services.core.impl.RoleService;
import com.retronic.persistence.daos.core.IGenericDao;
import com.retronic.persistence.entities.core.Role;
import com.retronic.persistence.mocks.core.RoleDaoMock;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.List;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.verify;

public class RoleServiceTest {

    private IGenericService<Role, Integer> roleService;
    private IGenericDao<Role, Integer> roleDao;

    @Before
    public void setUp() {
        roleService = new RoleService();
        roleDao = RoleDaoMock.getMock();
        ReflectionTestUtils.setField(roleService, "genericDao", roleDao);
    }

    @Test
    public void testGet() {
        Role role = roleService.get(1);
        verify(roleDao).read(1);
        assertTrue(role.getId().equals(1));
    }

    @Test
    public void testGetAll() {
        List<Role> roles = roleService.getAll();
        verify(roleDao).list();
        assertTrue(roles.size() > 1);
    }
}