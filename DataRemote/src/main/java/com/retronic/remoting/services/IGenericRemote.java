package com.retronic.remoting.services;

import javax.ws.rs.core.Response;

public interface IGenericRemote<T, I> {

    Response get(I id);

    Response getAll();

    Response create(T dto);

    Response update(I id, T dto);

    Response delete(I id);
}
