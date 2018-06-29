package com.retronic.remoting.services.hero.impl;

import com.retronic.business.services.IGenericService;
import com.retronic.persistence.entities.hero.Class;
import com.retronic.persistence.utils.DirtyReadTransactional;
import com.retronic.remoting.converter.IDtoConverter;
import com.retronic.remoting.dtos.hero.ClassDto;
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
@Path("/classes")
@Produces("application/json")
public class ClassRemote implements IGenericRemote<ClassDto, Integer> {

    @Autowired
    private IGenericService<Class, Integer> classService;

    @Autowired
    private IDtoConverter<ClassDto, Class> classDtoConverter;

    @GET
    @Path("/{id}")
    @PreAuthorize("hasAdminAccess()")
    @DirtyReadTransactional
    public Response get(@PathParam("id") Integer id) {
        Class entity = classService.get(id);

        if (entity == null) {
            return ResponseUtil.notFound();
        }

        return ResponseUtil.ok(classDtoConverter.convertToDto(entity));
    }

    @GET
    @PreAuthorize("hasAdminAccess()")
    @DirtyReadTransactional
    public Response getAll() {
        List<Class> entities = classService.getAll();
        return ResponseUtil.ok(ConverterUtils.convertToDTOs(classDtoConverter, entities));
    }

    @POST
    @PreAuthorize("hasSystemAccess()")
    @Transactional
    public Response create(ClassDto dto) {
        Integer id = classService.create(classDtoConverter.convertToEntity(dto));
        return ResponseUtil.created(id);
    }

    @PUT
    @Path("/{id}")
    @PreAuthorize("hasSystemAccess()")
    @Transactional
    public Response update(@PathParam("id") Integer id, ClassDto dto) {
        dto.setId(id);
        classService.update(classDtoConverter.convertToEntity(dto));
        return ResponseUtil.updated(id);
    }

    @DELETE
    @Path("/{id}")
    @PreAuthorize("hasSystemAccess()")
    @Transactional
    public Response delete(@PathParam("id") Integer id) {
        Class entity = classService.get(id);

        if (entity == null) {
            return ResponseUtil.notFound();
        }

        classService.delete(entity);
        return ResponseUtil.deleted(id);
    }
}
