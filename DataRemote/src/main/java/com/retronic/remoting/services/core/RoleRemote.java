package com.retronic.remoting.services.core;

import com.retronic.business.services.core.impl.RoleService;
import com.retronic.persistence.entities.core.Role;
import com.retronic.persistence.utils.DirtyReadTransactional;
import com.retronic.remoting.converter.IDtoConverter;
import com.retronic.remoting.dtos.core.RoleDto;
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
@Path("/roles")
@Produces("application/json")
public class RoleRemote implements IGenericRemote<RoleDto, Integer> {

    @Autowired
    private RoleService roleService;

    @Autowired
    private IDtoConverter<RoleDto, Role> roleDtoConverter;

    @GET
    @Path("/{id}")
    @PreAuthorize("hasAdminAccess()")
    @DirtyReadTransactional
    public Response get(@PathParam("id") Integer id) {
        Role entity = roleService.get(id);

        if(entity == null) {
            return ResponseUtil.notFound();
        }

        return ResponseUtil.ok(roleDtoConverter.convertToDto(entity));
    }

    @GET
    @Path("/")
    @PreAuthorize("hasAdminAccess()")
    @DirtyReadTransactional
    public Response getAll() {
        List<Role> entities = roleService.getAll();
        return ResponseUtil.ok(ConverterUtils.convertToDTOs(roleDtoConverter, entities));
    }

    @POST
    @Path("/")
    @PreAuthorize("hasSystemAccess()")
    @Transactional
    public Response create(RoleDto dto) {
        Integer id = roleService.create(roleDtoConverter.convertToEntity(dto));
        return ResponseUtil.created(id);
    }

    @PUT
    @Path("/{id}")
    @PreAuthorize("hasSystemAccess()")
    @Transactional
    public Response update(@PathParam("id") Integer id, RoleDto dto) {
        dto.setId(id);
        roleService.update(roleDtoConverter.convertToEntity(dto));
        return ResponseUtil.updated(id);
    }

    @DELETE
    @Path("/{id}")
    @PreAuthorize("hasSystemAccess()")
    @Transactional
    public Response delete(@PathParam("id") Integer id) {
        Role entity = roleService.get(id);

        if (entity == null) {
            return ResponseUtil.notFound();
        }

        roleService.delete(entity);
        return ResponseUtil.deleted(id);
    }
}
