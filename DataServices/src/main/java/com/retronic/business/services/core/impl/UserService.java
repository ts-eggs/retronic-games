package com.retronic.business.services.core.impl;

import com.retronic.business.services.core.IUserService;
import com.retronic.persistence.daos.core.IUserDao;
import com.retronic.persistence.entities.core.User;
import com.retronic.persistence.utils.PasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;

@Service("userService")
public class UserService extends GenericService<User, Integer> implements IUserService {

    @Autowired
    private IUserDao userDao;

    @Override
    public void init() {
        this.genericDao = userDao;
    }

    @Override
    public Integer create(User user) {
        boolean userExists = this.getByLogin(user.getLogin()) != null;

        if (!userExists) {
            user.setPassword(PasswordEncoder.encodePassword(user.getPassword(), user.getLogin()));
            user.setLoginTrials(0);
            user.setCreated(Calendar.getInstance());
            return super.create(user);
        }

        return null;
    }

    public User getByLogin(String login) {
        return userDao.readByLogin(login);
    }

    @Override
    public void setTrialCounts(User user, boolean reset) {
        if (reset) {
            user.resetTrialCount();
        } else {
            user.pushTrialCount();
        }

        this.update(user);
    }

    @Override
    public void changePassword(String password, User user) {
        userDao.changePassword(password, user);
    }
}
