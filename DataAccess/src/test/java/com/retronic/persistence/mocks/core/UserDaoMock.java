package com.retronic.persistence.mocks.core;

import com.retronic.persistence.daos.core.IUserDao;
import com.retronic.persistence.daos.core.impl.UserDao;
import com.retronic.persistence.entities.core.Country;
import com.retronic.persistence.entities.core.Role;
import com.retronic.persistence.entities.core.User;
import com.retronic.persistence.utils.MockUtils;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class UserDaoMock {

    public static Map<Integer, Serializable> users = new HashMap<Integer, Serializable>();

    static {
        createValues();
    }

    public static IUserDao getMock() {
        IUserDao dao = mock(UserDao.class);

        when(dao.read(anyInt())).thenAnswer(MockUtils.buildAnswerGet(1, users));
        when(dao.list()).thenAnswer(MockUtils.buildAnswerGetAll(users));

        return dao;
    }

    private static void createValues() {
        users.put(1, createUser(1, "test_user_sys", "test_user_sys", (Role) RoleDaoMock.roles.get(1), (Country) CountryDaoMock.countries.get(1)));
        users.put(2, createUser(2, "test_user_admin", "test_user_admin", (Role) RoleDaoMock.roles.get(2), (Country) CountryDaoMock.countries.get(1)));
        users.put(3, createUser(3, "test_user_advanced", "test_user_advanced", (Role) RoleDaoMock.roles.get(3), (Country) CountryDaoMock.countries.get(1)));
        users.put(4, createUser(4, "test_user_base", "test_user_base", (Role) RoleDaoMock.roles.get(4), (Country) CountryDaoMock.countries.get(2)));
    }

    private static User createUser(Integer id, String login, String password, Role role, Country country) {
        User user = new User();
        user.setId(id);
        user.setLogin(login);
        user.setPassword(password);
        user.setRole(role);
        user.setCountry(country);
        return user;
    }
}