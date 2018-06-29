package com.retronic.business.mocks.hero;

import com.retronic.business.services.IGenericService;
import com.retronic.business.services.hero.impl.ItemService;
import com.retronic.persistence.entities.hero.Item;
import com.retronic.persistence.mocks.hero.ItemDaoMock;
import com.retronic.persistence.utils.MockUtils;

import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ItemServiceMock {

    public static IGenericService<Item, Integer> getMock() {
        IGenericService<Item, Integer> service = mock(ItemService.class);

        when(service.get(anyInt())).thenAnswer(MockUtils.buildAnswerGet(1, ItemDaoMock.items));
        when(service.getAll()).thenAnswer(MockUtils.buildAnswerGetAll(ItemDaoMock.items));

        return service;
    }
}