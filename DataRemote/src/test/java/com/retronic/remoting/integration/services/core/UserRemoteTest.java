package com.retronic.remoting.integration.services.core;

import com.retronic.business.mocks.core.UserServiceMock;
import com.retronic.business.services.core.IUserService;
import com.retronic.persistence.entities.core.Country;
import com.retronic.persistence.entities.core.Role;
import com.retronic.persistence.entities.core.User;
import com.retronic.remoting.converter.IDtoConverter;
import com.retronic.remoting.converter.core.CountryDtoConverter;
import com.retronic.remoting.converter.core.RoleDtoConverter;
import com.retronic.remoting.converter.core.UserDtoConverter;
import com.retronic.remoting.dtos.core.CountryDto;
import com.retronic.remoting.dtos.core.RoleDto;
import com.retronic.remoting.dtos.core.UserDto;
import com.retronic.remoting.services.core.IUserRemote;
import com.retronic.remoting.services.core.impl.UserRemote;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.util.ReflectionTestUtils;

import javax.ws.rs.core.Response;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.verify;

public class UserRemoteTest {

    private IUserService userService;
    private IUserRemote userRemote;

    @Before
    public void setUp() {
        userRemote = new UserRemote();
        userService = UserServiceMock.getMock();
        IDtoConverter<UserDto, User> userDtoConverter = new UserDtoConverter();
        IDtoConverter<RoleDto, Role> roleDtoConverter = new RoleDtoConverter();
        IDtoConverter<CountryDto, Country> countryDtoConverter = new CountryDtoConverter();

        ReflectionTestUtils.setField(userRemote, "userService", userService);
        ReflectionTestUtils.setField(userRemote, "userDtoConverter", userDtoConverter);
        ReflectionTestUtils.setField(userDtoConverter, "roleDtoConverter", roleDtoConverter);
        ReflectionTestUtils.setField(userDtoConverter, "countryDtoConverter", countryDtoConverter);
    }

    @Test
    public void testGet() {
        Response response = userRemote.get(1);
        verify(userService).get(1);
        assertTrue(response.getStatus() == Response.Status.OK.getStatusCode());
        UserDto dto = (UserDto) response.getEntity();
        assertNotNull(dto);
        assertTrue(dto.getId().equals(1));
    }

    @Test
    @SuppressWarnings("unchecked")
    public void testGetAll() {
        Response response = userRemote.getAll();
        verify(userService).getAll();
        assertTrue(response.getStatus() == Response.Status.OK.getStatusCode());
        List<UserDto> dtos = (List<UserDto>) response.getEntity();
        assertNotNull(dtos);
        assertTrue(dtos.size() > 1);
    }
}
