package com.retronic.remoting.integration.services.hero;

import com.retronic.business.mocks.hero.SkillServiceMock;
import com.retronic.business.services.core.IGenericService;
import com.retronic.persistence.entities.hero.Skill;
import com.retronic.remoting.converter.IDtoConverter;
import com.retronic.remoting.converter.hero.SkillDtoConverter;
import com.retronic.remoting.dtos.hero.SkillDto;
import com.retronic.remoting.services.IGenericRemote;
import com.retronic.remoting.services.hero.SkillRemote;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.util.ReflectionTestUtils;

import javax.ws.rs.core.Response;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.verify;

public class SkillRemoteTest {

    private IGenericService<Skill, Integer> skillService;
    private IGenericRemote<SkillDto, Integer> skillRemote;

    @Before
    public void setUp() {
        skillRemote = new SkillRemote();
        skillService = SkillServiceMock.getMock();
        IDtoConverter<SkillDto, Skill> skillDtoConverter = new SkillDtoConverter();

        ReflectionTestUtils.setField(skillRemote, "skillService", skillService);
        ReflectionTestUtils.setField(skillRemote, "skillDtoConverter", skillDtoConverter);
    }

    @Test
    public void testGet() {
        Response response = skillRemote.get(1);
        verify(skillService).get(1);
        assertTrue(response.getStatus() == Response.Status.OK.getStatusCode());
        SkillDto dto = (SkillDto) response.getEntity();
        assertNotNull(dto);
        assertTrue(dto.getId().equals(1));
    }

    @Test
    @SuppressWarnings("unchecked")
    public void testGetAll() {
        Response response = skillRemote.getAll();
        verify(skillService).getAll();
        assertTrue(response.getStatus() == Response.Status.OK.getStatusCode());
        List<SkillDto> dtos = (List<SkillDto>) response.getEntity();
        assertNotNull(dtos);
        assertTrue(dtos.size() > 0);
    }
}
