package com.retronic.remoting.integration.services.hero;

import com.retronic.business.mocks.hero.RaceServiceMock;
import com.retronic.business.services.core.IGenericService;
import com.retronic.persistence.entities.hero.Race;
import com.retronic.persistence.entities.hero.Skill;
import com.retronic.remoting.converter.IDtoConverter;
import com.retronic.remoting.converter.hero.RaceDtoConverter;
import com.retronic.remoting.converter.hero.SkillDtoConverter;
import com.retronic.remoting.dtos.hero.RaceDto;
import com.retronic.remoting.dtos.hero.SkillDto;
import com.retronic.remoting.services.IGenericRemote;
import com.retronic.remoting.services.hero.RaceRemote;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.util.ReflectionTestUtils;

import javax.ws.rs.core.Response;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.verify;

public class RaceRemoteTest {

    private IGenericService<Race, Integer> raceService;
    private IGenericRemote<RaceDto, Integer> raceRemote;

    @Before
    public void setUp() {
        raceRemote = new RaceRemote();
        raceService = RaceServiceMock.getMock();
        IDtoConverter<RaceDto, Race> raceDtoConverter = new RaceDtoConverter();
        IDtoConverter<SkillDto, Skill> skillDtoConverter = new SkillDtoConverter();

        ReflectionTestUtils.setField(raceRemote, "raceService", raceService);
        ReflectionTestUtils.setField(raceRemote, "raceDtoConverter", raceDtoConverter);
        ReflectionTestUtils.setField(raceDtoConverter, "skillDtoConverter", skillDtoConverter);
    }

    @Test
    public void testGet() {
        Response response = raceRemote.get(1);
        verify(raceService).get(1);
        assertTrue(response.getStatus() == Response.Status.OK.getStatusCode());
        RaceDto dto = (RaceDto) response.getEntity();
        assertNotNull(dto);
        assertTrue(dto.getId().equals(1));
    }

    @Test
    @SuppressWarnings("unchecked")
    public void testGetAll() {
        Response response = raceRemote.getAll();
        verify(raceService).getAll();
        assertTrue(response.getStatus() == Response.Status.OK.getStatusCode());
        List<RaceDto> dtos = (List<RaceDto>) response.getEntity();
        assertNotNull(dtos);
        assertTrue(dtos.size() > 0);
    }
}
