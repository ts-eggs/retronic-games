package com.retronic.remoting.services.core.impl;

import com.retronic.business.services.core.IUserService;
import com.retronic.persistence.entities.core.User;
import com.retronic.persistence.entities.hero.Game;
import com.retronic.persistence.utils.DirtyReadTransactional;
import com.retronic.remoting.converter.IDtoConverter;
import com.retronic.remoting.dtos.core.UserDto;
import com.retronic.remoting.dtos.hero.GameDto;
import com.retronic.remoting.services.core.IUserRemote;
import com.retronic.remoting.utils.ConverterUtils;
import com.retronic.remoting.utils.ResponseUtil;
import com.retronic.security.services.impl.SecurityContextUserLocator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.Calendar;
import java.util.List;

@Service
@Path("/users")
@Produces("application/json")
public class UserRemote implements IUserRemote {

    @Autowired
    private IUserService userService;

    @Autowired
    private IDtoConverter<UserDto, User> userDtoConverter;

    @Autowired
    private IDtoConverter<GameDto, Game> gameDtoConverter;

    @Autowired
    private SecurityContextUserLocator securityContextUserLocator;

    @GET
    @Path("/{id}")
    @PreAuthorize("hasPermission(#id, T(com.retronic.security.enums.Permission).USER_READ)")
    @DirtyReadTransactional
    public Response get(@PathParam("id") Integer id) {
        User entity = userService.get(id);

        if (entity == null) {
            return ResponseUtil.notFound();
        }

        return ResponseUtil.ok(userDtoConverter.convertToDto(entity));
    }

    @GET
    @PreAuthorize("hasAdminAccess()")
    @DirtyReadTransactional
    public Response getAll() {
        List<User> entities = userService.getAll();
        return ResponseUtil.ok(ConverterUtils.convertToDTOs(userDtoConverter, entities));
    }

    @POST
    @PreAuthorize("hasAdminAccess()")
    @Transactional
    public Response create(UserDto dto) {
        Integer id = userService.create(userDtoConverter.convertToEntity(dto));
        return ResponseUtil.created(id);
    }

    @PUT
    @Path("/{id}")
    @PreAuthorize("hasPermission(#id, T(com.retronic.security.enums.Permission).USER_WRITE)")
    @Transactional
    public Response update(@PathParam("id") Integer id, UserDto dto) {
        dto.setId(id);
        userService.update(userDtoConverter.convertToEntity(dto));
        return ResponseUtil.updated(id);
    }

    @DELETE
    @Path("/{id}")
    @PreAuthorize("hasPermission(#id, T(com.retronic.security.enums.Permission).USER_WRITE)")
    @Transactional
    public Response delete(@PathParam("id") Integer id) {
        User entity = userService.get(id);

        if (entity == null) {
            return ResponseUtil.notFound();
        }

        userService.delete(entity);
        return ResponseUtil.deleted(id);
    }

    @GET
    @Path("/login")
    @PreAuthorize("isUser()")
    public Response login() {
        User user = securityContextUserLocator.getSecurityContextUser();

        if (user == null) {
            return ResponseUtil.notAuthorized();
        }

        user.setLastlogin(Calendar.getInstance());
        userService.update(user);
        return ResponseUtil.ok(userDtoConverter.convertToDto(user));
    }

    @GET
    @Path("/{id}/games")
    @PreAuthorize("isUser() and hasPermission(#id, T(com.retronic.security.enums.Permission).IS_USER)")
    @DirtyReadTransactional
    public Response getGames(@PathParam("id") Integer id) {
        User user = securityContextUserLocator.getSecurityContextUser();

        if (!id.equals(user.getId())) {
            return ResponseUtil.notAuthorized();
        }

        List<Game> games = user.getGames();

        if (games == null) {
            return ResponseUtil.notFound();
        }

        return ResponseUtil.ok(ConverterUtils.convertToDTOs(gameDtoConverter, games));
    }
}
