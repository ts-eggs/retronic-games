package com.retronic.remoting.integration.services.hero;

import com.retronic.business.mocks.hero.ItemServiceMock;
import com.retronic.business.services.core.IGenericService;
import com.retronic.persistence.entities.hero.Item;
import com.retronic.persistence.entities.hero.Skill;
import com.retronic.remoting.converter.IDtoConverter;
import com.retronic.remoting.converter.hero.ItemDtoConverter;
import com.retronic.remoting.converter.hero.SkillDtoConverter;
import com.retronic.remoting.dtos.hero.ItemDto;
import com.retronic.remoting.dtos.hero.SkillDto;
import com.retronic.remoting.services.IGenericRemote;
import com.retronic.remoting.services.hero.ItemRemote;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.util.ReflectionTestUtils;

import javax.ws.rs.core.Response;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.verify;

public class ItemRemoteTest {

    private IGenericService<Item, Integer> itemService;
    private IGenericRemote<ItemDto, Integer> itemRemote;

    @Before
    public void setUp() {
        itemRemote = new ItemRemote();
        itemService = ItemServiceMock.getMock();
        IDtoConverter<ItemDto, Item> itemDtoConverter = new ItemDtoConverter();
        IDtoConverter<SkillDto, Skill> skillDtoConverter = new SkillDtoConverter();

        ReflectionTestUtils.setField(itemRemote, "itemService", itemService);
        ReflectionTestUtils.setField(itemRemote, "itemDtoConverter", itemDtoConverter);
        ReflectionTestUtils.setField(itemDtoConverter, "skillDtoConverter", skillDtoConverter);
    }

    @Test
    public void testGet() {
        Response response = itemRemote.get(1);
        verify(itemService).get(1);
        assertTrue(response.getStatus() == Response.Status.OK.getStatusCode());
        ItemDto dto = (ItemDto) response.getEntity();
        assertNotNull(dto);
        assertTrue(dto.getId().equals(1));
    }

    @Test
    @SuppressWarnings("unchecked")
    public void testGetAll() {
        Response response = itemRemote.getAll();
        verify(itemService).getAll();
        assertTrue(response.getStatus() == Response.Status.OK.getStatusCode());
        List<ItemDto> dtos = (List<ItemDto>) response.getEntity();
        assertNotNull(dtos);
        assertTrue(dtos.size() > 0);
    }
}
