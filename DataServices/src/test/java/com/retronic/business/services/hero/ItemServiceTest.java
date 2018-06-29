package com.retronic.business.services.hero;

import com.retronic.business.services.IGenericService;
import com.retronic.business.services.hero.impl.ItemService;
import com.retronic.persistence.daos.core.IGenericDao;
import com.retronic.persistence.entities.hero.Item;
import com.retronic.persistence.mocks.hero.ItemDaoMock;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.List;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.verify;

public class ItemServiceTest {

    private IGenericService<Item, Integer> itemService;
    private IGenericDao<Item, Integer> itemDao;

    @Before
    public void setUp() {
        itemService = new ItemService();
        itemDao = ItemDaoMock.getMock();
        ReflectionTestUtils.setField(itemService, "genericDao", itemDao);
    }

    @Test
    public void testGet() {
        Item item = itemService.get(1);
        verify(itemDao).read(1);
        assertTrue(item.getId().equals(1));
    }

    @Test
    public void testGetAll() {
        List<Item> items = itemService.getAll();
        verify(itemDao).list();
        assertTrue(items.size() > 0);
    }
}