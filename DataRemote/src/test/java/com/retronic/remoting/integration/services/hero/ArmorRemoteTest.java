package com.retronic.remoting.integration.services.hero;

import com.retronic.business.mocks.hero.ArmorServiceMock;
import com.retronic.business.services.core.IGenericService;
import com.retronic.persistence.entities.hero.Armor;
import com.retronic.persistence.entities.hero.Skill;
import com.retronic.remoting.converter.IDtoConverter;
import com.retronic.remoting.converter.hero.ArmorDtoConverter;
import com.retronic.remoting.converter.hero.SkillDtoConverter;
import com.retronic.remoting.dtos.hero.ArmorDto;
import com.retronic.remoting.dtos.hero.SkillDto;
import com.retronic.remoting.services.IGenericRemote;
import com.retronic.remoting.services.hero.ArmorRemote;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.util.ReflectionTestUtils;

import javax.ws.rs.core.Response;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.verify;

public class ArmorRemoteTest {

    private IGenericService<Armor, Integer> armorService;
    private IGenericRemote<ArmorDto, Integer> armorRemote;

    @Before
    public void setUp() {
        armorRemote = new ArmorRemote();
        armorService = ArmorServiceMock.getMock();
        IDtoConverter<ArmorDto, Armor> armorDtoConverter = new ArmorDtoConverter();
        IDtoConverter<SkillDto, Skill> skillDtoConverter = new SkillDtoConverter();

        ReflectionTestUtils.setField(armorRemote, "armorService", armorService);
        ReflectionTestUtils.setField(armorRemote, "armorDtoConverter", armorDtoConverter);
        ReflectionTestUtils.setField(armorDtoConverter, "skillDtoConverter", skillDtoConverter);
    }

    @Test
    public void testGet() {
        Response response = armorRemote.get(1);
        verify(armorService).get(1);
        assertTrue(response.getStatus() == Response.Status.OK.getStatusCode());
        ArmorDto dto = (ArmorDto) response.getEntity();
        assertNotNull(dto);
        assertTrue(dto.getId().equals(1));
    }

    @Test
    @SuppressWarnings("unchecked")
    public void testGetAll() {
        Response response = armorRemote.getAll();
        verify(armorService).getAll();
        assertTrue(response.getStatus() == Response.Status.OK.getStatusCode());
        List<ArmorDto> dtos = (List<ArmorDto>) response.getEntity();
        assertNotNull(dtos);
        assertTrue(dtos.size() > 1);
    }
}
