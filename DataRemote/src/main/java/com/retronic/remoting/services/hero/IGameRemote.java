package com.retronic.remoting.services.hero;

import com.retronic.remoting.dtos.hero.GameDto;
import com.retronic.remoting.services.IGenericRemote;

import javax.ws.rs.core.Response;

public interface IGameRemote extends IGenericRemote<GameDto, Integer> {

    Response getBySecret(String secret);
}
