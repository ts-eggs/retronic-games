package com.retronic.remoting.integration.services.hero;

import com.retronic.business.mocks.hero.CharacterServiceMock;
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
import com.retronic.remoting.services.hero.CharacterRemote;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.util.ReflectionTestUtils;

import javax.ws.rs.core.Response;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.verify;

public class CharacterRemoteTest {

    private IGenericService<Character, Integer> characterService;
    private IGenericRemote<CharacterDto, Integer> characterRemote;

    @Before
    public void setUp() {
        characterRemote = new CharacterRemote();
        characterService = CharacterServiceMock.getMock();
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

        ReflectionTestUtils.setField(characterRemote, "characterService", characterService);
        ReflectionTestUtils.setField(characterRemote, "characterDtoConverter", characterDtoConverter);

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
    }

    @Test
    public void testGet() {
        Response response = characterRemote.get(1);
        verify(characterService).get(1);
        assertTrue(response.getStatus() == Response.Status.OK.getStatusCode());
        CharacterDto dto = (CharacterDto) response.getEntity();
        assertNotNull(dto);
        assertTrue(dto.getId().equals(1));
    }

    @Test
    @SuppressWarnings("unchecked")
    public void testGetAll() {
        Response response = characterRemote.getAll();
        verify(characterService).getAll();
        assertTrue(response.getStatus() == Response.Status.OK.getStatusCode());
        List<CharacterDto> dtos = (List<CharacterDto>) response.getEntity();
        assertNotNull(dtos);
        assertTrue(dtos.size() > 0);
    }
}
