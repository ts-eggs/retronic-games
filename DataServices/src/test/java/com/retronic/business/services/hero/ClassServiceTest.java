package com.retronic.business.services.hero;

import com.retronic.business.services.IGenericService;
import com.retronic.business.services.hero.impl.ClassService;
import com.retronic.persistence.daos.core.IGenericDao;
import com.retronic.persistence.entities.hero.Class;
import com.retronic.persistence.mocks.hero.ClassDaoMock;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.List;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.verify;

public class ClassServiceTest {

    private IGenericService<Class, Integer> classService;
    private IGenericDao<Class, Integer> classDao;

    @Before
    public void setUp() {
        classService = new ClassService();
        classDao = ClassDaoMock.getMock();
        ReflectionTestUtils.setField(classService, "genericDao", classDao);
    }

    @Test
    public void testGet() {
        Class heroClass = classService.get(1);
        verify(classDao).read(1);
        assertTrue(heroClass.getId().equals(1));
    }

    @Test
    public void testGetAll() {
        List<Class> classes = classService.getAll();
        verify(classDao).list();
        assertTrue(classes.size() > 0);
    }
}