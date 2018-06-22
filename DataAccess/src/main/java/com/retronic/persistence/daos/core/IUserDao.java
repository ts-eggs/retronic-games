package com.retronic.persistence.daos.core;

import com.retronic.persistence.entities.core.User;

public interface IUserDao extends IGenericDao<User, Integer> {

    User readByLogin(String login);

    void changePassword(final String password, final User user);
}
