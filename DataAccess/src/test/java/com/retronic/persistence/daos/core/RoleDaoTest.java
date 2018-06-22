package com.retronic.persistence.daos.core;

import com.retronic.persistence.entities.core.Role;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:spring.xml"})
@Transactional(transactionManager = "transactionManager")
@Rollback
public class RoleDaoTest extends AbstractTransactionalJUnit4SpringContextTests {

    @Autowired
    private IGenericDao<Role, Integer> roleDao;

    @Test
    public void testRead() throws Exception {
        Role role = roleDao.read(1);
        assertNotNull(role);
    }

    @Test
    public void testReadAll() throws Exception {
        List<Role> roles = roleDao.list();
        assertNotNull(roles);
        assertTrue(!roles.isEmpty());
    }
}
