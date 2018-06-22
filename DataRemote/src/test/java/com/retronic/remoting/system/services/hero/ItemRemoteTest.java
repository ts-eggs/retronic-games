package com.retronic.remoting.system.services.hero;

import com.retronic.remoting.dtos.hero.ItemDto;
import com.retronic.remoting.services.IGenericRemote;
import com.retronic.remoting.utils.SecurityTestUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.ws.rs.core.Response;
import java.util.List;

import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:spring-rest.xml"})
public class ItemRemoteTest {

    @Autowired
    private IGenericRemote<ItemDto, Integer> itemRemote;

    @Autowired
    private SecurityTestUtils securityTestUtils;

    @Test
    @SuppressWarnings("unchecked")
    public void testGetItem() throws Exception {
        securityTestUtils.setUpAuthentication(1);
        Response response = itemRemote.get(1);
        ItemDto dto = (ItemDto) response.getEntity();
        assertTrue(dto.getId().equals(1));
    }

    @Test
    @SuppressWarnings("unchecked")
    public void testGetItems() throws Exception {
        securityTestUtils.setUpAuthentication(1);
        Response response = itemRemote.getAll();
        List<ItemDto> dtos = (List<ItemDto>) response.getEntity();
        assertTrue(dtos.size() > 0);
    }
}
