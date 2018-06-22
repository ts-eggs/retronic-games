package com.retronic.remoting.integration.services.core;

import com.retronic.business.mocks.core.RoleServiceMock;
import com.retronic.business.services.core.IGenericService;
import com.retronic.persistence.entities.core.Role;
import com.retronic.remoting.converter.IDtoConverter;
import com.retronic.remoting.converter.core.RoleDtoConverter;
import com.retronic.remoting.dtos.core.RoleDto;
import com.retronic.remoting.services.IGenericRemote;
import com.retronic.remoting.services.core.RoleRemote;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.util.ReflectionTestUtils;

import javax.ws.rs.core.Response;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.verify;

public class RoleRemoteTest {

    private IGenericService<Role, Integer> roleService;
    private IGenericRemote<RoleDto, Integer> roleRemote;

    @Before
    public void setUp() {
        roleRemote = new RoleRemote();
        roleService = RoleServiceMock.getMock();
        IDtoConverter<RoleDto, Role> roleDtoConverter = new RoleDtoConverter();

        ReflectionTestUtils.setField(roleRemote, "roleService", roleService);
        ReflectionTestUtils.setField(roleRemote, "roleDtoConverter", roleDtoConverter);
    }

    @Test
    public void testGet() {
        Response response = roleRemote.get(1);
        verify(roleService).get(1);
        assertTrue(response.getStatus() == Response.Status.OK.getStatusCode());
        RoleDto roleDto = (RoleDto) response.getEntity();
        assertNotNull(roleDto);
        assertTrue(roleDto.getId().equals(1));
    }

    @Test
    @SuppressWarnings("unchecked")
    public void testGetAll() {
        Response response = roleRemote.getAll();
        verify(roleService).getAll();
        assertTrue(response.getStatus() == Response.Status.OK.getStatusCode());
        List<RoleDto> roleDtos = (List<RoleDto>) response.getEntity();
        assertNotNull(roleDtos);
        assertTrue(roleDtos.size() > 1);
    }
}
