package com.retronic.business.services.hero;

import com.retronic.business.services.IGenericService;
import com.retronic.business.services.hero.impl.SkillService;
import com.retronic.persistence.daos.core.IGenericDao;
import com.retronic.persistence.entities.hero.Skill;
import com.retronic.persistence.mocks.hero.SkillDaoMock;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.List;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.verify;

public class SkillServiceTest {

    private IGenericService<Skill, Integer> skillService;
    private IGenericDao<Skill, Integer> skillDao;

    @Before
    public void setUp() {
        skillService = new SkillService();
        skillDao = SkillDaoMock.getMock();
        ReflectionTestUtils.setField(skillService, "genericDao", skillDao);
    }

    @Test
    public void testGet() {
        Skill skill = skillService.get(1);
        verify(skillDao).read(1);
        assertTrue(skill.getId().equals(1));
    }

    @Test
    public void testGetAll() {
        List<Skill> skills = skillService.getAll();
        verify(skillDao).list();
        assertTrue(skills.size() > 1);
    }
}