package com.retronic.business.services.core;

import com.retronic.business.services.core.impl.UserService;
import com.retronic.persistence.daos.core.IUserDao;
import com.retronic.persistence.entities.core.User;
import com.retronic.persistence.mocks.core.UserDaoMock;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.List;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.verify;

public class UserServiceTest {

    private IUserService userService;
    private IUserDao userDao;

    @Before
    public void setUp() {
        userService = new UserService();
        userDao = UserDaoMock.getMock();
        ReflectionTestUtils.setField(userService, "genericDao", userDao);
    }

    @Test
    public void testGet() {
        User user = userService.get(1);
        verify(userDao).read(1);
        assertTrue(user.getId().equals(1));
    }

    @Test
    public void testGetAll() {
        List<User> users = userService.getAll();
        verify(userDao).list();
        assertTrue(users.size() > 1);
    }
}