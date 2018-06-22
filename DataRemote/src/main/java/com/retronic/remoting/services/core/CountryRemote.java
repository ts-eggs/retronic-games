package com.retronic.remoting.services.core;

import com.retronic.business.services.core.impl.CountryService;
import com.retronic.persistence.entities.core.Country;
import com.retronic.persistence.utils.DirtyReadTransactional;
import com.retronic.remoting.converter.IDtoConverter;
import com.retronic.remoting.dtos.core.CountryDto;
import com.retronic.remoting.services.IGenericRemote;
import com.retronic.remoting.utils.ConverterUtils;
import com.retronic.remoting.utils.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.List;

@Service
@Path("/countries")
@Produces("application/json")
public class CountryRemote implements IGenericRemote<CountryDto, Integer> {

    @Autowired
    private CountryService countryService;

    @Autowired
    private IDtoConverter<CountryDto, Country> countryDtoConverter;

    @GET
    @Path("/{id}")
    @PreAuthorize("hasAdminAccess()")
    @DirtyReadTransactional
    public Response get(@PathParam("id") Integer id) {
        Country entity = countryService.get(id);

        if(entity == null) {
            return ResponseUtil.notFound();
        }

        return ResponseUtil.ok(countryDtoConverter.convertToDto(entity));
    }

    @GET
    @Path("/")
    @PreAuthorize("hasAdminAccess()")
    @DirtyReadTransactional
    public Response getAll() {
        List<Country> entities = countryService.getAll();
        return ResponseUtil.ok(ConverterUtils.convertToDTOs(countryDtoConverter, entities));
    }

    @POST
    @Path("/")
    @PreAuthorize("hasSystemAccess()")
    @Transactional
    public Response create(CountryDto dto) {
        Integer id = countryService.create(countryDtoConverter.convertToEntity(dto));
        return ResponseUtil.created(id);
    }

    @PUT
    @Path("/{id}")
    @PreAuthorize("hasSystemAccess()")
    @Transactional
    public Response update(@PathParam("id") Integer id, CountryDto dto) {
        dto.setId(id);
        countryService.update(countryDtoConverter.convertToEntity(dto));
        return ResponseUtil.updated(id);
    }

    @DELETE
    @Path("/{id}")
    @PreAuthorize("hasSystemAccess()")
    @Transactional
    public Response delete(@PathParam("id") Integer id) {
        Country entity = countryService.get(id);

        if (entity == null) {
            return ResponseUtil.notFound();
        }

        countryService.delete(entity);
        return ResponseUtil.deleted(id);
    }
}
