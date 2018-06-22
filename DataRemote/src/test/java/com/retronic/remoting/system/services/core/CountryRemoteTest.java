package com.retronic.remoting.system.services.core;

import com.retronic.remoting.dtos.core.CountryDto;
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
public class CountryRemoteTest {

    @Autowired
    private IGenericRemote<CountryDto, Integer> countryRemote;

    @Autowired
    private SecurityTestUtils securityTestUtils;

    @Test
    @SuppressWarnings("unchecked")
    public void testGetCountry() throws Exception {
        securityTestUtils.setUpAuthentication(1);
        Response response = countryRemote.get(1);
        CountryDto dto = (CountryDto) response.getEntity();
        assertTrue(dto.getId().equals(1));
    }

    @Test
    @SuppressWarnings("unchecked")
    public void testGetCountrys() throws Exception {
        securityTestUtils.setUpAuthentication(1);
        Response response = countryRemote.getAll();
        List<CountryDto> dtos = (List<CountryDto>) response.getEntity();
        assertTrue(dtos.size() > 1);
    }
}
