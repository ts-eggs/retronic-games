package com.retronic.business.mocks.hero;

import com.retronic.business.services.core.IGenericService;
import com.retronic.business.services.hero.impl.SkillService;
import com.retronic.persistence.entities.hero.Skill;
import com.retronic.persistence.mocks.hero.SkillDaoMock;
import com.retronic.persistence.utils.MockUtils;

import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class SkillServiceMock {

    public static IGenericService<Skill, Integer> getMock() {
        IGenericService<Skill, Integer> service = mock(SkillService.class);

        when(service.get(anyInt())).thenAnswer(MockUtils.buildAnswerGet(1, SkillDaoMock.skills));
        when(service.getAll()).thenAnswer(MockUtils.buildAnswerGetAll(SkillDaoMock.skills));

        return service;
    }
}