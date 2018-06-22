package com.retronic.remoting.services.hero;

import com.retronic.business.services.core.IGenericService;
import com.retronic.persistence.entities.hero.Item;
import com.retronic.persistence.utils.DirtyReadTransactional;
import com.retronic.remoting.converter.IDtoConverter;
import com.retronic.remoting.dtos.hero.ItemDto;
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
@Path("/items")
@Produces("application/json")
public class ItemRemote implements IGenericRemote<ItemDto, Integer> {

    @Autowired
    private IGenericService<Item, Integer> itemService;

    @Autowired
    private IDtoConverter<ItemDto, Item> itemDtoConverter;

    @GET
    @Path("/{id}")
    @PreAuthorize("hasAdminAccess()")
    @DirtyReadTransactional
    public Response get(@PathParam("id") Integer id) {
        Item entity = itemService.get(id);

        if (entity == null) {
            return ResponseUtil.notFound();
        }

        return ResponseUtil.ok(itemDtoConverter.convertToDto(entity));
    }

    @GET
    @Path("/")
    @PreAuthorize("hasAdminAccess()")
    @DirtyReadTransactional
    public Response getAll() {
        List<Item> entities = itemService.getAll();
        return ResponseUtil.ok(ConverterUtils.convertToDTOs(itemDtoConverter, entities));
    }

    @POST
    @Path("/")
    @PreAuthorize("hasSystemAccess()")
    @Transactional
    public Response create(ItemDto dto) {
        Integer id = itemService.create(itemDtoConverter.convertToEntity(dto));
        return ResponseUtil.created(id);
    }

    @PUT
    @Path("/{id}")
    @PreAuthorize("hasSystemAccess()")
    @Transactional
    public Response update(@PathParam("id") Integer id, ItemDto dto) {
        dto.setId(id);
        itemService.update(itemDtoConverter.convertToEntity(dto));
        return ResponseUtil.updated(id);
    }

    @DELETE
    @Path("/{id}")
    @PreAuthorize("hasSystemAccess()")
    @Transactional
    public Response delete(@PathParam("id") Integer id) {
        Item entity = itemService.get(id);

        if (entity == null) {
            return ResponseUtil.notFound();
        }

        itemService.delete(entity);
        return ResponseUtil.deleted(id);
    }
}
