package com.retronic.remoting.services.hero;

import com.retronic.business.services.core.IGenericService;
import com.retronic.persistence.entities.hero.Game;
import com.retronic.persistence.utils.DirtyReadTransactional;
import com.retronic.remoting.converter.IDtoConverter;
import com.retronic.remoting.dtos.hero.GameDto;
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
@Path("/games")
@Produces("application/json")
public class GameRemote implements IGenericRemote<GameDto, Integer> {

    @Autowired
    private IGenericService<Game, Integer> gameService;

    @Autowired
    private IDtoConverter<GameDto, Game> gameDtoConverter;

    @GET
    @Path("/{id}")
    @PreAuthorize("hasPermission(#id, T(com.retronic.security.enums.Permission).GAME_READ)")
    @DirtyReadTransactional
    public Response get(@PathParam("id") Integer id) {
        Game entity = gameService.get(id);

        if (entity == null) {
            return ResponseUtil.notFound();
        }

        return ResponseUtil.ok(gameDtoConverter.convertToDto(entity));
    }

    @GET
    @Path("/")
    @PreAuthorize("hasAdminAccess()")
    @DirtyReadTransactional
    public Response getAll() {
        List<Game> entities = gameService.getAll();
        return ResponseUtil.ok(ConverterUtils.convertToDTOs(gameDtoConverter, entities));
    }

    @POST
    @Path("/")
    @PreAuthorize("freeForAll()")
    @Transactional
    public Response create(GameDto dto) {
        Integer id = gameService.create(gameDtoConverter.convertToEntity(dto));
        return ResponseUtil.created(id);
    }

    @PUT
    @Path("/{id}")
    @PreAuthorize("hasPermission(#id, T(com.retronic.security.enums.Permission).GAME_WRITE)")
    @Transactional
    public Response update(@PathParam("id") Integer id, GameDto dto) {
        dto.setId(id);
        gameService.update(gameDtoConverter.convertToEntity(dto));
        return ResponseUtil.updated(id);
    }

    @DELETE
    @Path("/{id}")
    @PreAuthorize("hasPermission(#id, T(com.retronic.security.enums.Permission).GAME_WRITE)")
    @Transactional
    public Response delete(@PathParam("id") Integer id) {
        Game entity = gameService.get(id);

        if (entity == null) {
            return ResponseUtil.notFound();
        }

        gameService.delete(entity);
        return ResponseUtil.deleted(id);
    }
}
