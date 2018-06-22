package com.retronic.persistence.daos.core.impl;

import com.retronic.persistence.daos.core.IUserDao;
import com.retronic.persistence.entities.core.User;
import com.retronic.persistence.utils.PasswordEncoder;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

@Repository("userDao")
public class UserDao extends GenericDao<User, Integer> implements IUserDao {

    @Override
    public void init() {
        this.entityClassType = User.class;
    }

    public User readByLogin(String login) {
        return this.findByCriterion(Restrictions.eq("login", login));
    }

    public void changePassword(final String password, final User user) {
        user.setPassword(PasswordEncoder.encodePassword(password, user.getLogin()));
        this.merge(user);
    }
}
