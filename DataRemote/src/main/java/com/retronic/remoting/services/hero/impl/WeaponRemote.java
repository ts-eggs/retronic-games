package com.retronic.remoting.services.hero.impl;

import com.retronic.business.services.IGenericService;
import com.retronic.persistence.entities.hero.Weapon;
import com.retronic.persistence.utils.DirtyReadTransactional;
import com.retronic.remoting.converter.IDtoConverter;
import com.retronic.remoting.dtos.hero.WeaponDto;
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
@Path("/weapons")
@Produces("application/json")
public class WeaponRemote implements IGenericRemote<WeaponDto, Integer> {

    @Autowired
    private IGenericService<Weapon, Integer> weaponService;

    @Autowired
    private IDtoConverter<WeaponDto, Weapon> weaponDtoConverter;

    @GET
    @Path("/{id}")
    @PreAuthorize("hasAdminAccess()")
    @DirtyReadTransactional
    public Response get(@PathParam("id") Integer id) {
        Weapon entity = weaponService.get(id);

        if (entity == null) {
            return ResponseUtil.notFound();
        }

        return ResponseUtil.ok(weaponDtoConverter.convertToDto(entity));
    }

    @GET
    @PreAuthorize("hasAdminAccess()")
    @DirtyReadTransactional
    public Response getAll() {
        List<Weapon> entities = weaponService.getAll();
        return ResponseUtil.ok(ConverterUtils.convertToDTOs(weaponDtoConverter, entities));
    }

    @POST
    @PreAuthorize("hasSystemAccess()")
    @Transactional
    public Response create(WeaponDto dto) {
        Integer id = weaponService.create(weaponDtoConverter.convertToEntity(dto));
        return ResponseUtil.created(id);
    }

    @PUT
    @Path("/{id}")
    @PreAuthorize("hasSystemAccess()")
    @Transactional
    public Response update(@PathParam("id") Integer id, WeaponDto dto) {
        dto.setId(id);
        weaponService.update(weaponDtoConverter.convertToEntity(dto));
        return ResponseUtil.updated(id);
    }

    @DELETE
    @Path("/{id}")
    @PreAuthorize("hasSystemAccess()")
    @Transactional
    public Response delete(@PathParam("id") Integer id) {
        Weapon entity = weaponService.get(id);

        if (entity == null) {
            return ResponseUtil.notFound();
        }

        weaponService.delete(entity);
        return ResponseUtil.deleted(id);
    }
}
