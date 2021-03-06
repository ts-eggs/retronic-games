package com.retronic.remoting.services.hero.impl;

import com.retronic.business.services.IGenericService;
import com.retronic.persistence.entities.hero.Character;
import com.retronic.persistence.utils.DirtyReadTransactional;
import com.retronic.remoting.converter.IDtoConverter;
import com.retronic.remoting.dtos.hero.CharacterDto;
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
@Path("/characters")
@Produces("application/json")
public class CharacterRemote implements IGenericRemote<CharacterDto, Integer> {

    @Autowired
    private IGenericService<Character, Integer> characterService;

    @Autowired
    private IDtoConverter<CharacterDto, Character> characterDtoConverter;

    @GET
    @Path("/{id}")
    @PreAuthorize("hasPermission(#id, T(com.retronic.security.enums.Permission).CHARACTER_READ)")
    @DirtyReadTransactional
    public Response get(@PathParam("id") Integer id) {
        Character entity = characterService.get(id);

        if (entity == null) {
            return ResponseUtil.notFound();
        }

        return ResponseUtil.ok(characterDtoConverter.convertToDto(entity));
    }

    @GET
    @PreAuthorize("hasAdminAccess()")
    @DirtyReadTransactional
    public Response getAll() {
        List<Character> entities = characterService.getAll();
        return ResponseUtil.ok(ConverterUtils.convertToDTOs(characterDtoConverter, entities));
    }

    @POST
    @PreAuthorize("freeForAll()")
    @Transactional
    public Response create(CharacterDto dto) {
        Integer id = characterService.create(characterDtoConverter.convertToEntity(dto));
        return ResponseUtil.created(id);
    }

    @PUT
    @Path("/{id}")
    @PreAuthorize("hasPermission(#id, T(com.retronic.security.enums.Permission).CHARACTER_WRITE)")
    @Transactional
    public Response update(@PathParam("id") Integer id, CharacterDto dto) {
        dto.setId(id);
        characterService.update(characterDtoConverter.convertToEntity(dto));
        return ResponseUtil.updated(id);
    }

    @DELETE
    @Path("/{id}")
    @PreAuthorize("hasPermission(#id, T(com.retronic.security.enums.Permission).CHARACTER_WRITE)")
    @Transactional
    public Response delete(@PathParam("id") Integer id) {
        Character entity = characterService.get(id);

        if (entity == null) {
            return ResponseUtil.notFound();
        }

        characterService.delete(entity);
        return ResponseUtil.deleted(id);
    }
}
