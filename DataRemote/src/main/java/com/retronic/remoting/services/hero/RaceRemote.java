package com.retronic.remoting.services.hero;

import com.retronic.business.services.core.IGenericService;
import com.retronic.persistence.entities.hero.Race;
import com.retronic.persistence.utils.DirtyReadTransactional;
import com.retronic.remoting.converter.IDtoConverter;
import com.retronic.remoting.dtos.hero.RaceDto;
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
@Path("/races")
@Produces("application/json")
public class RaceRemote implements IGenericRemote<RaceDto, Integer> {

    @Autowired
    private IGenericService<Race, Integer> raceService;

    @Autowired
    private IDtoConverter<RaceDto, Race> raceDtoConverter;

    @GET
    @Path("/{id}")
    @PreAuthorize("hasAdminAccess()")
    @DirtyReadTransactional
    public Response get(@PathParam("id") Integer id) {
        Race entity = raceService.get(id);

        if (entity == null) {
            return ResponseUtil.notFound();
        }

        return ResponseUtil.ok(raceDtoConverter.convertToDto(entity));
    }

    @GET
    @Path("/")
    @PreAuthorize("hasAdminAccess()")
    @DirtyReadTransactional
    public Response getAll() {
        List<Race> entities = raceService.getAll();
        return ResponseUtil.ok(ConverterUtils.convertToDTOs(raceDtoConverter, entities));
    }

    @POST
    @Path("/")
    @PreAuthorize("hasSystemAccess()")
    @Transactional
    public Response create(RaceDto dto) {
        Integer id = raceService.create(raceDtoConverter.convertToEntity(dto));
        return ResponseUtil.created(id);
    }

    @PUT
    @Path("/{id}")
    @PreAuthorize("hasSystemAccess()")
    @Transactional
    public Response update(@PathParam("id") Integer id, RaceDto dto) {
        dto.setId(id);
        raceService.update(raceDtoConverter.convertToEntity(dto));
        return ResponseUtil.updated(id);
    }

    @DELETE
    @Path("/{id}")
    @PreAuthorize("hasSystemAccess()")
    @Transactional
    public Response delete(@PathParam("id") Integer id) {
        Race entity = raceService.get(id);

        if (entity == null) {
            return ResponseUtil.notFound();
        }

        raceService.delete(entity);
        return ResponseUtil.deleted(id);
    }
}
