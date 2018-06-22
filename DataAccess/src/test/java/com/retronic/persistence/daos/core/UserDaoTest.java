package com.retronic.persistence.daos.core;

import com.retronic.persistence.entities.core.User;
import com.retronic.persistence.mocks.core.UserDaoMock;
import com.retronic.persistence.utils.PasswordEncoder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:spring.xml"})
@Transactional(transactionManager = "transactionManager")
@Rollback
public class UserDaoTest extends AbstractTransactionalJUnit4SpringContextTests {

    @Autowired
    private IUserDao userDao;

    @Test
    public void testList() throws Exception {
        List<User> users = userDao.list();
        assertNotNull(users);
        assertTrue(!users.isEmpty());
    }

    @Test
    public void testCRUD() throws Exception {
        Integer id = userDao.create(this.createUser());

        User getUser = userDao.read(id);
        assertEquals(id, getUser.getId());

        getUser.setFirstname("Hans");
        userDao.update(getUser);

        User getUpdateUser = userDao.read(id);
        assertEquals("Hans", getUpdateUser.getFirstname());

        userDao.delete(getUpdateUser);
        User getDeleteUser = userDao.read(id);
        assertNull(getDeleteUser);
    }

    private User createUser() {
        User user = (User) UserDaoMock.users.get(1);
        user.setPassword(PasswordEncoder.encodePassword(user.getPassword(), user.getLogin()));
        user.setLoginTrials(0);
        user.setCreated(Calendar.getInstance());
        return user;
    }
}
