package com.retronic.business.services.core;

import com.retronic.persistence.entities.core.User;

public interface IUserService extends IGenericService<User, Integer> {

    User getByLogin(String login);

    void changePassword(final String password, final User user);
}
