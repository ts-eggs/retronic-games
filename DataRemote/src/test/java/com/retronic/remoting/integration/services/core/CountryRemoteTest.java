package com.retronic.remoting.integration.services.core;

import com.retronic.business.mocks.core.CountryServiceMock;
import com.retronic.business.services.IGenericService;
import com.retronic.persistence.entities.core.Country;
import com.retronic.remoting.converter.IDtoConverter;
import com.retronic.remoting.converter.core.CountryDtoConverter;
import com.retronic.remoting.dtos.core.CountryDto;
import com.retronic.remoting.services.IGenericRemote;
import com.retronic.remoting.services.core.impl.CountryRemote;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.util.ReflectionTestUtils;

import javax.ws.rs.core.Response;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.verify;

public class CountryRemoteTest {

    private IGenericService<Country, Integer> countryService;
    private IGenericRemote<CountryDto, Integer> countryRemote;

    @Before
    public void setUp() {
        countryRemote = new CountryRemote();
        countryService = CountryServiceMock.getMock();
        IDtoConverter<CountryDto, Country> countryDtoConverter = new CountryDtoConverter();

        ReflectionTestUtils.setField(countryRemote, "countryService", countryService);
        ReflectionTestUtils.setField(countryRemote, "countryDtoConverter", countryDtoConverter);
    }

    @Test
    public void testGet() {
        Response response = countryRemote.get(1);
        verify(countryService).get(1);
        assertTrue(response.getStatus() == Response.Status.OK.getStatusCode());
        CountryDto countryDto = (CountryDto) response.getEntity();
        assertNotNull(countryDto);
        assertTrue(countryDto.getId().equals(1));
    }

    @Test
    @SuppressWarnings("unchecked")
    public void testGetAll() {
        Response response = countryRemote.getAll();
        verify(countryService).getAll();
        assertTrue(response.getStatus() == Response.Status.OK.getStatusCode());
        List<CountryDto> countryDtos = (List<CountryDto>) response.getEntity();
        assertNotNull(countryDtos);
        assertTrue(countryDtos.size() > 1);
    }
}
