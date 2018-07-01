package com.retronic.remoting.services.core;

import com.retronic.remoting.dtos.core.UserDto;
import com.retronic.remoting.services.IGenericRemote;

import javax.ws.rs.core.Response;

public interface IUserRemote extends IGenericRemote<UserDto, Integer> {

    Response login();

    Response getGames(Integer id);
}
