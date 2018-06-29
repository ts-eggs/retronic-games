package com.retronic.remoting.integration.services.hero;

import com.retronic.business.mocks.hero.WeaponServiceMock;
import com.retronic.business.services.IGenericService;
import com.retronic.persistence.entities.hero.Skill;
import com.retronic.persistence.entities.hero.Weapon;
import com.retronic.remoting.converter.IDtoConverter;
import com.retronic.remoting.converter.hero.SkillDtoConverter;
import com.retronic.remoting.converter.hero.WeaponDtoConverter;
import com.retronic.remoting.dtos.hero.SkillDto;
import com.retronic.remoting.dtos.hero.WeaponDto;
import com.retronic.remoting.services.IGenericRemote;
import com.retronic.remoting.services.hero.impl.WeaponRemote;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.util.ReflectionTestUtils;

import javax.ws.rs.core.Response;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.verify;

public class WeaponRemoteTest {

    private IGenericService<Weapon, Integer> weaponService;
    private IGenericRemote<WeaponDto, Integer> weaponRemote;

    @Before
    public void setUp() {
        weaponRemote = new WeaponRemote();
        weaponService = WeaponServiceMock.getMock();
        IDtoConverter<WeaponDto, Weapon> weaponDtoConverter = new WeaponDtoConverter();
        IDtoConverter<SkillDto, Skill> skillDtoConverter = new SkillDtoConverter();

        ReflectionTestUtils.setField(weaponRemote, "weaponService", weaponService);
        ReflectionTestUtils.setField(weaponRemote, "weaponDtoConverter", weaponDtoConverter);
        ReflectionTestUtils.setField(weaponDtoConverter, "skillDtoConverter", skillDtoConverter);
    }

    @Test
    public void testGet() {
        Response response = weaponRemote.get(1);
        verify(weaponService).get(1);
        assertTrue(response.getStatus() == Response.Status.OK.getStatusCode());
        WeaponDto dto = (WeaponDto) response.getEntity();
        assertNotNull(dto);
        assertTrue(dto.getId().equals(1));
    }

    @Test
    @SuppressWarnings("unchecked")
    public void testGetAll() {
        Response response = weaponRemote.getAll();
        verify(weaponService).getAll();
        assertTrue(response.getStatus() == Response.Status.OK.getStatusCode());
        List<WeaponDto> dtos = (List<WeaponDto>) response.getEntity();
        assertNotNull(dtos);
        assertTrue(dtos.size() > 1);
    }
}
