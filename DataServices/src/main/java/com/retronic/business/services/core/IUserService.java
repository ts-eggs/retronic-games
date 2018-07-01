package com.retronic.business.services.core;

import com.retronic.business.services.IGenericService;
import com.retronic.persistence.entities.core.User;

public interface IUserService extends IGenericService<User, Integer> {

    User getByLogin(String login);

    void setTrialCounts(User user, boolean reset);

    void changePassword(final String password, final User user);
}
