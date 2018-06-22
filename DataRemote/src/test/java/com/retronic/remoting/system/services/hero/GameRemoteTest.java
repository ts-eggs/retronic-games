package com.retronic.remoting.system.services.hero;

import com.retronic.persistence.enums.hero.Difficult;
import com.retronic.remoting.dtos.hero.CharacterDto;
import com.retronic.remoting.dtos.hero.GameDto;
import com.retronic.remoting.services.IGenericRemote;
import com.retronic.remoting.utils.SecurityTestUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.ws.rs.core.Response;
import java.util.Collections;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:spring-rest.xml"})
public class GameRemoteTest {

    @Autowired
    private IGenericRemote<GameDto, Integer> gameRemote;

    @Autowired
    private IGenericRemote<CharacterDto, Integer> characterRemote;

    @Autowired
    private SecurityTestUtils securityTestUtils;

    @Test
    @SuppressWarnings("unchecked")
    public void testCrud() throws Exception {
        securityTestUtils.setUpAuthentication(1);
        GameDto gameDto = createGame();
        Response response = gameRemote.create(gameDto);
        assertEquals(201, response.getStatus());
        Integer id = (Integer) response.getEntity();
        assertNotNull(id);
        assertTrue(id > 0);

        response = gameRemote.get(id);
        assertEquals(200, response.getStatus());
        GameDto getDto = (GameDto) response.getEntity();
        assertEquals(id, getDto.getId());
        assertNotNull(getDto.getCharacterDtos());
        assertTrue(!getDto.getCharacterDtos().isEmpty());

        String editedName = "edited_game_name";
        getDto.setName(editedName);
        response = gameRemote.update(getDto.getId(), getDto);
        assertEquals(200, response.getStatus());

        response = gameRemote.get(id);
        assertEquals(200, response.getStatus());
        GameDto updatedDto = (GameDto) response.getEntity();
        assertEquals(editedName, updatedDto.getName());

        response = gameRemote.delete(getDto.getId());
        assertEquals(204, response.getStatus());
    }

    private GameDto createGame() {
        GameDto dto = new GameDto();
        dto.setName("system-test_game");
        dto.setDifficult(Difficult.EASY.getId());

        Response response = characterRemote.get(1);
        dto.setCharacterDtos(Collections.singletonList((CharacterDto) response.getEntity()));
        return dto;
    }
}
