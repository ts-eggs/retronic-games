package com.retronic.remoting.services.hero;

import com.retronic.business.services.core.IGenericService;
import com.retronic.persistence.entities.hero.Skill;
import com.retronic.persistence.utils.DirtyReadTransactional;
import com.retronic.remoting.converter.IDtoConverter;
import com.retronic.remoting.dtos.hero.SkillDto;
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
@Path("/skills")
@Produces("application/json")
public class SkillRemote implements IGenericRemote<SkillDto, Integer> {

    @Autowired
    private IGenericService<Skill, Integer> skillService;

    @Autowired
    private IDtoConverter<SkillDto, Skill> skillDtoConverter;

    @GET
    @Path("/{id}")
    @PreAuthorize("hasAdminAccess()")
    @DirtyReadTransactional
    public Response get(@PathParam("id") Integer id) {
        Skill entity = skillService.get(id);

        if(entity == null) {
            return ResponseUtil.notFound();
        }

        return ResponseUtil.ok(skillDtoConverter.convertToDto(entity));
    }

    @GET
    @Path("/")
    @PreAuthorize("hasAdminAccess()")
    @DirtyReadTransactional
    public Response getAll() {
        List<Skill> entities = skillService.getAll();
        return ResponseUtil.ok(ConverterUtils.convertToDTOs(skillDtoConverter, entities));
    }

    @POST
    @Path("/")
    @PreAuthorize("hasSystemAccess()")
    @Transactional
    public Response create(SkillDto dto) {
        Integer id = skillService.create(skillDtoConverter.convertToEntity(dto));
        return ResponseUtil.created(id);
    }

    @PUT
    @Path("/{id}")
    @PreAuthorize("hasSystemAccess()")
    @Transactional
    public Response update(@PathParam("id") Integer id, SkillDto dto) {
        dto.setId(id);
        skillService.update(skillDtoConverter.convertToEntity(dto));
        return ResponseUtil.updated(id);
    }

    @DELETE
    @Path("/{id}")
    @PreAuthorize("hasSystemAccess()")
    @Transactional
    public Response delete(@PathParam("id") Integer id) {
        Skill entity = skillService.get(id);

        if (entity == null) {
            return ResponseUtil.notFound();
        }

        skillService.delete(entity);
        return ResponseUtil.deleted(id);
    }
}
