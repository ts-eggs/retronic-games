package com.retronic.remoting.integration.services.hero;

import com.retronic.business.mocks.hero.ClassServiceMock;
import com.retronic.business.services.core.IGenericService;
import com.retronic.persistence.entities.hero.Class;
import com.retronic.persistence.entities.hero.Skill;
import com.retronic.remoting.converter.IDtoConverter;
import com.retronic.remoting.converter.hero.ClassDtoConverter;
import com.retronic.remoting.converter.hero.SkillDtoConverter;
import com.retronic.remoting.dtos.hero.ClassDto;
import com.retronic.remoting.dtos.hero.SkillDto;
import com.retronic.remoting.services.IGenericRemote;
import com.retronic.remoting.services.hero.ClassRemote;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.util.ReflectionTestUtils;

import javax.ws.rs.core.Response;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.verify;

public class ClassRemoteTest {

    private IGenericService<Class, Integer> classService;
    private IGenericRemote<ClassDto, Integer> classRemote;

    @Before
    public void setUp() {
        classRemote = new ClassRemote();
        classService = ClassServiceMock.getMock();
        IDtoConverter<ClassDto, Class> classDtoConverter = new ClassDtoConverter();
        IDtoConverter<SkillDto, Skill> skillDtoConverter = new SkillDtoConverter();

        ReflectionTestUtils.setField(classRemote, "classService", classService);
        ReflectionTestUtils.setField(classRemote, "classDtoConverter", classDtoConverter);
        ReflectionTestUtils.setField(classDtoConverter, "skillDtoConverter", skillDtoConverter);
    }

    @Test
    public void testGet() {
        Response response = classRemote.get(1);
        verify(classService).get(1);
        assertTrue(response.getStatus() == Response.Status.OK.getStatusCode());
        ClassDto dto = (ClassDto) response.getEntity();
        assertNotNull(dto);
        assertTrue(dto.getId().equals(1));
    }

    @Test
    @SuppressWarnings("unchecked")
    public void testGetAll() {
        Response response = classRemote.getAll();
        verify(classService).getAll();
        assertTrue(response.getStatus() == Response.Status.OK.getStatusCode());
        List<ClassDto> dtos = (List<ClassDto>) response.getEntity();
        assertNotNull(dtos);
        assertTrue(dtos.size() > 0);
    }
}
