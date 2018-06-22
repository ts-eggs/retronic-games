package com.retronic.business.services.core;

import java.io.Serializable;
import java.util.List;

public interface IGenericService<T, I extends Serializable> {

    void init();

    T get(I id);

    List<T> getAll();

    I create(T entity);

    void update(T entity);

    void delete(T entity);
}
