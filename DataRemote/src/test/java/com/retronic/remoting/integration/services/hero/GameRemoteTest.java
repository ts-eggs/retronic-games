package com.retronic.remoting.integration.services.hero;

import com.retronic.business.mocks.hero.GameServiceMock;
import com.retronic.business.services.core.IGenericService;
import com.retronic.persistence.entities.core.Country;
import com.retronic.persistence.entities.core.Role;
import com.retronic.persistence.entities.core.User;
import com.retronic.persistence.entities.hero.*;
import com.retronic.persistence.entities.hero.Character;
import com.retronic.persistence.entities.hero.Class;
import com.retronic.remoting.converter.IDtoConverter;
import com.retronic.remoting.converter.core.CountryDtoConverter;
import com.retronic.remoting.converter.core.RoleDtoConverter;
import com.retronic.remoting.converter.core.UserDtoConverter;
import com.retronic.remoting.converter.hero.*;
import com.retronic.remoting.dtos.core.CountryDto;
import com.retronic.remoting.dtos.core.RoleDto;
import com.retronic.remoting.dtos.core.UserDto;
import com.retronic.remoting.dtos.hero.*;
import com.retronic.remoting.services.IGenericRemote;
import com.retronic.remoting.services.hero.GameRemote;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.util.ReflectionTestUtils;

import javax.ws.rs.core.Response;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.verify;

public class GameRemoteTest {

    private IGenericService<Game, Integer> gameService;
    private IGenericRemote<GameDto, Integer> gameRemote;

    @Before
    public void setUp() {
        gameRemote = new GameRemote();
        gameService = GameServiceMock.getMock();
        IDtoConverter<GameDto, Game> gameDtoConverter = new GameDtoConverter();
        IDtoConverter<CharacterDto, Character> characterDtoConverter = new CharacterDtoConverter();
        IDtoConverter<UserDto, User> userDtoConverter = new UserDtoConverter();
        IDtoConverter<RoleDto, Role> roleDtoConverter = new RoleDtoConverter();
        IDtoConverter<CountryDto, Country> countryDtoConverter = new CountryDtoConverter();
        IDtoConverter<RaceDto, Race> raceDtoConverter = new RaceDtoConverter();
        IDtoConverter<ClassDto, Class> classDtoConverter = new ClassDtoConverter();
        IDtoConverter<ArmorDto, Armor> armorDtoConverter = new ArmorDtoConverter();
        IDtoConverter<WeaponDto, Weapon> weaponDtoConverter = new WeaponDtoConverter();
        IDtoConverter<ItemDto, Item> itemDtoConverter = new ItemDtoConverter();
        IDtoConverter<SkillDto, Skill> skillDtoConverter = new SkillDtoConverter();

        ReflectionTestUtils.setField(gameRemote, "gameService", gameService);
        ReflectionTestUtils.setField(gameRemote, "gameDtoConverter", gameDtoConverter);

        ReflectionTestUtils.setField(userDtoConverter, "roleDtoConverter", roleDtoConverter);
        ReflectionTestUtils.setField(userDtoConverter, "countryDtoConverter", countryDtoConverter);

        ReflectionTestUtils.setField(raceDtoConverter, "skillDtoConverter", skillDtoConverter);
        ReflectionTestUtils.setField(classDtoConverter, "skillDtoConverter", skillDtoConverter);
        ReflectionTestUtils.setField(armorDtoConverter, "skillDtoConverter", skillDtoConverter);
        ReflectionTestUtils.setField(weaponDtoConverter, "skillDtoConverter", skillDtoConverter);
        ReflectionTestUtils.setField(itemDtoConverter, "skillDtoConverter", skillDtoConverter);

        ReflectionTestUtils.setField(characterDtoConverter, "userDtoConverter", userDtoConverter);
        ReflectionTestUtils.setField(characterDtoConverter, "raceDtoConverter", raceDtoConverter);
        ReflectionTestUtils.setField(characterDtoConverter, "classDtoConverter", classDtoConverter);
        ReflectionTestUtils.setField(characterDtoConverter, "armorDtoConverter", armorDtoConverter);
        ReflectionTestUtils.setField(characterDtoConverter, "weaponDtoConverter", weaponDtoConverter);
        ReflectionTestUtils.setField(characterDtoConverter, "itemDtoConverter", itemDtoConverter);

        ReflectionTestUtils.setField(gameDtoConverter, "userDtoConverter", userDtoConverter);
        ReflectionTestUtils.setField(gameDtoConverter, "characterDtoConverter", characterDtoConverter);
    }

    @Test
    public void testGet() {
        Response response = gameRemote.get(1);
        verify(gameService).get(1);
        assertTrue(response.getStatus() == Response.Status.OK.getStatusCode());
        GameDto dto = (GameDto) response.getEntity();
        assertNotNull(dto);
        assertTrue(dto.getId().equals(1));
    }

    @Test
    @SuppressWarnings("unchecked")
    public void testGetAll() {
        Response response = gameRemote.getAll();
        verify(gameService).getAll();
        assertTrue(response.getStatus() == Response.Status.OK.getStatusCode());
        List<GameDto> dtos = (List<GameDto>) response.getEntity();
        assertNotNull(dtos);
        assertTrue(dtos.size() > 0);
    }
}
