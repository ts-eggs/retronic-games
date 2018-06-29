package com.retronic.remoting.system.services.core;

import com.retronic.remoting.dtos.core.UserDto;
import com.retronic.remoting.dtos.hero.GameDto;
import com.retronic.remoting.services.core.IUserRemote;
import com.retronic.remoting.utils.SecurityTestUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.ws.rs.core.Response;
import java.util.List;

import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:spring-rest.xml"})
public class UserRemoteTest {

    @Autowired
    private IUserRemote userRemote;

    @Autowired
    private SecurityTestUtils securityTestUtils;

    @Test
    @SuppressWarnings("unchecked")
    public void testGetUser() throws Exception {
        securityTestUtils.setUpAuthentication(1);
        Response response = userRemote.get(1);
        UserDto dto = (UserDto) response.getEntity();
        assertTrue(dto.getId().equals(1));
    }

    @Test
    @SuppressWarnings("unchecked")
    public void testGetUsers() throws Exception {
        securityTestUtils.setUpAuthentication(1);
        Response response = userRemote.getAll();
        List<UserDto> dtos = (List<UserDto>) response.getEntity();
        assertTrue(dtos.size() > 0);
    }

    @Test
    @SuppressWarnings("unchecked")
    public void testGetGames() throws Exception {
        securityTestUtils.setUpAuthentication(3);
        Response response = userRemote.getGames(3);
        List<GameDto> dtos = (List<GameDto>) response.getEntity();
        assertTrue(dtos.size() > 0);
    }
}
