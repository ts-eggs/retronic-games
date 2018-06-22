package com.retronic.remoting.system.services.hero;

import com.retronic.remoting.dtos.hero.CharacterDto;
import com.retronic.remoting.dtos.hero.ClassDto;
import com.retronic.remoting.dtos.hero.RaceDto;
import com.retronic.remoting.services.IGenericRemote;
import com.retronic.remoting.utils.SecurityTestUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.ws.rs.core.Response;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:spring-rest.xml"})
public class CharacterRemoteTest {

    @Autowired
    private IGenericRemote<CharacterDto, Integer> characterRemote;

    @Autowired
    private IGenericRemote<RaceDto, Integer> raceRemote;

    @Autowired
    private IGenericRemote<ClassDto, Integer> classRemote;

    @Autowired
    private SecurityTestUtils securityTestUtils;

    @Test
    @SuppressWarnings("unchecked")
    public void testCrud() throws Exception {
        securityTestUtils.setUpAuthentication(1);
        CharacterDto characterDto = createCharacter();
        Response response = characterRemote.create(characterDto);
        assertEquals(201, response.getStatus());
        Integer id = (Integer) response.getEntity();
        assertNotNull(id);
        assertTrue(id > 0);

        response = characterRemote.get(id);
        assertEquals(200, response.getStatus());
        CharacterDto getDto = (CharacterDto) response.getEntity();
        assertEquals(id, getDto.getId());

        String editedName = "edited_char_name";
        getDto.setName(editedName);
        response = characterRemote.update(getDto.getId(), getDto);
        assertEquals(200, response.getStatus());

        response = characterRemote.get(id);
        assertEquals(200, response.getStatus());
        CharacterDto updatedDto = (CharacterDto) response.getEntity();
        assertEquals(editedName, updatedDto.getName());

        response = characterRemote.delete(getDto.getId());
        assertEquals(204, response.getStatus());
    }

    private CharacterDto createCharacter() {
        CharacterDto dto = new CharacterDto();
        dto.setName("system-test_char");
        dto.setLevel(1);
        dto.setStrength(2);
        dto.setVitality(3);
        dto.setDexterity(1);
        Response response = raceRemote.get(1);
        dto.setRaceDto((RaceDto) response.getEntity());
        response = classRemote.get(1);
        dto.setClassDto((ClassDto) response.getEntity());
        return dto;
    }
}
